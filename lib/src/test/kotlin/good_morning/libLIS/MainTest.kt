package good_morning.libLIS

import org.junit.Test
import java.util.Random

class CommonLISTest {

    private fun randomShuffle(array: IntArray) {
        val random = Random()
        for (ind in array.indices) {
            val ind0 = random.nextInt(array.size - ind) + ind
            array[ind] = array[ind0].also { array[ind0] = array[ind] }
        }
    }

    @Test
    fun testDirect() {
        val array = intArrayOf(1, 2, 3, 4, 5)
        val result = LIS(array)
        assert(cmpArrays(intArrayOf(1, 2, 3, 4, 5), result))
    }

    @Test
    fun test() {
        val array = intArrayOf(9, 3, 10, 4, 8, 1, 2, 12, 6, 5, 7, 11)
        val result = LIS(array)
        assert(result.size == 5)
        check(array, result)
    }

    @Test
    fun massiveTimeTest() {
        val array = IntArray(1e7.toInt(), {a->a})
        randomShuffle(array)
        check(array, LIS(array))
    }

    private fun check(source: IntArray, a: IntArray) {
        for (i in 1..a.size-1) {assert(a[i-1] < a[i])}
        var pointer = 0
        for (i in source) {
            assert(pointer < a.size)
            if (i == a[pointer]) pointer += 1
        }
        assert(pointer == a.size)
    }

    private fun cmpArrays(a: IntArray, b: IntArray): Boolean =
            if (a.size == b.size) iCmpArrays(a, b) else false

    private fun iCmpArrays(a: IntArray, b: IntArray): Boolean {
        var result = true
        a.forEachIndexed{ ind, _-> if (a[ind] != b[ind]) result = false }
        return result
    }
}