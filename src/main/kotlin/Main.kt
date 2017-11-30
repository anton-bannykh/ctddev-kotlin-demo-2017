import java.util.Random

/**
 * Created by Александр on 29.11.2017.
 */
fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start

fun Quicksort(ms: IntArray, l: Int = 0, r: Int = ms.size - 1) {
    if (l >= r) return
    val k = (l..r + 1).random()
    ms[r] = ms[k].also { ms[k] = ms[r] }
    val max = ms[r]
    var mid = l - 1
    for (i in l..r - 1) {
        if (ms[i] < max) {
            mid++
            ms[i] = ms[mid].also { ms[mid] = ms[i] }
        }
    }
    ms[r] = ms[mid + 1].also { ms[mid + 1] = ms[r] }
    mid++
    Quicksort(ms, l, mid - 1)
    Quicksort(ms, mid + 1, r)

}
