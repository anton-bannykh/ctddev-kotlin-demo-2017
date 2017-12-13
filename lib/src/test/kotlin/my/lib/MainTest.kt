package my.lib

import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test

class MainTest {

    private var size: Int = 5

    @Test
    fun linkTest() {
        var tree = EulerTourTree(size)
        tree.link(1, 2)
        tree.link(1, 3)
        assertTrue(tree.connected(2, 3))
    }

    @Test
    fun cutTest() {
        var tree = EulerTourTree(size)
        tree.link(1, 2)
        tree.link(1, 3)
        tree.cut(1, 2)
        assertFalse(tree.connected(2, 3))
    }

    @Test
    fun notConnectedTest() {
        var tree = EulerTourTree(size)
        tree.link(1, 2)
        tree.link(4, 3)
        assertFalse(tree.connected(2, 3))
    }

    @Test
    fun connectedAfterCutTest() {
        var tree = EulerTourTree(size)
        tree.link(1, 2)
        tree.link(1, 3)
        tree.cut(1, 2)
        assertTrue(tree.connected(1, 3))
    }

    @Test
    fun notInitiallyConnectedTest() {
        var tree = EulerTourTree(size)
        assertFalse(tree.connected(1, 2))
    }

    @Test
    fun sizeOfNodesComponentTest() {
        var tree = EulerTourTree(size)
        tree.link(1, 2)
        tree.link(3, 4)
        tree.link(4, 2)
        assertEquals(tree.sizeOfNodesComponent(1), 4)
    }
}