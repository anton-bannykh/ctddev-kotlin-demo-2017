package my.lib.splay

import hw1.splay.splaySetOf
import junit.framework.Assert.assertEquals
import junit.framework.Assert.assertTrue
import org.junit.Test
import java.util.Random

class SplaySetTest {
    private val n = 1000000
    private val random = Random()

    private fun <K : Comparable<K>> putAndCheck(vararg ks: K) {
        val splaySet = splaySetOf(*ks)
        val mutableSet = mutableSetOf(*ks)

        assertEquals(mutableSet.size, splaySet.size)

        for (number in ks) {
            assertEquals(
                    number in mutableSet,
                    number in splaySet
            )
        }
    }

    @Test
    fun sequenceIntsAddTest() =
            putAndCheck(*(1..n).toList().toTypedArray())

    @Test
    fun sequenceWithRepeatsTest() =
            putAndCheck(
                    *((1..n / 2).toList().toTypedArray()
                            + (1 .. n / 2).toList().toTypedArray()))

    @Test
    fun randomIntsAddTest() =
            putAndCheck(*Array(n) { random.nextInt() })

    @Test
    fun randomAddRemoveTest() {
        val mutableSet = mutableSetOf<Int>()
        val splaySet = splaySetOf<Int>()

        for (i in 1..n) {
            when (random.nextInt(2)) {
                0 -> {
                    val value = random.nextInt()

                    assertEquals(
                            splaySet.add(value),
                            mutableSet.add(value)
                    )
                }

                1 -> {
                    if (mutableSet.size > 1) {
                        assertEquals(
                                splaySet.remove(mutableSet.first()),
                                mutableSet.remove(mutableSet.first()))
                    }
                }

                2 -> {
                    for (element in mutableSet) {
                        assertTrue(element in splaySet)
                    }
                }
            }
        }
    }
}