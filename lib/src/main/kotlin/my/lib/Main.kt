package my.lib

fun create(count: Int, arr: Array<Int>): Array<Int> {
    val n = count
    var array = arr
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
    quickSort(0, n - 1)
    return array
}