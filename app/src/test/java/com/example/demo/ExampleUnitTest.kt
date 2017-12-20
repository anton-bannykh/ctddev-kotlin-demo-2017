package com.example.demo

import my.lib.test
import org.junit.Test

import org.junit.Assert.assertEquals

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */
class ExampleUnitTest {
    @Test
    fun addition_isCorrect() {
        assertEquals(4, 2 + 2)
    }
    @Test
    fun test100x2() {
        assertEquals(test(100, 2), true)
    }

    @Test
    fun test1000000x2() {
        assertEquals(test(1000000, 2), true)
    }

    @Test
    fun test1000000x50() {
        assertEquals(test(1000000, 50), true)
    }
}
