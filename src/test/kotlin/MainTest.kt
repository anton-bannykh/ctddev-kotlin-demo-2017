import org.junit.Assert.assertArrayEquals
import org.junit.Test

class MainTest {
    @Test
    fun testUnsorted() {
        val actual = arrayOf(32, -12, 2, 1, -4, -18, 21, 13, 8, 65)
        val expected = actual.clone()
        heapSort(actual)
        expected.sort()
        assertArrayEquals(expected, actual)
    }

    @Test
    fun testSorted() {
        val actual = arrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9)
        val expected = actual.clone()
        heapSort(actual)
        expected.sort()
        assertArrayEquals(expected, actual)
    }

    @Test
    fun randomTest1000Elements() {
        val actual = Array(1000, { (-10000..10000).random() })
        val expected = actual.clone()
        heapSort(actual)
        expected.sort()
        assertArrayEquals(expected, actual)
    }

    @Test
    fun randomTest10000Element() {
        val actual = Array(10000, { (-100000..100000).random() })
        val expected = actual.clone()
        heapSort(actual)
        expected.sort()
        assertArrayEquals(expected, actual)
    }

    @Test
    fun randomTest100000elements() {
        val actual = Array(100000, { (-1000000..1000000).random() })
        val expected = actual.clone()
        heapSort(actual)
        expected.sort()
        assertArrayEquals(expected, actual)
    }
}