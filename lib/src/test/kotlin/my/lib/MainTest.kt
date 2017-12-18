package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun firstTest() {
        val automation = Automation(1, 1, "1",
                "1 1 a")
        assertEquals(-1, automation.numberOfWords())
    }

    @Test
    fun secondTest() {
        val automation = Automation(3, 5, "3",
                "1 2 a 1 2 b 2 3 a 2 3 b 2 3 c")
        assertEquals(6, automation.numberOfWords())
    }

    @Test
    fun thirdTest() {
        val automation = Automation(6, 6, "2 3 4 5",
                "1 2 a 2 3 b 3 4 a 3 5 b 4 6 b 6 5 a")
        assertEquals(5, automation.numberOfWords())
    }

    @Test
    fun fourthTest() {
        val automation = Automation(2, 1, "",
                "1 2 a")
        assertEquals(0, automation.numberOfWords())
    }

    @Test
    fun fifthTest() {
        val automation = Automation(11, 13, "4 6 11 7",
                "1 2 a 2 4 b 2 5 c 2 3 a 4 8 a 8 9 b 9 11 a 5 7 d 3 7 a 3 6 b 6 7 b 7 10 e 10 9 f")
        assertEquals(9, automation.numberOfWords())
    }
}