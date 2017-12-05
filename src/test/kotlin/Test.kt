import org.junit.Assert.assertEquals
import org.junit.Test

class Test {
    @Test
    fun test1() {
        assertEquals(true, cbs("()"))
    }

    @Test
    fun test2() {
        assertEquals(true, cbs("(())()((()()))"))
    }

    @Test
    fun test3() {
        assertEquals(false, cbs(")"))
    }

    @Test
    fun test4() {
        assertEquals(true, cbs(""))
    }

    @Test
    fun test5() {
        assertEquals(false, cbs("(()))"))
    }

    @Test
    fun test6() {
        assertEquals(false, cbs(")()("))
    }

    @Test
    fun test7() {
        assertEquals(false, cbs("()(()"))
    }

    @Test
    fun test8() {
        assertEquals(false, cbs("(((()()()()()))()()()()()())((((((()))())))))("))
    }

    @Test
    fun test9() {
        assertEquals(true, cbs("((((((()))))))()(())()()"))
    }

    @Test
    fun test0() {
        assertEquals(true, cbs("()()()()()(()(()))"))
    }
}