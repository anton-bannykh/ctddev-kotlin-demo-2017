package my.lib

import java.util.Random

class Shuffler<T>() {
    val rnd = Random()
    init {
        rnd.setSeed(System.currentTimeMillis())
    }
    fun shuffle(arr: Array<T>) {
        for (i in arr.size - 1 downTo 1) {
            val i1 = rnd.nextInt(i)
            arr[i] = arr[i1].also({ arr[i1] = arr[i] })
        }
    }
    fun badShuffle(arr: Array<T>) {
        for (i in 0..arr.size - 1) {
            val i1 = rnd.nextInt(arr.size-1)
            arr[i] = arr[i1].also({ arr[i1] = arr[i] })
        }
    }
}

fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)
