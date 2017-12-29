package my.lib

import org.junit.Assert.assertSame
import org.junit.Test

class MainTest {
    @Test
    fun test8() {
        val a = algo(8)
        val b = a.energy.toInt()
        assertSame(0, b)
    }
    @Test
    fun test9() {
        val a = algo(9)
        val b = a.energy.toInt()
        assertSame(0, b)
    }
    @Test
    fun test10() {
        val a = algo(10)
        val b = a.energy.toInt()
        assertSame(0, b)
    }
}
