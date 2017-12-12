import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testBinTree() {
        val tree = LCA(7, arrayOf(1, 1, 2, 2, 3, 3))
        /*
        *      1
        *     / \
        *    2   3
        *   / \ / \
        *   4 5 6 7
         */
        assertEquals(1, tree.lca(1, 2))
        assertEquals(1, tree.lca(1, 3))
        assertEquals(1, tree.lca(2, 3))
        assertEquals(1, tree.lca(4, 6))
        assertEquals(2, tree.lca(4, 5))
        assertEquals(6, tree.lca(6, 6))
    }
    @Test
    fun testBambooTree() {
        val tree = LCA(5, arrayOf(1, 2, 3, 4))
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
        assertEquals(1, tree.lca(1, 2))
        assertEquals(2, tree.lca(2, 4))
        assertEquals(5, tree.lca(5, 5))
    }
    @Test
    fun testSunTree() {
        val tree = LCA(5, arrayOf(1, 1, 1, 1))
        /*
        *       1
        *   |  |  |  |
        *   2  3  4  5
         */
        assertEquals(1, tree.lca(2, 3))
        assertEquals(1, tree.lca(3, 4))
        assertEquals(1, tree.lca(1, 3))
    }
}