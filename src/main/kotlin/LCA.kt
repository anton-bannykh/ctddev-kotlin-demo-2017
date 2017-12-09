
var batya: MutableList<Int> = mutableListOf()
var depth: MutableList<Int> = mutableListOf()
var dp: MutableList<MutableList<Int>> = mutableListOf()

fun depthCounter(i: Int) {
    if (depth[i] == -1) {
        if (depth[batya[i]] == -1) {
            depthCounter(batya[i])
        }
        depth[i] = depth[batya[i]] + 1
    }
}

fun prep() {
    if (batya.size == 0) {
        return
    }
    depth.add(0)
    for (i in 1..(batya.size - 1)) {
        depth.add(-1)
    }
    for (i in 1..(batya.size - 1)) {
        depthCounter(i)
    }

    for (i in 0..(batya.size - 1)) {
        dp.add(mutableListOf())
        for (j in 0..18) {
            dp[i].add(0)
        }
    }

    for (i in 0..batya.size - 1) {
        dp[i].set(0, batya[i])

    }

    for (j in 1..18) {
        for (i in 1..(batya.size - 1)) {
            dp[i][j] = dp[dp[i][j - 1]][j - 1]
        }
    }
}

fun lca(uIn: Int, vIn: Int): Int {
    var u = uIn
    var v = vIn

    if (depth[u] > depth[v]) {
        u = v.also { v = u }
    }

    for (i in 18 downTo 0) {
        if (depth[dp[v][i]] >= depth[u]) {
            v = dp[v][i]
        }
    }

    if (u == v) {
        return u
    }

    for (i in 18 downTo 0) {
        if (dp[u][i] != dp[v][i]) {
            u = dp[u][i]
            v = dp[v][i]
        }
    }

    return batya[u]
}

fun solve(n: Int, m: Int, batyaIn: MutableList<Int>, questions: MutableList<Pair<Int, Int>>): MutableList<Int> {
    batya = batyaIn
    batya.add(0, 0)
    prep()

    var ans: MutableList<Int> = mutableListOf()
    for (i in 0..(m - 1)) {
        ans.add(lca(questions[i].first, questions[i].second))
    }
    return ans
}