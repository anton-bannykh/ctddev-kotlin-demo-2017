package graph.algorithms

import extensions.popBack
import graph.Edge
import graph.MultiGraph
import graph.MutablePath
import graph.Path

fun MultiGraph.findEulerPath(): Path {
    val copy = MultiGraph(vertexCount, edgeCount, g)
    println("g: \n ${g.mapIndexed { v, es -> "${v + 1} : ${es.map { (u, c) -> "cnt(${u + 1}) = $c" }.joinToString(", ", "{", "}")}" }}")
    val pathVertexes = arrayListOf<Int>()
    val stack = arrayListOf(0)
    fun MultiGraph.eraseEdge(u: Int, v: Int) {
        fun MultiGraph.eraseDirection(u: Int, v: Int) {
            if (this.g[u][v] == null)
                return
            this.g[u].put(v, this.g[u][v]!! - 1)
            if (this.g[u][v] == 0)
                this.g[u].remove(v)
        }
        this.eraseDirection(u, v)
        this.eraseDirection(v, u)
    }
    while (stack.isNotEmpty()) {
        val v = stack.last()
        if (copy.g[v].isEmpty()) {
            pathVertexes.add(v)
            stack.popBack()
        } else {
            val u = copy.g[v].firstEntry().key
            copy.eraseEdge(u, v)
            stack.add(u)
        }
    }
    return MutablePath().also { path ->
        pathVertexes.indices.drop(1).forEach {
            path.addEdge(Edge(
                    pathVertexes[it - 1],
                    pathVertexes[it]
            ))
        }
    }
}