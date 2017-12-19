package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.Random
import kotlin.collections.ArrayList

class MainTest {

    @Test
    fun testNullConstructor() {
        try {
            var x = DSU()
        } catch (x: Exception) {}
    }

    @Test
    fun testNodeConstructor() {
        var x = DSU(1)
    }

    @Test
    fun testMultipleElConstructor() {
        var x = DSU(100)
    }

    private val rand = Random()

    @Test
    fun test1() {
        val amount = Math.abs(rand.nextInt()) % 99 + 2
        val g: Array<ArrayList<Int>> = Array(amount, { ArrayList<Int>() })
        val mx: Array<Array<Int>> = Array(amount, { Array(amount, { 0 }) })
        var actual = DSU(amount)
        val E = Math.abs(rand.nextInt()) % amount * (amount / 2)
        for (i in 0 until E) {
            val f = Math.abs(rand.nextInt()) % amount
            val t = Math.abs(rand.nextInt()) % amount
            if (f != t && mx[f][t] == 0) {
                g[f].add(t)
                g[t].add(f)
                mx[f][t] = 1
                mx[t][f] = 1
                actual.Union(f, t)
            }
        }
        val q = Math.abs(rand.nextInt()) % 50 + 1
        for (i in 0..q) {
            val used = Array(amount, { false })
            fun DFS(v: Int, s: Int): Boolean {
                used[v] = true
                var res = false
                for (i in g[v]) {
                    if (i == s) {
                        return true
                    }
                    if (used[i] == false) {
                        res = DFS(i, s) or res
                    }
                }
                return res
            }

            val x = Math.abs(rand.nextInt()) % amount
            val y = Math.abs(rand.nextInt()) % amount
            assertEquals((DFS(x, y) || (x == y)), (actual.getX(x) == actual.getX(y)))
        }
    }

    @Test
    fun test2() {
        val amount = Math.abs(rand.nextInt()) % 99 + 2
        val g: Array<ArrayList<Int>> = Array(amount, { ArrayList<Int>() })
        val mx: Array<Array<Int>> = Array(amount, { Array(amount, { 0 }) })
        var actual = DSU(amount)
        val E = Math.abs(rand.nextInt()) % amount * (amount / 2)
        for (i in 0 until E) {
            val f = Math.abs(rand.nextInt()) % amount
            val t = Math.abs(rand.nextInt()) % amount
            if (f != t && mx[f][t] == 0) {
                g[f].add(t)
                g[t].add(f)
                mx[f][t] = 1
                mx[t][f] = 1
                actual.Union(f, t)
            }
        }
        val q = Math.abs(rand.nextInt()) % 50 + 1
        for (i in 0..q) {
            val used = Array(amount, { false })
            fun DFS(v: Int, s: Int): Boolean {
                used[v] = true
                var res = false
                for (i in g[v]) {
                    if (i == s) {
                        return true
                    }
                    if (used[i] == false) {
                        res = DFS(i, s) or res
                    }
                }
                return res
            }

            val x = Math.abs(rand.nextInt()) % amount
            val y = Math.abs(rand.nextInt()) % amount
            assertEquals((DFS(x, y) || (x == y)), (actual.getX(x) == actual.getX(y)))
        }
    }

    @Test
    fun test3() {
        val amount = Math.abs(rand.nextInt()) % 99 + 2
        val g: Array<ArrayList<Int>> = Array(amount, { ArrayList<Int>() })
        val mx: Array<Array<Int>> = Array(amount, { Array(amount, { 0 }) })
        var actual = DSU(amount)
        val E = Math.abs(rand.nextInt()) % amount * (amount / 2)
        for (i in 0 until E) {
            val f = Math.abs(rand.nextInt()) % amount
            val t = Math.abs(rand.nextInt()) % amount
            if (f != t && mx[f][t] == 0) {
                g[f].add(t)
                g[t].add(f)
                mx[f][t] = 1
                mx[t][f] = 1
                actual.Union(f, t)
            }
        }
        val q = Math.abs(rand.nextInt()) % 50 + 1
        for (i in 0..q) {
            val used = Array(amount, { false })
            fun DFS(v: Int, s: Int): Boolean {
                used[v] = true
                var res = false
                for (i in g[v]) {
                    if (i == s) {
                        return true
                    }
                    if (used[i] == false) {
                        res = DFS(i, s) or res
                    }
                }
                return res
            }

            val x = Math.abs(rand.nextInt()) % amount
            val y = Math.abs(rand.nextInt()) % amount
            assertEquals((DFS(x, y) || (x == y)), (actual.getX(x) == actual.getX(y)))
        }
    }
}