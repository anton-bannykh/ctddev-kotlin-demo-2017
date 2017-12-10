import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testFoo() {
        assertEquals(10, foo())
    }

    @Test
    fun testSumEmpty() {
        assertEquals(0, sum())
    }

    @Test
    fun testSumSingle() {
        assertEquals(42, sum(42))
    }

    @Test
    fun testSumMany() {
        assertEquals(6, sum(1, 2, 3))
    }

    @Test
    fun testSumFunEmpty() {
        assertEquals(0, sumFun())
    }

    @Test
    fun testSumFunSingle() {
        assertEquals(42, sumFun(42))
    }

    @Test
    fun testSumFunMany() {
        assertEquals(6, sumFun(1, 2, 3))
    }

    @Test
    fun testLCA() {
        var n = 5
        var m = 2
        var batyaList: MutableList<Int> = mutableListOf(0, 0, 1, 2)
        var questionList: MutableList<Pair<Int, Int>> = mutableListOf(Pair(1, 2), Pair(3, 4))
        var correctAns: MutableList<Int> = mutableListOf<Int>(0, 0)
        assertEquals(correctAns, solve(n, m, batyaList, questionList))

        n = 9
        m = 4
        batyaList = mutableListOf(0, 1, 1, 3, 0, 5, 5, 5)
        questionList = mutableListOf(Pair(2, 3), Pair(2, 4), Pair(1, 7), Pair(6, 8))
        correctAns = mutableListOf<Int>(1, 1, 0, 5)
        assertEquals(correctAns, solve(n, m, batyaList, questionList))
    }

    @Test
    fun testLCA2() {
        val n = 5
        val m = 3
        val batyaList: MutableList<Int> = mutableListOf(0, 0, 0, 0)
        val questionList: MutableList<Pair<Int, Int>> = mutableListOf(Pair(1, 2), Pair(3, 4), Pair (0, 3))
        val correctAns: MutableList<Int> = mutableListOf<Int>(0, 0, 0)
        assertEquals(correctAns, solve(n, m, batyaList, questionList))
    }

    @Test
    fun testLCA3() {
        val n = 5
        val m = 3
        val batyaList: MutableList<Int> = mutableListOf(0, 1, 2, 3)
        val questionList: MutableList<Pair<Int, Int>> = mutableListOf(Pair(1, 2), Pair(3, 4), Pair (0, 3))
        val correctAns: MutableList<Int> = mutableListOf<Int>(1, 3, 0)
        assertEquals(correctAns, solve(n, m, batyaList, questionList))
    }
}