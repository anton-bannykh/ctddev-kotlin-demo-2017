import java.io.File
import java.lang.Math.abs
import java.lang.Math.sqrt

fun List<Double>.toPoint(id: Int) = Point(this[0], this[1], id)

fun upd_ans(a: Point, b: Point) {
    val dist = sqrt(Math.pow(a.x - b.x, 2.0) + Math.pow(a.y - b.y, 2.0))
    if (dist < ansLen) {
        ansLen = dist
        ans = Pair(a, b)
    }
}

fun rec(l: Int, r: Int, points: Array<Point>) {
    if (l == r) {
        return
    }
    val m = (l + r) / 2
    val mx = points[m].x
    rec(l, m, points)
    rec(m + 1, r, points)
    val temp = ArrayList<Point>()
    var i = l
    var j = m + 1
    while (i <= m && j <= r) {
        temp.add(if (points[i].y < points[j].y) points[i++] else points[j++])
    }
    while (i <= m) {
        temp.add(points[i++])
    }
    while (j <= r) {
        temp.add(points[j++])
    }
    @Suppress("NAME_SHADOWING")
    for (i in 0..r - l) {
        points[i + l] = temp[i]
    }
    var tsz = 0
    @Suppress("NAME_SHADOWING")
    for (i in l..r) {
        if (abs(points[i].x - mx) < ansLen) {
            j = tsz - 1
            while (j >= 0 && points[i].y - ansLen < temp[j].y) {
                upd_ans(points[i], temp[j])
                j--
            }
            temp[tsz++] = points[i]
        }
    }
}

fun smallestDist(points: Array<Point>) : Double {
    ansLen = Double.POSITIVE_INFINITY
    points.sortWith(compareBy({ it.x }, { it.y }))
    rec(0, points.size - 1, points)
    return ansLen
}

var ansLen = Double.POSITIVE_INFINITY
var ans: Pair<Point, Point>? = null

fun main(args : Array<String>) {
    val reader = File("input.txt").bufferedReader()
    val writer = File("output.txt").printWriter()
    val n = reader.readLine().toInt()
    if (n <= 1) {
        println("Need at least 2 points")
        return
    }
    val points = Array(n, { i -> reader.readLine().split(' ').map(String::toDouble).toPoint(i + 1) })
    points.sortWith(compareBy({ it.x }, { it.y }))
    rec(0, n - 1, points)
    println(ans!!.first)
    println(ans!!.second)
    println("distance == $ansLen")
    reader.close()
    writer.close()
}