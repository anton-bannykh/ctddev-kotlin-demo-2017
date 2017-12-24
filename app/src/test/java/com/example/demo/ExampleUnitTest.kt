package com.example.demo

import my.lib.condensate
import my.lib.generateGraph
import org.junit.Assert
import org.junit.Test

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * See [testing documentation](http://d.android.com/tools/testing).
 */

import java.util.Random

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start

class MainTest {

    private fun dfs(v: Int, reachable: BooleanArray, graph: Array<ArrayList<Int>>) {
        reachable[v] = true
        for (u in graph[v]) {
            if (!reachable[u]) {
                dfs(u, reachable, graph)
            }
        }
    }

    private fun checkCondensate(graph: Array<ArrayList<Int>>, comps: ArrayList<ArrayList<Int>>): Boolean {
        val matrReachable = Array(graph.size, { BooleanArray(graph.size, { false }) })
        for (i in graph.indices) {
            dfs(i, matrReachable[i], graph)
        }
        for (comp in comps) {
            for (u in comp) {
                for (v in comp) {
                    if (!(matrReachable[u][v] && matrReachable[v][u])) {
                        return false
                    }
                }
            }
        }
        for (comp1 in comps) {
            for (comp2 in comps) {
                if (comp1 == comp2) {
                    continue
                }
                for (u in comp1) {
                    for (v in comp2) {
                        if (matrReachable[u][v] && matrReachable[v][u]) {
                            return false
                        }
                    }
                }
            }
        }
        return true
    }
    @Test
    fun testLeaves() {
        val graph = generateGraph(10, 0)
        Assert.assertTrue(checkCondensate(graph, condensate(graph)))
    }

    @Test
    fun smallTest() {
        val graph = generateGraph(10, 100)
        Assert.assertTrue(checkCondensate(graph, condensate(graph)))
    }

    @Test
    fun normalTest() {
        val graph = generateGraph(100, 10000)
        Assert.assertTrue(checkCondensate(graph, condensate(graph)))
    }

    @Test
    fun hugeTest() {
        val graph = generateGraph(500, 100000)
        Assert.assertTrue(checkCondensate(graph, condensate(graph)))
    }

    @Test
    fun randTest1() {
        val graph = generateGraph((0..500).random(), (0..100000).random())
        Assert.assertTrue(checkCondensate(graph, condensate(graph)))
    }

    @Test
    fun randTestMany() {
        var i = 0
        while (i < 10) {
            randTest1()
            i++
        }
    }
}
