import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testDFS() {
        assertEquals(mutableListOf(1, 3, 2), dfsIterative(3, arrayOf (
                Pair (1, 3),
                Pair (2, 3)
        )))
        assertEquals (mutableListOf(1, 3, 4, 5, 2, 6, 8, 7), dfsIterative (8, arrayOf (
                Pair (1, 3),
                Pair (1, 8),
                Pair (8, 3),
                Pair (8, 4),
                Pair (3, 4),
                Pair (4, 5),
                Pair (4, 6),
                Pair (2, 6),
                Pair (2, 5),
                Pair (2, 8),
                Pair (7, 8)
        )))
        assertEquals (mutableListOf(1), dfsIterative (12, arrayOf (
                Pair (2, 4),
                Pair (3, 6),
                Pair (2, 12),
                Pair (5, 6),
                Pair (9, 11),
                Pair (10, 12),
                Pair (2, 3),
                Pair (3, 7),
                Pair (8, 11),
                Pair (11, 4),
                Pair (2, 9),
                Pair (9, 3)
        )))
        assertEquals (mutableListOf(1, 2, 4, 3, 10, 6, 5, 14, 9, 12, 11, 15, 7, 13, 8), dfsIterative (15, arrayOf (
                Pair (1, 2),
                Pair (2, 13),
                Pair (2, 4),
                Pair (2, 7),
                Pair (4, 7),
                Pair (7, 13),
                Pair (4, 8),
                Pair (4, 3),
                Pair (4, 5),
                Pair (3, 11),
                Pair (3, 10),
                Pair (10, 6),
                Pair (5, 6),
                Pair (5, 14),
                Pair (6, 14),
                Pair (9, 14),
                Pair (9, 15),
                Pair (9, 12),
                Pair (12, 11)
        )))
    }
}