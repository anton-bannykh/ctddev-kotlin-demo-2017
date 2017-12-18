package my.lib

import org.junit.Assert.assertEquals

import org.junit.Test

class MainTest {

    @Test

    fun test1() {

        val a = intArrayOf(1, 2, 3, 3, 4, 4, 7, 8, 10)

        assertEquals(2, BinarySearch(a, 3))

    }

    @Test

    fun test2() {

        val a = intArrayOf(1, 2, 15, 40, 60, 70)

        assertEquals(2, BinarySearch(a, 15))

    }

    @Test

    fun test3() {

        val a = intArrayOf(10, 20, 30, 40, 99, 200, 300)

        assertEquals(5, BinarySearch(a, 200))

    }

    @Test

    fun test4() {

        val a = intArrayOf(13, 17, 65, 200, 658)

        assertEquals(2, BinarySearch(a, 65))

    }

    @Test

    fun test5() {
        val a = intArrayOf(45, 405, 963, 1000, 10023, 50000)
        assertEquals(2, BinarySearch(a, 963))

    }

}