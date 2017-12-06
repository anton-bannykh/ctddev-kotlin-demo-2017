import org.junit.Assert.assertEquals
import org.junit.Test
import segmenttree.RSQ
import java.util.Random

class MainTest {
    @Test
    fun testSingle() {
        val rsq = RSQ(Array<Int>(1, { i -> 1 }))
        assertEquals(1, rsq.findAnswer(0, 0))
        rsq.add(0, 0, 5)
        assertEquals(6, rsq.findAnswer(0, 0))
    }

    @Test
    fun testRandom() {
        val rand = Random()
        val a = Array<Int>(rand.nextInt(100) + 1, { i -> rand.nextInt(100 ) })
        /*for (x in a.indices) {
            println("a[$x] = ${a[x]}")
        }*/
        val rsq = RSQ(a)
        val q = Array<Int>(rand.nextInt(100) + 1, { i -> rand.nextInt(2) })
        val l = Array<Int>(q.size, { i -> rand.nextInt(a.size) })
        val r = Array<Int>(q.size, { i -> rand.nextInt(a.size) })
        val value = Array<Int>(q.size, { i -> rand.nextInt(100) })
        /*for (x in q.indices) {
            println("$x. ${q[x]} ${l[x]} ${r[x]} ${value[x]}")
        }*/
        for (i in q.indices) {
            var left = l[i]
            var right = r[i]
            if (right < left) {
                left = right.also { right = left }
            }
            if (q[i] == 0) {
                var res: Int = 0
                for (j in left..right) {
                    res += a[j]
                }
                assertEquals(res, rsq.findAnswer(left, right))
            } else if (q[i] == 1) {
                for (j in left..right) {
                    a[j] += value[i]
                }
                rsq.add(left, right, value[i])
            }
        }
    }

    @Test
    fun testsRandom() {
        for (i in 0..100) {
            testRandom()
        }
    }
}