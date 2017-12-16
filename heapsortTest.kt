import java.util.*
import org.junit.Assert.*
import org.junit.Test

class heapsortTest {
    @Test
    fun test1() {
        var ex = arrayOf(2, 0, 1, 1, 3, 2)
        var pat = ex.clone()
        heapsort(ex)
        pat.sort()
        assertTrue(Arrays.equals(pat, ex))
    }
    @Test
    fun test2() {
        var ex = arrayOf(5, 0, 6, 32, 16, 1)
        var pat = ex.clone()
        heapsort(ex)
        pat.sort()
        assertTrue(Arrays.equals(pat, ex))
    }
    @Test
    fun test3() {
        var ex = arrayOf(3, 2, 1, 0, 1, 5, 4, 5, 6, 7, 8)
        var pat = ex.clone()
        heapsort(ex)
        pat.sort()
        assertTrue(Arrays.equals(pat, ex))
    }
    @Test
    fun test4() {
        var ex = arrayOf(-6535, 6534, -89, 0, 0, 11)
        var pat = ex.clone()
        heapsort(ex)
        pat.sort()
        assertTrue(Arrays.equals(pat, ex))
    }
}