import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testSearch0() {
        assertEquals(5, search("56", "123456789", 101))
    }
    @Test
    fun testSearch1() {
        assertEquals(4, search("4", "123456789", 101))
    }
}