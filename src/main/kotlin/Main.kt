fun max( a: Int, b: Int ) = if (a > b) a else b

fun LCS(x: String?, y: String?): Int {
    if (x == null || y == null) {
        return 0
    } else {
        val mas: MutableList<Int> = mutableListOf(0)

        for (i in 1..y.length) {
            mas.add(0)
        }

        var lcs: MutableList<MutableList<Int>> = mutableListOf(mas)

        for (i in 1..x.length) {
            lcs.add(mas)
        }

        for (i in 1..x.length - 1) {
            for (j in 1..y.length - 1) {
                if (x[i] == y[j]) {
                    lcs[i][j] = lcs[i - 1][j - 1] + 1
                } else {
                    lcs[i][j] = max(lcs[i - 1][j], lcs[i][j - 1])
                }
            }
        }

        return lcs[x.length - 1][y.length - 1]
    }
}