package my.lib

import java.lang.Math.abs
import java.lang.Math.sqrt

class Point(var x: Double, var y: Double, var id: Int) {
    override fun toString() = "point #$id: ($x, $y)"
}

fun dist(a : Point, b : Point) = sqrt(Math.pow(a.x - b.x, 2.0) + Math.pow(a.y - b.y, 2.0))

fun smallestDist(points: ArrayList<Point>) : Pair<Point, Point> {
    var ansLen = Double.POSITIVE_INFINITY
    lateinit var ans: Pair<Point, Point>

    fun updAns(a: Point, b: Point) {
        val dist = dist(a, b)
        if (dist < ansLen) {
            ansLen = dist
            ans = Pair(a, b)
        }
    }

    fun rec(l: Int, r: Int, points: ArrayList<Point>) {
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
                    updAns(points[i], temp[j])
                    j--
                }
                temp[tsz++] = points[i]
            }
        }
    }

    @Suppress("NAME_SHADOWING")
    val points = ArrayList(points)
    points.sortWith(compareBy({ it.x }, { it.y }))
    rec(0, points.size - 1, points)
    return Pair(ans.first, ans.second)
}