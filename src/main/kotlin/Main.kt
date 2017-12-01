fun kth(array: IntArray, k: Int): Int {
    checkInput(array, k)
    return kth(array, 0, array.size, k)
}

private fun checkInput(array: IntArray, k: Int) {
    when {
        array.isEmpty() -> throw IllegalArgumentException("Empty array")
        k <= 0 -> throw IllegalArgumentException("Non-positive 'k' to find order statistic")
        k > array.size -> throw IllegalArgumentException("Size of array smaller then 'k' to find order statistic")
    }
}

private fun kth(array: IntArray, begin: Int, end: Int, k: Int): Int {
    var pivotIndex = getPivotIndex(array, begin, end)
    pivotIndex = partition(array, begin, end, pivotIndex)

    val idx = pivotIndex - begin + 1

    return when {
        k == idx -> array[pivotIndex]
        k < idx -> kth(array, begin, pivotIndex, k)
        else -> kth(array, pivotIndex + 1, end, k - idx)
    }
}

private fun getPivotIndex(array: IntArray, begin: Int, end: Int): Int {
    if (begin == end - 1)
        return begin

    val groupsCount = (end - begin + 4) / 5

    for (i in 0 until groupsCount) {
        val startIndex = begin + 5 * i
        val endIndex = minOf(begin + 5 * (i + 1), end)
        array.sort(startIndex, endIndex)
        array.swap(begin + i, (endIndex + startIndex) / 2)
    }

    return getPivotIndex(array, begin, begin + groupsCount)
}

private fun partition(array: IntArray, begin: Int, end: Int, pivotIndex: Int): Int {
    val pivot = array[pivotIndex]
    var splitIndex = begin

    array.swap(pivotIndex, end - 1)

    (begin until end)
            .filter { array[it] < pivot }
            .forEach { array.swap(splitIndex++, it) }

    array.swap(splitIndex, end - 1)
    return splitIndex
}

private fun IntArray.swap(i: Int, j: Int) {
    val t = this[i]
    this[i] = this[j]
    this[j] = t
}
