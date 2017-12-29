var viz = ArrayList<Boolean>()
var graph = ArrayList<ArrayList<Int>>()
var ans = ArrayList<Int>()

fun dfs(u: Int, graph: ArrayList<ArrayList<Int>>, viz: ArrayList<Boolean>, ans: ArrayList<Int>) {
    viz[u] = false
    for (i in graph[u]) {
        if (viz[i]) {
            dfs(i, graph, viz, ans)
        }
    }
    ans.add(u)
}

fun main(args: Array<String>) {
    var n = args[0].toInt()
    for (i in (0..n)) {
        viz.add(true)
        graph.add(ArrayList())
    }
    var dop = 1
    for (i in (1..n)) {
        var k = args[dop].toInt()
        dop += 1
        for (j in (0..k - 1)) {
            graph[i].add(args[dop].toInt())
            dop += 1
        }
    }
    dfs(1, graph, viz, ans)
    ans.reverse()
    for (i in ans) {
        print(i.toString() + ' ')
    }
}