package graph.algorithms

import graph.*


fun Graph.findMinPathCoverageInOriented(): List<Path> {

    val bipartie = BipartieGraph(vertexCount, vertexCount, g)

    val matching = bipartie.findMatching()
    val reverseMatching = Array<Int?>(vertexCount, { null })
    matching.forEachIndexed { right, left -> left?.also { reverseMatching[it] = right } }
    val used = Array(vertexCount, { false })

    fun exploreVertex(vertex: Int): Path {
        fun pathStart(right: Int): Int = matching[right]?.let(::pathStart) ?: right
        var current = pathStart(vertex)
        used[current] = true
        return MutablePath().also { path ->
            while (true) {
                reverseMatching[current]?.also {
                    path.addEdge(Edge(current, it))
                    current = it
                    used[current] = true
                } ?: break
            }
            if (path.edges.isEmpty())
                path.addEdge(Edge(current, current))
        }
    }

    val pathes = arrayListOf<Path>()
    (0 until vertexCount).forEach {
        if (!used[it])
            pathes.add(exploreVertex(it))
    }

    return pathes

}


fun Graph.findMinPathCoverageInNonOriented(): List<Path> {
    val extraGraph = this.toMultiGraph()
    var lastOddVertex: Int? = null
    val (extraEdges, ordinaryEdges) = Pair(hashSetOf<Edge>(), hashSetOf<Edge>())
    (0 until vertexCount).forEach { v ->
        if (g[v].size % 2 == 1) {
            if (lastOddVertex != null) {
                val u = lastOddVertex!!
                if (extraGraph.g[u].containsKey(v)) {
                    ordinaryEdges.add(Edge(u, v))
                    ordinaryEdges.add(Edge(v, u))
                }
                extraGraph.addEdge(v, u)
                extraEdges.add(Edge(v, u))
                extraEdges.add(Edge(u, v))
                lastOddVertex = null
            } else {
                lastOddVertex = v
            }
        }
    }
    val eulerPath = extraGraph.findEulerPath()
    val result = arrayListOf(MutablePath())
    eulerPath.edges.forEach {
        if (extraEdges.contains(it)) {
            if (ordinaryEdges.contains(it)) {
                ordinaryEdges.remove(it)
                ordinaryEdges.remove(Edge(it.to, it.from))
                result.last().addEdge(it)
            } else {
                result.add(MutablePath())
            }
        } else {
            result.last().addEdge(it)
        }
    }
    if (result.size > 1 && result.last().last().to == result[0][0].from) {
        result.last().merge(result[0])
        result.removeAt(0)
    }
    return result
}