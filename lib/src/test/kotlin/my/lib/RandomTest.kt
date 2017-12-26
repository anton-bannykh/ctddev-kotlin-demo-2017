package my.lib

import java.util.*

class RandomTest {
    private val rand = Random()
    private val max = 50

    private fun getToken(a: Int, n: Int) = if (a > n) "!" + "x" + (a - n).toString() else "x" + a.toString()

    private fun createVariable(a: Int, b: Int, n: Int) = "(" + getToken(a, n) + " || " + getToken(b, n) + ")"

    fun getRandom(n: Int): String {
        val m = rand.nextInt(max)
        val sb = StringBuilder()
        for (i in 0 until m) {
            sb.append(createVariable(rand.nextInt(2 * n) + 1, rand.nextInt(2 * n) + 1, n) + if (i < m - 1) " && " else "")
        }
        return sb.toString()
    }
}