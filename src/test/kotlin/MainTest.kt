import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun test1() {
        assertEquals(3, solve(5, arrayOf(1, 2, 5, 4, 3)))
    }

    @Test
    fun test2() {
        assertEquals(1, solve(5, arrayOf(5, 4, 3, 2, 1)))
    }

    @Test
    fun test3() {
        assertEquals(5, solve(5, arrayOf(1, 2, 3, 4, 5)))
    }
}