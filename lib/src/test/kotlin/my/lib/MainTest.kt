package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testFoo() {
        assertEquals(10, foo())
    }

    @Test
    fun testSumEmpty() {
        assertEquals(0, sum())
    }

    @Test
    fun testSumSingle() {
        assertEquals(42, sum(42))
    }

    @Test
    fun testSumMany() {
        assertEquals(6, sum(1, 2, 3))
    }

    @Test
    fun testSumFunEmpty() {
        assertEquals(0, sumFun())
    }

    @Test
    fun testSumFunSingle() {
        assertEquals(42, sumFun(42))
    }

    @Test
    fun testSumFunMany() {
        assertEquals(6, sumFun(1, 2, 3))
    }
}