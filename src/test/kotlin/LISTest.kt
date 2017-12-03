import org.junit.Test

class CommonLISTest {
    @Test
    fun testDirect() {
        val array: Array<Int> = arrayOf(1, 2, 3, 4, 5)
        val result = LIS(array)
        assert(cmpArrays(intArrayOf(1, 2, 3, 4, 5).toTypedArray(), result))
    }

    @Test
    fun test() {
        val array: Array<Int> = arrayOf(9, 3, 10, 4, 8, 1, 2, 12, 6, 5, 7, 11)
        val result = LIS(array)
        assert(result.size == 5)
        check(array, result)
    }

    @Test
    fun massiveTimeTest() {
        val array = Array(1e7.toInt(), {a->a})
        randomShuffle(array)
        check(array, LIS(array))
    }

    private fun check(source: Array<Int>, a: Array<Int>) {
        for (i in 1..a.size-1) {assert(a[i-1] < a[i])}
        var pointer = 0
        for (i in source) {
            assert(pointer < a.size)
            if (i == a[pointer]) pointer += 1
        }
        assert(pointer == a.size)
    }

    private fun cmpArrays(a: Array<Int>, b: Array<Int>): Boolean =
        if (a.size == b.size) iCmpArrays(a, b) else false

    private fun iCmpArrays(a: Array<Int>, b: Array<Int>): Boolean {
        var result = true
        a.forEachIndexed{ ind, _-> if (a[ind] != b[ind]) result = false }
        return result
    }
}
