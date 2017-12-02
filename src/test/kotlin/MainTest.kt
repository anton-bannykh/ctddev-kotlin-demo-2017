import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Random

class MainTest {
    @Test
    fun testSmallestDist1() {
        val random = Random()
        for (cnt in 0..1000) { // number of tests
            val n = random.nextInt(1000) + 1
            val points = Array(n, { i -> Point(random.nextDouble() * 10000, random.nextDouble() * 10000, i) })
            val calculatedAns = smallestDist(points)
            var realSmallestDist = Double.POSITIVE_INFINITY
            for (i in 0..n - 1) {
                for (j in i + 1..n - 1) {
                    val dist = Math.sqrt(Math.pow(points[i].x - points[j].x, 2.0) + Math.pow(points[i].y - points[j].y, 2.0))
                    realSmallestDist = minOf(realSmallestDist, dist)
                }
            }
            assertEquals(calculatedAns, realSmallestDist, 1e-5)
        }
    }
}