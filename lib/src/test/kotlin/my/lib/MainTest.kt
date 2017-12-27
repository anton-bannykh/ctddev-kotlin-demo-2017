package my.lib

import org.junit.Test
import java.lang.Integer.max
import java.lang.Integer.min
import java.util.Random

val random = Random()

fun generateGraph(n: Int, m: Int, k: Int): List<Pair<Int, Int>> {
    assert(k >= 0 && k <= n * m)
    val set = mutableSetOf<Pair<Int, Int>>()

    while (set.size != k) {
        set.add(random.nextInt(n) to random.nextInt(m))
    }

    return set.toList()
}

fun checkMatching(matching: List<Pair<Int, Int>>): Boolean {
    matching.forEach { e1 ->
        matching.forEach { e2 ->
            if (e1 != e2 && (e1.first == e2.first || e1.second == e2.second))
                return false
        }
    }

    return true
}

fun bruteMatching(edges: List<Pair<Int, Int>>): Int {
    var ans = 0

    for (mask in 0 until (1L shl edges.size)) {
        val matching = arrayListOf<Pair<Int, Int>>()
        edges.forEachIndexed { i, it -> if (((1L shl i) and mask) > 0) matching.add(it) }
        if (checkMatching(matching))
            ans = max(ans, matching.size)
    }
    println("Brute found $ans")

    return ans
}

class MainTest {

    private fun testWithRandomGraphs(kMin: Double, kMax: Double, testsNum: Int, maxSetSize: Int = 10) {
        (1 .. testsNum).forEach {
            println("Test #$it")
            val n = Random().nextInt(maxSetSize) + 1
            val m = Random().nextInt(maxSetSize) + 1
            val k = min(21, (n * m * (kMin + (kMax - kMin) * random.nextDouble())).toInt())
            println("Params: n = $n, m = $m, k = $k")
            val edges = generateGraph(n, m, k)
            val matching = getMaxMatching(n, m, edges.map { pair -> pair.first + 1 to pair.second + 1 }).map { pair -> pair.first - 1 to pair.second - 1 }
            println("Main solution has found matching of size ${matching.size}")
            assert(checkMatching(matching) && matching.size == bruteMatching(edges))
            println("OK\n")
        }
    }

    @Test
    fun testWithRandomSparseGraphs() {
        println("Testing with sparse graphs")
        testWithRandomGraphs(0.0, 0.5, 100)
    }

    @Test
    fun testWithRandomDenseGraphs() {
        println("Testing with dense graphs")
        testWithRandomGraphs(0.5, 1.0, 100, 6)
    }

}