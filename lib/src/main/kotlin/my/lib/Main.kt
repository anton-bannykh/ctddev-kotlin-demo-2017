package my.lib

class SplayTree<T : Comparable<T>> {

    private var root : Node? = null

    private inner class Node constructor(_key: T) {
        var key : T = _key
        var left : Node? = null
        var right : Node? = null
    }

    private fun splay(cnd : Node?, key : T) : Node? {
        var nd : Node? = cnd
        if (nd == null) return null
        if (key < nd.key) {
            if (nd.left == null) return nd
            if (key < nd.left!!.key) {
                nd.left!!.left = splay(nd.left!!.left, key)
                nd = rotateRight(nd)
            } else if (key > nd.left!!.key) {
                if (nd.left!!.right != null) {
                    nd.left!!.right = splay(nd.left!!.right, key)
                    nd.left = rotateLeft(nd.left!!)
                }
            }
            return if (nd!!.left == null) nd else rotateRight(nd)
        } else if (key > nd.key) {
            if (nd.right == null) return nd
            if (key < nd.right!!.key) {
                if (nd.right!!.left != null) {
                    nd.right!!.left = splay(nd.right!!.left, key)
                    nd.right = rotateRight(nd.right!!)
                }
            } else if (key > nd.right!!.key) {
                nd.right!!.right = splay(nd.right!!.right, key)
                nd = rotateLeft(nd)
            }
            return if (nd!!.right == null) return nd else rotateLeft(nd)
        }
        return nd
    }

    private fun rotateLeft(cur : Node) : Node? {
        val tmp : Node = cur.right!!
        cur.right = tmp.left
        tmp.left = cur
        return tmp
    }

    private fun rotateRight(cur : Node) : Node? {
        val tmp : Node = cur.left!!
        cur.left = tmp.right
        tmp.right = cur
        return tmp
    }

    private fun prev(cur : Node?, k : T) : Node? {
        if (cur == null) return null
        return when (k.compareTo(cur.key)) {
            -1 -> prev(cur.left, k)
            0 -> cur
            1 -> {
                val tmp: Node? = prev(cur.right, k)
                tmp ?: cur
            }
            else -> null
        }
    }

    private fun next(cur : Node?, k : T) : Node? {
        if (cur == null) return null
        return when (k.compareTo(cur.key)) {
            1 -> next(cur.right, k)
            0 -> cur
            -1 -> {
                val tmp : Node? = next(cur.left, k)
                tmp ?: cur
            }
            else -> null
        }
    }

    fun put(k : T) {
        if (root == null) {
            root = Node(k)
            return
        }
        root = splay(root, k)
        if (k < root!!.key) {
            val tmp = Node(k)
            tmp.left = root!!.left
            tmp.right = root
            root!!.left = null
            root = tmp
        } else if (k > root!!.key) {
            val tmp = Node(k)
            tmp.right = root!!.right
            tmp.left = root
            root!!.right = null
            root = tmp
        }
    }

    fun contains(k : T) : Boolean {
        root = splay(root, k)
        if (root == null) {
            return false
        }
        return root!!.key == k
    }

    fun del(k : T) {
        if (root == null) return
        root = splay(root, k)
        if (root!!.key == k) {
            if (root!!.left == null) root = root!!.right
            else {
                val tmp = root!!.right
                root = root!!.left
                root = splay(root, k)
                root!!.right = tmp
            }
        }
    }

    fun prev(k : T) : T? {
        return prev(root, k)?.key
    }

    fun next(k : T) : T? {
        return next(root, k)?.key
    }

}

fun main(args: Array<String>) {

}