package my.lib
import java.util.Stack

fun main (args: Array<String>) {
    println ()
    return
}

fun dfsIterative (n: Int, graph: Array<Pair<Int, Int>>): MutableList<Int> {
    //graph is defined by its edges; the returned list is the order in which the vertices are visited
    //if a vertex has multiple neighbors, they are visited in an ascending order
    //the first vertex is always (1)
    val s = Stack<Int>()
    val g: MutableList<MutableList<Int>> = mutableListOf()
    for (i in 0..n - 1) g.add(mutableListOf())
    for (i in graph.indices) {
        g[graph[i].first - 1].add(graph[i].second - 1)
        g[graph[i].second - 1].add(graph[i].first - 1)
    }
    for (i in 0..n - 1) g[i].sortDescending()
    val res: MutableList<Int> = mutableListOf()
    val v = Array<Boolean> (n, { false })
    s.add(0)
    while (!s.empty()) {
        val e = s.pop()
        if (!v[e]) {
            v[e] = true
            res.add(e + 1)
        }
        for (i in g[e]) {
            if (!v[i]) s.add(i)
        }
    }
    return res
}