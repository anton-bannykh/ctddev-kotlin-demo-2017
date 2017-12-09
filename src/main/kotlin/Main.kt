fun main(args: Array<String>) {
    println("Hello world!")
}

fun foo() = 10

fun sum(vararg ints: Int): Int {
    var result = 0
    for (v in ints) {
        result += v
    }
    return result
}

fun sumFun(vararg ints: Int) = ints.fold(0) { acc, i -> acc + i }

fun findBridges(numberOfVertexex: Int, vararg edjes: Pair<Int, Int>): Int {

    val g: Array<ArrayList<Int>> = Array(numberOfVertexex, { ArrayList<Int>() })
    val used = BooleanArray(numberOfVertexex, { false })
    var timer = 0
    val tin = IntArray(numberOfVertexex)
    val fup = IntArray(numberOfVertexex)
    val ans: ArrayList<Pair<Int, Int>> = ArrayList()

    for (e in edjes) {
        g[e.first - 1].add(e.second - 1)
        g[e.second - 1].add(e.first - 1)
    }

    fun dfs(vertex: Int, p: Int = -1) {
        used[vertex] = true
        fup[vertex] = timer++
        tin[vertex] = fup[vertex]
        for (i in g[vertex].indices) {
            val to = g[vertex][i]
            if (to == p) continue
            if (used[to]) {
                fup[vertex] = minOf(fup[vertex], tin[to])
            } else {
                dfs(to, vertex)
                fup[vertex] = minOf(fup[vertex], fup[to])
                if (fup[to] > tin[vertex]) {
                    ans.add(Pair(vertex, to))
                }
            }
        }
    }

    for (i in used.indices) {
        if (!used[i]) {
            dfs(i)
        }
    }

    return ans.size
}