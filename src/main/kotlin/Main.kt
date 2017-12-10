import java.util.*;
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
}

/*
* type = 1 -> delete edge from v to u
* type = 2 -> add edge from v to u
* type = 3 -> check v and u are in one component
* */
class Command(val type: Int, inputV: Pair<Int, Int>) {
    val vertex = if (inputV.first > inputV.second) Pair(inputV.second, inputV.first) else inputV

}

typealias PairInt = Pair<Pair<Int, Int>, Int>
typealias EdgeArray = ArrayList<Pair<Int, Int>>

class DynamicConnectivity(val n: Int, val m: Int, val commands: ArrayList<Command>) {
    private val dsuSize = ArrayList<Int>()
    private val dsuParent = ArrayList<Int>()
    private val edge = ArrayList<PairInt>()
    private val inputCommand = ArrayList<Command>()
    private val requestSize: Int = 0
    private val segmentTree = ArrayList<EdgeArray>()
    //((b, c), a) a - last value, b != -1 => update dsuParent, c != -1 => update dsuSize
    private val addedEdges = Vector<PairInt>()


    init {
        commands.forEach { inputCommand.add(it) }
        buildSegmentTree(m).forEach { segmentTree.add(it) }
        requestSize.plus(m)
        dsuSize.clear()
        dsuParent.clear()
    }

    /*
    * return segment tree
    * */
    private fun buildSegmentTree(n: Int): Array<EdgeArray> {
        var curSize = 1
        while (curSize < n) {
            curSize *= 2
        }
        return Array<EdgeArray>(2 * curSize, { i -> EdgeArray() })
    }

    /*
    * return root v
    * */
    private fun getDsuRoot(v: Int): Int {
        var cur = v
        while (dsuParent[cur] != cur) {
            cur = dsuParent[cur]
        }
        return cur
    }

    /*
    * union (u, v)
    * */
    private fun unionVertex(v: Int, u: Int) {
        val a = getDsuRoot(v)
        val b = getDsuRoot(u)
        if (a == b) {
            return
        }
        if (dsuSize[a] > dsuSize[b]) {
            addedEdges.add(PairInt(Pair(b, -1), dsuParent[b]))
            dsuParent[b] = a
        } else if (dsuSize[a] < dsuSize[b]) {
            addedEdges.add(PairInt(Pair(a, -1), dsuParent[a]))
            dsuParent[a] = b
        } else {
            addedEdges.add(PairInt(Pair(a, -1), dsuParent[a]))
            dsuParent[a] = b
            addedEdges.add(PairInt(Pair(-1, b), dsuSize[b]))
            dsuSize[b]++
        }
    }

    /*
    * Add edge (v, u) when it exist in graph
    * */
    private fun addToSegmentTree(v: Int, tl: Int, tr: Int, l: Int, r: Int, value: Pair<Int, Int>): Unit {
        if (l > r) {
            return
        }
        if (l == tl && r == tr) {
            segmentTree[v].add(value)
            return
        }
        val tm = (tl + tr) / 2
        addToSegmentTree(v * 2, tl, tm, l, Math.min(r, tm), value);
        addToSegmentTree(v * 2 + 1, tm + 1, tr, Math.max(tm + 1, l), r, value)
    }

    /*
    * solve task
    * */
    private fun getResult(v: Int, tl: Int, tr: Int): String {
        var result = ""
        val sz = addedEdges.size
        for (i in segmentTree[v]) {
            unionVertex(i.first, i.second)
        }
        if (tl == tr) {
            if (tl <= requestSize && inputCommand[tl - 1].type == 3) {
                if (getDsuRoot(inputCommand[tl - 1].vertex.first) == getDsuRoot(inputCommand[tl - 1].vertex.second)) {
                    result = "1"
                } else {
                    result = "0"
                }
            }
        } else {
            val tm = (tl + tr) / 2
            result += getResult(v * 2, tl, tm)
            result += getResult(v * 2 + 1, tm + 1, tr)
        }
        while (addedEdges.size > sz) {
            if (addedEdges[addedEdges.size - 1].first.first != -1) {
                val last = addedEdges[addedEdges.size - 1].first.first
                dsuParent[last] = addedEdges[addedEdges.size - 1].second
            } else {
                val last = addedEdges[addedEdges.size - 1].first.second
                dsuSize[last] = addedEdges[addedEdges.size - 1].second
            }
            addedEdges.remove(addedEdges[addedEdges.size - 1])
        }

        return result
    }

    /*
    * m must be equal commands.size
    * */
    fun solve(): String {

        var time = 1

        for (i in 0..n - 1) {
            dsuSize.add(1)
            dsuParent.add(i)
        }

        for (i in commands) {
            if (i.type == 1) {
                edge.add(PairInt(i.vertex, time))
            } else if (i.type == 2) {
                val remove = ArrayList<PairInt>()
                for (it in edge) {
                    if (it.first != i.vertex) {
                        continue
                    }
                    addToSegmentTree(1, 1, segmentTree.size / 2, it.second, time, it.first)
                    remove.add(it)
                }
                for (it in remove) {
                    edge.remove(it)
                }
            }
            time++
        }
        for (i in edge) {
            addToSegmentTree(1, 1, segmentTree.size / 2, i.second, m, i.first)
        }
        return getResult(1, 1, segmentTree.size / 2)
    }

}
