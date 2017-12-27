import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Random

class MainTest {
    private fun runTest(string: String, check: Boolean = true) {
        val answer = buildSufArray(string)
        if (check) {
            val real_answer = easyBuildSufArray(string)
            assertEquals(answer, real_answer)
        }
    }

    private fun generate(n: Int, minCode: Int, maxCode: Int): String {
        var str = ""
        for (i in 0 until n) {
            val code = Random().nextInt(maxCode + 1 - minCode) + minCode
            str += code.toChar()
        }
        return str
    }

    @Test
    fun testSmall() {
        val string = "abacabacaaabzd"
        runTest(string)
    }

    @Test
    fun testEmpty() {
        val string = ""
        runTest(string)
    }

    @Test
    fun testAaaa() {
        val string = "aaaaaaaaaaa"
        runTest(string)
    }

    @Test
    fun testSpecialCharacters() {
        val string = " a as as \n 123124 12e@#%@!@%Q^^&@U*CEJI!(#*@RUHFwi aesdf      {{{{{}}}__---=-"
        runTest(string)
    }

    @Test
    fun testBigRandom() {
        for (n in 10..20000 step 5000) {
            runTest(generate(n, 0, 255))
        }
    }

    @Test
    fun testBigRandomWithoutCheck() {
        for (n in 10..200000 step 50000) {
            runTest(generate(n, 0, 255), check = false)
        }
    }
}