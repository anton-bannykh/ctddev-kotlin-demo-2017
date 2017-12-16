package my.lib
import java.util.Scanner

/**
 * Created by Maria Popyrkina on 04.12.2017.
 */

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)
    val vers = sc.nextInt()
    val edges = sc.nextInt()

    val g = Array(vers, { IntArray(vers) })
    for (it in 1..edges) {
        val fromEdge = sc.nextInt() - 1
        val toEdge = sc.nextInt() - 1
        g[fromEdge][toEdge] += 1
    }

    val k = sc.nextLong()
    val modulo = 1000000000

    val answer = matrixPower(g, k, modulo)
    for (line in answer) {
        println(line.joinToString(" "))
    }
}

fun matrixIdentity(n: Int): Array<IntArray> {
    val answer = Array(n, { IntArray(n) })
    for (i in 0 until n) {
        answer[i][i] = 1
    }
    return answer
}

fun matrixProduct(a: Array<IntArray>, b: Array<IntArray>, modulo: Int): Array<IntArray> {
    if (a[0].size != b.size) {
        throw IllegalArgumentException("Number of columns in A should be equal to number of rows in B.")
    }
    val answer = Array(a.size, { IntArray(b[0].size) })
    for (i in 0 until a.size) {
        for (j in 0 until b[0].size) {
            for (k in 0 until b.size) {
                answer[i][j] = (answer[i][j] + a[i][k].toLong() * b[k][j]).rem(modulo).toInt()
            }
        }
    }
    return answer
}

fun matrixPower(g: Array<IntArray>, length: Long, modulo: Int): Array<IntArray> {
    val n = g.size
    var answer = matrixIdentity(n)
    var x = length
    var matrix = g
    while (x > 0) {
        if (x.and(1) == 1L) {
            answer = matrixProduct(answer, matrix, modulo)
        }
        matrix = matrixProduct(matrix, matrix, modulo)
        x /= 2
    }
    return answer
}
