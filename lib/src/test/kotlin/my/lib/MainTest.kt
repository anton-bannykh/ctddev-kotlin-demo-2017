package my.lib

import org.junit.Assert.assertEquals
import org.junit.Assert.assertNotEquals
import org.junit.Test

class MainTest {
    @Test
    fun testFind() {
        val a: MyDSU = MyDSU()
        a.init(5)
        a.union(3, 4)
        assertEquals(a.find(3), a.find(4))
    }

    @Test
    fun testFindAndNum() {
        val a: MyDSU = MyDSU()
        a.init(5)
        a.union(3, 4)
        assertEquals(4, a.find(3))
    }

    @Test
    fun testFindNotEquals() {
        val a: MyDSU = MyDSU()
        a.init(5)
        a.union(3, 4)
        assertNotEquals(a.find(1), a.find(3))
    }

    @Test
    fun testFindInOneBigSet() {
        val a: MyDSU = MyDSU()
        a.init(10)
        for (i in (1..9))
            a.union(i, i + 1)
        assertEquals(a.find(1), a.find(10))
    }

    @Test
    fun testCountSets() {
        val a: MyDSU = MyDSU()
        a.init(10)
        assertEquals(a.countSets(), 10)
    }

    @Test
    fun testFindAgain() {
        val a: MyDSU = MyDSU()
        a.init(5)
        a.union(3, 4)
        a.union(1, 2)
        a.union(2, 3)
        assertEquals(a.find(2), a.find(3))
    }

    @Test
    fun testCountSetsInSmallGroup() {
        val a: MyDSU = MyDSU()
        a.init(5)
        a.union(3, 4)
        a.union(1, 2)
        a.union(2, 3)
        assertEquals(a.countSets(), 2)
    }

    @Test
    fun testSetSize() {
        val a: MyDSU = MyDSU()
        a.init(5)
        a.union(3, 4)
        a.union(1, 2)
        a.union(2, 3)
        assertEquals(a.size(1), 4)
    }

}
