package my.lib

/**
 * Created by Facmad on 12.12.2017.
 */

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.ArrayList
import java.util.Random

class MainTest {

    private fun initBamboo(graph : Array<ArrayList<Int>>) {
        for (i in 1 until graph.size) {
            graph[i - 1].add(i)
            graph[i].add(i-1)
        }
    }

    private fun initHeap(graph: Array<ArrayList<Int>>) {
        for (i in 0 until graph.size) {
            if (i * 2 + 1 < graph.size && i * 2 + 2 < graph.size) {
                graph[i].add(i * 2 + 1)
                graph[i * 2 + 1].add(i)
                graph[i].add(i * 2 + 2)
                graph[i * 2 + 2].add(i)
            } else if ( i * 2 + 1 < graph.size) {
                graph[i].add(i * 2 + 1)
                graph[i * 2 + 1].add(i)
            }
        }
    }

    private fun initMyTree(graph: Array<ArrayList<Int>>) {
        graph[1].add(0)
        graph[0].add(1)
        graph[2].add(0)
        graph[0].add(2)
        graph[3].add(0)
        graph[0].add(3)
        for (i in 2 until 4) {
            if (i == 2) {
                for (j in 4 until 7) {
                    graph[j].add(i)
                    graph[i].add(j)
                }
            } else {
                for (j in 7 until 10) {
                    graph[i].add(j)
                    graph[j].add(i)
                }
            }
        }
    }

    @Test
    fun bambooLcaTest() {
        val bambooSize = 100
        val bamboo = Array(bambooSize) { ArrayList<Int>(0) }
        initBamboo(bamboo)
        val tree = Tree(bambooSize, bamboo)
        for (i in 1 until 10) {
            val random = Random()
            val u = random.nextInt(bambooSize)
            var v = random.nextInt(bambooSize)
            while (u == v) {
                v = random.nextInt(bambooSize)
            }
            assertEquals(if (u < v) u else v, tree.getLca(u, v))
        }
    }

    @Test
    fun heapLcaTest() {
        val heapSize = 15
        val heap = Array(heapSize) { ArrayList<Int>(0) }
        initHeap(heap)
        val tree = Tree(heapSize, heap)
        for (i in 7 until heapSize step 2) {
            assertEquals(i / 2, tree.getLca(i, i + 1))
        }
    }

    @Test
    fun myTreeTest() {
        val myTreeSize = 10
        val myTree = Array(myTreeSize) { ArrayList<Int>(0) }
        initMyTree(myTree)
        val tree = Tree(myTreeSize, myTree)
        assertEquals(0, tree.getLca(1, 9))
        assertEquals(0, tree.getLca(4, 7))
        assertEquals(2, tree.getLca(5, 6))
        assertEquals(3, tree.getLca(7, 8))
        assertEquals(0, tree.getLca(2, 3))
    }

}