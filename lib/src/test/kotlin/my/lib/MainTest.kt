package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testSample() {
        assertEquals(5, kth(intArrayOf(0, 2, 6, 5, 7, 9, 5), 3))
    }

    @Test
    fun testFirst() {
        assertEquals(0, kth(intArrayOf(0, 2, 6, 5, 7, 9, 5), 1))
    }

    @Test
    fun testLast() {
        assertEquals(9, kth(intArrayOf(0, 2, 6, 5, 7, 9, 5), 7))
    }

    @Test
    fun testSingleElement() {
        assertEquals(42, kth(intArrayOf(42), 1))
    }

    @Test(expected = IllegalArgumentException::class)
    fun testNonPositiveK() {
        kth(intArrayOf(0, 2, 6, 5, 7, 9, 5), -1)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testEmptyArray() {
        kth(intArrayOf(), 3)
    }

    @Test(expected = IllegalArgumentException::class)
    fun testKBiggerThanSize() {
        kth(intArrayOf(0, 2, 6, 5, 7, 9, 5), 10)
    }
}