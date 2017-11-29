import java.util.*

/**
 * Created by Александр on 29.11.2017.
 */

fun print(ms: IntArray) {
    for (i in ms) {
        print("$i ")
    }
    print('\n')
}

fun main(args: Array<String>) {
    test1()
    test2()
    test3()
    test4()
    test5()
}

fun test1() {
    val ms = intArrayOf(1, 4, 2, 3, 1, 8765, 876875, 876, 76786, 97678, 876876, -99999999)
    val ans = ms.clone()
    Quicksort(ms)
    ans.sort()
    assert(Arrays.equals(ans, ms))
}

fun test2() {
    val ms = intArrayOf(0, 1, 2, 3, 4, 5)
    val ans = ms.clone()
    Quicksort(ms)
    ans.sort()
    assert(Arrays.equals(ans, ms))
}

fun test3() {
    val ms = intArrayOf(100, 67, 32, 10, 0, -100)
    val ans = ms.clone()
    Quicksort(ms)
    ans.sort()
    assert(Arrays.equals(ans, ms))
}

fun test4() {
    val ms = IntArray(100, { (-10000..10000).random() })
    val ans = ms.clone()
    Quicksort(ms)
    ans.sort()
    assert(Arrays.equals(ans, ms))
}

fun test5() {
    val ms = IntArray(10000, { (-100000..100000).random() })
    val ans = ms.clone()
    Quicksort(ms)
    ans.sort()
    assert(Arrays.equals(ans, ms))
}