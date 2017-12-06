package math

fun log2(n: Int): Int {
    var num: Int = 1
    var res: Int = 0
    while (num < n) {
        num *= 2
        res++
    }
    return res
}

fun pow2(n: Int) = 1.shl(n)