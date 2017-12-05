import org.junit.Assert
import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class MainTest {
    var n = 0
    @Test
    fun testCiclic() {
        n = 5;
        val (color, graph, topol) = triple()
        for(i in 0..n-1){
            graph[i].add((i+1)%n)
        }
        assertEquals(false, topsort(color,graph,topol))
    }

    @Test
    fun testSimple(){
        n = 4
        val (color, graph, topol) = triple()
        graph[0].add(3)
        graph[3].add(1)
        graph[3].add(2)
        graph[2].add(1)
        val answer = arrayOf(1,4,3,2)
        topsort(color,graph, topol)
        Assert.assertArrayEquals(answer, topol)
    }

    @Test
    fun testChain(){
        n = 4
        val (color, graph, topol) = triple()
        for(i in 0..n-2)
            graph[i].add(i+1)
        val answer = arrayOf(1,2,3,4)
        topsort(color,graph, topol)
        Assert.assertArrayEquals(answer,topol)
    }

    private fun triple(): Triple<Array<Int>, Array<ArrayList<Int>>, Array<Int>> {
        val color = Array(n, { i -> 0 })
        val graph = Array<ArrayList<Int>>(n, { i -> ArrayList() })
        val topol = Array<Int>(n, { 0 })
        return Triple(color, graph, topol)
    }
}