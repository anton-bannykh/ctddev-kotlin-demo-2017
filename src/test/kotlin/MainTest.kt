import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Random
import kotlin.collections.ArrayList

class MainTest {
    private val random = Random()

    private fun MutableList<Int>.sum(l: Int, r: Int): Int {
        var sum = 0
        for (i in l..(r - 1)) {
            sum += this[i]
        }
        return sum
    }

    private fun randomQuery(tree: SegmentTree, list: MutableList<Int>, mode: Int) {
        val q = random.nextInt(mode)
        var a = random.nextInt(tree.size())
        var b = random.nextInt(tree.size())
        if (a > b) {
            val t = a
            a = b
            b = t
        }
        b++
        val c = random.nextInt(1000) - 1000
        when (q) {
            0 -> assertEquals(list.sum(a, b), tree.ask(a, b))
            1 -> {
                tree[a] = c
                list[a] = c
            }
            2 -> assertEquals(list, tree.toList())
        }
    }

    @Test
    fun zeros100() {
        val tree = SegmentTree(100)
        val list = ArrayList<Int>()
        for (i in 0..99) {
            list.add(0)
        }
        for (i in 1..1000) {
            randomQuery(tree, list, 3)
        }
    }

    @Test
    fun zeros10000() {
        val tree = SegmentTree(10000)
        val list = ArrayList<Int>()
        for (i in 0..9999) {
            list.add(0)
        }
        for (i in 1..1000) {
            randomQuery(tree, list, 2)
        }
    }

    @Test
    fun random100() {
        val tree = SegmentTree(100)
        val list = ArrayList<Int>()
        for (i in 0..99) {
            val t = random.nextInt(1000) - 1000
            list.add(t)
            tree[i] = t
        }
        for (i in 1..1000) {
            randomQuery(tree, list, 3)
        }
    }

    @Test
    fun random10000() {
        val tree = SegmentTree(10000)
        val list = ArrayList<Int>()
        for (i in 0..9999) {
            val t = random.nextInt(1000) - 1000
            list.add(t)
            tree[i] = t
        }
        for (i in 1..1000) {
            randomQuery(tree, list, 2)
        }
    }

}