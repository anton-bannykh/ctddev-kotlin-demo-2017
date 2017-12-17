package my.lib

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import quickSort
import java.util.Random

class MainTest {
    @Test
    fun testQuickSortEmpty() {
        val array = ArrayList<Int>()
        quickSort(array)
        assertEquals(ArrayList<Int>(), array)
    }

    @Test
    fun testQuickSortSingleElement() {
        val array = arrayListOf(1)
        quickSort(array)
        assertEquals(arrayListOf(1), array)
    }

    @Test
    fun testQuickSortSortedArray() {
        val array = arrayListOf(1, 2, 3, 4, 5)
        quickSort(array)
        assertEquals(arrayListOf(1, 2, 3, 4, 5), array)
    }

    @Test
    fun testQuickSortTrivial() {
        val array = arrayListOf(2, 4, 1, 3, 5)
        quickSort(array)
        assertEquals(arrayListOf(1, 2, 3, 4, 5), array)
    }

    @Test
    fun testQuickSortRepeatingElements() {
        val array = arrayListOf(-3, 0, -3, 1, 2, 1, 1)
        quickSort(array)
        assertEquals(arrayListOf(-3, -3, 0, 1, 1, 1, 2), array)
    }

    @Test
    fun testQuickSortBigInput() {
        val random = Random()
        val inputSize = 1_000_000
        val array = ArrayList<Int>(inputSize)
        repeat(inputSize, { array.add(random.nextInt()) })
        quickSort(array)
        for (i in 0..array.size - 2)
            assertTrue(array[i] <= array[i + 1])
    }

    @Test
    fun testQuickSortString() {
        val array = arrayListOf("ab", "b", "ac", "aba", "ba")
        quickSort(array)
        assertEquals(arrayListOf("ab", "aba", "ac", "b", "ba"), array)
    }
}
