import java.util.Random

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) +  start

fun Array<Int>.swap(index1: Int, index2: Int) {
    val tmp = this[index1]
    this[index1] = this[index2]
    this[index2] = tmp
}

fun partition(arr: Array<Int>, left: Int, right: Int) : Int {
    if (left != right) {
        arr.swap(left + (0..right - left).random(), right)
    }
    var lastElementValue = arr[right];
    var i = left - 1;
    for (j in left..right){
        if (arr[j] <= lastElementValue){
            i++;
            arr.swap(i, j);
        }
    }
    return i;
}

fun nth_element(array: Array<Int>, ind : Int) : Int {
    var left = 0
    var right = array.size - 1
    var index = ind - 1
    while (true) {
        var mid = partition(array, left, right)
        if (mid == index) {
            return array[mid]
        } else if (index < mid) {
            right = mid - 1
        } else {
            left = mid + 1
        }
    }
}

fun main(args: Array<String>) {
    val input = arrayOf(-50, 50, -2500)
    println(nth_element(input, 1))
}
