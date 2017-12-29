import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {

    @Test
    fun test1() {
        var n = 4
        viz = ArrayList<Boolean>()
        ans = ArrayList<Int>()
        graph = ArrayList<ArrayList<Int>>()
        for (i in (0..n)) {
            viz.add(true)
            graph.add(ArrayList())
        }
        graph[1].add(2)
        graph[2].add(3)
        graph[3].add(4)
        val otvet = arrayListOf<Int>(1, 2, 3, 4)
        dfs(1, graph, viz, ans)
        ans.reverse()
        assertEquals(otvet, ans)
    }

    @Test
    fun test2() {
        var n = 7
        viz = ArrayList<Boolean>()
        ans = ArrayList<Int>()
        graph = ArrayList<ArrayList<Int>>()
        for (i in (0..n)) {
            viz.add(true)
            graph.add(ArrayList())
        }
        graph[1].add(2)
        graph[1].add(3)
        graph[1].add(4)
        graph[2].add(5)
        graph[3].add(6)
        graph[4].add(7)
        graph[6].add(5)
        graph[7].add(6)
        val otvet = arrayListOf<Int>(1, 4, 7, 3, 6, 2, 5)
        dfs(1, graph, viz, ans)
        ans.reverse()
        assertEquals(otvet, ans)
    }

    @Test
    fun test3() {
        var n = 3
        viz = ArrayList<Boolean>()
        ans = ArrayList<Int>()
        graph = ArrayList<ArrayList<Int>>()
        for (i in (0..n)) {
            viz.add(true)
            graph.add(ArrayList())
        }
        graph[1].add(2)
        graph[1].add(3)
        val otvet = arrayListOf<Int>(1, 3, 2)
        dfs(1, graph, viz, ans)
        ans.reverse()
        assertEquals(otvet, ans)

    }

    @Test
    fun test4() {
        var n = 3
        viz = ArrayList<Boolean>()
        ans = ArrayList<Int>()
        graph = ArrayList<ArrayList<Int>>()
        for (i in (0..n)) {
            viz.add(true)
            graph.add(ArrayList())
        }
        graph[1].add(2)
        graph[2].add(3)
        graph[1].add(2)
        val otvet = arrayListOf<Int>(1, 2, 3)
        dfs(1, graph, viz, ans)
        ans.reverse()
        assertEquals(otvet, ans)
    }
}