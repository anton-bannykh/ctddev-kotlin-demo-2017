import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testFoo() {
        assertEquals(10, foo())
    }

    @Test
    fun testSumEmpty() {
        assertEquals(0, sum())
    }

    @Test
    fun testSumSingle() {
        assertEquals(42, sum(42))
    }

    @Test
    fun testSumMany() {
        assertEquals(6, sum(1, 2, 3))
    }

    @Test
    fun testSumFunEmpty() {
        assertEquals(0, sumFun())
    }

    @Test
    fun testSumFunSingle() {
        assertEquals(42, sumFun(42))
    }

    @Test
    fun testSumFunMany() {
        assertEquals(6, sumFun(1, 2, 3))
    }

    @Test
    fun testEmptyGraph() {
        assertEquals(0, findBridges(0))
    }

    @Test
    fun testOnlyVertexex() {
        assertEquals(0, findBridges(11))
    }

    @Test
    fun testOneEdge() {
        assertEquals(1, findBridges(5, Pair(1, 4)))
    }

    @Test
    fun testHard() {
        assertEquals(6, findBridges(16, Pair(1, 2), Pair(3, 4), Pair(3, 7), Pair(7, 8), Pair(5, 9), Pair(5, 10),
                Pair(9, 10), Pair(10, 11), Pair(11, 12), Pair(11, 15), Pair(12, 16), Pair(15, 16), Pair(9, 14), Pair(10, 14), Pair(13, 14)))
    }

}