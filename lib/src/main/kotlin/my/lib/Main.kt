import java.util.Random

val random = Random()

fun <T> partition(array: ArrayList<T>, leftBorder: Int, rightBorder: Int, predicate: (T) -> Boolean): Int {
    var left = leftBorder
    var right = rightBorder - 1
    while (left < right) {
        while (left < right && predicate(array[left]))
            left++
        while (left < right && !predicate(array[right]))
            right--
        if (left < right) {
            val temp = array[left]
            array[left] = array[right]
            array[right] = temp
        }
    }
    return left
}

fun <T : Comparable<T>> quickSort(array: ArrayList<T>, leftBorder: Int = 0, rightBorder: Int = array.size) {
    if (leftBorder + 1 >= rightBorder)
        return
    val pivotIndex = leftBorder + random.nextInt(rightBorder - leftBorder)
    val pivot = array[pivotIndex]
    val leftSeparator = partition(array, leftBorder, rightBorder, { x -> x < pivot })
    val rightSeparator = partition(array, leftSeparator, rightBorder, { x -> x <= pivot })
    quickSort(array, leftBorder, leftSeparator)
    quickSort(array, rightSeparator, rightBorder)
}
