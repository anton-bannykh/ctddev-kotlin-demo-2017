package my.lib

import java.lang.Math.min
import java.lang.Math.pow

class RMQ {
    var a = Array<Int>(1, { 0 })
    var n: Int = 0
    var s: Int = 1000000001
    var y: Int = 0

    fun set(z: Int, v: Int) {
        a[y + z - 1] = v
        var q: Int = y + z - 1
        while (q > 0) {
            val h: Int = (q - 1) / 2
            a[h] = min(a[2 * h + 1], a[2 * h + 2])
            q = h
        }
    }

    fun minstart(u: Int, v: Int): Int {
        return mind(u - 1, v - 1)
    }

    fun mind(l: Int, r: Int, node: Int = 0, L: Int = 0, R: Int = y): Int {
        if (l <= L && R <= r) return a[node]
        if (R < l || r < L) return s
        val m: Int = (L + R) / 2
        return min(mind(l, r, 2 * node + 1, L, m), mind(l, r, 2 * node + 2, m + 1, R))
    }

    fun create_segment_tree(tmp: Int, mass: Array<Int>) {
        n = tmp
        var k: Double = 0.0
        while (pow(2.0, k) < n) k++
        y = pow(2.0, k).toInt() - 1
        val x: Int = pow(2.0, k + 1).toInt() - 1
        a = Array<Int>(x + 1, { 0 })
        for (i in y..y + n - 1)
            a[i] = mass[i - y]
        for (i in y + n..x)
            a[i] = s
        for (c in y - 1 downTo 0 step 1)
            a[c] = min(a[2 * c + 1], a[2 * c + 2])
    }
}

fun main(args: Array<String>) {
    val rmq0 = RMQ()
    rmq0.create_segment_tree(5, arrayOf(1, 2, 3, 4, 5))
    println(rmq0.minstart(1, 2))
    rmq0.set(1, 2)
    println(rmq0.minstart(1, 2))
    rmq0.set(5, -1)
    println(rmq0.minstart(3, 5))
}