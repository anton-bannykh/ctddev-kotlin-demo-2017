import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun test1() {
        val rmq1 = RMQ()
        rmq1.create_segment_tree(5, arrayOf(1, 2, 3, 4, 5))
        assertEquals(1, rmq1.minstart(1, 2))
        assertEquals(1, rmq1.minstart(1, 3))
        rmq1.set(3, -1)
        assertEquals(-1, rmq1.minstart(2, 3))
        assertEquals(4, rmq1.minstart(4, 5))
        rmq1.set(4, -5)
        assertEquals(-5, rmq1.minstart(1, 5))
        rmq1.set(5, 10)
        assertEquals(10, rmq1.minstart(5, 5))
    }

    @Test
    fun test2() {
        val rmq2 = RMQ()
        rmq2.create_segment_tree(10, arrayOf(-1, 4, 0, 5, 12, 18, -4, -2, 6, 1))
        assertEquals(-4, rmq2.minstart(1, 10))
        assertEquals(-1, rmq2.minstart(1, 3))
        assertEquals(0, rmq2.minstart(3, 3))
        rmq2.set(3, 4)
        assertEquals(-4, rmq2.minstart(3, 7))
        rmq2.set(1, 2)
        assertEquals(2, rmq2.minstart(1, 6))
    }

    @Test
    fun test3() {
        val rmq3 = RMQ()
        rmq3.create_segment_tree(7, arrayOf(1, 1, 1, 1, 1, 1, 1))
        assertEquals(1, rmq3.minstart(5, 5))
        rmq3.set(5, 6)
        assertEquals(6, rmq3.minstart(5, 5))
        assertEquals(1, rmq3.minstart(4, 5))
        rmq3.set(7, -3)
        rmq3.set(6, -4)
        assertEquals(-4, rmq3.minstart(6, 7))
        rmq3.set(1, 0)
        assertEquals(0, rmq3.minstart(1, 4))
    }
}