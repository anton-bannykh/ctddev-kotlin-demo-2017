/**
 * Created by ugai1 on 04.12.2017.
 */

import java.lang.Math

class DSU(var data: Array<node> = Array(0, { node() })) {
    class node(var p: Int = 0, var c: Int = 0, var min_: Int = 0, var max_: Int = 0, var am: Int = 0, var r: Int = 0)

    constructor(
            amount: Int
    ) : this() {
        data = Array(amount, { i -> node(i, i, i, i, 1, 0) })
    }

    fun getX(x: Int): Int {
        if (data[x].p == x) {
            return x
        } else {
            val ans = getX(data[x].p)
            data[x].p = ans
            return ans
        }
    }

    fun Union(x: Int, y: Int) {
        var headx = getX(x)
        var heady = getX(y)
        if (headx == heady)
            return
        if (data[headx].r < data[heady].r) {
            headx = heady.also { heady = headx }
        }
        data[heady].p = data[headx].c
        data[headx].c = data[heady].c
        data[headx].min_ = Math.min(data[headx].min_, data[heady].min_)
        data[headx].max_ = Math.max(data[headx].max_, data[heady].max_)
        data[headx].am = data[headx].am + data[heady].am
    }

    operator fun DSU.get(x: Int): Int {
        return getX(x)
    }
}

fun main(args: Array<String>) {
    val amount = 10
    val check: DSU = DSU(amount)
    check.Union(0, 2)
    check.Union(2, 4)
    for (i in 0 until amount) {
        println("the answer for $i is " + check.getX(i))
    }
}