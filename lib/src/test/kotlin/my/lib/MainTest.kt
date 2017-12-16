package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Random

class MainTest {
    @Test
    fun testSmallestDist1() {
        val random = Random()
        for (cnt in 0..1000) { // number of tests
            val n = random.nextInt(1000) + 2
            val points = ArrayList(Array(n, { i -> Point(random.nextDouble() * 10000, random.nextDouble() * 10000, i) }).toList())
            val ans = smallestDist(points)
            val calculatedAns = dist(ans.first, ans.second)
            var realSmallestDist = Double.POSITIVE_INFINITY
            for (i in 0 until n) {
                for (j in i + 1 until n) {
                    realSmallestDist = minOf(realSmallestDist, dist(points[i], points[j]))
                }
            }
            assertEquals(calculatedAns, realSmallestDist, 1e-5)
        }
    }
}