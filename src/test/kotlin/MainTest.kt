import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun test1() {
        val arr = intArrayOf(0)
        assertEquals(0, solve(arr.size, arr, 1, 1))
    }
    
    @Test
    fun test2() {
        val arr = intArrayOf(-7, 4, 3, 2, 5, 8, -1)
        assertEquals(-7, solve(arr.size, arr, 1, 7))
    }

    @Test
    fun test3() {
        val arr = intArrayOf(-7, 4, 3, 2, 5, 8, -1)
        assertEquals(2, solve(arr.size, arr, 6, 2))
    }

    @Test
    fun test4() {
        val arr = intArrayOf(-7, 4, 3, 2, 5, 8, -1)
        assertEquals(-1, solve(arr.size, arr, 7, 7))
    }

    @Test
    fun test5() {
        val arr = intArrayOf(-7, 4, 3, 2, 5, -8, -1)
        assertEquals(-8, solve(arr.size, arr, 6, 7))
    }
}