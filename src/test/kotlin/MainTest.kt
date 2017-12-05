import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Random
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.ArrayList

class MainTest {
    @Test
    fun testCountSets() {
        var count = 0
        for (num in 0..1000) {
            makeSet(num)
            count += 1
            assertEquals(countElement, count)
            assertEquals(parent.size, count)
            assertEquals(rank.size, count)
        }
        clear()
        count = 0
        assertEquals(countElement, count)
        assertEquals(parent.size, count)
        assertEquals(rank.size, count)
    }

    @Test
    fun testFindAfterMake1() {
        var array = ArrayList<Int>()
        for (num in 0..10) {
            array.add(num)
        }
        ranmodShuffle(array)
        for (it in array) {
            makeSet(it)
        }
        for (num1 in 0..10) {
            for (num2 in 0..10) {
                assertEquals(num1 == num2,
                        findLead(num1) == findLead(num2))
            }
        }
        clear()
    }

    @Test
    fun testFindAfterMake2() {
        var array = ArrayList<Int>()
        for (num in 0..100) {
            array.add(num)
        }
        ranmodShuffle(array)
        for (it in array) {
            makeSet(it)
        }
        for (num1 in 0..100) {
            for (num2 in 0..100) {
                assertEquals(num1 == num2,
                        findLead(num1) == findLead(num2))
            }
        }
        clear()
    }

    @Test
    fun testFindAfterMake3() {
        var array = ArrayList<Int>()
        for (num in 0..1000) {
            array.add(num)
        }
        ranmodShuffle(array)
        for (it in array) {
            makeSet(it)
        }
        for (num1 in 0..1000) {
            for (num2 in 0..1000) {
                assertEquals(num1 == num2,
                        findLead(num1) == findLead(num2))
            }
        }
        clear()
    }

    @Test
    fun testAllUnion1() {
        var array = ArrayList<Int>()
        for (num in 0..1000) {
            array.add(num)
        }
        ranmodShuffle(array)
        for (num in array) {
            makeSet(num)
        }
        ranmodShuffle(array)
        for (num in array) {
            unionSet(0, num)
        }
        ranmodShuffle(array)
        val leadAll = findLead(0)
        for (v in array) {
            assertEquals(findLead(v), leadAll)
        }
        clear()
    }

    @Test
    fun testUnion() {
        var array = ArrayList<Int>()
        for (num in 0..1000) {
            array.add(num)
        }
        ranmodShuffle(array)
        for (num in array) {
            makeSet(num)
        }

        for (num1 in array)
            for (num2 in array) {
                if (num1 % 2 == num2 % 2) {
                    unionSet(num1, num2)
                }
            }

        ranmodShuffle(array)
        for (num1 in array)
            for (num2 in array) {
            assertEquals(findLead(num1) == findLead(num2), num1 % 2 == num2 % 2)
        }

        ranmodShuffle(array)

        for (num1 in array)
            for (num2 in array) {
                if (num1 % 2 == num2 % 2) {
                    unionSet(num1, num2)
                }
            }

        ranmodShuffle(array)
        for (num1 in array)
            for (num2 in array) {
                assertEquals(findLead(num1) == findLead(num2), num1 % 2 == num2 % 2)
            }
        clear()
    }
}

fun ranmodShuffle(arr: ArrayList<Int>) {
    var j: Int
    var rand: Random = ThreadLocalRandom.current()
    for ( i in arr.size - 1 downTo 0) {
        j = rand.nextInt(i + 1)
        if (i != j) {
            arr[j] = arr[i] xor arr[j]
            arr[i] = arr[i] xor arr[j]
            arr[j] = arr[i] xor arr[j]
        }
    }
}
