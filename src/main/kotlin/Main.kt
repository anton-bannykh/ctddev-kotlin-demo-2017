import java.util.Random

fun heapSort(a: Array<Int>) {
    buildHeap(a)
    var n = a.size
    while (n - 1 > 0) {
        a[n - 1] = a[0].also { a[0] = a[n - 1] }
        siftDown(a, 0, n - 1)
        n--
    }
}

fun buildHeap(a: Array<Int>) {
    for (i in a.size / 2 downTo 0) {
        siftDown(a, i, a.size)
    }
}

fun siftDown(a: Array<Int>, index: Int, size: Int) {
    var i = index
    while (i * 2 + 1 < size) {
        var child = 2 * i + 1
        if (child + 1 < size && a[child] < a[child + 1]) {
            child++
        }
        if (a[i] < a[child]) {
            a[child] = a[i].also { a[i] = a[child] }
            i = child
        } else {
            return
        }
    }
}

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive + 1 - start) + start