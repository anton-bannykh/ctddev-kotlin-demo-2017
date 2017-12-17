package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun test1() {
        assertEquals(arrayOf(0, 1, 2, 3, 3, 4, 7, 7, 8, 9), create(10, arrayOf(7, 4, 3, 8, 0, 1, 9, 3, 7, 2)))
    }

    @Test
    fun test2() {
        assertEquals(arrayOf(1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15), create(15, arrayOf(15, 14, 13, 12, 11, 10, 9, 8, 7, 6, 5, 4, 3, 2, 1)))
    }

    @Test
    fun test3() {
        assertEquals(arrayOf(0, 1, 7, 8, 17, 17, 18, 19, 25, 25, 31, 35, 43, 49, 50, 51, 52, 52, 78, 84, 84, 85, 89, 215, 481), create(25, arrayOf(17, 52, 78, 1, 49, 35, 18, 7, 25, 17, 85, 8, 25, 0, 51, 89, 50, 84, 52, 19, 31, 43, 84, 481, 215)))
    }
}