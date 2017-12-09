import org.junit.Assert.assertArrayEquals
import org.junit.Test
import java.util.*
import kotlin.collections.ArrayList

class MainTest {
    @Test
    fun testEasy() {
        val n = 3
        val used = BooleanArray(n + 1)
        val parent = IntArray(n + 1)
        val size = IntArray(n + 1)
        val graph = ArrayList<ArrayList<Int>>()
        for (i in 0..n) {
            graph.add(ArrayList())
        }
        graph[1].add(2)
        graph[2].add(1)
        graph[2].add(3)
        graph[3].add(2)
        val truth: IntArray = intArrayOf(2, 0, 2)
        build(graph, 1, size, used, parent, 0)
        assertArrayEquals(GiveMeOtvet(n, parent), truth)
    }

    @Test
    fun testHard() {
        val n = 9
        val used = BooleanArray(n + 1)
        val parent = IntArray(n + 1)
        val size = IntArray(n + 1)
        val graph = ArrayList<ArrayList<Int>>()
        for (i in 0..n) {
            graph.add(ArrayList())
        }
        graph[1].add(2)
        graph[1].add(5)
        graph[1].add(6)
        graph[2].add(1)
        graph[2].add(3)
        graph[2].add(4)
        graph[3].add(2)
        graph[4].add(2)
        graph[5].add(1)
        graph[6].add(1)
        graph[6].add(7)
        graph[6].add(8)
        graph[7].add(6)
        graph[8].add(6)
        graph[8].add(9)
        graph[9].add(8)
        val truth: IntArray = intArrayOf(0, 1, 2, 2, 1, 1, 6, 6, 8)
        build(graph, 1, size, used, parent, 0)
        assertArrayEquals(GiveMeOtvet(n, parent), truth)
    }
} /*
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
*/