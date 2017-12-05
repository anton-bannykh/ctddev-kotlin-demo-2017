var n: Int = 0
var array = Array<Int>(1, { 0 })

fun quickSort(left: Int, right: Int) {
    var l = left
    var r = right
    var save: Int
    var midel: Int = array[(l + r) / 2]
    while (l <= r) {
        while (array[l] < midel)
            l++
        while (array[r] > midel)
            r--
        if (l <= r) {
            save = array[l]
            array[l] = array[r]
            array[r] = save
            l++
            r--
        }
    }
    if (left < r)
        quickSort(left, r)
    if (l < right)
        quickSort(l, right)
}

fun create(count: Int, arr: Array<Int>) {
    n = count
    array = arr
}