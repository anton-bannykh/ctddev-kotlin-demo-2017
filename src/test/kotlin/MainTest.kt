import junit.framework.Assert.assertEquals
import org.junit.Test
import java.util.Random

/**
 * Created by Maxim on 05.12.2017.
 */

class MainTest {
    private fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start

    private fun brutForce(a: IntArray) : Int {
        var f = IntArray(a.size)

        var result = 0
        for (i in 0..a.size - 1) {
            f[i] = 1
            for (j in 0..i - 1) {
                if (a[i] > a[j]) {
                    f[i] = maxOf(f[i], f[j] + 1)
                }
            }
            result = maxOf(result, f[i])
        }

        return result
    }

    @Test
    fun testRandom5() {
        val arr = IntArray(5, { (-1000000000..1000000000).random() })
        assertEquals(brutForce(arr), getLIS(arr))
    }

    @Test
    fun testRandom10() {
        val arr = IntArray(10, { (-1000000000..1000000000).random() })
        assertEquals(brutForce(arr), getLIS(arr))
    }

    @Test
    fun testRandom50() {
        val arr = IntArray(50, { (-1000000000..1000000000).random() })
        assertEquals(brutForce(arr), getLIS(arr))
    }

    @Test
    fun testRandom100() {
        val arr = IntArray(100, { (-1000000000..1000000000).random() })
        assertEquals(brutForce(arr), getLIS(arr))
    }

    @Test
    fun testRandom1000() {
        val arr = IntArray(1000, { (-1000000000..1000000000).random() })
        assertEquals(brutForce(arr), getLIS(arr))
    }

    @Test
    fun testRandom5000() {
        val arr = IntArray(5000, { (-1000000000..1000000000).random() })
        assertEquals(brutForce(arr), getLIS(arr))
    }
}
