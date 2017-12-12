import org.junit.Test
import java.util.Random

class MainTest {
   
    private fun randomTest(n: Int, maxCoordinate: Int, doubleCoordinate: Boolean = true): Boolean {
        val random = Random()
        val dc = if (doubleCoordinate) 1 else 0
        val array = Array(n,
                { Pair(random.nextInt(maxCoordinate).toDouble() + dc * random.nextDouble(),
                        random.nextInt(maxCoordinate).toDouble() + dc * random.nextDouble()) })

        val x = calculateTSP(array)

        val b = BooleanArray(n, { false })
        for (i in x.second) {
            if (b[i]) {
                return false
            }
            b[i] = true
        }

        val r = calculateDistance(array, x.second)
        return ((x.first - 0.000001) < r && r < (x.first + 0.000001))

    }

    @Test
    fun randomIntTest10x100() {
        assert(randomTest(10, 100, false))
    }

    @Test
    fun randomTest10x100() {
        assert(randomTest(10, 100, true))
    }

    @Test
    fun randomTest10x10() {
        assert(randomTest(10, 10, true))
    }

    @Test
    fun randomIntTest10x10() {
        assert(randomTest(10, 10, false))
    }
    @Test
    fun randomIntTest5x5() {
        assert(randomTest(5, 5, false))
    }

    @Test
    fun randomTest1000x1000() {
        assert(randomTest(1000, 1000, true))
    }

    @Test
    fun randomIntTest1e5x1e4() {
        assert(randomTest(100000, 10000, false))
    }

    @Test
    fun randomTest1e5x1e4() {
        assert(randomTest(100000, 10000, true))
    }

}
