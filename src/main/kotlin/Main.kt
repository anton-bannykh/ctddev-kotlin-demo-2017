import kotlin.collections.ArrayList
import java.util.TreeSet

val INF = 1000000000

class Graph {
    class Edge(val from: Int, val to: Int, val len: Int)
    class Vertex(val num: Int) : Comparable<Vertex> {
        var key: Int = INF
        override fun compareTo(other: Vertex) = key.compareTo(other.key)
    }

    var graph: ArrayList<MutableList<Edge>> = ArrayList()
    var vertices: ArrayList<Vertex> = ArrayList()
    var vertexCount: Int = 0
    fun addEdge(a: Int, b: Int, c: Int) {
        graph[a - 1].add(Edge(a - 1, b - 1, c))
        graph[b - 1].add(Edge(b - 1, a - 1, c))
    }

    fun setSize(size: Int) {
        vertexCount = size
        graph.ensureCapacity(size)
        vertices.ensureCapacity(size)
        for (i in 0..vertexCount) {
            graph.add(mutableListOf())
            vertices.add(Vertex(i))
        }
    }

    fun getMST(): List<Pair<Int, Int>> {
        val p = IntArray(vertexCount, { _ -> -1 })
        val isInQueue = BooleanArray(vertexCount, { _ -> true })
        vertices[0].key = 0
        val q = TreeSet<Vertex>()
        for (i in 0..vertexCount - 1) {
            q.add(vertices[i])
        }
        val result = mutableListOf<Pair<Int, Int>>()
        while (!q.isEmpty()) {
            val v = q.pollFirst().num
            if (p[v] != -1) {
                result.add(Pair(v, p[v]))
            }
            isInQueue[v] = false
            for (e in graph[v]) {
                if (isInQueue[e.to] && vertices[e.to].key > e.len) {
                    p[e.to] = v
                    q.remove(vertices[e.to])
                    vertices[e.to].key = e.len
                    q.add(vertices[e.to])
                }
            }
        }
        return result
    }

    fun getAnswAsString(): String {
        val edges = getMST().sortedWith(compareBy({ it.second }, { it.first }))
        val sb = StringBuilder()
        for (e in edges) {
            sb.append(Math.min(e.first + 1, e.second + 1).toString() + " " + Math.max(e.first + 1, e.second + 1) + " ")
        }
        return sb.toString()
    }
}

fun main(args: Array<String>) {
    //
}
