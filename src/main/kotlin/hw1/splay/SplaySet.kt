package hw1.splay

fun <K : Comparable<K>> splaySetOf(vararg ks: K): SplaySet<K> {
    val tree = SplaySet<K>()

    tree += ks

    return tree
}

class SplaySet<K : Comparable<K>>() {
    private var root: SplayNode<K>? = null

    var size: Int = 0
        private set

    private constructor(root: SplayNode<K>?) : this() {
        this.root = root
    }

    fun add(key: K): Boolean {
        if (root == null) {
            root = SplayNode(key)
            size++
            return true
        }

        val placeToInsert = root!!.find(key)

        if (placeToInsert.key == key) {
            root = placeToInsert
            return false
        }

        val (left, right) = placeToInsert.split(key)

        val v = SplayNode(key, left = left, right = right)

        v.keepParent()

        root = v

        size++
        return true

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
    fun remove(key: K): Boolean {
        val v = root?.find(key)

        if (v == null || v.key != key) {
            return false
        }

        size--

        v.left?.parent = null
        v.right?.parent = null

        root = when {
            v.left == null -> v.right

            v.right == null -> v.left

            else -> v.left!!.merge(v.right!!)
        }

        return true
    }

    operator fun contains(key: K): Boolean {
        root = root?.find(key)

        return root?.key == key
    }
}
