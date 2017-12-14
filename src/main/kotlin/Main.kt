import kotlin.collections.ArrayList

var graph: ArrayList<ArrayList<Int>> = arrayListOf()
var visited: ArrayList<Boolean> = arrayListOf()
var result = arrayListOf<Int>()

fun dfs(n: Int, m: Int, edges: ArrayList<Pair<Int, Int>>, root: Int): ArrayList<Int> {
    //val scanner = Scanner(System.`in`)
    //print("Enter amount of vertexes: ")
    //var n = scanner.nextInt()
    graph = arrayListOf()
    visited = arrayListOf()
    result = arrayListOf()
    for (i in 0 .. n - 1) {
        graph.add(arrayListOf<Int>())
        visited.add(false)
    }
    //print("Enter amount of edges: ")
    //var m = scanner.nextInt()
    //println("Enter $m pairs of linked vertexes ")
    for (i in 0 .. m - 1) {
        val u = edges[i].first
        val v = edges[i].second
        graph[u - 1].add(v - 1)
        graph[v - 1].add(u - 1)
    }
    dfsich(root - 1)
    return result
}

fun dfsich(vertex : Int) {
    visited[vertex] = true
    result.add(vertex + 1)
    for (i in 0 .. graph[vertex].size - 1)
            if (!visited[graph[vertex][i]]) {
                dfsich(graph[vertex][i])
            }
}
