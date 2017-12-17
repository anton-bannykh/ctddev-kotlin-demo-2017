package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {

    private fun checkAns(n: Int, W: Int, weight: IntArray, price: IntArray): ArrayList<Int> {
        val ans = ArrayList<Int>()
        var pr = 0
        val x = 1 shl (n) - 1

        for (i in 1..x) {
            var k = i
            var c = 0
            var pr2 = 0
            var v = 0
            val ans2 = ArrayList<Int>()
            while (k > 0) {
                if (k % 2 == 1 && v + weight[n - 1 - c] <= W) {
                    v += weight[n - 1 - c]
                    pr2 += price[n - 1 - c]
                    ans2.add(n - c)
                }
                k /= 2
                c++
            }
            if (pr2 > pr) {
                pr = pr2
                ans.clear()
                ans.addAll(ans2)
            }
        }
        ans.sort()
        return ans
    }

    @Test
    fun test1() {
        assertEquals(arrayListOf(1, 3, 4), solve(4, 6, intArrayOf(2, 4, 1, 2), intArrayOf(7, 2, 5, 1)))
    }

    @Test
    fun test2() {
        assertEquals(arrayListOf(1), solve(1, 1, intArrayOf(1), intArrayOf(1)))
    }

    @Test
    fun test3() {
        val v = 10
        for (n in 3..15) {
            val price = IntArray(n, { i -> i * i })
            val weight = IntArray(n, { i -> i + 1 })
            assertEquals(checkAns(n, v, weight, price), solve(n, v, weight, price))
        }
    }

    @Test
    fun test4() {
        assertEquals(arrayListOf<Int>(), solve(3, 1, intArrayOf(3, 3, 3), intArrayOf(4, 4, 4)))
    }
}