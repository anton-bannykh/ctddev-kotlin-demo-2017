import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class MainTest {
    @Test
    fun testSolve1() {
        val input = arrayOf(
                Command(1, Pair<Int, Int>(0, 1)),
                Command(1, Pair<Int, Int>(3, 1)),
                Command(3, Pair<Int, Int>(0, 3)),
                Command(3, Pair<Int, Int>(0, 4))
        )
        val command = ArrayList<Command>(input.asList())
        val task = DynamicConnectivity(6, 4, command);
        val result = Task.solve()
        assertEquals("10", result)
    }

    @Test
    fun testSolve2() {
        val input = arrayOf(
                Command(1, Pair<Int, Int>(0, 1)),
                Command(1, Pair<Int, Int>(3, 1)),
                Command(3, Pair<Int, Int>(0, 3)),
                Command(2, Pair<Int, Int>(1, 3)),
                Command(3, Pair<Int, Int>(0, 3))
        )
        val command = ArrayList<Command>(input.asList())
        val task = DynamicConnectivity(6, 4, command);
        val result = Task.solve()
        assertEquals("10", result)
    }

}