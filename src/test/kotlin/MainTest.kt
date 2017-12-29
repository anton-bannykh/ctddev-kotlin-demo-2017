import org.junit.Test
import org.junit.Assert.assertEquals

class MainTest {

    @Test
    fun test1() {
        var edges: ArrayList<Pair<Int, Int>> = ArrayList()
        val result = arrayListOf(1, 2, 3, 5, 4)
        edges.add(Pair(1, 2))
        edges.add(Pair(2, 3))
        edges.add(Pair(4, 2))
        edges.add(Pair(1, 5))
        edges.add(Pair(3, 5))
        assertEquals(result, dfs(5, 5, edges, 1) )

    }

    @Test
    fun test2() {
        var edges: ArrayList<Pair<Int, Int>> = ArrayList()
        var result = arrayListOf(3, 2, 5, 1, 4)
        edges.add(Pair(1, 5))
        edges.add(Pair(5, 2))
        edges.add(Pair(3, 2))
        edges.add(Pair(4, 1))
        assertEquals(result, dfs(5, 4, edges, 3))
    }

    @Test
    fun test0() {
        var edges: ArrayList<Pair<Int, Int>> = ArrayList()
        var result: ArrayList<Int> = arrayListOf(1)
        assertEquals(result, dfs(1, 0, edges, 1))
    }

}
