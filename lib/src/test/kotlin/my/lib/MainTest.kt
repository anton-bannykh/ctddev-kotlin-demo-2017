package my.lib

import org.junit.Test
import org.junit.Assert.assertEquals
import java.util.ArrayList
import java.util.Random

val rand = Random()

class MainTest {

    @Test
    fun intTest() {
        val sequenceOne = listOf(1, 5, 8, 4, 7)
        val sequenceTwo = listOf(3, 2, 11, 9, 37, 3, 0)
        assertEquals(lcs(sequenceOne, sequenceTwo), 0)
    }

    @Test
    fun charTest() {
        val sequenceOne = "abacaba"
        val sequenceTwo = "abba"
        val charSequenceOne = sequenceOne.toList()
        val charSequenceTwo = sequenceTwo.toList()
        assertEquals(lcs(charSequenceOne, charSequenceTwo), 4)
    }

    @Test
    fun randomTest() {
        val listOne = ArrayList<Int>()
        for (i in 1..1000)
            listOne.add(rand.nextInt(10234))

        val listTwo = ArrayList<Int>()
        for (i in 1..1000)
            listTwo.add(rand.nextInt(2134) + 20000)
        assertEquals(lcs(listOne, listTwo), 0)

    }
    @Test
    fun bigTest() {
        val listOne = (1..20000).toList()
        val listTwo = (1..5000).map { 2 * it }
        assertEquals(lcs(listOne, listTwo), 5000)

    }
}