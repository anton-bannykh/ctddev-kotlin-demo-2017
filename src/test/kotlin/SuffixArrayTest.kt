import org.junit.Assert
import org.junit.Test

class SuffixArrayTest {

    @Test
    fun testOne() {
        Assert.assertArrayEquals(suffixArray("ababb"), intArrayOf(0, 2, 4, 1, 3))
    }

    @Test
    fun testTwo() {
        Assert.assertArrayEquals(suffixArray("banana"), intArrayOf(5, 3, 1, 0, 4, 2))
    }

    @Test
    fun testThree() {
        Assert.assertArrayEquals(suffixArray("abracadabra"), intArrayOf(10, 7, 0, 3, 5, 8, 1, 4, 6, 9, 2))
    }

    @Test
    fun testFour() {
        Assert.assertArrayEquals(suffixArray("abaab"), intArrayOf(2, 3, 0, 4, 1))
    }

    @Test
    fun testFive() {
        Assert.assertArrayEquals(suffixArray("abacaba"), intArrayOf(6, 4, 0, 2, 5, 1, 3))
    }

    @Test
    fun testSix() {
        Assert.assertArrayEquals(suffixArray("mississippi"), intArrayOf(10, 7, 4, 1, 0, 9, 8, 6, 3, 5, 2))
    }

    @Test
    fun testSimilar() {
        Assert.assertArrayEquals(suffixArray("aaaaaaaaaa"), intArrayOf(9, 8, 7, 6, 5, 4, 3, 2, 1, 0))
    }

    @Test
    fun testDifferent() {
        Assert.assertArrayEquals(suffixArray("abcdefghijklmnopqrstuvwxyz"),
                intArrayOf(0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25))
    }

    @Test
    fun longTest() {
        Assert.assertArrayEquals(suffixArray("abcdefghijklmnopqrstuvwxyzabcdefghijklmnopqrstuvwxyz"),
                intArrayOf(26, 0, 27, 1, 28, 2, 29, 3, 30, 4, 31, 5, 32, 6, 33, 7, 34, 8, 35, 9, 36, 10, 37, 11, 38, 12,
                        39, 13, 40, 14, 41, 15, 42, 16, 43, 17, 44, 18, 45, 19, 46, 20, 47, 21, 48, 22, 49, 23, 50, 24, 51, 25))
    }

}
