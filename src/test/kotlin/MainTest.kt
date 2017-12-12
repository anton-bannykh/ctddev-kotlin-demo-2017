import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Random

class MainTest {

    @Test
    fun testNoUpdates() {
        val fw = createFenwickTree(10)
        for (i in 0 until fw.size) {
            assertEquals(getElement(i, fw), 0)
        }
    }

    @Test
    fun testPointUpdates() {
        val n = 12345
        val r = Random()
        var currentArray = Array<Int>(n, { 0 })
        val fw = createFenwickTree(n)
        val q = Math.abs(r.nextInt()) % 1000 + 10000
        for (i in 0..q) {
            val index = Math.abs(r.nextInt()) % currentArray.size
            val value = Math.abs(r.nextInt()) % 1000
            currentArray[index] += value
            updateAtPoint(index, value, fw)
        }
        for (i in 0 until fw.size) {
            assertEquals(currentArray[i], getElement(i, fw))
        }
    }

    @Test
    fun testAllUpdates() {
        val n = 123
        val r = Random()
        var currentArray = Array<Int>(n, { 0 })
        val fw = createFenwickTree(n)
        val q = Math.abs(r.nextInt()) % 1000 + 10000
        for (i in 0..q) {
            val index = Math.abs(r.nextInt()) % currentArray.size
            val value = Math.abs(r.nextInt()) % 1000
            currentArray[index] += value
            updateAtPoint(index, value, fw)
        }
        val qq = r.nextInt()
        for (i in 0..qq) {
            var l = Math.abs(r.nextInt()) % fw.size
            var rr = Math.abs(r.nextInt()) % fw.size
            if (l > rr) {
                val tmp = l
                l = rr
                rr = tmp
            }
            val value = Math.abs(r.nextInt()) % 1000
            updateAtSegment(l, rr, value, fw)
            for (j in l..rr) {
                currentArray[j] += value
            }
        }
        for (i in 0 until fw.size) {
            assertEquals(currentArray[i], getElement(i, fw))
        }
    }
}
