import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testPathExample() {
        val maxn = 2000
        val g = Array(maxn, { _ -> ArrayList<Pair<Int, Int>>(0) })
        g[0] = arrayListOf(Pair(0, 0), Pair(2, 2))
        g[1] = arrayListOf(Pair(0, 3), Pair(1, 0))
        g[2] = arrayListOf(Pair(1, 4), Pair(2, 0))
        assertEquals(6, s_path(3, 1, 2, g))
    }

    @Test
    fun testPathForOneNode() {
        val maxn = 2000
        val g = Array(maxn, { _ -> ArrayList<Pair<Int, Int>>(0) })
        assertEquals(0, s_path(1, 1, 1, g))
    }

    @Test
    fun testPathForTwoNodes() {
        val maxn = 2000
        val g = Array(maxn, { _ -> ArrayList<Pair<Int, Int>>(0) })
        g[0] = arrayListOf(Pair(1, 1))
        g[1] = arrayListOf(Pair(0, 3))
        assertEquals(1, s_path(2, 1, 2, g))
        assertEquals(3, s_path(2, 2, 1, g))
    }

    @Test
    fun testPathForCycle() {
        val maxn = 2000
        val g = Array(maxn, { _ -> ArrayList<Pair<Int, Int>>(0) })
        g[0] = arrayListOf(Pair(1, 1))
        g[1] = arrayListOf(Pair(2, 2))
        g[2] = arrayListOf(Pair(3, 3))
        g[3] = arrayListOf(Pair(0, 4))
        assertEquals(1, s_path(4, 1, 2, g))
        assertEquals(2, s_path(4, 2, 3, g))
        assertEquals(3, s_path(4, 3, 4, g))
        assertEquals(4, s_path(4, 4, 1, g))
    }

    @Test
    fun testPathForCycleNotOriented() {
        val maxn = 2000
        val g = Array(maxn, { _ -> ArrayList<Pair<Int, Int>>(0) })
        g[0] = arrayListOf(Pair(1, 1), Pair(3, 4))
        g[1] = arrayListOf(Pair(2, 2), Pair(0, 1))
        g[2] = arrayListOf(Pair(3, 3), Pair(1, 2))
        g[3] = arrayListOf(Pair(0, 4), Pair(2, 3))
        assertEquals(1, s_path(4, 1, 2, g))
        assertEquals(1, s_path(4, 2, 1, g))
        assertEquals(2, s_path(4, 2, 3, g))
        assertEquals(2, s_path(4, 3, 2, g))
        assertEquals(3, s_path(4, 3, 4, g))
        assertEquals(3, s_path(4, 4, 3, g))
        assertEquals(4, s_path(4, 4, 1, g))
        assertEquals(4, s_path(4, 1, 4, g))
    }
}