package kruskal

import org.junit.Assert.assertEquals
import org.junit.Test

class KruskalTest {
    @Test
    fun testSimpleGraphMST() {
        assertEquals(
                11,
                getWeightOfMST(5, arrayOf(
                        Edge(0, 1, 3),
                        Edge(1, 2, 5),
                        Edge(2, 3, 2),
                        Edge(2, 4, 6),
                        Edge(3, 4, 7),
                        Edge(1, 4, 4),
                        //Edge(1, 4, 1)
                ))
        )
    }

    @Test
    fun testCompleteGraphMST() {
        assertEquals(
                17,
                getWeightOfMST(5, arrayOf(
                        Edge(0, 1, 9),
                        Edge(1, 2, 10),
                        Edge(2, 3, 15),
                        Edge(3, 4, 12),
                        Edge(4, 0, 11),
                        Edge(0, 2, 3),
                        Edge(0, 3, 5),
                        Edge(1, 3, 2),
                        Edge(1, 4, 7),
                        Edge(2, 4, 8)
                ))
        )
    }

    @Test
    fun testPetersenGraphMST() {
        assertEquals(
                91,
                getWeightOfMST(10, arrayOf(
                        Edge(0, 1, 10),
                        Edge(1, 2, 77),
                        Edge(2, 3, 7),
                        Edge(3, 4, 9),
                        Edge(4, 0, 19),
                        Edge(0, 6, 8),
                        Edge(1, 7, 25),
                        Edge(2, 8, 80),
                        Edge(3, 9, 3),
                        Edge(4, 5, 90),
                        Edge(6, 8, 21),
                        Edge(6, 9, 20),
                        Edge(7, 5, 51),
                        Edge(7, 9, 12),
                        Edge(5, 8, 2)
                ))
        )
    }
}