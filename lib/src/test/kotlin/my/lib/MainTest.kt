//<<<<<<< HEAD:lib/src/test/kotlin/my/lib/MainTest.kt
package my.lib

//=======
//>>>>>>> Tests:src/test/kotlin/MainTest.kt
import org.junit.Test

class MainTest {
    @Test
    fun oneComp() {
        val t = components(listOf(listOf(1), listOf(0, 2), listOf(0)))
        assert(t[1] == t[0] && t[1] == t[2])
    }

    @Test
    fun twoComp() {
        val t = components(listOf(listOf(1), listOf(0, 2), listOf()))
        assert(t[1] == t[0] && t[1] != t[2])
    }

    @Test
    fun threeComp() {
        val t = components(listOf(listOf(1), listOf(2), listOf()))
        assert(t[1] != t[0] && t[1] != t[2] && t[2] != t[0])
    }
}