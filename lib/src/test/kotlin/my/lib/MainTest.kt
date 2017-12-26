/*
package my.lib

import check.Checker
import junit.framework.TestCase.assertTrue

import org.junit.Test

class TestFactory {
    */
/*private fun getToken(a: Int, n: Int) = if (a > n) "!" + "x" + (a - n).toString() else "x" + a.toString()

    private fun createVariable(a: Int, b: Int, n: Int): String = "(" + getToken(a, n) + " || " + getToken(b, n) + ")"

    private fun getTest(func: ArrayList<Pair<Int, Int>>): String {
        val next = StringBuilder()
        val size = func[0].first
        val n = func[0].second
        next.append(size.toString() + "\n")
        for (i in 1 until n) {
            val a = func[i].first
            val b = func[i].second
            next.append(createVariable(a, b, size) + if (i < n - 1) " && " else "")
        }
        return next.toString()
    }*//*


    private fun parseToken(size: Int, s: String): Int {
        return s.split("x")[1].toInt() - 1 + if (s[0] == '!') size else 0
    }

    fun getFunc(n: Int, test: String): ArrayList<Pair<Int, Int>> {
        val ans = ArrayList<Pair<Int, Int>>()
        test.replace("(", "")
                .replace(")", "")
                .replace(" ", "")
                .split("&&")
                .map { it.split("||") }
                .mapTo(ans) { Pair(parseToken(n, it[0]), parseToken(n, it[1])) }
        return ans
    }
}

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

//    @Test
//    fun randTest1() {
//        val n = 6
//        val test = RandomTest().getRandom(n)
//        try {
//            assertTrue { check(test, n) }
//        } catch (e: AssertionError) {
//            e.printStackTrace()
//            System.out.println("Test " + n.toString() + '\n' + test  + " failed")
//        }
//    }
//
//    @Test
//    fun randTest2() {
//        val n = 6
//        val test = RandomTest().getRandom(n)
//        try {
//            assertTrue { check(test, n) }
//        } catch (e: AssertionError) {
//            e.printStackTrace()
//            System.out.println("Test " + n.toString() + '\n' + test  + " failed")
//        }
//    }
//
//    @Test
//    fun randTest3() {
//        val n = 6
//        val test = RandomTest().getRandom(n)
//        try {
//            assertTrue { check(test, n) }
//        } catch (e: AssertionError) {
//            e.printStackTrace()
//            System.out.println("Test " + n.toString() + '\n' + test  + " failed")
//        }
//    }
//
//    @Test
//    fun randTest4() {
//        val n = 6
//        val test = RandomTest().getRandom(n)
//        try {
//            assertTrue { check(test, n) }
//        } catch (e: AssertionError) {
//            e.printStackTrace()
//            System.out.println("Test " + n.toString() + '\n' + test  + " failed")
//        }
//    }
//
//    @Test
//    fun randTest5() {
//        val n = 6
//        val test = RandomTest().getRandom(n)
//        try {
//            assertTrue { check(test, n) }
//        } catch (e: AssertionError) {
//            e.printStackTrace()
//            System.out.println("Test " + n.toString() + '\n' + test  + " failed")
//        }
//    }
//
//    @Test
//    fun randTest6() {
//        val n = 6
//        val test = RandomTest().getRandom(n)
//        try {
//            assertTrue { check(test, n) }
//        } catch (e: AssertionError) {
//            e.printStackTrace()
//            System.out.println("Test " + n.toString() + '\n' + test  + " failed")
//        }
//    }
//
//    @Test
//    fun randTest7() {
//        val n = 6
//        val test = RandomTest().getRandom(n)
//        try {
//            assertTrue { check(test, n) }
//        } catch (e: AssertionError) {
//            e.printStackTrace()
//            System.out.println("Test " + n.toString() + '\n' + test  + " failed")
//        }
//    }
//
//    @Test
//    fun randTest8() {
//        val n = 6
//        val test = RandomTest().getRandom(n)
//        try {
//            assertTrue { check(test, n) }
//        } catch (e: AssertionError) {
//            //System.out.println(e.toString())
//            System.out.println("Test\n" + n.toString() + '\n' + test  + "\nis failed")
//        }
//    }
//
//    @Test
//    fun randTest9() {
//        val n = 6
//        val test = RandomTest().getRandom(n)
//        try {
//            assertTrue { check(test, n) }
//        } catch (e: AssertionError) {
//            e.printStackTrace()
//            System.out.println("Test " + n.toString() + '\n' + test  + " failed")
//        }
//    }


}*/
