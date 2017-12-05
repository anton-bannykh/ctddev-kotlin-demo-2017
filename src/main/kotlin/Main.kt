import java.util.Scanner

fun main(args: Array<String>) {
    val scn = Scanner(System.`in`)
    val n = scn.nextInt() // size of array
    val q = scn.nextInt() // number of queries
    val fenwickTree = createFenwickTree(n)
    for (i in 0 until q) {
        val type = scn.nextInt()
        if (type == 0) {
            val l = scn.nextInt()
            val r = scn.nextInt()
            val x = scn.nextInt()
            updateAtSegment(l, r, x, fenwickTree)
        } else {
            val index = scn.nextInt()
            val x = scn.nextInt()
            updateAtPoint(index, x, fenwickTree)
        }
    }
    for (i in 0 until q) {
        System.out.println(getElement(i, fenwickTree))
    }
}

fun createFenwickTree(n: Int): Array<Int> {
    return Array<Int>(n, { 0 })
}

fun updateAtPoint(index: Int, value: Int, fw: Array<Int>) {
    updateAtSegment(index, index, value, fw)
}

fun update(index: Int, value: Int, fw: Array<Int>) {
    var currentIndex = index
    while (currentIndex < fw.size) {
        fw[currentIndex] += value
        currentIndex = (currentIndex.or(currentIndex + 1))
    }
}

fun updateAtSegment(leftBorder: Int, rightBorder: Int, value: Int, fw: Array<Int>) {
    update(leftBorder, value, fw)
    if (rightBorder + 1 < fw.size) {
        update(rightBorder + 1, -value, fw)
    }
}

// Getting sum from [0..r]
fun getSum(r: Int, fw: Array<Int>): Int {
    var currentIndex = r
    var result = 0
    while (currentIndex >= 0) {
        result += fw[currentIndex]
        currentIndex = currentIndex.and(currentIndex + 1) - 1
    }
    return result
}

fun getElement(position: Int, fw: Array<Int>): Int {
    return getSum(position, fw)
}
