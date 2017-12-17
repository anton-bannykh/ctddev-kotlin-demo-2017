<<<<<<< HEAD:src/main/kotlin/Main.kt
import kotlin.math.min
=======
package my.lib

fun main(args: Array<String>) {
    println("Hello world!")
}
>>>>>>> upstream/master:lib/src/main/kotlin/my/lib/Main.kt

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
<<<<<<< HEAD:src/main/kotlin/Main.kt
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
=======
    return result
}

fun sumFun(vararg ints: Int) = ints.fold(0) { acc, i -> acc + i }
>>>>>>> upstream/master:lib/src/main/kotlin/my/lib/Main.kt
