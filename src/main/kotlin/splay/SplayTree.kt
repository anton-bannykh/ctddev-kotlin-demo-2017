package splay

data class SplayNode<K : Comparable<K>, V>(
        val key: K,
        val value: V,
        var parent: SplayNode<K, V>?,
        var left: SplayNode<K, V>?,
        var right: SplayNode<K, V>?
)

fun <K : Comparable<K>, V> keepParent(v: SplayNode<K, V>?) {
    v?.left?.parent = v
    v?.right?.parent = v
}

fun <K : Comparable<K>, V> rotate(parent: SplayNode<K, V>, child:SplayNode<K, V>) {
    val grandParent = parent.parent

    if (grandParent?.left == parent) {
        grandParent.left = child
    } else {
        grandParent?.right = child
    }

    if (parent.left == child) {
        child.right = parent.also {
            parent.left = child.right
        }
    } else {
        child.left = parent.also {
            parent.right = child.left
        }
    }

    keepParent(child)
    keepParent(parent)
}

tailrec fun <K : Comparable<K>, V> splay(v: SplayNode<K, V>): SplayNode<K, V> {
    val parent = v.parent
    val grandParent = parent?.parent

    if (parent == null) {
        return v
    }

    // zig
    if (grandParent == null) {
        rotate(parent, v)
        return v
    }

    // zig-zig
    if ((grandParent.left == parent) == (parent.left == v)) {
        rotate(grandParent, parent)
        rotate(parent, v)
    } else {
        rotate(parent, v)
        rotate(grandParent, v)
    }

    return splay(v)
}

fun <K : Comparable<K>, V> find(v: SplayNode<K, V>, key: K): SplayNode<K, V> {
    fun <K : Comparable<K>, V> findImpl(v: SplayNode<K, V>, key: K): SplayNode<K, V> {
        if (v.key < key && v.left != null) {
            find(v.left!!, key)
        }

        if (v.key > key && v.right != null) {
            find(v.right!!, key)
        }

        return v
    }

    return splay(findImpl(v, key))
}

fun <K : Comparable<K>, V> merge(left: SplayNode<K, V>, right: SplayNode<K, V>): SplayNode<K, V> {
    val root = find(right, left.key)

    root.left = left

    keepParent(root)

    return root
}

fun <K : Comparable<K>, V> split(v: SplayNode<K, V>, key: K): Pair<SplayNode<K, V>?, SplayNode<K, V>?> {
    TODO()
}

