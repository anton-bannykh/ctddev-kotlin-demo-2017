package splay

data class SplayNode<K : Comparable<K>>(
        val key: K,
        var parent: SplayNode<K>? = null,
        var left: SplayNode<K>? = null,
        var right: SplayNode<K>? = null
) {
    private fun keepParent() {
        left?.parent = this
        right?.parent = this
    }

    private fun moveToRoot() {
        val parent = this.parent ?: return

        val grandParent = parent.parent

        if (grandParent?.left == parent) {
            grandParent.left = this
        } else {
            grandParent?.right = this
        }

        if (parent.left == this) {
            this.right = parent.also {
                parent.left = this.right
            }
        } else {
            this.left = parent.also {
                parent.right = this.left
            }
        }

        this.keepParent()
        parent.keepParent()
    }

    private tailrec fun splay(): SplayNode<K> {
        val parent = this.parent
        val grandParent = parent?.parent

        if (parent == null) {
            return this
        }

        // zig
        if (grandParent == null) {
            this.moveToRoot()
            return this
        }

        // zig-zig
        if ((grandParent.left == parent) == (parent.left == this)) {
            parent.moveToRoot()
            this.moveToRoot()
        } else {
            this.moveToRoot()
            this.moveToRoot()
        }

        return splay()
    }

    fun find(v: SplayNode<K>, key: K): SplayNode<K> {
        fun findImpl(v: SplayNode<K>, key: K): SplayNode<K> {
            if (v.key < key && v.left != null) {
                find(v.left!!, key)
            }

            if (v.key > key && v.right != null) {
                find(v.right!!, key)
            }

            return v
        }

        return findImpl(v, key).splay()
    }

    fun merge(left: SplayNode<K>, right: SplayNode<K>)
            : SplayNode<K> {
        val root = find(right, left.key)

        root.left = left

        root.keepParent()

        return root
    }

    fun split(v: SplayNode<K>, key: K)
            : Pair<SplayNode<K>?, SplayNode<K>?> {
        val root = find(v, key)

        return if (root.key <= key) {
            val right = root.right
            right?.parent = null
            root.right = null

            root to right
        } else {
            val left = root.left
            left?.parent = null
            root.left = null

            left to root
        }
    }
}
