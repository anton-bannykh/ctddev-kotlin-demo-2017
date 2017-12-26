package my.lib

fun main(args: Array<String>) {
}
val graph = mutableListOf(mutableListOf<Int>())
fun gcreat() {
    for (i in 1..3) {
        graph.add(mutableListOf(i))
        graph.add(mutableListOf(i))
    }
    for (i in 0..2) {
        graph[i].add(2 * (i + 1))
        graph[i].add(2 * (i + 1) + 1)
    }
}
var d = IntArray(7)
var used = BooleanArray(7)
fun bfs(a: Int) {
    for (i in d.indices) d[i] = 0
    for (i in used.indices) used[i] = false
    used[a - 1] = true
    val q: MutableList <Int> = mutableListOf(a)
    while (q.size > 0) {
        var inf: Int = q[0] - 1
        q.removeAt(0)
        for (i in graph[inf].indices) {
            if (!used[graph[inf][i] - 1]) {
                d[graph[inf][i] - 1] = d[inf] + 1
                q.add(graph[inf][i])
                used[graph[inf][i] - 1] = true
            }
        }
    }
}