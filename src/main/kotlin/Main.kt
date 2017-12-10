fun logarithm(x: Int): Int {
    if (x == 1) {
        return 0
    } else {
        return logarithm(x / 2) + 1
    }
}

fun power(aa: Int, nn: Int): Int {
    var a = aa
    var n = nn
    var res = 1
    while (n > 0) {
        if (n % 2 == 1) {
            res *= a
        }
        a *= a
        n /= 2
    }
    return res
}

fun solve(n: Int, mas: IntArray, ll: Int, rr: Int): Int {
    val logarithms = IntArray(n + 1, { i -> 0 })
    for (i in 1..n) {
        logarithms[i] = logarithm(i)
    }
    val powers = IntArray(logarithms[n] + 1, { i -> (power(2, i)) })
    val sparseTable = Array<IntArray>(logarithms[n] + 1, { i -> IntArray(n - powers[i] + 1, { j -> 0 }) })
    for (i in 0..sparseTable[0].size - 1) {
        sparseTable[0][i] = mas[i]
    }
    for (i in 1..sparseTable.size - 1) {
        for (j in 0..sparseTable[i].size - 1) {
            sparseTable[i][j] = Math.min(sparseTable[i - 1][j], sparseTable[i - 1][j + powers[i - 1]])
        }
    }

    var left = ll
    var right = rr
    if (left > right) {
        val p = left
        left = right
        right = p
    }
    val k = logarithms[right - left]
    return Math.min(sparseTable[k][left - 1], sparseTable[k][right - powers[k]])
}

fun main(args: Array<String>) {

}
