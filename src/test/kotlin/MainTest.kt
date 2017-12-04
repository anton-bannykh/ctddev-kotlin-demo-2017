import org.junit.jupiter.api.Assertions.assertArrayEquals
import org.junit.jupiter.api.Test

class MainTest {
    @Test
    fun test1() {
        assertArrayEquals(arrayOf(1, -1, 1),
                buildCentroidDec(arrayOf(mutableSetOf(1), mutableSetOf(0, 2), mutableSetOf(1))))
    }

    @Test
    fun test2() {
        assertArrayEquals(arrayOf(-1, 0, 1, 1, 0, 0, 5, 5, 7),
                buildCentroidDec(arrayOf(mutableSetOf(1, 4, 5), mutableSetOf(2, 3, 0), mutableSetOf(1), mutableSetOf(1),
                        mutableSetOf(0), mutableSetOf(0, 6, 7), mutableSetOf(5), mutableSetOf(5, 8), mutableSetOf(7))))
    }

    @Test
    fun test3() {
        assertArrayEquals(arrayOf(-1, 0), buildCentroidDec(arrayOf(mutableSetOf(1), mutableSetOf(0))))
    }

    @Test
    fun test4() {
        assertArrayEquals(arrayOf(1, -1, 1, 2),
                buildCentroidDec(arrayOf(mutableSetOf(1), mutableSetOf(0, 2), mutableSetOf(1, 3), mutableSetOf(2))))
    }

    @Test
    fun test5() {
        assertArrayEquals(arrayOf(-1, 0, 0, 0),
                buildCentroidDec(arrayOf(mutableSetOf(1, 2, 3), mutableSetOf(0), mutableSetOf(0), mutableSetOf(0))))
    }

    @Test
    fun test6() {
        assertArrayEquals(arrayOf(2, 0, -1, 2, 2),
                buildCentroidDec(arrayOf(mutableSetOf(1, 2), mutableSetOf(0), mutableSetOf(0, 3, 4), mutableSetOf(2),
                        mutableSetOf(2))))
    }

    @Test
    fun test7() {
        assertArrayEquals(arrayOf(1, -1, 1, 2, 1),
                buildCentroidDec(arrayOf(mutableSetOf(1), mutableSetOf(0, 2, 4), mutableSetOf(1, 3), mutableSetOf(2),
                        mutableSetOf(1))))
    }
}
