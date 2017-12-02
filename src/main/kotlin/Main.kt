import java.util.*
import kotlin.collections.ArrayList

fun check(edges: Array<ArrayList<Int>>, n: Int): Boolean {
    val used = Array(n, { false })

    fun dfs(v: Int) {
        used[v] = true
        for (u in edges[v]) {
            if (!used[u]) {
                dfs(u)
            }
        }
    }

    for (v in edges.indices) {
        if (edges[v].size > 0) {
            dfs(v)
            break
        }
    }

    for (v in edges.indices) {
        if (!used[v] && edges[v].size > 0) {
            return false
        }
    }

    val inDeg = Array(n, { 0 })
    val outDeg = Array(n, { 0 })

    for (v in edges.indices) {
        outDeg[v] = edges[v].size
        for (u in edges[v]) {
            inDeg[u]++
        }
    }

    for (v in inDeg.indices) {
        if (outDeg[v] != inDeg[v]) {
            return false
        }
    }

    return true
}

fun findCycle(edges: Array<ArrayList<Int>>, n: Int): ArrayList<Int> {
    val answer = ArrayList<Int>()
    val path = Stack<Int>()
    val indices = Array(n, { 0 })

    for (v in 0..n - 1) {
        if (edges[v].size > 0) {
            path.push(v)
            break
        }
    }

    while (path.isNotEmpty()) {
        val v = path.peek()
        if (edges[v].size > indices[v]) {
            path.push(edges[v][indices[v]++])
        } else {
            answer.add(v)
            path.pop()
        }
    }

    answer.reverse()

    return answer
}

fun solve(edges: Array<ArrayList<Int>>, n: Int) = if (check(edges, n)) findCycle(edges, n) else null

