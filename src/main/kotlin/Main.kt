import java.util.*;
import kotlin.collections.ArrayList

fun main(args: Array<String>) {
}

/*
* type = 1 -> delete edge from v to u
* type = 2 -> add edge from v to u
* type = 3 -> check v and u are in one component
* */
class Command(type_: Int, vertex_: Pair<Int, Int>) {
    val type = type_
    val vertex = if (vertex_.first > vertex_.second) Pair<Int, Int>(vertex_.second, vertex_.first) else vertex_
}

typealias PairInt = Pair<Pair<Int, Int>, Int>
typealias EdgeArray = ArrayList<Pair<Int, Int>>


var InputCommand = ArrayList<Command>()
var dsuSize = ArrayList<Int>()
var dsuParent = ArrayList<Int>()
var SegmentTree = Array<EdgeArray>(1, { i -> EdgeArray() })
var edge = ArrayList<PairInt>()
var RequestSize: Int = 0

//((b, c), a) a - last value, b != -1 => update dsuParent, c != -1 => update dsuSize
var AddedEdges = Vector<PairInt>()

/*
* return segment tree
* */
fun BuildSegmentTree(n: Int): Array<EdgeArray> {
    var curSize = 1
    while (curSize < n) {
        curSize *= 2
    }
    return Array<EdgeArray>(2 * curSize, { i -> EdgeArray() })
}

/*
* return root v
* */
fun getDsuRoot(v: Int): Int {
    var cur = v
    while (dsuParent[cur] != cur) {
        cur = dsuParent[cur]
    }
    return cur
}

/*
* union (u, v)
* */
fun UnionVertex(v: Int, u: Int) {
    val a = getDsuRoot(v)
    val b = getDsuRoot(u)
    if (a == b) {
        return
    }
    if (dsuSize[a] > dsuSize[b]) {
        AddedEdges.add(PairInt(Pair(b, -1), dsuParent[b]))
        dsuParent[b] = a
    } else if (dsuSize[a] < dsuSize[b]) {
        AddedEdges.add(PairInt(Pair(a, -1), dsuParent[a]))
        dsuParent[a] = b
    } else {
        AddedEdges.add(PairInt(Pair(a, -1), dsuParent[a]))
        dsuParent[a] = b
        AddedEdges.add(PairInt(Pair(-1, b), dsuSize[b]))
        dsuSize[b]++
    }
}

/*
* Add edge (v, u) when it exist in graph
* */
fun AddToSegmentTree(v: Int, tl: Int, tr: Int, l: Int, r: Int, value: Pair<Int, Int>): Unit {
    if (l > r) {
        return
    }
    if (l == tl && r == tr) {
        SegmentTree[v].add(value)
        return
    }
    val tm = (tl + tr) / 2
    AddToSegmentTree(v * 2, tl, tm, l, Math.min(r, tm), value);
    AddToSegmentTree(v * 2 + 1, tm + 1, tr, Math.max(tm + 1, l), r, value)
}

/*
* solve task
* */
fun getResult(v: Int, tl: Int, tr: Int): String {
    var result = ""
    val sz = AddedEdges.size
    for (i in SegmentTree[v]) {
        UnionVertex(i.first, i.second)
    }
    if (tl == tr) {
        if (tl <= RequestSize && InputCommand[tl - 1].type == 3) {
            if (getDsuRoot(InputCommand[tl - 1].vertex.first) == getDsuRoot(InputCommand[tl - 1].vertex.second)) {
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
    while (AddedEdges.size > sz) {
        if (AddedEdges[AddedEdges.size - 1].first.first != -1) {
            val last = AddedEdges[AddedEdges.size - 1].first.first
            dsuParent[last] = AddedEdges[AddedEdges.size - 1].second
        } else {
            val last = AddedEdges[AddedEdges.size - 1].first.second
            dsuSize[last] = AddedEdges[AddedEdges.size - 1].second
        }
        AddedEdges.remove(AddedEdges[AddedEdges.size - 1])
    }

    return result
}

/*
* m must be equal commands.size
* */
fun solve(n: Int, m: Int, commands: ArrayList<Command>): String {
    InputCommand = commands
    SegmentTree = BuildSegmentTree(m)
    RequestSize = m
    dsuSize.clear()
    dsuParent.clear()
    var time = 1

    for (i in 0..n - 1) {
        dsuSize.add(1)
        dsuParent.add(i)
    }

    for (i in commands) {
        if (i.type == 1) {
            edge.add(PairInt(i.vertex, time))
        } else if (i.type == 2) {
            var remove = ArrayList<PairInt>()
            for (it in edge) {
                if (it.first != i.vertex) {
                    continue
                }
                AddToSegmentTree(1, 1, SegmentTree.size / 2, it.second, time, it.first)
                remove.add(it)
            }
            for (it in remove) {
                edge.remove(it)
            }
        }
        time++
    }
    for (i in edge) {
        AddToSegmentTree(1, 1, SegmentTree.size / 2, i.second, m, i.first)
    }
    return getResult(1, 1, SegmentTree.size / 2)
}