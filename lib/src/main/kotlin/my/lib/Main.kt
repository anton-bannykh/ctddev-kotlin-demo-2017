fun getMaxMatching(n: Int, m: Int, e: List<Pair<Int, Int>>): List<Pair<Int, Int>> {
    val g = Array(n, { arrayListOf<Int>() })

    e.forEach { (u, v) -> g[u].add(v) }

    val pair = Array<Int?>(m, { null })
    val used = Array(n, { false })

    for (v in 0 until n) {
        fun dfs(v: Int): Boolean {
            if (used[v])
                return false
            used[v] = true
            return g[v].any { to -> (pair[to]?.let { dfs(it) } != false).also { if (it) pair[to] = v } }
        }

        used.fill(false)
        dfs(v)
    }

    return pair.filterNotNull().mapIndexed { index, v -> v to index }
}