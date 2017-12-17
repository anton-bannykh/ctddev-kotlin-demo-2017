package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class mainTest {
    @Test
    fun nthTest() {
        assertEquals(3, nth_element(arrayOf(1, 2, 3, 4, 5, 6), 3))
        assertEquals(-50 , nth_element(arrayOf(-2500, -50, -10, 10, -40), 2))
        assertEquals(152 , nth_element(arrayOf(0, 1, 2, -30, 152), 5))
        assertEquals(42 , nth_element(arrayOf(30, -30, 42, -42, 52), 4))
        assertEquals(1337 , nth_element(arrayOf(-1337, 0, 6, 13, 1337, 37, 2400, 2437), 6))
        assertEquals(0 , nth_element(arrayOf(-1, 0, 1, 2, 6, 10), 2))
    }
}