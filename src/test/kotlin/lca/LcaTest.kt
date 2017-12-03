package lca

import org.junit.Assert.assertEquals
import org.junit.Test
import java.util.*

class MainTest{
    private val bambooSize = 100
    private var bamboo = Array(bambooSize){ArrayList<Int>(0)}
    private val heapSize = 15
    private var heap = Array(heapSize){ArrayList<Int>(0)}
    private val myTreeSize = 10
    private var myTree = Array(heapSize){ArrayList<Int>(0)}
    init {
        initBamboo(bamboo)
        initHeap(heap)
        initMyTree(myTree)

    }

    private fun initBamboo(graph : Array<ArrayList<Int>>){
        for (i in 1 until graph.size){
            graph[i-1].add(i)
            graph[i].add(i-1)
        }
    }

    private fun initHeap(graph: Array<ArrayList<Int>>){
        for (i in 0 until heapSize){
            if (i * 2 + 1 < heapSize && i * 2 + 2 < heapSize){
                graph[i].add(i * 2 + 1)
                graph[i * 2 + 1].add(i)
                graph[i].add(i * 2 + 2)
                graph[i * 2 + 2].add(i)
            } else if ( i * 2 + 1 < heapSize){
                graph[i].add(i * 2 + 1)
                graph[i * 2 + 1].add(i)
            }
        }
    }

    private fun initMyTree(graph: Array<ArrayList<Int>>){
        graph[1].add(0)
        graph[0].add(1)
        graph[2].add(0)
        graph[0].add(2)
        graph[3].add(0)
        graph[0].add(3)
        for (i in 2 until 4){
            if (i == 2){
                for (j in 4 until 7){
                    graph[j].add(i)
                    graph[i].add(j)
                }
            } else {
                for (j in 7 until 10){
                    graph[i].add(j)
                    graph[j].add(i)
                }
            }
        }
    }

    @Test
    fun bambooLcaTest(){
        val tree = Tree(bambooSize, graph = bamboo)
        for (i in 1 until 10){
            val random = Random()
            var u = random.nextInt(bambooSize)
            var v = random.nextInt(bambooSize)
            while (u == v){
                v = random.nextInt(bambooSize)
            }
            assertEquals(if (u < v) u else v, tree.getLca(u,v))
        }
    }

    @Test
    fun heapLcaTest(){
        val tree = Tree(heapSize, graph = heap)
        for (i in 7 until heapSize step 2){
            assertEquals(i / 2, tree.getLca(i, i + 1))
        }
    }

    @Test
    fun myTreeTest(){
        val tree = Tree(myTreeSize, graph = myTree)
        assertEquals(0, tree.getLca(1,9))
        assertEquals(0, tree.getLca(4,7))
        assertEquals(2, tree.getLca(5,6))
        assertEquals(3, tree.getLca(7,8))
        assertEquals(0, tree.getLca(2,3))
    }

}