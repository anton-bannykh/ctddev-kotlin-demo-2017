import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Arrays

/**
 * Created by Александр on 29.11.2017.
 */
class MainTest {

    @Test
    fun testSortedArray() {
        val ms = intArrayOf(0, 1, 2, 3, 4, 5)
        val ans = ms.clone()
        Quicksort(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }

    @Test
    fun testUnSortedArray() {
        val ms = intArrayOf(100, 67, 32, 10, 0, -100)
        val ans = ms.clone()
        Quicksort(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }

    @Test
    fun test12Element() {
        val ms = intArrayOf(1, 4, 2, 3, 1, 8765, 876875, 876, 76786, 97678, 876876, -99999999)
        val ans = ms.clone()
        Quicksort(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }

    @Test
    fun test100Element() {
        val ms = IntArray(100, { (-10000..10000).random() })
        val ans = ms.clone()
        Quicksort(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }

    @Test
    fun test10000Element() {
        val ms = IntArray(10000, { (-100000..100000).random() })
        val ans = ms.clone()
        Quicksort(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }

}

