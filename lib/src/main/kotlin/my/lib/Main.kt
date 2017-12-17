package my.lib

import java.util.Random

fun dfs1(v: Int, used: BooleanArray, graph: Array<ArrayList<Int>>, order: ArrayList<Int>) {
    used[v] = true
    for (u in graph[v]) {
        if (!used[u]) {
            dfs1(u, used, graph, order)
        }
    }
    order += v
}

fun dfs2(v: Int, used: BooleanArray, reverseGraph: Array<ArrayList<Int>>, comp: ArrayList<Int>) {
    used[v] = true
    comp += v
    for (u in reverseGraph[v]) {
        if (!used[u]) {
            dfs2(u, used, reverseGraph, comp)
        }
    }
}

fun buildReverseGraph(graph: Array<ArrayList<Int>>): Array<ArrayList<Int>> {
    val reverseGraph = Array(graph.size, { ArrayList<Int>() })
    for (i in graph.indices) {
        for (u in graph[i]) {
            reverseGraph[u].add(i)
        }
    }
    return reverseGraph
}

fun condensate(graph: Array<ArrayList<Int>>): ArrayList<ArrayList<Int>> {
    val reverseGraph = buildReverseGraph(graph)
    val used = BooleanArray(graph.size)
    val order = ArrayList<Int>()
    for (i in graph.indices) {
        if (!used[i]) {
            dfs1(i, used, graph, order)
        }
    }
    used.fill(false)
    val ans = ArrayList<ArrayList<Int>>()
    for (i in graph.indices) {
        val v = order[graph.size - 1 - i]
        if (!used[v]) {
            val comp = ArrayList<Int>()
            dfs2(v, used, reverseGraph, comp)
            ans += comp
        }
    }
    return ans
}

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start

fun generateGraph(n: Int, m: Int): Array<ArrayList<Int>> {
    val graph = Array(n, { ArrayList<Int>() })
    var edges = 0
    while (edges < m) {
        val from = (0..n).random()
        val to = (0..n).random()
        graph[from].add(to)
        edges++
    }
    return graph
}
