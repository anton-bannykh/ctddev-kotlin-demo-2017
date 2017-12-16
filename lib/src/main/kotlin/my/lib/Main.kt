package my.lib

import kotlin.math.min
import kotlin.math.max

fun main(args: Array<String>) {
    var tree = SegmentTree(arrayOf(1, 5, 2, 4, 3, 7, 1, 6))
    tree.add(0, 5)
}

class SegmentTree(var elem: Array<Int>) {
    private var root = 0
    private val INF = 10000000
    private var tree = MutableList(3 * elem.size, { Node(0, 0, 0, 0, 0) })

    init {
        var IND = 0
        fun build(L: Int, R: Int): Int {
            val INd = IND
            if (L == R - 1) {
                tree[IND] = Node(elem[L], L, R, -1, -1)
            } else {
                val mid = (L + R) / 2
                val ls = ++IND
                val lres = build(L, mid)
                val rs = ++IND
                val rres = build(mid, R)
                if (lres > rres) {
                    tree[INd] = Node(rres, L, R, ls, rs)
                } else {
                    tree[INd] = Node(lres, L, R, ls, rs)
                }
            }
            return tree[INd].value
        }
        build(0, elem.size)
    }

    data class Node(var value: Int, var left: Int, var right: Int, var left_son: Int, var right_son: Int) { //right not include
    }

    /*fun print(vertex: Int) {
        if (vertex == -1) return
        if (tree[vertex].left + 1 == tree[vertex].right) {
            print("${tree[vertex].value} ")
        } else {
            print(tree[vertex].left_son)
            print(tree[vertex].right_son)
        }
    }*/

    private fun add(index: Int, value: Int, vertex: Int): Int {
        if (tree[vertex].left == index && tree[vertex].left + 1 == tree[vertex].right) {
            tree[vertex].value += value
        } else {
            val mid = (tree[vertex].left + tree[vertex].right) / 2
            if (index >= mid) {
                val nres = add(index, value, tree[vertex].right_son)
                tree[vertex].value = min(nres, tree[tree[vertex].left_son].value)
            } else {
                val nres = add(index, value, tree[vertex].left_son)
                tree[vertex].value = min(nres, tree[tree[vertex].right_son].value)
            }
        }
        return tree[vertex].value
    }

    //add value in tree[index]
    fun add(index: Int, value: Int) { //index = [0, elem.size)
        add(index, value, root)
    }

    private fun set(index: Int, value: Int, vertex: Int): Int {
        if (tree[vertex].left == index && tree[vertex].left + 1 == tree[vertex].right) {
            tree[vertex].value = value
        } else {
            val mid = (tree[vertex].left + tree[vertex].right) / 2
            if (index >= mid) {
                val nres = set(index, value, tree[vertex].right_son)
                tree[vertex].value = min(nres, tree[tree[vertex].left_son].value)
            } else {
                val nres = set(index, value, tree[vertex].left_son)
                tree[vertex].value = min(nres, tree[tree[vertex].right_son].value)
            }
        }
        return tree[vertex].value
    }

    //set value in tree[index]
    fun set(index: Int, value: Int) { //index = [0, elem.size)
        set(index, value, root)
    }

    private fun get(left: Int, right: Int, vertex: Int): Int {
        if (left >= right) {
            return INF
        }
        if (left == tree[vertex].left && right == tree[vertex].right) {
            return tree[vertex].value
        } else {
            val mid = (tree[vertex].left + tree[vertex].right) / 2
            return min(get(left, min(mid, right), tree[vertex].left_son), get(max(mid, left), right, tree[vertex].right_son))
        }
    }

    //return min in [left, right)
    fun get(left: Int, right: Int): Int {
        return get(left, right, root)
    }
}