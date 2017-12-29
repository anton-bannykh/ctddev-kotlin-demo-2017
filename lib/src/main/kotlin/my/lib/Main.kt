package my.lib

val START_TEMP = 30.0
val END_TEMP = 0.5
val ALPHA = 0.98
val STEPS = 100

fun getRand(n : Int) : Int = ((Math.random() * 100) % (n - 1)).toInt()

class Solution(size : Int) {
    var solution = IntArray(size)
    var energy = 0.0

    init {
        for (i in 0..solution.size - 1) {
            solution[i] = i
        }
        for (i in 0..solution.size - 1) {
            pickUpSoilution()
        }
        countEnergy()
    }

    fun pickUpSoilution() {
        var x = getRand(solution.size)
        var y : Int

        do {
            y = getRand(solution.size)
        } while (x == y)

        var t = solution[x]
        solution[x] = solution[y]
        solution[y] = t
    }

    fun countEnergy() {
        var errors = 0.0

        for (i in 0..solution.size - 1)
            for (j in 0..solution.size - 1) {
                val dx = Math.abs(j-i)
                val dy = Math.abs(solution[i] - solution[j])

                if (dx == dy)
                    errors++
            }

        energy = errors - solution.size
    }
}

fun copy(s : Solution) : Solution {
    var r = Solution(s.solution.size)
    r.solution = s.solution.copyOf()
    r.energy = s.energy

    return r
}

fun algo(n : Int) : Solution {
    var temp = START_TEMP

    var current = Solution(n)
    var working = Solution(n)
    var best = Solution(n)

    while (temp > END_TEMP) {

        for (i in 0..STEPS) {
            var accept = false

            working.pickUpSoilution()
            working.countEnergy()

            if (working.energy <= current.energy) {
                accept = true
            } else if (Math.exp(-(working.energy - current.energy)/temp) > Math.random()) {
                accept = true
            }

            if (accept) {
                current = copy(working)

                if (current.energy < best.energy)
                    best = copy(current)

            } else {
                working = copy(current)
            }
        }
        temp *= ALPHA
    }
    return best
}

