package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testYes1() {
        val search = mutableListOf(Pair(0, 1), Pair(1, 2), Pair(2, 3), Pair(3, 0))
        val g = answerRequest(search, 4)
        var f: Boolean = true
        for (i in 0..g.size - 2) {
            if (!search.contains(Pair(g[i], g[i+ 1]))) f = false
        }
        assertEquals(true, f)
    }
    @Test
    fun testYes2() {
        val search = mutableListOf(Pair(0, 1), Pair(1, 2), Pair(2, 3), Pair(3, 2), Pair(3, 0), Pair(3, 1))
        val g = answerRequest(search, 4)
        var f: Boolean = true
        for (i in 0..g.size - 2) {
            if (!search.contains(Pair(g[i], g[i+ 1]))) f = false
        }
        assertEquals(true, f)
    }
    @Test
    fun testNo() {
        val g = answerRequest(mutableListOf(Pair(0, 1), Pair(1, 2), Pair(2, 3)), 4)
        var f : Boolean = false
        if (g.size == 0) { f = true }
        assertEquals(true, f)
    }
}