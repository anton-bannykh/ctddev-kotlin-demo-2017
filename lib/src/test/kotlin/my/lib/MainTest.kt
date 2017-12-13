package my.lib

//import org.junit.Assert.assertEquals
import org.junit.Test
import org.junit.Assert.assertTrue
//import org.junit.Test
import java.util.*

class MainTest {
    @Test
    fun SortedArray() {
        val arr = intArrayOf(0, 1, 2, 3, 4, 5)
        val ans = intArrayOf(0, 1, 2, 3, 4, 5)
        mergeSort(arr)
        assertTrue(Arrays.equals(arr, ans))
    }

    @Test
    fun ReverseArray() {
        val arr = intArrayOf(5, 4, 3, 2, 1, 0)
        val ans = intArrayOf(0, 1, 2, 3, 4, 5)
        mergeSort(arr);
        assertTrue(Arrays.equals(arr, ans))
    }

    @Test
    fun EqualElements() {
        val arr = intArrayOf(5, 5, 5, 5)
        val ans = intArrayOf(5, 5, 5, 5)
        mergeSort(arr);
        assertTrue(Arrays.equals(arr, ans))
    }
    @Test
    fun NegativeNumber() {
        val arr = intArrayOf(-8, 3, 0, -7, 4)
        val ans = intArrayOf(-8, -7, 0, 3, 4)
        mergeSort(arr);
        assertTrue(Arrays.equals(arr, ans))
    }
    @Test
    fun RandomElements() {
        val arr = intArrayOf(1, 10, 3, 4567, -100, -3, 3, 3, 600, -1)
        val ans = intArrayOf(-100, -3, -1, 1, 3, 3, 3, 10, 600, 4567)
        mergeSort(arr);
        assertTrue(Arrays.equals(arr, ans))
    }
}
