package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testSimple() {
        val g = Graph(4)
        g.addEdge(0, 1, 4)
        g.addEdge(1, 2, -7)
        g.addEdge(2, 3, 2)
        g.addEdge(1, 3, 5)
        g.addEdge(3, 0, 6)
        g.addEdge(0, 2, 3)
        val dist = arrayListOf(0, 4, -3, -1)
        val paths = arrayListOf(
                arrayListOf(),
                arrayListOf(0, 1),
                arrayListOf(0, 1, 2),
                arrayListOf(0, 1, 2, 3)
        )
        val result = g.fordBellman(0)
        assertEquals(dist, result.first!!.toList())
        assertEquals(paths, result.second)
    }

    @Test
    fun testNegativeCycle() {
        val g = Graph(6)
        g.addEdge(0, 5, 14)
        g.addEdge(5, 2, 2)
        g.addEdge(5, 4, 9)
        g.addEdge(0, 2, 9)
        g.addEdge(4, 3, 6)
        g.addEdge(2, 3, 11)
        g.addEdge(3, 1, -15)
        g.addEdge(2, 4, -10)
        g.addEdge(1, 0, -7)
        val paths = arrayListOf(
                arrayListOf(0, 2, 4, 3, 1)
        )
        assertEquals(Pair(null, paths), g.fordBellman(0))
    }

    @Test
    fun testNot0Start() {
        val g = Graph(6)
        g.addEdge(0, 5, 14)
        g.addEdge(5, 2, 2)
        g.addEdge(5, 4, 9)
        g.addEdge(0, 2, -9)
        g.addEdge(4, 3, 6)
        g.addEdge(2, 3, 11)
        g.addEdge(3, 1, -4)
        g.addEdge(2, 3, 11)
        g.addEdge(1, 0, 7)
        val dist = arrayListOf(14, 7, 0, 11, 37, 28)
        val paths = arrayListOf(
                arrayListOf(2, 3, 1, 0),
                arrayListOf(2, 3, 1),
                arrayListOf(),
                arrayListOf(2, 3),
                arrayListOf(2, 3, 1, 0, 5, 4),
                arrayListOf(2, 3, 1, 0, 5)
        )
        val result = g.fordBellman(2)
        assertEquals(dist, result.first!!.toList())
        assertEquals(paths, result.second)
    }

    @Test
    fun testUnusedVertex() {
        val g = Graph(6)
        g.addEdge(0, 1, -3)
        g.addEdge(0, 0, 8)
        g.addEdge(1, 2, 1)
        g.addEdge(0, 2, 1)
        g.addEdge(2, 3, -2)
        g.addEdge(2, 0, 2)
        g.addEdge(3, 5, 10)
        val dist = arrayListOf(0, -3, -2, -4, Int.MAX_VALUE, 6)
        val paths = arrayListOf(
                arrayListOf(),
                arrayListOf(0, 1),
                arrayListOf(0, 1, 2),
                arrayListOf(0, 1, 2, 3),
                arrayListOf(Int.MAX_VALUE),
                arrayListOf(0, 1, 2, 3, 5)
        )
        val result = g.fordBellman(0)
        assertEquals(dist, result.first!!.toList())
        assertEquals(paths, result.second)
    }

    @Test
    fun test5() {
        val g = Graph(5)
        g.addEdge(0, 3, 2)
        g.addEdge(0, 1, 7)
        g.addEdge(4, 1, 9)
        g.addEdge(1, 2, -2)
        g.addEdge(2, 1, 5)
        g.addEdge(4, 1, -3)
        g.addEdge(2, 4, 8)
        g.addEdge(3, 2, 6)
        g.addEdge(3, 4, 7)
        g.addEdge(2, 0, -4)
        val dist = arrayListOf(-2, 4, 2, 0, 7)
        val paths = arrayListOf(
                arrayListOf(3, 4, 1, 2, 0),
                arrayListOf(3, 4, 1),
                arrayListOf(3, 4, 1, 2),
                arrayListOf(),
                arrayListOf(3, 4)
        )
        val result = g.fordBellman(3)
        assertEquals(dist, result.first!!.toList())
        assertEquals(paths, result.second)
    }

    @Test
    fun testAnotherNegativeCycle() {
        val g = Graph(5)
        g.addEdge(0, 3, 2)
        g.addEdge(0, 1, 5)
        g.addEdge(4, 1, 9)
        g.addEdge(1, 2, -2)
        g.addEdge(2, 1, 5)
        g.addEdge(4, 1, -3)
        g.addEdge(2, 4, 8)
        g.addEdge(3, 2, 6)
        g.addEdge(3, 4, 7)
        g.addEdge(2, 0, -4)
        val paths = arrayListOf(
                arrayListOf(2, 0, 1)
        )
        assertEquals((Pair(null, paths)), g.fordBellman(3))
    }
}