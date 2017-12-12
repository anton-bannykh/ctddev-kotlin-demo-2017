import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Random
import java.util.concurrent.ThreadLocalRandom
import kotlin.collections.ArrayList

class MainTest {
    @Test
    fun testCountSets() {
        var dsu = DsuBase();
        var count = 0
        for (num in 0..1000) {
            dsu.addNode()
            count += 1
            assertEquals(dsu.size(), count)
        }
        dsu.clear()
        count = 0
        assertEquals(dsu.size(), count)
    }

    @Test
    fun testFindAfterMake1() {
        var dsu = DsuBase(10)
        var array = ArrayList<Int>()
        for (num in 0..9) {
            array.add(num)
        }
        ranmodShuffle(array)
        for (num1 in 0..9) {
            for (num2 in 0..9) {
                assertEquals(num1 == num2,
                        dsu.commonSet(num1, num2))
            }
        }
    }

    @Test
    fun testFindAfterMake2() {
        var dsu = DsuBase(101)
        var array = ArrayList<Int>()
        for (num in 0..100) {
            array.add(num)
        }
        ranmodShuffle(array)
        for (num1 in 0..100) {
            for (num2 in 0..100) {
                assertEquals(num1 == num2,
                        dsu.commonSet(num1, num2))
            }
        }
    }

    @Test
    fun testFindAfterMake3() {
        var dsu = DsuBase(1001)
        var array = ArrayList<Int>()
        for (num in 0..1000) {
            array.add(num)
        }
        ranmodShuffle(array)
        for (num1 in 0..1000) {
            for (num2 in 0..1000) {
                assertEquals(num1 == num2,
                        dsu.commonSet(num1, num2))
            }
        }
    }

    @Test
    fun testAllUnion1() {
        var dsu = DsuBase(1001)
        var array = ArrayList<Int>()
        for (num in 0..1000) {
            array.add(num)
        }
        ranmodShuffle(array)
        for (num in array) {
            dsu.unionSet(0, num)
        }
        ranmodShuffle(array)
        for (v in array) {
            assert(dsu.commonSet(0, v))
        }
    }

    @Test
    fun testUnion() {
        var dsu = DsuBase(1001)
        var array = ArrayList<Int>()
        for (num in 0..1000) {
            array.add(num)
        }
        ranmodShuffle(array)

        for (num1 in array)
            for (num2 in array) {
                if (num1 % 2 == num2 % 2) {
                    dsu.unionSet(num1, num2)
                }
            }

        ranmodShuffle(array)
        for (num1 in array)
            for (num2 in array) {
            assertEquals(dsu.commonSet(num1, num2), num1 % 2 == num2 % 2)
        }

        ranmodShuffle(array)
        for (num1 in array)
            for (num2 in array) {
                if (num1 % 2 == num2 % 2) {
                    dsu.unionSet(num1, num2)
                }
            }

        ranmodShuffle(array)
        for (num1 in array)
            for (num2 in array) {
                assertEquals(dsu.commonSet(num1, num2), num1 % 2 == num2 % 2)
            }
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
