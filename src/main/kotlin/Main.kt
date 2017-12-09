import java.util.ArrayList

fun dfs1(graph: ArrayList<ArrayList<Int>>, v: Int, size: IntArray, used: BooleanArray, parent: Int): Int {
    size[v] = 1
    for (i in 0..graph[v].size - 1) {
        val next: Int = graph[v][i]
        if (!used[next] && next != parent) {
            size[v] += dfs1(graph, next, size, used, v)
        }
    }
    return size[v]
}

fun dfs2(graph: ArrayList<ArrayList<Int>>, v: Int, size: IntArray, used: BooleanArray, parent: Int, cur_size: Int): Int {
    for (i in 0..graph[v].size - 1) {
        val next: Int = graph[v][i]
        if (!used[next] && next != parent && 2 * size[next] > cur_size) {
            return dfs2(graph, next, size, used, v, cur_size)
        }
    }
    return v
}

fun build(graph: ArrayList<ArrayList<Int>>, v: Int, size: IntArray, used: BooleanArray, parent: IntArray, last: Int) {
    val cur_size = dfs1(graph, v, size, used, -1)
    val cur_center = dfs2(graph, v, size, used, -1, cur_size)
    parent[cur_center] = last
    used[cur_center] = true
    for (i in 0..graph[cur_center].size - 1) {
        val next = graph[cur_center][i]
        if (!used[next]) {
            build(graph, next, size, used, parent, cur_center)
        }
    }
}

fun GiveMeOtvet(n: Int, arr: IntArray): IntArray {
    val res = IntArray(n)
    for (i in 0..n - 1) {
        res[i] = arr[i + 1]
    }
    return res
}
