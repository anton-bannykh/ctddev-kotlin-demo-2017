import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testEasy() {
        val numbersArray = mutableListOf<Int>(1, 2, 3, 4, 5)
        val segmentTree = SegmentTree(numbersArray)
        assertEquals(3, segmentTree.query(0, 2))
    }



    @Test
    fun testHard() {
        val numbersArray = mutableListOf<Int>(1, 2, 3, 4, 5)
        val segmentTree = SegmentTree(numbersArray)
        segmentTree.modify(2, 7)
        assertEquals(7, segmentTree.query(0, 2))
        segmentTree.modify(1, 100)
        segmentTree.modify(0, 200)
        assertEquals(200, segmentTree.query(0, 2))
        assertEquals(100, segmentTree.query(1, 2))
        assertEquals(5, segmentTree.query(4, 4))
    }
}