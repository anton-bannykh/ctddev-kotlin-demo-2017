import org.junit.Test
import java.util.*
import kotlin.test.assertTrue

class MainTest{
    val random = Random()

    @Test
    fun test1() {
        println("Test 1")
        val n = 3
        val m = 3
        val ex = intArrayOf(1, 2, 1, 3, 2, 3)
        val myAns = arrayListOf(1, 2, 3)
        val bfsAns = Bfs(n, m, ex,0).check()
        assertTrue {myAns == bfsAns}
    }

    @Test
    fun test2() {
        println("Test 2")
        val n = 5
        val m = 5
        val ex = intArrayOf(1, 5, 4, 2, 3, 2, 5, 3, 5, 4)
        val myAns = arrayListOf(1, 5, 3, 4, 2)
        val bfsAns = Bfs(n, m, ex,0).check()
        assertTrue {myAns == bfsAns}
    }

    @Test
    fun test3() {
        println("Test 3")
        val n = 6
        val m = 6
        val ex = intArrayOf(3, 2, 4, 2, 1, 3, 3, 3, 1, 2, 1, 4)
        val myAns = arrayListOf(1, 3, 2, 4)
        val bfsAns = Bfs(n, m, ex,0).check()
        assertTrue {myAns == bfsAns}
    }

    @Test
    fun test4() {
        println("Test 4")
        val n = 6
        val m = 8
        val ex = intArrayOf(3, 1, 1, 2, 1, 3, 1, 4, 4, 4, 4, 5, 4, 6, 4, 3)
        val myAns = arrayListOf(1, 3, 2, 4, 5, 6)
        val bfsAns = Bfs(n, m, ex,0).check()
        assertTrue {myAns == bfsAns}
    }

    @Test
    fun test5() {
        println("Test 5")
        val n = 8
        val m = 9
        val ex = intArrayOf(5, 4, 3, 4, 4, 2, 7, 5, 6, 1, 6, 6, 3, 1, 2, 4, 4, 5)
        val myAns = arrayListOf(1, 6, 3, 4, 5, 2, 7)
        val bfsAns = Bfs(n, m, ex,0).check()
        assertTrue {myAns == bfsAns}
    }

    @Test
    fun test2_1() {
        println("Test 2.1")
        var r = random.nextInt(2) + 3
        val n = r
        val m = r + 1
        val ex = IntArray(2*m, {random.nextInt(n-1) + 1})
        Bfs(n, m, ex)
    }

    @Test
    fun test2_2() {
        println("Test 2.2")
        val r = random.nextInt(7) + 3
        val n = r
        val m = r - 1
        val ex = IntArray(2*m, {random.nextInt(n-1) + 1})
        Bfs(n, m, ex)
    }

    @Test
    fun test2_3() {
        println("Test 2.3")
        val r = random.nextInt(17) + 3
        val n = r
        val m = r + 3
        val ex = IntArray(2*m, {random.nextInt(n-1) + 1})
        Bfs(n, m, ex)
    }

    @Test
    fun test2_4() {
        println("Test 2.4")
        val r = random.nextInt(27) + 3
        val n = r
        val m = r - 2
        val ex = IntArray(2*m, {random.nextInt(n-1) + 1})
        Bfs(n, m, ex)
    }

    @Test
    fun test2_5() {
        println("Test 2.5")
        val r = random.nextInt(37) + 3
        val n = r
        val m = r + 5
        val ex = IntArray(2*m, {random.nextInt(n-1) + 1})
        Bfs(n, m, ex)
    }
}
