package splay

import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.util.*

class SplaySetTest {
    private val n = 100000
    private val random = Random()

    private fun <K : Comparable<K>> putAndCheck(vararg ks: K) {
        val splaySet = splaySetOf(*ks)

        assertEquals(n, splaySet.size)

        for (number in ks) {
            assertTrue(number in splaySet)
        }
    }

    @Test
    fun sequenceIntsAddTest() =
            putAndCheck(*Array(n) { it })

    @Test
    fun randomIntsAddTest() =
            putAndCheck(*Array(n) { random.nextInt() })

    @Test
    fun randomAddRemoveTest() {
        val arrayList = arrayListOf<Int>()
        val splaySet = splaySetOf<Int>()

        for (i in 1..n) {
            when (random.nextInt(2)) {
                0 -> {
                    val value = random.nextInt()
                    splaySet.add(value)
                    arrayList.add(value)
                }

                1 -> {
                    if (arrayList.size > 1) {
                        val index = random.nextInt(arrayList.size - 1)
                        splaySet.remove(arrayList[index])
                        arrayList.remove(arrayList[index])
                    }
                }

                2 -> {
                    for (element in arrayList) {
                        assertTrue(element in splaySet)
                    }
                }
            }
        }
    }
}