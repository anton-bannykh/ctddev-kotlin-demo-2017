package my.lib.splay

internal data class SplayNode<K : Comparable<K>>(
        val key: K,
        var parent: SplayNode<K>? = null,
        var left: SplayNode<K>? = null,
        var right: SplayNode<K>? = null
) {
    fun keepParent() {
        left?.parent = this
        right?.parent = this
    }

    fun moveToRoot() {
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

        this.parent = grandParent
    }

    tailrec fun splay(): SplayNode<K> {
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
        if (grandParent.left == parent == (parent.left == this)) {
            parent.moveToRoot()
            this.moveToRoot()
        } else {
            this.moveToRoot()
            this.moveToRoot()
        }

        return splay()
    }

    fun merge(right: SplayNode<K>): SplayNode<K> {
        val v = right.find(key)

        v.left = this

        v.keepParent()

        return v
    }

    fun split(key: K): Pair<SplayNode<K>?, SplayNode<K>?> {
        val v = find(key)

        return if (v.key <= key) {
            val right = v.right
            right?.parent = null
            v.right = null

            v to right
        } else {
            val left = v.left
            left?.parent = null
            v.left = null

            left to v
        }
    }

    fun find(key: K): SplayNode<K> {
        tailrec fun findImpl(v: SplayNode<K>, key: K): SplayNode<K> {
            if (v.key > key && v.left != null) {
                return findImpl(v.left!!, key)
            }

            return if (v.key < key && v.right != null) {
                findImpl(v.right!!, key)
            } else v

        }

        return findImpl(this, key).splay()
    }
}