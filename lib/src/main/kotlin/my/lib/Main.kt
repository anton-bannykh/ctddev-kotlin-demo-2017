package my.lib

fun binSearch(a: ArrayList<Int>, l: Int, r: Int, x: Int): Int {
    val m = (l + r) / 2
    if (x == a[m]) {
        return m + 1
    }
    if(l == r)
        return a.size+1
    return if (a[m] >= x) {
        binSearch(a, l, m, x)
    } else {
        binSearch(a, m + 1, r, x)
    }
}