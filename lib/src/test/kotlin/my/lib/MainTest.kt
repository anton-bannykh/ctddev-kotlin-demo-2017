package my.lib

import org.junit.Test
import java.util.Arrays

class MainTest {
    @Test
    fun testDisconnectedGraph() {
        startTest(10, 4)
    }

    @Test
    fun testProbabilityOfEdge25() {
        startTest(10, 3)
    }

    @Test
    fun testProbabilityOfEdge50() {
        startTest(10, 2)
    }

    @Test
    fun testProbabilityOfEdge75() {
        startTest(10, 1)
    }

    @Test
    fun testCompleteGraph() {
        startTest(10, 0)
    }

    private fun startTest(n: Int, probability: Int) {
        val from = (0..n).random()
        val edges = Array(n, { ArrayList<Pair<Int, Long>>() })
        val dist = LongArray(n, { 1_000_000_000_000 })
        dist[from] = 0
        val was = BooleanArray(n, { false })

        fun generateGraph() {
            for (i in 0..(n - 1)) {
                for (j in (i + 1)..(n - 1)) {
                    val edgeExist = ((1..5).random() > probability)
                    if (edgeExist) {
                        edges[i].add(Pair(j, (1..100_000).random().toLong()))
                        edges[j].add(Pair(i, (1..100_000).random().toLong()))
                    }
                }
            }
        }

        var way: Long = 0
        fun checkAns(v: Int) {
            was[v] = true
            for ((u, len) in edges[v]) {
                if (was[u]) continue
                way += len
                if (way < dist[u]) {
                    dist[u] = way
                }
                checkAns(u)
                way -= len
            }
            was[v] = false
        }

        generateGraph()
        val ans = dijkstra(from, edges)
        checkAns(from)
        assert(Arrays.equals(dist, ans))
    }
}