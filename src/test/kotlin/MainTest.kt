import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {

    @Test
    fun test1_1() {
        val n = 3
        val m = 3
        val ex = mutableListOf(1, 2, 1, 3, 2, 3)
        val myAns = mutableListOf(1, 2, 3)
        val bfsAns = bfs(n, m, ex, 0)
        assertEquals (myAns, bfsAns)
    }

    @Test
    fun test1_2() {
        val n = 5
        val m = 5
        val ex = mutableListOf(1, 5, 4, 2, 3, 2, 5, 3, 5, 4)
        val myAns = mutableListOf(1, 5, 3, 4, 2)
        val bfsAns = bfs(n, m, ex, 0)
        assertEquals (myAns, bfsAns)
    }

    @Test
    fun test1_3() {
        val n = 6
        val m = 6
        val ex = mutableListOf(3, 2, 4, 2, 1, 3, 3, 3, 1, 2, 1, 4)
        val myAns = mutableListOf(1, 3, 2, 4)
        val bfsAns = bfs(n, m, ex, 0)
        assertEquals (myAns, bfsAns)
    }

    @Test
    fun test1_4() {
        val n = 6
        val m = 8
        val ex = mutableListOf(3, 1, 1, 2, 1, 3, 1, 4, 4, 4, 4, 5, 4, 6, 4, 3)
        val myAns = mutableListOf(1, 3, 2, 4, 5, 6)
        val bfsAns = bfs(n, m, ex, 0)
        assertEquals (myAns, bfsAns)
    }

    @Test
    fun test1_5() {
        val n = 8
        val m = 9
        val ex = mutableListOf(5, 4, 3, 4, 4, 2, 7, 5, 6, 1, 6, 6, 3, 1, 2, 4, 4, 5)
        val myAns = mutableListOf(1, 6, 3, 4, 5, 2, 7)
        val bfsAns = bfs(n, m, ex, 0)
        assertEquals (myAns, bfsAns)
    }
}
