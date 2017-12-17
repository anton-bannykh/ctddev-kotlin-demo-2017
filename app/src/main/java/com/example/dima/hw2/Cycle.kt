package com.example.dima.hw2

/**
 * Created by Dima on 11.12.2017.
 */
class Cycle(n: Int) {
    var c_st : Int = -1
    var number : Int = 1
    var c_end: Int = 0
    val graph = mutableListOf<MutableList<Int>>()
    val cycle: MutableList<Int> = mutableListOf()
    val parent: MutableList<Int> = mutableListOf()
    var f : Boolean = false

    fun addEdge(start: Int, end: Int) {
        graph[start].add(end)
        f = true
    }
    fun clearEd() {
        c_st = -1
        c_end = 0
        for(i in 0..number - 1) {
            graph[i].clear()
        }
        f = false
    }
    fun contain(a : Int, b : Int) : Boolean{
        return graph[a].contains(b)
    }

    fun dfs(v: Int, graph: MutableList<MutableList<Int>>, cycle: MutableList<Int>, parent: MutableList<Int>) : Boolean {
        cycle[v] = 1
        for (i in graph[v]) {
            if (cycle[i] == 0) {
                parent[i] = v
                if (dfs(i, graph, cycle, parent)) {
                    return true
                }
            } else if (cycle[i] == 1) {
                c_end = v
                c_st = i
                return true
            }
        }
        cycle[v] = 2
        return false
    }

    fun answerRequest() : MutableList<Int> {
        if(f) {
            for (i in 0..number - 1) {
                if (dfs(i, graph, cycle, parent)) break
            }
            for(i in 0..number - 1) {
                cycle[i] = 0
            }
            val temp: MutableList<Int> = mutableListOf()
            if (c_st == -1) {
                for(i in 0..number - 1) {
                    parent[i] = 0
                }
                return temp
            } else {
                temp.add(c_st + 1)
                var i = c_end
                while (i != c_st) {
                    temp.add(i + 1)
                    i = parent[i]
                }
                temp.add(c_st + 1)
                temp.reverse()
                for(i in 0..number - 1) {
                    parent[i] = 0
                }
                return temp
            }
        }
        return mutableListOf()
    }

    init {
        number = n
        for (i in 0..n - 1) {
            parent.add(-1)
            cycle.add(0)
            graph.add(mutableListOf())
        }
    }
}
