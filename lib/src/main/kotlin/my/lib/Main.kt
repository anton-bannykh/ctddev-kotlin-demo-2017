package my.lib

fun mergeSort(a : IntArray, l : Int = 0, r : Int = a.size) {
    if (r - l < 2)
        return
    val m = (l + r) / 2
    mergeSort(a, l, m)
    mergeSort(a, m, r)
    merge(a, l, m, r)
}

fun merge(a : IntArray, l : Int, m : Int, r : Int) {
    val tmp = IntArray(r - l)
    var it1 = l
    var it2 = m
    var dst = 0
    while (dst < tmp.size && it1 < m && it2 < r) {
        tmp[dst++] = if (a[it1] < a[it2]) a[it1++] else a[it2++]
    }
    if (dst == tmp.size) return
    val range = if (it1 == m) IntRange(it2, r - 1) else IntRange(it1, m - 1)
    for (i in range) {
        tmp[dst++] = a[i]
    }
    for (i in l until r) {
        a[i] = tmp[i - l]
    }
}