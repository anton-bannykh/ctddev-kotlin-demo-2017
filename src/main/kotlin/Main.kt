import kotlin.collections.ArrayList

/**
 * Created by григорий on 04.12.2017.
 */

fun s_path(n: Int, s1: Int, f1: Int, g: Array<ArrayList<Pair<Int, Int>>>): Int {
    var s = s1 - 1
    var f = f1 - 1
    val inf: Int = 100000000
    val maxn: Int = 2000
    val d = Array<Int>(maxn, { _ -> inf })
    val used = Array<Boolean>(maxn, { _ -> false })
    d[s] = 0
    for (i in 0..n - 1) {
        var v = -1
        for (j in 0.. n - 1) {
            if (!used[j] && (v == - 1 || d[j] < d[v])) {
                v = j
            }
        }
        if (d[v] == inf) {
            break
        }
        used[v] = true
        for (j in 0..g[v].size - 1) {
            val to = g[v][j].first
            val cost = g[v][j].second
            if (d[v] + cost < d[to]) {
                d[to] = d[v] + cost
            }
        }
    }
    return d[f]
}