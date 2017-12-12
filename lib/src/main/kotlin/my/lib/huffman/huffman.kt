package my.lib.huffman

import java.util.*

/**
 * Created by Telnov Sergey on 01.12.2017.
 */

internal data class Node(val priority: Int, val name: Char = '#', val left: Node? = null, val right: Node? = null) : Comparable<Node> {
    override fun compareTo(other: Node) = when {
        priority < other.priority -> -1
        priority > other.priority -> 1
        else -> 0
    }
}

fun decodeHuffmanEncode(code: String, digitsInfo: HashMap<String, Char>) = buildString {

    var index = 0
    while (index < code.length) {

        var curIndex = index + 1
        while (curIndex < code.length && !digitsInfo.containsKey(code.substring(index, curIndex)))
            curIndex++

        append(digitsInfo[code.substring(index, curIndex)])

        index = curIndex
    }
}

fun huffmanEncode(s: String): String {
    val digitsInfo = hashMapOf<Char, Int>()

    s.forEach {
        digitsInfo.compute(it) { _, value ->
            return@compute value?.plus(1) ?: 1
        }
    }

    val queue = PriorityQueue<Node>()
    digitsInfo.forEach { key, value -> queue.add(Node(value, key)) }

    while (queue.size > 1) {
        val left = queue.poll()
        val right = queue.poll()
        queue.add(Node(
                left.priority + right.priority,
                left = left,
                right = right)
        )
    }

    val keys = hashMapOf<Char, String>()

    fun dfs(curr: Node, code: String) {
        if (curr.name != '#') {
            keys.put(curr.name, code)
        }
        if (curr.left != null) {
            dfs(curr.left, code + "0")
        }
        if (curr.right != null) {
            dfs(curr.right, code + "1")
        }
    }

    val root = queue.poll()
    dfs(root, if (root.name != '#') "0" else "")

    return buildString {
        s.forEach {
            append(keys[it])
        }
    }
}
