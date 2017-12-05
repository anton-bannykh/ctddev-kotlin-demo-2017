fun main(args: Array<String>) {

}

fun solve(n: Int, a: Array<Int>): Int {
    var d = Array(n + 1, { _ -> Int.MAX_VALUE })
    d[0] = Int.MIN_VALUE
    for (i in 0..n - 1) {
        var j = d.binarySearch(a[i])
        if (j < 0) {
            j = -j - 1
        }
        if (d[j - 1] < a[i] && a[i] < d[j]) {
            d[j] = a[i]
        }
    }

    var ans = n
    for (i in 0..n) {
        if (d[i] == Int.MAX_VALUE) {
            ans = i - 1
            break
        }
    }
    return ans
}
