import kotlin.collections.ArrayList
import kotlin.math.log2

class Point{
    var parent = 0
    var depth = 0
    var value = 0
    var sons = ArrayList<Int>()
    constructor(parent: Int, value: Int) {
        this.parent = parent
        this.value = value
    }
}
var visit = Array<Boolean>(1, {false})
var tree = Array<Point>(1, {Point(0, 0)})
var n : Int = 0
fun dfs(now: Int, depth: Int) {
    visit[now] = false
    tree[now].depth = depth
    for (i in tree[now].sons) {
        if (!visit[i]) {
            dfs(i, depth + 1)
        }
    }
}
var d = ArrayList<ArrayList<Int>>()
fun binaryRise() {
    dfs(0, 0)
    val p = (log2(n.toDouble()) + 1).toInt()
    for (i in 0..(n - 1)) {
        var tmp = ArrayList<Int>()
        d.add(tmp)
        for (j in 0..(p - 1)) {
            d[i].add(0)
        }
        d[i][0] = tree[i].parent
    }
    for (j in 1..(p - 1)) {
        for (i in 1..(n - 1)) {
            d[i][j] = d[d[i][j - 1]][j - 1]
        }
    }
}

fun LCA(a: Int, b: Int) : Int {
    var v = tree[a - 1]
    var u = tree[b - 1]
    if (v.depth > u.depth) {
        var tmp: Point = v
        v = u
        u = tmp
    }
    val p: Int = (log2(n.toDouble()) + 1).toInt()
    var i: Int = p - 1
    while (i >= 0) {
        if (u.depth - v.depth >= (1 shl i)) {
            u = tree[d[u.value][i]]
        }
        if (v.value == u.value) {
            return v.value + 1
        }
        i--
    }
    i = p - 1
    while (i >= 0) {
        if (d[v.value][i] != d[u.value][i]) {
            v = tree[d[v.value][i]]
            u = tree[d[u.value][i]]
        }
        i--
    }
    return v.parent + 1;
}

fun create_tree(newN: Int, parent: Array<Int>) {
    n = newN
    tree = Array<Point>(n, {Point(0, 0)})
    var help = Point(0, 0)
    tree[0] = help
    for (i in 1..(n-1)) {
        var tmp: Int = parent[i - 1] - 1
        var help = Point(tmp, i)
        tree[i] = help
        tree[tmp].sons.add(i)
    }
    visit = Array<Boolean>(n, {false})
    binaryRise()
}

fun main(args: Array<String>) {
    //Basic_Test
    create_tree(7, arrayOf(1, 1, 2, 2, 3, 3))
    println(LCA(2, 1))
}