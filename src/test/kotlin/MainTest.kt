import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {

    @Test
    fun testEasy1() {
        assertEquals(mutableListOf(2, 1), nextPerm(1, 2))
    }

    @Test
    fun testEasy2() {
        assertEquals(mutableListOf(1, 2, 4, 3), nextPerm(1, 2, 3, 4))
    }

    @Test
    fun test1() {
        assertEquals(mutableListOf(1, 2, 4, 3, 6, 5), nextPerm(1, 2, 4, 3, 5, 6))
    }

    @Test
    fun test2() {
        assertEquals(mutableListOf(1, 2, 4, 6, 3, 5), nextPerm(1, 2, 4, 5, 6, 3))
    }

    @Test
    fun test3() {
        assertEquals(mutableListOf(2, 1, 3, 4, 5, 6), nextPerm(1, 6, 5, 4, 3, 2))
    }

    @Test
    fun testNotCorrect1() {
        assertEquals(mutableListOf(0, 0, 0, 0, 0, 0), nextPerm(6, 5, 4, 3, 2, 1))
    }

    @Test
    fun testNotCorrect2() {
        assertEquals(mutableListOf(0), nextPerm(1))
    }
}