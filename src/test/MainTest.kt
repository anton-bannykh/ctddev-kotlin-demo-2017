package test

import check.Checker
import org.testng.annotations.Test
import twoSat.TwoSat
import kotlin.test.assertTrue

class MainTest {

    private fun check(test: String, n: Int): Boolean {
        return Checker()
                .checkAns(n, TwoSat()
                        .twoSat(n, test),
                        TestFactory()
                                .getFunc(n, test))
    }

    @Test
    fun first() {
        assertTrue {
            check("(x1 || x2) && (!x1 || !x2) && (x3 || x4) && (!x3 || !x4) && (x3 || !x2) && (!x3 || !x4) && (!x2 || x1)", 5)
        }
    }

    @Test
    fun second() {
        assertTrue { check("(x1 || x2) && (!x1 || !x2) && (x3 || x4) && (!x3 || !x4) && (x3 || !x2) && (!x3 || !x4) && (!x2 || x1) && (x2 || !x1)", 5) }
    }
}