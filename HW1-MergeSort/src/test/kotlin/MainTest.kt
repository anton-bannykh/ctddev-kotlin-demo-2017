import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Random

class MainTest {
    @Test
    fun permutationTest() {
        val a : Array<Int> = arrayOf(5, 2, 4, 3, 1)
        val correct : List<Int> = a.sorted()
        mergeSort(a)
        assertEquals(a.toList(), correct)
    }

    @Test
    fun multipermutationTest() {
        val a : Array<Int> = arrayOf(1, 1, 5, 2, 5, 3, 3, 4, 6, 3)
        val correct : List<Int> = a.sorted()
        mergeSort(a)
        assertEquals(a.toList(), correct)
    }

    @Test
    fun singleTest() {
        val a : Array<Int> = arrayOf(1)
        val correct : List<Int> = a.sorted()
        mergeSort(a)
        assertEquals(a.toList(), correct)
    }

    @Test
    fun randomTests() {
        val cntTests = 10
        for (i in 0..cntTests) {
            val a : Array<Int> = Array<Int>(100000, { Random().nextInt(1000000000) })
            val correct : List<Int> = a.sorted()
            mergeSort(a)
            assertEquals(a.toList(), correct)
        }
    }
}