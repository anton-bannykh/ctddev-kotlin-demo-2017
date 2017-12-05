package my.lib

fun sort_(arr: IntArray) {
    var N: Int = arr.size
    var k: Int = N / 2

    while (k > 0) {
        builtHeap(arr, k, N)
        k--
    }
    do run {
        var T = arr[0]
        arr[0] = arr[N - 1]
        arr[N - 1] = T
        N--
        builtHeap(arr, 1, N)
    } while (N > 1)
}

fun builtHeap(arr: IntArray, k: Int, N: Int) {
    var k = k
    var T = arr[k - 1]
    while (k <= N / 2) {
        var j = 2 * k
        if ((j < N) && (arr[j - 1] < arr[j])) j++
        if (T >= arr[j - 1]) {
            break
        } else {
            arr[k - 1] = arr[j - 1]
            k = j
        }
    }
    arr[k - 1] = T
}

fun printArray(ms: IntArray) {
    for (it in ms) {
        print("$it ")
    }
    println()
}

fun main(args: Array<String>) {
    val ms = intArrayOf(1, 4, 2, 5, 6, 4, -4, -43, 313, 242, 32, 42, 2, 4, 42)
    sort_(ms)
    printArray(ms)
}