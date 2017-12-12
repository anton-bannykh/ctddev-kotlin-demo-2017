import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun test1() {
        assertEquals(2, minCostAssignment(
                listOf(
                        listOf(1, 2),
                        listOf(2, 1)
                )
        ))
        assertEquals(12, minCostAssignment(
                listOf(
                        listOf(3, 3, 3, 3),
                        listOf(3, 3, 3, 3),
                        listOf(3, 3, 3, 3),
                        listOf(3, 3, 3, 3)
                )
        ))
        assertEquals(12, minCostAssignment(
                listOf(
                        listOf(5, 7, 5, 6),
                        listOf(4, 3, 3, 7),
                        listOf(3, 2, 8, 4),
                        listOf(9, 1, 7, 2)
                )
        ))
    }

    @Test
    fun testNegative() {
        assertEquals(-9, minCostAssignment(
                listOf(
                        listOf(-1, -2, -3),
                        listOf(-2, -3, -1),
                        listOf(-3, -1, -2)
                )
        ))
        assertEquals(-12, minCostAssignment(
                listOf(
                        listOf(-1, 5),
                        listOf(-17, -3)
                )
        ))
    }

    @Test
    fun testEmpty() {
        assertEquals(0, minCostAssignment(listOf<List<Int>>()))
    }
}