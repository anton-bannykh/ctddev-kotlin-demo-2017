import java.util.ArrayList

open class DsuBase(private var countElement: Int = 0) {
    private class Node (var parent: Int, var rank: Int = 0) { }

    private var nodes = ArrayList<Node>()
    init {
        for (i in 0..countElement-1) {
            nodes.add(Node(i))
        }
    }

    fun addNode(countNewNode: Int = 1): Int {
        val indexFirstNewNode = countNewNode
        for (i in 0..countNewNode-1)
            nodes.add(Node(countElement++))
        return indexFirstNewNode
    }

    private fun findLead(v: Int): Int {
        if (nodes[v].parent == v) {
            return v
        }
        nodes[v].parent = findLead(nodes[v].parent)
        return nodes[v].parent
    }

    fun commonSet(a: Int, b: Int): Boolean {
        return findLead(a) == findLead(b);
    }

    fun unionSet(x: Int, y: Int): Boolean {
        var leadX = findLead(x)
        var leadY = findLead(y)
        if (leadX != leadY) {
            if (nodes[leadX].rank < nodes[leadY].rank) {
                leadX = leadY.also { leadY = leadX }
            }
            if (nodes[leadX].rank == nodes[leadY].rank)
                ++nodes[leadX].rank
            nodes[leadY].parent = leadX
            return true
        } else {
            return false
        }
    }

    fun size() = countElement

    fun clear() {
        nodes.clear()
        countElement = 0;
    }
}