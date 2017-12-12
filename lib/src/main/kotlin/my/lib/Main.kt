fun minCostAssignment(a: List<List<Int>>): Int {
    val n = a.size
    a.filter { it.size != n }.forEach { throw Exception("Wrong size of the matrix") }
    if (n == 0)
        return 0
    val minA = a.map{it.min()!!}.min()!!
    val u1 = IntArray(n, {0})
    val u2 = IntArray(n, {minA})
    val p2 = IntArray(n + 1, {-1})
    for (i in 0 until n) {
        val vis1 = BooleanArray(i + 1, {false})
        val vis2 = BooleanArray(n + 1, {false})
        val minv = Array(n, {Pair(Int.MAX_VALUE, -1)})
        p2[n] = i
        var j0 = n
        while (true) {
            val i0 = p2[j0]
            if (i0 == -1)
                break
            vis1[i0] = true
            vis2[j0] = true
            var dj = Pair(Int.MAX_VALUE, -1)
            for (j in 0 until n) {
                if (!vis2[j]) {
                    minv[j] = min(minv[j], Pair(a[i0][j] - u1[i0] - u2[j], j0))
                    dj = min(dj, Pair(minv[j].first, j))
                }
            }
            val delta = dj.first
            (0..i).filter { vis1[it] }.forEach { u1[it] += delta }
            for (j in 0 until n) {
                if (vis2[j]) {
                    u2[j] -= delta
                } else {
                    minv[j] = Pair(minv[j].first - delta, minv[j].second)
                }
            }
            j0 = dj.second
        }
        while (j0 != n) {
            p2[j0] = p2[minv[j0].second]
            j0 = minv[j0].second
        }
    }
    return u1.sum() + u2.sum()
}

fun min(a: Pair<Int, Int>, b: Pair<Int, Int>): Pair<Int, Int> {
    if (a.first < b.first)
        return a
    else
        return b
}
