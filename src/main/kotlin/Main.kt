import kotlin.collections.ArrayList
import kotlin.math.max
import kotlin.math.min

fun pairsRank(array: List<Pair<Int, Int>>): ArrayList<Int> {
    var n = 0
    var shift = 0
    array.forEach {v ->
        n = max(n, max(v.first, v.second) + 1)
        shift = min(shift, min(v.first, v.second))
    }
    val mappingArray = Array(n - shift, { ArrayList<Int>() })
    for ((ind, value) in array.withIndex()) {
        mappingArray[value.second - shift].add(ind)
    }
    val tempArray = ArrayList<Int>()
    mappingArray.forEach {
        tempArray += it
        it.clear()
    }
    for (ind in tempArray) {
        mappingArray[array[ind].first - shift].add(ind)
    }
    tempArray.clear()
    mappingArray.forEach {
        tempArray += it
        it.clear()
    }
    val result = Array<Int>(array.size, { 0 })
    var curRank = 0
    for (i in 0..array.size - 1) {
        if (i > 0 && array[tempArray[i]] != array[tempArray[i - 1]]) {
            curRank++
        }
        result[tempArray[i]] = curRank
    }
    return ArrayList(result.toList())
}


fun buildSufArray(string: String): Array<Int> {
    val n = string.length + 1
    var colors = pairsRank(string.toList().map {Pair(it.toInt(), it.toInt())})
    colors.add(-1)
    var blockSize = 1
    while (blockSize <= 2 * n) {
        val pairs = Array(n, { index -> Pair(colors[index], colors[(index + blockSize) % n])})
        colors = pairsRank(pairs.toList())
        blockSize *= 2
    }
    val permetation = Array(n - 1, { 0 })
    for (index in 0..n - 2) {
        permetation[colors[index] - 1] = index
    }
    return permetation
}

fun easyBuildSufArray(string: String): Array<Int> {
    val n = string.length
    val sufs = Array(n, { i -> string.subSequence(i, n)})
    sufs.sort()
    return Array(n, { i -> n - sufs[i].length })
}
