package my.lib
class DSU(amount: Int = 0) {
    var data: Array<Node>

    class Node(var p: Int = 0, var c: Int = 0, var min_: Int = 0, var max_: Int = 0, var am: Int = 0, var r: Int = 0)

    init {
        data = Array(amount, { i -> Node(i, i, i, i, 1, 0) })
    }

    fun getX(x: Int): Int {
        return if (data[x].p == x) {
            x
        } else {
            val ans = getX(data[x].p)
            data[x].p = ans
            ans
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