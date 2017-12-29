package my.lib

import org.junit.Test
import org.junit.Assert.*
import java.lang.Math

fun test(numOfTests: Long, arrSize: Int): Boolean {
    val myArr = Array(arrSize, { i -> i })
    val countArr = Array<Array<Int>>(arrSize, { Array<Int>(arrSize, { 0 }) })
    val badCountArr = Array<Array<Int>>(arrSize, { Array<Int>(arrSize, { 0 }) })
    val shuffler = Shuffler<Int>()
    for (i: Long in 1..numOfTests) {
        shuffler.shuffle(myArr)
        for (j in 0..arrSize - 1) {
            countArr[j][myArr[j]]++
            myArr[j] = j
        }
        shuffler.badShuffle(myArr)
        for (j in 0..arrSize - 1) {
            badCountArr[j][myArr[j]]++
            myArr[j] = j
        }
    }
    val average: Long = numOfTests / (arrSize - 1)
    var max: Long = 0
    var badMax: Long = 0
    for (item in countArr) {
        for (item1 in item) {
            if (item1 != 0) {
                val diff: Long = Math.abs(average - item1)
                max = if (diff > max) diff else max
            }
        }
    }
    val average2: Long = numOfTests / arrSize
    for (item in badCountArr) {
        for (item1 in item) {
            if (item1 != 0) {
                val diff: Long = Math.abs(average2 - item1)
                badMax = if (diff > badMax) diff else badMax
            }
        }
    }
    val procentage = (max.toDouble() / average) * 100
    val badProcentage = (badMax.toDouble() / average) * 100
    //println("max diff:")
    //println("good shuffle - ${procentage.format(2)}%, bad shuffle - ${badProcentage.format(2)}%")
    return procentage < badProcentage
}

class MainTest {
    @Test
    fun test100x2() {
        assertEquals(test(100, 2), true)
    }

    @Test
    fun test1000000x2() {
        assertEquals(test(1000000, 2), true)
    }

    @Test
    fun test1000000x50() {
        assertEquals(test(1000000, 50), true)
    }
}