package my.lib

import org.junit.Assert
import org.junit.Test

class MainTest {

    @Test
    fun testMatrixIdentity() {
        val n = 5
        val matrix = matrixIdentity(n)
        Assert.assertEquals("Size of matrix should be $n", matrix.size, n)
        for (line in matrix) {
            Assert.assertEquals("Size of each line should be $n", line.size, n)
        }
        for (line in matrix.withIndex()) {
            for (entry in line.value.withIndex()) {
                val msg = "Matrix should be identity"
                if (line.index == entry.index) {
                    Assert.assertEquals(msg, entry.value, 1)
                } else {
                    Assert.assertEquals(msg, entry.value, 0)
                }
            }
        }
    }

    @Test
    fun testMatrixProduct1() {
        val a = Array(1, { intArrayOf(5) })
        val b = Array(1, { intArrayOf(6) })
        Assert.assertArrayEquals(matrixProduct(a, b, 19), Array(1, { intArrayOf(11) }))
    }

    @Test
    fun testMatrixProduct2() {
        /**
         * | 1 1 |   | 2 2 |   | 5  5  |
         * | 2 2 | x | 3 3 | = | 10 10 |
         */
        val a = Array(2, { intArrayOf(it + 1, it + 1) })
        val b = Array(2, { intArrayOf(it + 2, it + 2) })
        val actual = arrayOf(intArrayOf(5, 5), intArrayOf(10, 10))
        Assert.assertArrayEquals(matrixProduct(a, b, Int.MAX_VALUE / 3), actual)
    }

    /*@Test(expected = IllegalArgumentException::class)
=======
    @Test(expected = IllegalArgumentException::class)
>>>>>>> Stashed changes
    fun testMatrixProduct3() {
        val a = Array(2, { intArrayOf(1, 2) })
        val b = Array(1, { intArrayOf(1) })
        matrixProduct(a, b, 123)
<<<<<<< Updated upstream
        Assert.fail("Product of matrices of incorrect sizes must throw exception");
    }*/

    @Test
    fun testMatrixPower1() {
        val a = arrayOf(intArrayOf(0, 1, 0), intArrayOf(0, 0, 1), intArrayOf(0, 0, 0))
        val square = arrayOf(intArrayOf(0, 0, 1), intArrayOf(0, 0, 0), intArrayOf(0, 0, 0))
        val cube = Array(3, { IntArray(3) })
        Assert.assertArrayEquals(matrixPower(a, 2, 135), square)
        Assert.assertArrayEquals(matrixPower(a, 3, 135), cube)
    }

    @Test
    fun testMatrixPower2() {
        val n = 12
        Assert.assertArrayEquals(matrixPower(matrixIdentity(n), 123, 123), matrixIdentity(n))
    }
}
