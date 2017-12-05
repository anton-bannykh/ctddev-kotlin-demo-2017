import org.junit.Assert
import org.junit.Test
import java.util.*

class TreapTest {

    fun getArray(x: Array<Treap>): Array<Int> {
        val ans = Array(x.size, { 0 })
        for (i in 0 until x.size) {
            ans[getKey(x[i])-1] = x[i].value
        }
        return ans
    }

    // Hard to make good tests
    fun testBase() {
        val n = 10000
        val simpleArray = Array(n, { Random().nextInt() })
        val decartTree = Array(n, { Treap() })
        for (i in 0 until simpleArray.size) {
            decartTree[i].value = simpleArray[i]
        }
        for (i in 0 until simpleArray.size - 1) {
            merge(getRoot(decartTree[i]), getRoot(decartTree[i + 1]))
        }
        Assert.assertArrayEquals(simpleArray, getArray(decartTree))
        split(getRoot(decartTree[0]), getKey(decartTree[1999]))
        split(getRoot(decartTree[3000]), getKey(decartTree[4000]))
        merge(getRoot(decartTree[2010]), getRoot(decartTree[0]))
        merge(getRoot(decartTree[0]), getRoot(decartTree[4010]))
        val ans = getArray(decartTree)
        val dumbInsert = Array(n, { 0 })
        for(i in 2000..4000) {
            dumbInsert[i-2000] = simpleArray[i]
        }
        for(i in 0..1999) {
            dumbInsert[2001+i] = simpleArray[i]
        }
        for(i in 4001..simpleArray.size-1) {
            dumbInsert[i] = simpleArray[i]
        }
        Assert.assertArrayEquals(dumbInsert, ans)
    }

    @Test
    fun test1() {
        testBase()
    }

    @Test
    fun test2() {
        testBase()
    }

    @Test
    fun test3() {
        testBase()
    }

    @Test
    fun test4() {
        testBase()
    }
}