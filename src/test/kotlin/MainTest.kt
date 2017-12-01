import org.junit.Assert.assertArrayEquals
import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun test1() {
        val graph = Array<ArrayList<Pair<Int, Int>>>(5, {i -> ArrayList() })
        for (i in 1..4) {
            graph[0].add(Pair(i, 0))
        }
        graph[1].add(Pair(2, 1))
        graph[2].add(Pair(3, 3))
        graph[4].add(Pair(3, -1))
        graph[3].add(Pair(1, -2))
        graph[2].add(Pair(4, 5))
        val answer = arrayOf(
                arrayOf(0, 1, 4, 6),
                arrayOf(1, 0, 3, 5),
                arrayOf(-2, -1, 0, 4),
                arrayOf(-3, -2, -1, 0)
        )
        assertEquals(answer, johnson(graph))
    }

    @Test
    fun test2() {
        val graph = Array<ArrayList<Pair<Int, Int>>>(4, {i -> ArrayList() })
        for (i in 1..3) {
            graph[0].add(Pair(i, 0))
        }
        graph[1].add(Pair(2, -1))
        graph[2].add(Pair(3, -1))
        graph[3].add(Pair(1, -1))
        assertEquals(null, johnson(graph))
    }

    @Test
    fun test3() {
        val graph = Array<ArrayList<Pair<Int, Int>>>(3, {i -> ArrayList() })
        for (i in 1..2) {
            graph[0].add(Pair(i, 0))
        }
        graph[1].add(Pair(2, 1))
        val answer = arrayOf(
                arrayOf(0, 1),
                arrayOf(Int.MAX_VALUE, 0)
        )
        assertEquals(answer, johnson(graph))
    }

    @Test
    fun test4() {
        val graph = Array<ArrayList<Pair<Int, Int>>>(6, {i -> ArrayList() })
        for (i in 1..5) {
            graph[0].add(Pair(i, 0))
        }
        graph[1].add(Pair(2, 2))
        graph[2].add(Pair(3, 3))
        graph[3].add(Pair(4, -1))
        graph[4].add(Pair(5, 1))
        graph[5].add(Pair(3, -10))
        assertEquals(null, johnson(graph))
    }

    @Test
    fun test5() {
        val graph = Array<ArrayList<Pair<Int, Int>>>(5, {i -> ArrayList() })
        for (i in 1..4) {
            graph[0].add(Pair(i, 0))
        }
        graph[1].add(Pair(2, -1))
        graph[2].add(Pair(3, 1))
        graph[2].add(Pair(4, 10))
        graph[3].add(Pair(4, 1))
        val answer = arrayOf(
                arrayOf(0, -1, 0, 1),
                arrayOf(Int.MAX_VALUE, 0, 1, 2),
                arrayOf(Int.MAX_VALUE, Int.MAX_VALUE, 0, 1),
                arrayOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE,0)
        )
        assertEquals(answer, johnson(graph))
    }

    @Test
    fun test6() {
        val graph = Array<ArrayList<Pair<Int, Int>>>(8, {i -> ArrayList() })
        for (i in 1..7) {
            graph[0].add(Pair(i, 0))
        }
        graph[1].add(Pair(2, -1))
        graph[1].add(Pair(3, 1))
        graph[2].add(Pair(4, -10))
        graph[3].add(Pair(6, 10))
        graph[4].add(Pair(5, 0))
        graph[5].add(Pair(6, 0))
        graph[6].add(Pair(7, 0))
        graph[7].add(Pair(4, 0))
        val answer = arrayOf(
                arrayOf(0, -1, 1, -11, -11, -11, -11),
                arrayOf(Int.MAX_VALUE, 0, Int.MAX_VALUE, -10, -10, -10, -10),
                arrayOf(Int.MAX_VALUE, Int.MAX_VALUE, 0, 10, 10, 10, 10),
                arrayOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE, 0, 0, 0, 0),
                arrayOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE, 0, 0, 0, 0),
                arrayOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE, 0, 0, 0, 0),
                arrayOf(Int.MAX_VALUE, Int.MAX_VALUE, Int.MAX_VALUE, 0, 0, 0, 0)
        )
        assertEquals(answer, johnson(graph))
    }

    @Test
    fun test7() {
        val graph = Array<ArrayList<Pair<Int, Int>>>(8, {i -> ArrayList() })
        for (i in 1..7) {
            graph[0].add(Pair(i, 0))
        }
        graph[1].add(Pair(2, 1))
        graph[1].add(Pair(5, -1))
        graph[2].add(Pair(3, 1))
        graph[2].add(Pair(4, -1))
        graph[5].add(Pair(6, 1))
        graph[5].add(Pair(7, -1))
        graph[6].add(Pair(4, 1))
        graph[4].add(Pair(3, 1))
        graph[3].add(Pair(7,- 1))
        graph[7].add(Pair(1, 10))
        val answer = arrayOf(
                arrayOf(0, 1, 1, 0, -1, 0, -2),
                arrayOf(9, 0, 0, -1, 8, 9, -1),
                arrayOf(9, 10, 0, 9, 8, 9, -1),
                arrayOf(10, 11, 1, 0, 9, 10, 0),
                arrayOf(9, 10, 3, 2, 0, 1, -1),
                arrayOf(11, 12, 2, 1, 10, 0, 1),
                arrayOf(10, 11, 11, 10, 9, 10, 0)
        )
        assertEquals(answer, johnson(graph))
    }
}