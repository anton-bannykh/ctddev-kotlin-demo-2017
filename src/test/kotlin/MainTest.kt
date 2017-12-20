import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun test1() {
        assertEquals(1, calcPalindromes("a"))
        assertEquals(3, calcPalindromes("abc"))
    }

    @Test
    fun test2() {
        assertEquals(6, calcPalindromes("aaa"))
        assertEquals(7, calcPalindromes("aaycc"))
    }

    @Test
    fun test3() {
        assertEquals(10, calcPalindromes("abcdcba"))
        assertEquals(9, calcPalindromes("abccba"))
    }
}
