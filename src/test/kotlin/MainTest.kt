import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun testBinTree() {
        create_tree(7, arrayOf(1, 1, 2, 2, 3, 3))
        /*
        *      1
        *     / \
        *    2   3
        *   / \ / \
        *   4 5 6 7
         */
        assertEquals(1, LCA(1, 2))
        assertEquals(1, LCA(1, 3))
        assertEquals(1, LCA(2, 3))
        assertEquals(1, LCA(4, 6))
        assertEquals(2, LCA(4, 5))
        assertEquals(6, LCA(6, 6))
    }
    @Test
    fun testBambooTree() {
        create_tree(5, arrayOf(1, 2, 3, 4))
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
        assertEquals(1, LCA(1, 2))
        assertEquals(2, LCA(2, 4))
        assertEquals(5, LCA(5, 5))
    }
    @Test
    fun testSunTree() {
        create_tree(5, arrayOf(1, 1, 1, 1))
        /*
        *       1
        *   |  |  |  |
        *   2  3  4  5
         */
        assertEquals(1, LCA(2, 3))
        assertEquals(1, LCA(3, 4))
        assertEquals(1, LCA(1, 3))
    }
}