package graph

import java.util.*


data class Edge(val from: Int, val to: Int) {
    operator fun contains(vertex: Int) = vertex in listOf(from, to)
}

open class Path(val edges: List<Edge>) {
    operator fun get(i: Int) = edges[i]
    fun last() = edges.last()
}

class MutablePath(edges: MutableList<Edge> = mutableListOf()): Path(edges) {
    fun addEdge(e: Edge) = (edges as MutableList).add(e)
    fun merge(other: Path) = other.edges.forEach { addEdge(it) }
}


interface AbstractGraph

/* oriented graph class */
class Graph(val vertexCount: Int, val edgeCount: Int, val g: Array<MutableList<Int>>) : AbstractGraph {

    val isNonOriented: Boolean by lazy {
        val edges = mutableSetOf<Edge>()
        g.forEachIndexed { v, outcoming ->
            outcoming.forEach { edges.add(Edge(v, it)) }
        }
        edges.all { Edge(it.to, it.from) in edges }
    }

    companion object {

        val default = Graph(2, 2, Array(2) { arrayListOf(1 - it) })

        fun create(vertexCount: Int, edgeCount: Int, gBuilder: () -> Array<MutableList<Int>>) = Graph(
                vertexCount,
                edgeCount,
                gBuilder().also {
                    assert(it.map { it.size }.sum() == edgeCount) // also check if we are given exact edge number
                }
        )

        fun create(vertexCount: Int, edgeCount: Int, edges: List<Edge>) = create(vertexCount, edgeCount) {
            Array(vertexCount) { mutableListOf<Int>() }.also { g ->
                edges.forEach { g[it.from - 1].add(it.to - 1) }
            }
        }

    }

    fun toMultiGraph() = MultiGraph.create(vertexCount, edgeCount) {
        Array(vertexCount) { v ->
            TreeMap<Int, Int>().also {
                g[v].forEach { u -> it.put(u, (it[u] ?: 0) + 1) }
            }
        }
    }

}

class BipartieGraph(val leftPartSize: Int,
                    val rightPartSize: Int,
                    val g: Array<MutableList<Int>>): AbstractGraph

class MultiGraph(val vertexCount: Int, val edgeCount: Int, val g: Array<TreeMap<Int, Int>>): AbstractGraph {
    fun addEdge(u: Int, v: Int) {
        fun addDir(u: Int, v: Int) {
            g[u].put(v, (g[u][v] ?: 0) + 1)
        }
        addDir(u, v).also { addDir(v, u) }
    }
    companion object {
        fun create(vertexCount: Int, edgeCount: Int, gBuilder: () -> Array<TreeMap<Int, Int>>) = MultiGraph(
                vertexCount,
                edgeCount,
                gBuilder()
        )
    }
}