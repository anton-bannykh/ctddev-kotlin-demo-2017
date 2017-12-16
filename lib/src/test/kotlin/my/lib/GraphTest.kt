package my.lib

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Random

class GraphTest {

    @Test
    fun testEmpty() {
        val graph = Graph(0)
        assertTrue(graph.getScc().isEmpty())
    }

    @Test
    fun testNoEdges() {
        val graph = Graph(1000)
        val scc = graph.getScc()
        val set = mutableSetOf<Int>()
        scc.entries.forEach { set.add(it.key) }
        assertEquals(set.size, graph.n)
    }

    @Test(expected = GraphException::class)
    fun testIncorrectEdges_equalToN() {
        val graph = Graph(100)
        graph.addEdge(10, 20)
        graph.addEdge(10, 100)
        graph.addEdge(0, 1)
    }

    @Test(expected = GraphException::class)
    fun testIncorrectEdges_greaterThanN() {
        val graph = Graph(100500)
        graph.addEdge(1, 3)
        graph.addEdge(3, 5)
        val random = Random()
        for (i in 0 until 100_000) {
            graph.addEdge(random.nextInt() % graph.n, random.nextInt() % graph.n)
        }
        graph.addEdge(200500, 300600)
    }

    @Test(expected = GraphException::class)
    fun testIncorrectEdges_lessThanZero() {
        val graph = Graph(3001)
        graph.addEdge(1, 3)
        graph.addEdge(3, 5)
        val random = Random()
        for (i in 0 until 100_000) {
            graph.addEdge(random.nextInt() % graph.n, random.nextInt() % graph.n)
        }
        graph.addEdge(-100, 5)
    }

    @Test
    fun testGetScc_loop() {
        val graph = Graph( 5)
        for (i in 0 until graph.n) {
            graph.addEdge(i, (i + 1) % graph.n)
        }
        var scc = graph.getScc()
        scc.entries.forEach { assertEquals(it.value, 1) }
    }

    @Test
    fun testGetScc() {
        val graph = Graph(5)

        // first scc
        graph.addEdge(0, 1)
        graph.addEdge(1, 2)
        graph.addEdge(2, 0)

        // second and third scc
        graph.addEdge(3, 4)

        val scc = graph.getScc()

        assertEquals(scc[0], scc[1])
        assertEquals(scc[0], scc[2])
        assertEquals(scc[1], scc[2])

        assertNotEquals(scc[0], scc[3])
        assertNotEquals(scc[0], scc[4])
        assertNotEquals(scc[1], scc[3])
        assertNotEquals(scc[1], scc[4])

        assertNotEquals(scc[3], scc[4])

        // Check that amount of SCC is equal to 3
        assertEquals(scc.entries
                .stream()
                .max { a, b -> a.value.compareTo(b.value) }
                .get().value, 3)
    }

}