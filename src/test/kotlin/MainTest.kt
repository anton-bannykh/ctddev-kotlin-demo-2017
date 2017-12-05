
import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun t1() {
        assertEquals(
                7,
                minSpanningTreeWeight(4, arrayOf(
                        Edge(0, 1, 1),
                        Edge(0, 2, 2),
                        Edge(0, 2, 3),
                        Edge(1, 2, 3),
                        Edge(1, 3, 4),
                        Edge(2, 3, 5)
                ))
        )
    }
    @Test
    fun t2() {
        assertEquals(
                9,
                minSpanningTreeWeight(10, arrayOf(
                        Edge(0, 1, 1),
                        Edge(1, 2, 1),
                        Edge(2, 3, 1),
                        Edge(3, 4, 1),
                        Edge(4, 5, 1),
                        Edge(5, 6, 1),
                        Edge(6, 7, 1),
                        Edge(7, 8, 16),
                        Edge(8, 9, 1),
                        Edge(9, 0, 1)
                ))
        )
    }
    @Test
    fun t3() {
        assertEquals(
                31,
                minSpanningTreeWeight(8, arrayOf(
                        Edge(0, 1, 1),
                        Edge(2, 3, 2),
                        Edge(4, 5, 3),
                        Edge(6, 7, 4),
                        Edge(0, 2, 5),
                        Edge(1, 3, 6),
                        Edge(4, 6, 7),
                        Edge(5, 7, 8),
                        Edge(2, 4, 9),
                        Edge(3, 5, 10)
                ))
        )
    }
}