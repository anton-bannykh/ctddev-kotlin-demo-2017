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
    fun testPoints() {
        var g : Graph = Graph(4)
        g.insert(0, 1)
        g.insert(1, 2)
        g.insert(2, 0)
        g.insert(2, 3)
        var ans : ArrayList <Boolean> = points(g)
        var ans_e : ArrayList <Boolean> = ArrayList(listOf(false, false, true, false))
        assertEquals(ans, ans_e)
    }

    @Test
    fun testPoints2() {
        var g : Graph = Graph(2)
        g.insert(0, 1)
        var ans : ArrayList <Boolean> = points(g)
        var ans_e : ArrayList <Boolean> = ArrayList(listOf(false, false))
        assertEquals(ans, ans_e)
    }
}
