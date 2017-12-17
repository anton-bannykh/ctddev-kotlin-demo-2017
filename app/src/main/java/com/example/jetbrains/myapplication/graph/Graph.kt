package graph

import com.example.jetbrains.myapplication.exceptions.IncorrectNumberOfEdgesException
import com.example.jetbrains.myapplication.exceptions.IncorrectNumberOfVertexesException
import com.example.jetbrains.myapplication.exceptions.UnableToBuildAcyclicGraphException
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

    val isAcyclic: Boolean by lazy {
        println("in isAcyclic")
        val (WHITE, GRAY, BLACK) = listOf(0, 1, 2)
        val color = Array(vertexCount) { WHITE }
        fun dfs(v: Int) : Boolean = when (color[v]) {
            WHITE -> {
                color[v] = GRAY
                g[v].any(::dfs).also { color[v] = BLACK }
            }
            GRAY -> true
            else -> false
        }
        println("for :")
        (0 until vertexCount).none(::dfs).also {
            println("isAcyclic : $it")
        }
    }

    val edges: List<Edge> by lazy {
        println("in edges")
        mutableListOf<Edge>().also {
            g.forEachIndexed { v, outcoming ->
                it.addAll(outcoming.map { Edge(v, it) })
            }
        }.also {  println("out of edges") }
    }

    companion object {

        private val random = Random()
        private val MAX_ITER_COUNT = 5000

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

        fun randomGraph(n: Int, m: Int): Graph {
            println("randomAcyclicGraph($n $m)")
            if (n < 0 || n > 20) throw IncorrectNumberOfVertexesException(n)
            if (m < 0 || m > minOf(20, n * n / 2)) throw IncorrectNumberOfEdgesException(n)
            println("no-exept")
            return Graph.create(n, m, (1..m).map {
                println("edges-gen")
                Edge(random.nextInt(n) + 1, random.nextInt(n) + 1).also(::println)
            })
        }

        fun randomAcyclicGraph(n: Int, m: Int): Graph? {
            println("randomAcyclicGraph($n $m)")
            var it = 0
            while (it++ < MAX_ITER_COUNT) {
                val res = randomGraph(n, m).takeIf {
                    it.isAcyclic
                } ?: continue
                println("res is OK")
                return res
            }
            throw UnableToBuildAcyclicGraphException(n, m)
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