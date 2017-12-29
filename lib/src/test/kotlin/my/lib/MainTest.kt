package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testEmptyString1() {
        var expected = ArrayList<Int>()
        assertEquals(expected, substringSearch("", "aaa"))
    }

    @Test
    fun testEmptyString2() {
        var expected = ArrayList<Int>()
        assertEquals(expected, substringSearch("aaa", ""))
    }

    @Test
    fun testEmptyStrings() {
        var expected = ArrayList<Int>()
        assertEquals(expected, substringSearch("", ""))
    }

    @Test
    fun testZeroIndex() {
        var expected = arrayListOf<Int>(0)
        assertEquals(expected, substringSearch("a", "abc"))
    }

    @Test
    fun testWithoutOccurrences() {
        var expected = ArrayList<Int>()
        assertEquals(expected, substringSearch("d", "abc"))
    }

    @Test
    fun testSimple() {
        var expected = arrayListOf<Int>(2)
        assertEquals(expected, substringSearch("c", "abc"))
    }

    @Test
    fun testMany() {
        var expected = arrayListOf<Int>(0, 4, 7, 11)
        assertEquals(expected, substringSearch("aba", "abacabaabacaba"))
    }
}
