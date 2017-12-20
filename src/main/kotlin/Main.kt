fun main(args: Array<String>): Int {
    println(calcPalindromes("abccba"))
    return 0
}
fun min(arg1 : Int, arg2 : Int) = if (arg1 > arg2) arg2 else arg1

fun calcPalindromes(str : String): Int {
    var calc_value = ArrayList<Int>()
    var new_str = ArrayList<Char>()
    for ((index, value) in str.withIndex()) {
        new_str.add('#')
        new_str.add(value)
    }
    new_str.add('#')
    var l = 0
    var r = -1
    var k: Int
    for ((index, value) in new_str.withIndex()) {
        calc_value.add(0)
        k = (if (index > r) 1 else min(calc_value[l + r - index], r - index))
        while (index + k < new_str.size && index - k >= 0 && new_str[index + k] == new_str[index - k]) {
            k++
        }

        calc_value.set(index, k)
        k--
        if (index + k > r) {
            l = index - k
            r = index + k
        }

    }
    var result = 0
    for (i in 0 .. new_str.size - 1) {
        result += if (new_str[i] == '#') (calc_value[i] - 1) / 2 else calc_value[i] / 2
    }
    return result
}