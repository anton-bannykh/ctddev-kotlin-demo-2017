import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
//    @Test

    var s = arrayOf(11, 1, 0, 1, 2, 0, 1, 3, 0, 1, 4, 1, 4, 5, 1, 8, 6, 1, 16, 7, 2, 32, 8, 3, 64, 9, 3, 128, 10, 5, 512)
    var l = Math.log(11.toDouble()).toInt()
    var builded = Build_graph(s)

    fun test4_6() {
        assertEquals(20, LCA(4, 6, l))
    }

    @Test
    fun test4_0() {
        assertEquals(5, LCA(4, 0, l))
    }

    @Test
    fun test4_9() {
        assertEquals(134, LCA(4, 9, l))
    }

    @Test
    fun test10_6() {
        assertEquals(536, LCA(10, 6, l))
    }

    @Test
    fun test0_0() {
        assertEquals(0, LCA(0, 0, l))
    }

    @Test
    fun test6_8() {
        assertEquals(82, LCA(6, 8, l))
    }
}