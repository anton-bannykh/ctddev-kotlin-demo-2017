import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun test1() {
        val a: IntArray = intArrayOf(1, 2, 3, 8, 10, 13, 15, 18, 22, 100)
        assertEquals(3, binSearch(a, -1, a.size, 8))
        assertEquals(8, binSearch(a, -1, a.size, 22))
    }

    @Test
    fun test2() {
        val a: IntArray = intArrayOf(10, 20, 30, 80, 100, 103, 150, 180, 202, 300, 405, 409, 430, 502, 600)
        assertEquals(14, binSearch(a, -1, a.size, 600))
        assertEquals(11, binSearch(a, -1, a.size, 409))
    }

    @Test
    fun test3() {
        val a: IntArray = intArrayOf(2, 4, 6, 8, 13, 14, 20, 22, 64, 100, 200, 300, 400, 500, 600, 700, 1000, 1200, 50000, 700000)
        assertEquals(17, binSearch(a, -1, a.size, 1200))
        assertEquals(6, binSearch(a, -1, a.size, 20))
    }
}
