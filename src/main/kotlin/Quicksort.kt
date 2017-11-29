import java.util.*

/**
 * Created by Александр on 29.11.2017.
 */

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) +  start

fun partition(ms: IntArray, l: Int, r: Int): Int {
    val k = (l..r + 1).random()
    ms[r] = ms[k].also {ms[k] = ms[r]}
    val max = ms[r]
    var ind = l - 1
    for (i in l..r - 1) {
        if(ms[i] < max) {
            ind++
            ms[i] = ms[ind].also {ms[ind ] = ms[i]}
        }
    }
    ms[r] = ms[ind + 1].also {ms[ind + 1] = ms[r]}
    return ind + 1
}

fun Quicksort(ms: IntArray, l: Int = 0, r: Int = ms.size - 1) {
    if (l >= r) return
    val mid = partition(ms, l, r)
    Quicksort(ms, l, mid - 1)
    Quicksort(ms, mid + 1, r)

}
