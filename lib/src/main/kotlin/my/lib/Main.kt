package my.lib

import java.util.ArrayDeque
import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.math.min

const val INF = (1e9).toInt()

class Edge(val u: Int, val c: Int, var f: Int, val ind: Int, val rev: Int)

fun bfs(g: Array<ArrayList<Edge>>, d: IntArray, s: Int, t: Int, a: Int): Boolean {
    val n = g.size
    d.fill(n + 10)
    d[s] = 0
    val que = ArrayDeque<Int>(n)
    que.add(s)
    while (!que.isEmpty()) {
        val v = que.pollFirst()
        for (e in g[v]) {
            val u = e.u
            if ((e.c - e.f >= a) && (d[u] > d[v] + 1)) {
                d[u] = d[v] + 1
                que.addLast(u)
            }
        }
    }
    return (d[t] != n + 10)
}

fun dfs(v: Int, minc: Int, g: Array<ArrayList<Edge>>, h: IntArray, d: IntArray, t: Int): Int {
    if (v == t || minc == 0) {
        return minc
    }
    while (h[v] < g[v].size) {
        val e = g[v][h[v]]
        if ((d[e.u] == d[v] + 1) && (e.c > e.f)) {
            val delta = dfs(e.u, min(minc, e.c - e.f), g, h, d, t)
            if (delta > 0) {
                g[v][h[v]].f += delta
                g[e.u][e.rev].f -= delta
                return delta
            }
        }
        ++h[v]
    }
    return 0
}

fun dinitz(g: Array<ArrayList<Edge>>, s: Int, t: Int): IntArray {
    val d = IntArray(g.size, { 0 })
    val h = IntArray(g.size, { 0 })
    var cnt = 0
    var maxc = -1
    for (i in g.indices) {
        for (e in g[i]) {
            maxc = max(maxc, e.c)
            cnt = max(cnt, e.ind)
        }
    }
    var a = 1
    while (2 * a <= maxc) {
        a *= 2
    }
    while (a > 0) {
        while (bfs(g, d, s, t, a)) {
            h.fill(0)
            var flow = dfs(s, INF, g, h, d, t)
            while (flow > 0) {
                flow = dfs(s, INF, g, h, d, t)
            }
        }
        a /= 2
    }
    val ans = IntArray(cnt, { 0 })
    g.forEach { (it.filter { it.ind > 0 }).forEach { ans[it.ind - 1] = it.f } }
    return ans
}

fun addEdge(a: Int, b: Int, c: Int, ind: Int, g: Array<ArrayList<Edge>>) {
    val reva = g[b].size
    val revb = g[a].size
    g[a].add(Edge(b, c, 0, ind, reva))
    g[b].add(Edge(a, 0, 0, -ind, revb))
}

fun maxFlow(n: Int, m: Int, v: IntArray, u: IntArray, c: IntArray, s: Int, t: Int): IntArray {
    val g = Array(n) { ArrayList<Edge>() }
    for (i in 0..(m - 1)) {
        addEdge(v[i] - 1, u[i] - 1, c[i], i + 1, g)
    }
    return dinitz(g, s, t)
}
