package my.lib

fun main(args: Array<String>) {
    println("Hello world!")
}

fun quickSort(array: ArrayList<Int>, left: Int = 0, right: Int = array.size - 1) {
    if (left >= right)
        return
    var i = left
    var j = right
    var mid = i - (i - j) / 2
    while (i < j) {
        while (i < mid && array[i] <= array[mid]) {
            i++
        }
        while (j > mid && array[mid] <= array[j]) {
            j--
        }
        if (i < j) {
            val temp = array[i]
            array[i] = array[j]
            array[j] = temp
            if (i == mid)
                mid = j
            else if (j == mid)
                mid = i
        }
    }
    quickSort(array, left, mid)
    quickSort(array, mid + 1, right)
}

fun foo() = 10

fun sum(vararg ints: Int): Int {
    var result = 0
    for (v in ints) {
        result += v
    }
    return result
}

fun sumFun(vararg ints: Int) = ints.fold(0) { acc, i -> acc + i }