package twoSat

import kotlin.collections.ArrayList

class TwoSat {

    private var topSort = ArrayList<Int>()
    private var used = ArrayList<Boolean>()
    private var colors = ArrayList<Int>()
    private var graph = ArrayList<ArrayList<Int>>()
    private var tGraph = ArrayList<ArrayList<Int>>()

    private fun dfs1(v: Int) {
        used[v] = true
        val curSize = graph[v].size
        for (i in 0 until curSize) {
            val next = graph[v][i]
            if (!used[next]) {
                dfs1(next)
            }
        }
        topSort.add(v)
    }

    private fun dfs2(v: Int, color: Int) {
        used[v] = true
        val curSize = tGraph[v].size
        for (i in 0 until curSize) {
            val next = tGraph[v][i]
            if (!used[next]) {
                dfs2(next, color)
            }
        }
        colors[v] = color
    }

    fun twoSat(size: Int, func: String): String {


        for (i in 0 until 2 * size) {
            graph.add(ArrayList())
            tGraph.add(ArrayList())
        }

        val n = 2 * size

        var s = func


        s = s.replace("(", "")
        s = s.replace(")", "")
        s = s.replace(" ", "")

        val ss = s.split("&&")
        for (i in 0 until ss.size) {
            val sss = ss[i].split("||")
            val first =  (if (sss[0][0] == '!') size else 0) + sss[0].split("x")[1].toInt() - 1
            val second =  (if (sss[1][0] == '!') size else 0) + sss[1].split("x")[1].toInt() - 1
            val notFirst = first + if (first >= size) -size else size
            val notSecond = second + if (second >= size) -size else size
            graph[notFirst].add(second)
            graph[notSecond].add(first)
            tGraph[second].add(notFirst)
            tGraph[first].add(notSecond)
        }

        for (i in 0 until n) {
            used.add(false)
        }

        (0 until n)
                .filterNot { used[it] }
                .forEach { dfs1(it) }

        for (i in 0 until n) {
            used[i] = false
            colors.add(0)
        }

        var curColor = 1
        for (i in 0 until n) {
            val cur = topSort[n - i - 1]
            if (!used[cur]) {
                dfs2(cur, curColor++)
            }
        }

        (0 until size)
                .filter { colors[it] == colors[it + size] }
                .forEach { return "NO SOLUTION" }

        val sb = StringBuilder()

        for (i in 0 until size) {
            sb.append("x" + (i + 1).toString() + " = " + (if (colors[i] < colors[i + size]) 0 else 1).toString() + '\n')
        }
        return sb.toString()
    }
}

