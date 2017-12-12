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

fun stringToIntArray(str: String): Array<Int> {
    val list = str.split(" ")
    val res = Array<Int>(list.size, {_ -> 0})
    var i: Int = 0
    for (x in list) {
        res[i] = x.toInt()
        i++
    }
    return res
}
