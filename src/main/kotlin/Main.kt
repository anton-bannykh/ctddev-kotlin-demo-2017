fun binSearch(a: IntArray, l: Int, r: Int, x: Int): Int {
    val m = (l + r) / 2
    if (x == a[m]) {
        return m
    }
    return if (a[m] >= x) {
        binSearch(a, l, m, x)
    } else {
        binSearch(a, m + 1, r, x)
    }
}