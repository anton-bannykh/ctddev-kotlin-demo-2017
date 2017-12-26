package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testOneVertex() {
        val g = Graph()
        g.setSize(1)
        assertEquals(g.answer(), "")
    }

    @Test
    fun testTwoVertices() {
        val g = Graph()
        g.setSize(2)
        g.addEdge(1, 2, 5)
        assertEquals(g.answer(), "1 2 ")
    }

    @Test
    fun testThreeComplete() {
        val g = Graph()
        g.setSize(3)
        g.addEdge(1, 2, 5)
        g.addEdge(1, 3, 10)
        g.addEdge(2, 3, 15)
        assertEquals(g.answer(),"1 2 1 3 ")
    }

    @Test
    fun testBambooSeven() {
        val g = Graph()
        g.setSize(7)
        g.addEdge(1, 2, 1)
        g.addEdge(2, 3, 1)
        g.addEdge(3, 4, 1)
        g.addEdge(4, 5, 1)
        g.addEdge(5, 6, 1)
        g.addEdge(6, 7, 1)
        assertEquals(g.answer(), "1 2 2 3 3 4 4 5 5 6 6 7 ")
    }

    @Test
    fun testNeerc() {
        val g = Graph()
        g.setSize(5)
        g.addEdge(1, 4, 1)
        g.addEdge(1, 2, 3)
        g.addEdge(1, 3, 4)
        g.addEdge(2, 3, 5)
        g.addEdge(3, 4, 6)
        g.addEdge(3, 5, 2)
        g.addEdge(4, 5, 7)
        assertEquals(g.answer(),"1 2 1 3 1 4 3 5 ")
    }

    @Test
    fun test8() {
        val g = Graph()
        g.setSize(8)
        g.addEdge(1, 2, 6)
        g.addEdge(2, 3, 17)
        g.addEdge(1, 4, 25)
        g.addEdge(3, 4, 8)
        g.addEdge(2, 4, 11)
        g.addEdge(4, 5, 2)
        g.addEdge(5, 6, 21)
        g.addEdge(5, 7, 14)
        g.addEdge(7, 8, 9)
        g.addEdge(8, 1, 19)
        assertEquals(g.answer(),"1 2 2 4 3 4 4 5 5 6 5 7 7 8 ")
    }
}