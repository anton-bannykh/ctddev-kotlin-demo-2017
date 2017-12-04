package graph.algorithms

import graph.BipartieGraph

fun BipartieGraph.findMatching(): List<Int?> {
    val (parent, time) = Pair(Array<Int?>(rightPartSize) { null }, Array(leftPartSize) { -1 })
    var matchingSize = 0
    fun dfs(v: Int): Boolean = if (time[v] == matchingSize) false else {
        time[v] = matchingSize
        g[v].any { u ->
            (parent[u]?.let(::dfs) ?: true).also { if (it) parent[u] = v }
        }
    }
    (0 until leftPartSize).forEach {
        if (dfs(it))
            matchingSize++
    }
    return parent.asList()
}