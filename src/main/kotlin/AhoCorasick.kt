
const val ALPHABET_SIZE = 26 // можно ли это внести внутрь класса?
const val START_CHAR = 'a'
class AhoCorasick (vararg strs: String) {
    private var nodes: MutableList<Node>
    init {
        nodes = MutableList(1, { Node(-1, '#') })
        for (s in strs)
            addString(s)
    }

    private data class Node(val parent: Int, val chFromParent: Char) { // не видит поля внешнего класса
        var isLeaf = false
        var suffLink = -1
        var strings: MutableList<String> = MutableList(0, { "" })
        var children: MutableList<Int> = MutableList(ALPHABET_SIZE, { -1 })
        var transitions: MutableList<Int> = MutableList(ALPHABET_SIZE, { -1 })

    }

    fun addString(s: String) {
        var cur = 0
        for (ch in s) {
            val c = ch - START_CHAR
            if (nodes[cur].children[c] == -1) {
                nodes.add(Node(cur, ch))
                nodes[cur].children[c] = nodes.size - 1
            }
            cur = nodes[cur].children[c]
        }
        nodes[cur].isLeaf = true
        nodes[cur].strings.add(s)
    }

    fun findPos(s: String): List<Int> {
        var node = 0
        val result = MutableList<Int>(0, { 0 })
        for (i in s.indices) {
            node = transition(node, s[i])
            var up = node

            while (up != 0) {
                if (nodes[up].isLeaf)
                    for (str in nodes[up].strings)
                        result.add(i + 1 - str.length)
                up = suffLink(up)
            }

        }
        return result.sorted()
    }
    private fun suffLink(index: Int): Int {
        val node = nodes[index]
        if (node.suffLink == -1)
            if (node.parent == 0)
                node.suffLink = 0
            else node.suffLink = transition(suffLink(node.parent), node.chFromParent)
        return node.suffLink
    }

    private fun transition(index: Int, ch: Char): Int {
        val c: Int = ch - START_CHAR
        val node = nodes[index]
        if (node.transitions[c] == -1)
            if (node.children[c] != -1)
                node.transitions[c] = node.children[c]
            else if (index == 0) node.transitions[c] = 0
                else node.transitions[c] = transition(suffLink(index), ch)
        return node.transitions[c]
    }
}

