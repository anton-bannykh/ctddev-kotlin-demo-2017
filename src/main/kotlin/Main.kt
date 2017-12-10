import java.lang.Math.sqrt
import java.util.Random
import java.lang.Math.min
import java.lang.Math.exp

private val maxOperations = 1e6.toInt()
private var numberOperations = 0
private var b = intArrayOf()
private var distB = 0.0

private fun sqr(d: Double) = d * d
private fun distance(x: Pair<Double, Double>, y: Pair<Double, Double>) =
        sqrt(sqr(x.first - y.first) + sqr(x.second - y.second))
private fun mod(x: Int, y: Int) = (x + y) % y

private fun calculateDistance(array: Array<Pair<Double, Double>>, a: IntArray) =
        a.indices.sumByDouble { distance(array[a[it]], array[a[(it + 1) % a.size]]) }

private fun changeDistance(array: Array<Pair<Double, Double>>, a: IntArray, x: Int, y: Int) : Double {
    var p = -distance(array[a[x]], array[a[mod(x - 1, a.size)]]) -
            distance(array[a[x]], array[a[mod(x + 1, a.size)]]) -
            distance(array[a[y]], array[a[mod(y - 1, a.size)]]) -
            distance(array[a[y]], array[a[mod(y + 1, a.size)]])

    var z = a[x]
    a[x] = a[y]
    a[y] = z
    p += distance(array[a[x]], array[a[mod(x - 1, a.size)]]) +
            distance(array[a[x]], array[a[mod(x + 1, a.size)]]) +
            distance(array[a[y]], array[a[mod(y - 1, a.size)]]) +
            distance(array[a[y]], array[a[mod(y + 1, a.size)]])
    z = a[x]
    a[x] = a[y]
    a[y] = z
    return p
}

private fun simulatedAnnealing(array: Array<Pair<Double, Double>>, a: IntArray, random: Random) : Double {
    var dist = calculateDistance(array, a)
    numberOperations += a.size
    var t = 1e9
    val dt = min(0.9, random.nextDouble())

    while (t > 0.0001) {
        val x = random.nextInt(a.size)
        val y = random.nextInt(a.size)

        val ndist = dist + changeDistance(array, a, x, y)
        val p = exp((dist - ndist) / t)
        if (p > random.nextDouble()) {
            dist = ndist
            val z = a[x]
            a[x] = a[y]
            a[y] = z
        }

        t *= dt
        numberOperations++

        if (dist < distB) {
            distB = dist
            b = a.clone()
        }

    }
    return dist
}

fun calculateTSP(array: Array<Pair<Double, Double>>) : Pair<Double, IntArray> {
    numberOperations = 0

    val a = IntArray(array.size, { i -> i })

    b = a.clone()
    distB = calculateDistance(array, b)
    val random = Random()

    var e = 0

    while (numberOperations < maxOperations) {
        simulatedAnnealing(array, a, random)
        e++
    }

    return Pair(distB, b)
}
