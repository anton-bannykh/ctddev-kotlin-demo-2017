package ru.rofleksey.android.androidhw1

import kotlin.collections.ArrayList
import java.util.TreeSet

class Graph {
    companion object{
        val INF = 1000000000
    }
    class Edge(val from: Int, val to: Int, val len: Int)
    class Vertex(val num: Int) : Comparable<Vertex> {
        var key: Int = INF
        var used = false
        override fun compareTo(other: Vertex) = if(key != other.key) {key.compareTo(other.key)} else {num.compareTo(other.num)}
        override fun toString(): String {
            return "#$num: $key"
        }
    }

    private var graph: ArrayList<MutableList<Edge>> = ArrayList()
    private var vertices: ArrayList<Vertex> = ArrayList()
    private var vertexCount: Int = 0

    private fun checkConnectivity(): Boolean {
        dfs(0)
        //println(v.num.toString() + " is not used!!!!!")
        return vertices.none { !it.used }
    }

    private fun dfs(v: Int) {
        vertices[v].used = true
        graph[v].filterNot { vertices[it.to].used }.forEach { dfs(it.to) }
        //graph[v].filterNot { vertices[it.to].used }.forEach { dfs(it.to) }
    }

    fun addEdge(a: Int, b: Int, c: Int) {
        //println("ADDED EDGE $a->$b")
        graph[a - 1].add(Edge(a - 1, b - 1, c))
        graph[b - 1].add(Edge(b - 1, a - 1, c))
    }

    fun setSize(size: Int) {
        //println("SIZE = " + size)
        vertexCount = size
        graph.ensureCapacity(size)
        vertices.ensureCapacity(size)
        for (i in 0 until vertexCount) {
            graph.add(mutableListOf())
            vertices.add(Vertex(i))
        }
    }

    fun getMST(): List<Pair<Int, Int>> {
        if (!checkConnectivity()) {
            throw RuntimeException("NO MST!")
        }
        val p = IntArray(vertexCount, { _ -> -1 })
        val isInQueue = BooleanArray(vertexCount, { _ -> true })
        vertices[0].key = 0
        val q = TreeSet<Vertex>()
        q += vertices
        //println(q.size.toString()+" ? "+vertexCount+" ? "+vertices.size)
        val result = mutableListOf<Pair<Int, Int>>()
        while (!q.isEmpty()) {
            //println(q.toArray().contentDeepToString())
            val v = q.pollFirst().num
            if (p[v] != -1) {
                result.add(Pair(v, p[v]))
            }
            isInQueue[v] = false
            for (e in graph[v]) {
                if (isInQueue[e.to] && vertices[e.to].key > e.len) {
                    p[e.to] = v
                    q.remove(vertices[e.to])
                    vertices[e.to].key = e.len
                    q.add(vertices[e.to])
                }
            }
        }
        return result
    }
}
