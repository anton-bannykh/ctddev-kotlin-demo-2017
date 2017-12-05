import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testBase() {
        val graph = Array(4){ arrayListOf<Int>()}
        val used = Array(4, { false })
        val parent = Array(4,  { 0 })
        val size = Array(4, { 0 })
            graph[1] = arrayListOf(2)
            graph[2] = arrayListOf(1, 3)
            graph[3].add(2)
        build(1, 0, size, graph, used, parent)
        assertEquals(arrayOf(2, 0, 2), ans(3, parent))
    }

   @Test
    fun testSecond() {
       val graph = Array(10){ arrayListOf<Int>()}
       val used = Array(10, { false })
       val parent = Array(10,  { 0 })
       val size = Array(10, { 0 })
       graph[1] = arrayListOf(2, 5, 6)
       graph[2] = arrayListOf(3, 4, 1)
       graph[3] = arrayListOf(2)
       graph[4] = arrayListOf(2)
       graph[5] = arrayListOf(1)
       graph[6] = arrayListOf(1, 7, 8)
       graph[7] = arrayListOf(6)
       graph[8] = arrayListOf(6, 9)
       graph[9] = arrayListOf(8)
       build(1, 0, size, graph, used, parent)
       assertEquals(arrayOf(0, 1, 2, 2, 1, 1, 6, 6, 8), ans(9, parent))
    }
}