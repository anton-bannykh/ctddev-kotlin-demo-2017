import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {

    @Test
    fun test1() {
        var n: Int = 3
        var a: MutableList<Int> = mutableListOf(1, 0, 1)
        var b: MutableList<Int> = mutableListOf(2, 0, 1)
        var c: MutableList<MutableList <Int> > = mutableListOf(a, b)
        var p: Pair<Int, Int> = Pair(0, 0)
        assertEquals(0, solve(n, c, p))

    }

    @Test
    fun test2() {
        var n: Int = 3
        var a: MutableList<Int> = mutableListOf(1, 0, 1)
        var b: MutableList<Int> = mutableListOf(2, 0, 1)
        var c: MutableList<MutableList <Int> > = mutableListOf(a, b)
        var p: Pair<Int, Int> = Pair(2, 1)
        assertEquals(2, solve(n, c, p))
    }

    @Test
    fun test3() {
        var n: Int = 3
        var a: MutableList<Int> = mutableListOf(1, 0, 1)
        var b: MutableList<Int> = mutableListOf(2, 0, 1)
        var c: MutableList<MutableList <Int> > = mutableListOf(a, b)
        var p: Pair<Int, Int> = Pair(1, 2)
        assertEquals(2, solve(n, c, p))
    }



    @Test
    fun test4() {
        var n: Int = 3
        var a: MutableList<Int> = mutableListOf(1, 0, 1)
        var b: MutableList<Int> = mutableListOf(2, 0, 1)
        var c: MutableList<MutableList <Int> > = mutableListOf(a, b)
        var p: Pair<Int, Int> = Pair(1, 0)
        assertEquals(1, solve(n, c, p))
    }


    @Test
    fun test5() {
        var n: Int = 4
        var a: MutableList<Int> = mutableListOf(1, 0, 1)
        var b: MutableList<Int> = mutableListOf(2, 0, 2)
        var bb: MutableList<Int> = mutableListOf(2, 3, 1)
        var c: MutableList<MutableList <Int> > = mutableListOf(a, b, bb)
        var p: Pair<Int, Int> = Pair(1, 3)
        assertEquals(4, solve(n, c, p))
    }

    @Test
    fun test6() {
        var n: Int = 4
        var a: MutableList<Int> = mutableListOf(1, 0, 1)
        var b: MutableList<Int> = mutableListOf(2, 0, 2)
        var bb: MutableList<Int> = mutableListOf(2, 3, 1)
        var c: MutableList<MutableList <Int> > = mutableListOf(a, b, bb)
        var p: Pair<Int, Int> = Pair(0, 3)
        assertEquals(3, solve(n, c, p))
    }

    @Test
    fun test7() {
        var n: Int = 5
        var a: MutableList<Int> = mutableListOf(1, 0, 1)
        var b: MutableList<Int> = mutableListOf(2, 0, 2)
        var bb: MutableList<Int> = mutableListOf(2, 3, 10)
        var ba: MutableList<Int> = mutableListOf(2, 4, 10)
        var c: MutableList<MutableList <Int> > = mutableListOf(a, b, bb, ba)
        var p: Pair<Int, Int> = Pair(4, 3)
        assertEquals(20, solve(n, c, p))
    }


    @Test
    fun test8() {
        var n: Int = 5
        var a: MutableList<Int> = mutableListOf(1, 0, 1)
        var b: MutableList<Int> = mutableListOf(2, 0, 2)
        var bb: MutableList<Int> = mutableListOf(2, 3, 10)
        var ba: MutableList<Int> = mutableListOf(2, 4, 10)
        var c: MutableList<MutableList <Int> > = mutableListOf(a, b, bb, ba)
        var p: Pair<Int, Int> = Pair(4, 1)
        assertEquals(13, solve(n, c, p))
    }

    @Test
    fun test9() {
        var n: Int = 6
        var a: MutableList<Int> = mutableListOf(1, 0, 1)
        var b: MutableList<Int> = mutableListOf(2, 0, 2)
        var bb: MutableList<Int> = mutableListOf(2, 3, 10)
        var ba: MutableList<Int> = mutableListOf(2, 4, 10)
        var baa: MutableList<Int> = mutableListOf(5, 4, 10)
        var c: MutableList<MutableList <Int> > = mutableListOf(a, b, bb, ba, baa)
        var p: Pair<Int, Int> = Pair(5, 1)
        assertEquals(23, solve(n, c, p))
    }

    @Test
    fun test10() {
        var n: Int = 6
        var a: MutableList<Int> = mutableListOf(1, 0, 1)
        var b: MutableList<Int> = mutableListOf(2, 0, 2)
        var bb: MutableList<Int> = mutableListOf(2, 3, 10)
        var ba: MutableList<Int> = mutableListOf(2, 4, 10)
        var baa: MutableList<Int> = mutableListOf(5, 4, 10)
        var c: MutableList<MutableList <Int> > = mutableListOf(a, b, bb, ba, baa)
        var p: Pair<Int, Int> = Pair(5, 3)
        assertEquals(30, solve(n, c, p))
    }
}