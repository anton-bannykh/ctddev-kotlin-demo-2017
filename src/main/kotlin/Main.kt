import kotlin.math.min

fun floyd(d: Array<Array<Int>>): Array<Array<Int>> {
    var n = d.size
    val inf = Int.MAX_VALUE
    for (i in 0 until n) {
        for (j in 0 until n) {
            if (d[i][j] == 0) {
                if (i == j) {
                    d[i][j] = 0
                } else {
                    d[i][j] = inf
                }
            }
        }
    }
    for (k in 0 until n) {
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (d[i][k] < inf && d[k][j] < inf) {
                    d[i][j] = min(d[i][j], d[i][k] + d[k][j])
                }
            }
        }
    }
    return d
}