import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testEmpty() {
        assertEquals(0, LCS(null, null))
    }

    @Test
    fun testSingleNull() {
        assertEquals(0, LCS("abc", null))
    }

    @Test
    fun test1() {
        assertEquals(2, LCS("abcdef", "hdbclmn"))
    }

    @Test
    fun test2() {
        assertEquals(7, LCS("sdsssgshstmslrkrr", "edgheetmlkeeee"))
    }

    @Test
    fun test3() {
        assertEquals(1, LCS("abcdefgh", "xyzdmnop"))
    }

    @Test
    fun test4() {
        assertEquals(6, LCS("kablcdlllef", "mmmabmcmdmemfmk"))
    }
}
