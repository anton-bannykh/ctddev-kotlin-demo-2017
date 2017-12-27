import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun test23() {
        assertEquals(true, isPrime(23))
    }
    @Test
    fun test133() {
        assertEquals(false, isPrime(133))
    }
    @Test
    fun test7() {
        assertEquals(true, isPrime(7))
    }

}
