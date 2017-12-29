package ru.walingar.kmp

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Random

class MainTest {
    @Test
    fun testA() {
        assertEquals(0, searchSubstring("aaa", "a"))
    }

    @Test
    fun testSecondIndex() {
        assertEquals(1, searchSubstring("apple", "ppl"))
    }

    @Test
    fun testNotOccurrence() {
        assertEquals(-1, searchSubstring("apple", "mandarin"))
    }

    @Test
    fun testRandomSuffixFromRandomString() {
        val s = StringBuilder()
        val sizeOfString = Random().nextInt(100000) + 1
        for (i in 1..sizeOfString)
            s.append((Random().nextInt() + 1).toChar())
        val ind = Random().nextInt(s.length)

        assertEquals(s.indexOf(s.substring(ind)), searchSubstring(s.toString(), s.substring(ind)))
    }

    @Test
    fun testRandomSuffixFromConstString() {
        val s = "Abacabadababubababa"
        val ind = Random().nextInt(s.length)
        assertEquals(s.indexOf(s.substring(ind)), searchSubstring(s, s.substring(ind)))
    }

    @Test
    fun testRandomSubstringFromRandomString() {
        val s = StringBuilder()
        val sizeOfString = Random().nextInt(100000) + 1
        for (i in 1..sizeOfString)
            s.append((Random().nextInt() + 1).toChar())
        val indStart = Random().nextInt(s.length)
        val indEnd = Random().nextInt(s.length - indStart) + indStart
        assertEquals(
                s.indexOf(s.substring(indStart, indEnd)),
                searchSubstring(s.toString(), s.substring(indStart, indEnd))
        )
    }
}