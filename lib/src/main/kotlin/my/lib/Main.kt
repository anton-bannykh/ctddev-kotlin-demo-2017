package my.lib

import java.util.ArrayList
import java.util.LinkedList
import java.util.Scanner

val C = 505
val INF = 1000 * 1000 * 1000

class Edge(val a: Int, val b: Int, val cap: Int) {
    var flow = 0
}

var n = 0
var m = 0
var s = 0
var t = 0
var edges = ArrayList<Edge>()
var g = Array(C, { ArrayList<Int>() })
var pt = IntArray(C, { 0 })
var d = IntArray(C, { 0 })

fun addEdge(edge: Edge) {
    g[edge.a].add(edges.size)
    edges.add(edge)

    g[edge.b].add(edges.size)
    edges.add(Edge(edge.b, edge.a, edge.cap))
}

fun bfs(flow: Int): Boolean {
    d = IntArray(C, { INF })
    d[s] = 0

    val q = LinkedList<Int>()
    q.push(s)

    while (!q.isEmpty() && d[t] == INF) {
        val cur = q.first
        q.pop()

        for (id in g[cur]) {
            val edge = edges[id]
            val to = edge.b
            if (d[to] == INF && edge.cap - edge.flow >= flow) {
                d[to] = d[cur] + 1
                q.push(to)
            }
        }
    }

    return d[t] != INF
}

fun dfs(v: Int, flow: Int): Boolean {
    if (flow == 0)
        return false
    if (v == t)
        return true
    while (pt[v] != g[v].size) {
        val id = g[v][pt[v]]
        val edge = edges[id]
        val to = edge.b

        if (d[to] == d[v] + 1 && edge.cap - edge.flow >= flow) {
            val pushed = dfs(to, flow)
            if (pushed) {
                edge.flow += flow
                edges[id xor 1].flow -= flow
                return true
            }
        }
        pt[v]++
    }
    return false
}

fun clearData() {
    edges.clear()
    for (i in 0 until C) {
        g[i].clear()
        pt[i] = 0
    }
}

fun solve(verticesCnt: Int, edges: Array<Edge>): Int {
    clearData()
    n = verticesCnt
    m = edges.size
    s = 0
    t = n - 1
    for (edge in edges)
        addEdge(edge)
    var result = 0
    var flow = 1 shl 30
    while (flow > 0) {
        if (!bfs(flow)) {
            flow /= 2
            continue
        }

        pt = IntArray(C, { 0 })

        while (dfs(s, flow)) {
            result += flow
        }
    }
    return result
}

fun main(args: Array<String>) {
    val sc = Scanner(System.`in`)

    val n = sc.nextInt()
    val m = sc.nextInt()

    val edges = Array(m, { Edge(0, 0, 0) })

    for (i in 0 until m) {
        val a = sc.nextInt()
        val b = sc.nextInt()
        val c = sc.nextInt()

        edges[i] = Edge(a - 1, b - 1, c)
    }

    println(solve(n, edges))
}
