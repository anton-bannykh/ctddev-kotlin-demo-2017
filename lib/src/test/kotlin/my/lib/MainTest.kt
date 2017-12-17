package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun test1() {
        val n = 4
        val edges = Array(5, { Edge(0, 0, 0) })

        edges[0] = Edge(0, 1, 1)
        edges[1] = Edge(0, 2, 2)
        edges[2] = Edge(2, 1, 1)
        edges[3] = Edge(1, 3, 2)
        edges[4] = Edge(2, 3, 1)

        val expectedResult = 3

        assertEquals(expectedResult, solve(n, edges))
    }

    @Test
    fun test2() {
        val n = 5
        val edges = Array(7, { Edge(0, 0, 0) })

        edges[0] = Edge(0, 1, 1)
        edges[1] = Edge(1, 4, 5)
        edges[2] = Edge(0, 2, 6)
        edges[3] = Edge(2, 3, 2)
        edges[4] = Edge(3, 4, 1)
        edges[5] = Edge(2, 1, 3)
        edges[6] = Edge(1, 3, 1)

        val expectedResult = 6

        assertEquals(expectedResult, solve(n, edges))
    }

    @Test
    fun test3() {
        val n = 4
        val edges = Array(5, { Edge(0, 0, 0) })

        edges[0] = Edge(0, 1, 1000000000)
        edges[1] = Edge(0, 2, 1000000000)
        edges[2] = Edge(1, 2, 1)
        edges[3] = Edge(2, 3, 1000000000)
        edges[4] = Edge(1, 3, 1000000000)

        val expectedResult = 2000000000

        assertEquals(expectedResult, solve(n, edges))
    }

    @Test
    fun testEmptyGraph() {
        val n = 100
        val edges = Array(0, { Edge(0, 0, 0) })

        val expectedResult = 0

        assertEquals(expectedResult, solve(n, edges))
    }

    @Test
    fun testSimpleGraph() {
        val n = 2
        val edges = Array(1, { Edge(0, 1, 5) })

        val expectedResult = 5

        assertEquals(expectedResult, solve(n, edges))
    }

    @Test
    fun testGraphNoWay() {
        val n = 5
        val edges = Array(5, { Edge(0, 0, 0) })

        edges[0] = Edge(0, 1, 2)
        edges[1] = Edge(0, 2, 4)
        edges[2] = Edge(1, 2, 7)
        edges[3] = Edge(3, 4, 7)
        edges[4] = Edge(4, 3, 8)

        val expectedResult = 0

        assertEquals(expectedResult, solve(n, edges))
    }

    @Test
    fun testBigGraph() {
        val n = 100
        val m = 10000
        val edges = Array(m, { Edge(0, 0, 0) })

        for (i in 0 until m) {
            edges[i] = Edge(i / n, i % n, i * i % 12345)
        }

        val expectedResult = 897810

        assertEquals(expectedResult, solve(n, edges))
    }
}