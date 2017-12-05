package test

class TestFactory {
    /*private fun getToken(a: Int, n: Int) = if (a > n) "!" + "x" + (a - n).toString() else "x" + a.toString()

    private fun createVariable(a: Int, b: Int, n: Int): String = "(" + getToken(a, n) + " || " + getToken(b, n) + ")"

    private fun getTest(func: ArrayList<Pair<Int, Int>>): String {
        val next = StringBuilder()
        val size = func[0].first
        val n = func[0].second
        next.append(size.toString() + "\n")
        for (i in 1 until n) {
            val a = func[i].first
            val b = func[i].second
            next.append(createVariable(a, b, size) + if (i < n - 1) " && " else "")
        }
        return next.toString()
    }*/

    private fun parseToken(size: Int, s: String): Int {
        return s.split("x")[1].toInt() - 1 + if (s[0] == '!') size else 0
    }

    fun getFunc(n: Int, test: String): ArrayList<Pair<Int, Int>> {
        val ans = ArrayList<Pair<Int, Int>>()
        test.replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .split("&&")
                .map { it.split("||") }
                .mapTo(ans) { Pair(parseToken(n, it[0]), parseToken(n, it[1])) }
        return ans
    }
}