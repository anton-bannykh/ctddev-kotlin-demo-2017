import kotlin.math.sqrt

fun main(args: Array<String>) {

}

fun pointCalculator(arrOfPoints: ArrayList<Point>, controlPoint: Point): Double {
    //n-мерное пространство
    var minDist: Double = 1000000.0
    var pointToSave: Point = controlPoint
    for (i in arrOfPoints) {
        var locDist = calcDist(controlPoint, i)
        if (locDist < minDist) {
            minDist = locDist
            pointToSave = i
        }
    }

    return minDist
}

fun printPoint(p: Point) {
    print("(")
    for (i in p.coordinates) {
        if (i == p[p.coordinates.size - 1]) {
            print("$i")
            break
        }
        print("$i, ")
    }
    println(")")
}

fun calcDist(p1: Point, p2: Point): Double {
    var dist: Double = 0.0
    for (i in p1.coordinates.indices) {
        dist += (p1[i] - p2[i]) * (p1[i] - p2[i])
    }
    dist = sqrt(dist)

    return dist
}