import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun test1() {
        val n = 6
        val queries = arrayOf(arrayOf(-1, 1, 2), arrayOf(-1, 2, 3), arrayOf(-1, 3, 4), arrayOf(-2), arrayOf(-1, 4, 5),
                arrayOf(-1, 5, 1), arrayOf(-2), arrayOf(-1, 4, 6), arrayOf(-2), arrayOf(-1, 5, 6), arrayOf(-2))
        val ans = mutableListOf<Int>(3, 0, 1, 0)
        assertEquals(ans, runTest(n, queries))
    }

    @Test
    fun test2() {
        val n = 6
        val queries = arrayOf(arrayOf(-1, 1, 2), arrayOf(-1, 1, 3), arrayOf(-2), arrayOf(-1, 2, 3), arrayOf(-2), arrayOf(-1, 3, 4), arrayOf(-2),
                arrayOf(-1, 4, 5), arrayOf(-2), arrayOf(-1, 5, 1), arrayOf(-2), arrayOf(-1, 5, 6), arrayOf(-2))
        val ans = mutableListOf<Int>(2, 0, 1, 2, 0, 1)
        assertEquals(ans, runTest(n, queries))
    }

    @Test
    fun test3() {
        val n = 6
        val queries = arrayOf(arrayOf(-1, 1, 2), arrayOf(-1, 1, 3), arrayOf(-1, 1, 4), arrayOf(-2), arrayOf(-1, 2, 3), arrayOf(-2),
                arrayOf(-1, 1, 5), arrayOf(-2), arrayOf(-1, 4, 5), arrayOf(-2), arrayOf(-1, 5, 6), arrayOf(-2))
        val ans = mutableListOf<Int>(3, 1, 2, 0, 1)
        assertEquals(ans, runTest(n, queries))
    }

    @Test
    fun test4() {
        val n = 7
        val queries = arrayOf(arrayOf(-1, 1, 2), arrayOf(-1, 1, 3), arrayOf(-1, 1, 4), arrayOf(-1, 1, 5),
                arrayOf(-1, 1, 6), arrayOf(-1, 1, 7), arrayOf(-2), arrayOf(-1, 2, 4), arrayOf(-2))
        val ans = mutableListOf<Int>(6, 4)
        assertEquals(ans, runTest(n, queries))
    }
}