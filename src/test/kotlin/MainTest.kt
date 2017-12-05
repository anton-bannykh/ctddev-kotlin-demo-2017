import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testGen() {
        var tree = SegmentTree(arrayOf(1, 5, 2, 4, 3, 7, 1, 6))
        assertEquals(2, tree.get(2, 3))
        assertEquals(1, tree.get(0, 8))
        assertEquals(3, tree.get(3, 6))
    }

    @Test
    fun testAdd() {
        var tree = SegmentTree(arrayOf(1, 5, 2, 4, 3, 7, 1, 6))
        tree.add(0, 5)
        assertEquals(1, tree.get(0, 8))
        assertEquals(2, tree.get(0, 6))
        tree.add(3, -100)
        assertEquals(-96, tree.get(0, 8))
    }

    @Test
    fun testSet() {
        var tree = SegmentTree(arrayOf(1, 5, 2, 4, 3, 7, 1, 6))
        tree.set(0, 5)
        assertEquals(1, tree.get(0, 8))
        assertEquals(2, tree.get(0, 6))
        tree.set(3, -100)
        assertEquals(-100, tree.get(0, 8))
    }
}