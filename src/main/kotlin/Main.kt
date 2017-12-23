import kotlin.collections.ArrayList

fun dfs(n: Int, m: Int, edges: ArrayList<Pair<Int, Int>>, root: Int): ArrayList<Int> {
    var graph: ArrayList<ArrayList<Int>> = arrayListOf()
    var visited: ArrayList<Boolean> = arrayListOf()
    var result = arrayListOf<Int>()
    for (i in 0 .. n - 1) {
        graph.add(arrayListOf<Int>())
        visited.add(false)
    }
    for (i in 0 .. m - 1) {
        val u = edges[i].first
        val v = edges[i].second
        graph[u - 1].add(v - 1)
        graph[v - 1].add(u - 1)
    }
    dfsich(root - 1, graph, result, visited)
    return result
}

fun dfsich(vertex : Int, graph : ArrayList<ArrayList<Int>>, result : ArrayList<Int>, visited : ArrayList<Boolean>) {
    visited[vertex] = true
    result.add(vertex + 1)
    for (i in 0 .. graph[vertex].size - 1)
            if (!visited[graph[vertex][i]]) {
                dfsich(graph[vertex][i], graph, result, visited)
            }
}
