package splay

fun <K : Comparable<K>> splaySetOf(vararg ks: K): SplaySet<K> {
    val tree = SplaySet<K>()

    tree += ks

    return tree
}

class SplaySet<in K : Comparable<K>>() {
    private var root: SplayNode<K>? = null
    var size: Int = 0
        private set

    private constructor(root: SplayNode<K>?) : this() {
        this.root = root
    }

    fun add(key: K) {
        size++

        if (root == null) {
            root = SplayNode(key)
            return
        }

        val (left, right) = root!!.split(key)

        val v = SplayNode(key, left = left, right = right)

        v.keepParent()

        root = v

    }

    operator fun plusAssign(keys: Iterable<K>) {
        for (key in keys) {
            add(key)
        }
    }

    operator fun plusAssign(keys: Array<out K>) {
        for (key in keys) {
            add(key)
        }
    }

    fun remove(key: K) {
        val v = root?.find(key) ?: throw IllegalArgumentException("Can't remove item from empty tree")

        size--

        v.left?.parent = null
        v.right?.parent = null

        root = when {
            v.left == null -> v.right

            v.right == null -> v.left

            else -> v.left!!.merge(v.right!!)
        }
    }

    operator fun contains(key: K): Boolean {
        root = root?.find(key)

        return root?.key == key
    }


    private data class SplayNode<K : Comparable<K>>(
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
            if ((grandParent.left == parent) == (parent.left == this)) {
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

                if (v.key < key && v.right != null) {
                    return findImpl(v.right!!, key)
                }

                return v
            }

            return findImpl(this, key).splay()
        }
    }
}
