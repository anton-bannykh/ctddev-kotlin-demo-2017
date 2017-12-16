package my.lib

import org.junit.Assert.assertSame
import org.junit.Test

class MainTest {
    @Test
    fun testFor8Queens() {
        val a = algo(8)
        val b = a.energy.toInt()
        assertSame(0, b)
    }
    @Test
    fun testFor9Queens() {
        val a = algo(9)
        val b = a.energy.toInt()
        assertSame(0, b)
    }
    @Test
    fun testFor10Queens() {
        val a = algo(10)
        val b = a.energy.toInt()
        assertSame(0, b)
    }
    @Test
    fun testFor11Queens() {
        val a = algo(11)
        val b = a.energy.toInt()
        assertSame(0, b)
    }
    @Test
    fun testFor12Queens() {
        val a = algo(12)
        val b = a.energy.toInt()
        assertSame(0, b)
    }
    @Test
    fun testFor13Queens() {
        val a = algo(13)
        val b = a.energy.toInt()
        assertSame(0, b)
    }
    @Test
    fun testFor14Queens() {
        val a = algo(14)
        val b = a.energy.toInt()
        assertSame(0, b)
    }
    @Test
    fun testFor15Queens() {
        val a = algo(15)
        val b = a.energy.toInt()
        assertSame(0, b)
    }
}