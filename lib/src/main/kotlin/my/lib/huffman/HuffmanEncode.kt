package my.lib.huffman

import java.util.*

/**
 * Created by Telnov Sergey on 11.12.2017.
 */

class HuffmanEncode {
    var digitsCode: String
        get() = buildString {
                keys.forEach {
                    append("${it.key}: ${it.value}\n")
                }
            }

    private val keys: HashMap<Char, String> = hashMapOf()

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

        keys.clear()

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

    init {
        digitsCode = ""
    }
}