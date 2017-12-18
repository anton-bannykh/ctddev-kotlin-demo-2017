package my.lib

fun BinarySearch(a: IntArray, x: Int): Int {

    fun binSearchRec(x: Int, left: Int, right: Int): Int {

        if (left + 1 == right) {

            return right

        }

        val middle = (left + right) / 2

        return if (a[middle] < x) {

            binSearchRec(x, middle, right)

        } else {

            binSearchRec(x, left, middle)

        }

    }

    return binSearchRec(x, -1, a.size)

}