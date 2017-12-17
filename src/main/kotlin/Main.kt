private fun make_ans(i: Int, j: Int, weight: Array<Int>, ans: ArrayList<Int>, d: Array<Array<Int>>) {
    if (d[i][j] == 0) {
        return
    }
    if (d[i - 1][j] == d[i][j]) {
        make_ans(i - 1, j, weight, ans, d)
    } else {
        make_ans(i - 1, j - weight[i], weight, ans, d)
        ans.add(i)
    }
}

fun solve(n: Int, W: Int, weight2: Array<Int>, price2: Array<Int>): ArrayList<Int> {
    val price = arrayOf(0).plus(price2.plus(Array(1000 - n, { 0 })))
    val weight = arrayOf(0).plus(weight2.plus(Array(1000 - n, { 0 })))
    val d = Array(1001, { Array(1001, { 0 }) })
    val ans = ArrayList<Int>()

    for (i in 1..n) {
        for (j in 1..W) {
            if (j >= weight[i]) {
                d[i][j] = Math.max(d[i - 1][j],
                        d[i - 1][j - weight[i]] + price[i])
            } else {
                d[i][j] = d[i - 1][j]
            }
        }
    }

    make_ans(n, W, weight, ans, d)
    ans.sort()
    return ans

}
