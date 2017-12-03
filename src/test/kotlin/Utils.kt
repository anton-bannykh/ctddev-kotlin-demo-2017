import java.util.*

fun randomShuffle(array: Array<Int>) {
    val random = Random()
    for (ind in array.indices) {
        val ind0 = random.nextInt(array.size - ind) + ind
        array[ind] = array[ind0].also { array[ind0] = array[ind] }
    }
}

