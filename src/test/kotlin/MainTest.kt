
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Arrays

class MainTest {

    @Test
    fun test1() {
        val ms = intArrayOf(0, 1, 2, 3, 4, 5, 6, 7)
        val ans = ms.clone()
        quickSort(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }

    @Test
    fun test2() {
        val ms = intArrayOf(10, 13, - 17, 10, 0, -100, -1)
        val ans = ms.clone()
        quickSort(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }

    @Test
    fun test3() {
        val ms = intArrayOf(1, 4, 2, 3, 1, 8765, 876875, 876, 76786, 97678, 876876, -99999999)
        val ans = ms.clone()
        quickSort(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }

    @Test
    fun test4() {
        val ms = intArrayOf(0, 0, -1, 5, 6, 3, 9, -1000000 )
        val ans = ms.clone()
        quickSort(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }
}