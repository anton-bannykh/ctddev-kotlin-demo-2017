import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun test1() {
        create_segment_tree(5, arrayOf(1, 2, 3, 4, 5))
        assertEquals(1, minstart(1, 2))
        assertEquals(1, minstart(1, 3))
        set(3, -1)
        assertEquals(-1, minstart(2, 3))
        assertEquals(4, minstart(4, 5))
        set(4, -5)
        assertEquals(-5, minstart(1, 5))
        set(5, 10)
        assertEquals(10, minstart(5, 5))
    }
    @Test
    fun test2() {
        create_segment_tree(10, arrayOf(-1, 4, 0, 5, 12, 18, -4, -2, 6, 1))
        assertEquals(-4, minstart(1, 10))
        assertEquals(-1, minstart(1, 3))
        assertEquals(0, minstart(3, 3))
        set(3, 4)
        assertEquals(-4, minstart(3, 7))
        set(1, 2)
        assertEquals(2, minstart(1, 6))
    }
    @Test
    fun test3() {
        create_segment_tree(7, arrayOf(1, 1, 1, 1, 1, 1, 1))
        assertEquals(1, minstart(5, 5))
        set(5, 6)
        assertEquals(6, minstart(5, 5))
        assertEquals(1, minstart(4, 5))
        set(7, -3)
        set(6, -4)
        assertEquals(-4, minstart(6, 7))
        set(1, 0)
        assertEquals(0, minstart(1, 4))
    }
}