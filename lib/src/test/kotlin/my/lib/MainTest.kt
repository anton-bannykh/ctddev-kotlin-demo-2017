package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testBinTree() {
        val lca = LCA(7, arrayOf(0, 1, 1, 2, 2, 3, 3))
        /*
        *      1
        *     / \
        *    2   3
        *   / \ / \
        *   4 5 6 7
         */
        assertEquals(1, lca.lca(1, 2))
        assertEquals(1, lca.lca(1, 3))
        assertEquals(1, lca.lca(2, 3))
        assertEquals(1, lca.lca(4, 6))
        assertEquals(2, lca.lca(4, 5))
        assertEquals(6, lca.lca(6, 6))
    }

    @Test
    fun testBambooTree() {
        val lca = LCA(5, arrayOf(0, 1, 2, 3, 4))
        /*
        *  1
        *  |
        *  2
        *  |
        *  3
        *  |
        *  4
        *  |
        *  5
         */
        assertEquals(1, lca.lca(1, 2))
        assertEquals(2, lca.lca(2, 4))
        assertEquals(5, lca.lca(5, 5))
    }

    @Test
    fun testSunTree() {
        val lca = LCA(5, arrayOf(0, 1, 1, 1, 1))
        /*
        *       1
        *   |  |  |  |
        *   2  3  4  5
         */
        assertEquals(1, lca.lca(2, 3))
        assertEquals(1, lca.lca(3, 4))
        assertEquals(1, lca.lca(1, 3))
    }
}