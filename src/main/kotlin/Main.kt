fun main(args: Array<String>) {
    println("Hello world!")
}
/**
 * Created by tujh on 04.12.2017.
 */

fun dfs(v: Int, comp: IntArray, graph: List<List<Int>>, c: Int = 1, ord: MutableList<Int>? = null) {
    comp[v] = c
    for (i in graph[v])
        if (comp[i] == 0)
            dfs(i, comp, graph, c, ord)

    ord?.add(v)
}

fun components(graph: List<List<Int>>): IntArray {
    val reverseGraph = ArrayList<ArrayList<Int>>()

    for (i in 0 until graph.size)
        reverseGraph.add(ArrayList())
    for (i in 0 until graph.size)
        for (u in graph[i])
            reverseGraph[u].add(i)
    val used = IntArray(graph.size)
    val ord = ArrayList<Int>()
    for (i in 0 until graph.size)
        if (used[i] == 0)
            dfs(i, used, graph, ord = ord)
    val components = IntArray(graph.size)
    var comp = 1

    for (i in 0 until graph.size)
        if (components[ord[graph.size - 1 - i]] == 0)
            dfs(i, components, reverseGraph, comp++)
    return components
}