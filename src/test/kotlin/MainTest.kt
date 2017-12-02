import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Assert.assertNull
import org.junit.Assert.fail
import org.junit.Test

class MainTest {
    fun checkAnswer(edges: Array<ArrayList<Int>>, answer: ArrayList<Int>?, m: Int) {
        if (answer == null) {
            fail()
            return
        }

        assertEquals(answer[0], answer[answer.size - 1])
        assertEquals(answer.size, m + 1)

        for (i in 1..answer.size - 1) {
            assertTrue(edges[answer[i - 1]].contains(answer[i]))
            edges[answer[i - 1]].remove(answer[i])
        }
    }

    @Test
    fun test1() {
        val n = 3
        val m = 3
        val edges = Array(n, { ArrayList<Int>() })

        edges[0] = arrayListOf(1)
        edges[1] = arrayListOf(2)
        edges[2] = arrayListOf(0)

        checkAnswer(edges, solve(edges, n), m)
    }

    @Test
    fun test2() {
        val n = 6
        val m = 11
        val edges = Array(n, { ArrayList<Int>() })

        edges[0] = arrayListOf(1)
        edges[1] = arrayListOf(2, 3)
        edges[2] = arrayListOf(4, 5)
        edges[3] = arrayListOf(2, 5)
        edges[4] = arrayListOf(0, 3)
        edges[5] = arrayListOf(1, 4)

        checkAnswer(edges, solve(edges, n), m)
    }

    @Test
    fun test3() {
        val n = 2
        val edges = Array(n, { ArrayList<Int>() })

        edges[0] = arrayListOf(1)

        assertNull(solve(edges, n))
    }

    @Test
    fun test4() {
        val n = 6
        val edges = Array(n, { ArrayList<Int>() })

        edges[0] = arrayListOf(1)
        edges[1] = arrayListOf(2)
        edges[2] = arrayListOf(0)

        edges[3] = arrayListOf(4)
        edges[4] = arrayListOf(5)
        edges[5] = arrayListOf(3)

        assertNull(solve(edges, n))
    }

    @Test
    fun test5() {
        val n = 10
        val m = 10
        val edges = Array(n, { ArrayList<Int>() })

        edges[0] = arrayListOf(3, 4)
        edges[1] = arrayListOf(0, 4)
        edges[2] = arrayListOf(0, 1)
        edges[3] = arrayListOf(1, 2)
        edges[4] = arrayListOf(2, 3)

        checkAnswer(edges, solve(edges, n), m)
    }
}