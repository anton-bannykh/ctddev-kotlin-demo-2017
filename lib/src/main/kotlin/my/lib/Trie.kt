package my.lib

class Trie(list: List<String> = emptyList()) {
    private val ROOT = Node(null, '$')

    private inner class Node(private val parent: Node?, private val symb: Char) {
        val next: MutableMap<Char, Node> = HashMap()
        var pattern: String? = null
        val autoMove: MutableMap<Char, Node> = HashMap()
        var flag = false

        val suffLink: Node by lazy {
            if (this == ROOT || parent == null || parent == ROOT)
                ROOT
            else
                getAutoMove(parent.suffLink, symb)
        }

        val suffShortLink: Node by lazy {
            when {
                suffLink == ROOT -> ROOT
                suffLink.flag -> suffLink
                else -> suffLink.suffShortLink
            }
        }
    }

    private fun <T> iterateInTrie(s: String, first: T, next: (Node, Char) -> T): T {
        var currNode = ROOT
        var acc = first
        for (c in s) {
            if (!currNode.next.containsKey(c)) {
                acc = next(currNode, c)
            }
            if (!currNode.next.containsKey(c)) {
                break
            }
            currNode = currNode.next[c]!!
        }
        return acc
    }

    fun add(s: String) {
        val currNode = iterateInTrie(s, ROOT, { node, c ->
            val vertex = Node(node, c)
            node.next.put(c, vertex)
            vertex
        })
        currNode.flag = true
        currNode.pattern = s
    }

    fun contains(s: String): Boolean {
        return iterateInTrie(s, true, { _, _ -> false })
    }

    private fun getAutoMove(v: Node, c: Char): Node {
        if (!v.autoMove.containsKey(c)) {
            when {
                v.next.containsKey(c) -> v.autoMove.put(c, v.next[c]!!)
                v == ROOT -> v.autoMove.put(c, ROOT)
                else -> v.autoMove.put(c, getAutoMove(v.suffLink, c))
            }
        }
        return v.autoMove[c]!!
    }

    fun findAll(s: String): List<Pair<Int, String>> {
        var u = ROOT
        val res: MutableList<Pair<Int, String>> = ArrayList()
        for (i in 0 until s.length) {
            u = getAutoMove(u, s[i])
            var v = u
            while (v != ROOT) {
                if (v.flag) {
                    res.add(Pair(i - v.pattern!!.length + 1, v.pattern!!))
                }
                v = v.suffShortLink
            }
        }
        return res
    }

    init {
        for (s in list) {
            add(s)
        }
    }
}

