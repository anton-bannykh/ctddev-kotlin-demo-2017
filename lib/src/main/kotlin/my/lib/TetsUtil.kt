package my.lib

import java.util.Random

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive + 1 - start) + start

class Graph(val v: IntArray, val u: IntArray, val c: IntArray)

fun checkGraph(v: Int, g: Array<ArrayList<Int>>, used: Array<Boolean>, t: Int): Boolean {
    if (v == t) {
        return true
    }
    used[v] = true
    return g[v].any { !used[it] && checkGraph(it, g, used, t) }
}

fun genTest(n: Int, m: Int, maxc: Int): Graph {
    while (true) {
        val v = IntArray(m, { 0 })
        val u = IntArray(m, { 0 })
        val g = Array(n, { ArrayList<Int>() })
        var ind = 0
        val eds = Array(n + 1, { Array(n + 1) { false } })
        while (ind < m) {
            val a = (1..n).random()
            val b = (1..n).random()
            if ((a == b) || eds[a][b]) {
                continue
            }
            eds[a][b] = true
            v[ind] = a
            u[ind] = b
            ++ind
        }
        for (i in 0..(m - 1)) {
            g[v[i] - 1].add(u[i] - 1)
        }
        val used = Array(n, { false })
        if (!checkGraph(0, g, used, n - 1)) {
            continue
        }
        val c = IntArray(m) { (1..maxc).random() }
        return Graph(v, u, c)
    }
}
