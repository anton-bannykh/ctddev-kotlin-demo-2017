package my.lib

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test
import java.util.Arrays
import java.util.Random

class MainTest {
    fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start
    @Test
    fun test1() {
        val ms = intArrayOf(0, 1, 2, 3, 5, 4)
        val ans = ms.clone()
        sort_(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }

    @Test
    fun test2() {
        val ms = intArrayOf(1, 4, 2, 5, 6, 4, -4, -43, 313, 242, 32, 42, 2, 4, 42)
        val ans = ms.clone()
        sort_(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }

    @Test
    fun test3() {
        val ms = intArrayOf(8, 7, 6, 5, 4, 3, 2, 1, -555)
        val ans = ms.clone()
        sort_(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }

    @Test
    fun test4() {
        val ms = intArrayOf(28, 53, -24, 3552, 69426, -535)
        val ans = ms.clone()
        sort_(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }

    @Test
    fun test5() {
        val ms = intArrayOf(1, 1, 1, 1, 1, 1, 1, 1, 0)
        val ans = ms.clone()
        sort_(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }

    @Test
    fun test6() {
        val ms = IntArray(10000, { (-100000..100000).random() })
        val ans = ms.clone()
        sort_(ms)
        ans.sort()
        assertTrue(Arrays.equals(ans, ms))
    }
}