import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class MainTest {
    /*@Test
    fun testAddToSegmentTree() {

    }*/
    @Test
    fun testBuildSegmentTree1() {
        val result = BuildSegmentTree(4)
        val expectedValue = Array<EdgeArray>(8, { i -> EdgeArray() })
        assertEquals(expectedValue, result)
    }

    @Test
    fun testBuildSegmentTree2() {
        val result = BuildSegmentTree(3)
        val expectedValue = Array<EdgeArray>(8, { i -> EdgeArray() })
        assertEquals(expectedValue, result)
    }

    @Test
    fun testAddSegmentTree1() {
        val expectedValue = Array<EdgeArray>(8, { i -> EdgeArray() })
        expectedValue[2].add(Pair<Int, Int>(1, 2))
        expectedValue[6].add(Pair<Int, Int>(1, 2))
        SegmentTree = BuildSegmentTree(4)
        AddToSegmentTree(1, 1, SegmentTree.size / 2, 1, 3, Pair<Int, Int>(1, 2))
        assertEquals(expectedValue, SegmentTree)
    }

    @Test
    fun testAddSegmentTree2() {
        val expectedValue = Array<EdgeArray>(8, { i -> EdgeArray() })
        expectedValue[1].add(Pair<Int, Int>(1, 2))
        expectedValue[7].add(Pair<Int, Int>(3, 3))
        expectedValue[2].add(Pair<Int, Int>(3, 3))

        SegmentTree = BuildSegmentTree(4)
        AddToSegmentTree(1, 1, SegmentTree.size / 2, 1, 4, Pair<Int, Int>(1, 2))
        AddToSegmentTree(1, 1, SegmentTree.size / 2, 4, 4, Pair<Int, Int>(3, 3))
        AddToSegmentTree(1, 1, SegmentTree.size / 2, 1, 2, Pair<Int, Int>(3, 3))
        assertEquals(expectedValue, SegmentTree)
    }

    @Test
    fun testUnionSet1() {
        val expectedValueParent = arrayOf(0, 2, 2, 3)
        for (i in 0..3) {
            dsuParent.add(i)
            dsuSize.add(i)
        }
        UnionVertex(1, 2)
        assertEquals(expectedValueParent, dsuParent.toArray())
    }

    @Test
    fun testUnionSet2() {
        val expectedValueParent = arrayOf(2, 2, 2, 3)
        for (i in 0..3) {
            dsuParent.add(i)
            dsuSize.add(i)
        }
        UnionVertex(1, 2)
        UnionVertex(2, 0)
        assertEquals(expectedValueParent, dsuParent.toArray())
    }

    @Test
    fun testSolve1() {
        val input = arrayOf(
                Command(1, Pair<Int, Int>(0, 1)),
                Command(1, Pair<Int, Int>(3, 1)),
                Command(3, Pair<Int, Int>(0, 3)),
                Command(3, Pair<Int, Int>(0, 4))
        )
        val command = ArrayList<Command>(input.asList())
        val result = solve(6, 4, command)
        assertEquals("10", result)
    }

    @Test
    fun testSolve2() {
        val input = arrayOf(
                Command(1, Pair<Int, Int>(0, 1)),
                Command(1, Pair<Int, Int>(3, 1)),
                Command(3, Pair<Int, Int>(0, 3)),
                Command(2, Pair<Int, Int>(1, 3)),
                Command(3, Pair<Int, Int>(0, 3))
        )
        val command = ArrayList<Command>(input.asList())
        val result = solve(6, 5, command)
        assertEquals("10", result)
    }
}