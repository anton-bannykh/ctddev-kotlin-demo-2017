package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    val automat1 = AhoCorasick()
    val automat2 = AhoCorasick("kek", "lol")

    @Test
    fun test1() {
        automat1.addString("aaa")
        val check = listOf<Int>(0, 1, 2)
        assertEquals(check, automat1.findPos("aaaaa"))
    }

    @Test
    fun test2() {
        automat1.addString("aaa")
        automat1.addString("bb")
        val check = listOf<Int>(3, 5, 6, 9, 10)
        assertEquals(check, automat1.findPos("caabbaaaabbb"))
    }

    @Test
    fun test3() {
        automat1.addString("aaa")
        val check = listOf<Int>()
        assertEquals(check, automat1.findPos("c"))
    }

    @Test
    fun test4() {
        val check = listOf<Int>()
        assertEquals(check, automat1.findPos(""))
    }

    @Test
    fun test5() {
        val check = listOf<Int>(0, 3, 12)
        assertEquals(check, automat2.findPos("lolkekcheburkek"))
    }

    @Test
    fun test6() {
        automat2.addString("k")
        val check = listOf<Int>(0, 3, 3, 5, 12, 12, 14)
        assertEquals(check, automat2.findPos("lolkekcheburkek"))
    }
}