package com.example.sun.centroiddecomposition

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dsl.constraintLayout
import dsl.onCLick


class MainActivity : AppCompatActivity() {
    var TextView.stringVal: String
        get() {
            var a = text.toString()
            return a
        }
        set(x) {
            text = x
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(R.id.layoutId) {
                    textView(R.id.CreateText) {
                        text = "Create a graph"
                        topField(R.id.layoutId, TOP, dp(16))
                        middle(R.id.layoutId)
                    }
                    val numV = numeric(R.id.NumberV) {
                        topField(R.id.CreateText, BOTTOM, dp(12))
                        leftField(R.id.layoutId, LEFT, dp(60))
                    }

                    button(R.id.NButton) {
                        topField(R.id.layoutId, TOP, dp(71))
                        leftField(R.id.NumberV, RIGHT, dp(62))
                        rightField(R.id.layoutId, RIGHT, dp(69))
                        text = "Add N"

                        onCLick {
                            n = numV.stringVal.toInt()
                            graph = Array(n + 1) { arrayListOf<Int>() }
                            numV.stringVal = ""
                        }
                    }

                    val edge = numeric(R.id.Edges) {
                        topField(R.id.NumberV, BOTTOM, dp(17))
                        leftField(R.id.layoutId, LEFT, dp(60))
                    }

                    button(R.id.edgeButton) {
                        topField(R.id.NButton, BOTTOM, dp(21))
                        leftField(R.id.Edges, RIGHT, dp(63))
                        rightField(R.id.layoutId, RIGHT, dp(71))
                        text = "Add edge"

                        onCLick {
                            val array = edge.stringVal.split("\u0020")
                            val from = array[0].toInt()
                            val to = array[1].toInt()
                            graph[from].add(to)
                            graph[to].add(from)
                            edge.stringVal = ""
                        }
                    }
                    val ans = numeric(R.id.ansText) {
                        topField(R.id.mainButton, BOTTOM, dp(32))
                        middle(R.id.layoutId)
                    }

                    button(R.id.mainButton) {
                        topField(R.id.Edges, BOTTOM, dp(78))
                        middle(R.id.layoutId)
                        text = "Centroid is"

                        onCLick {
                            val used = Array(n + 1, { false })
                            val parent = Array(n + 1) { 0 }
                            val size = Array(n + 1, { 0 })
                            build(1, 0, size, graph, used, parent)
                            ans.stringVal = parent.drop(1).joinToString(" ")
                        }
                    }

                    button(R.id.StartButton) {
                        topField(R.id.ansText, BOTTOM, dp(28))
                        middle(R.id.layoutId)
                        text = "Start again"

                        onCLick {
                            graph = Array(1) { arrayListOf<Int>() }
                            n = 0
                            ans.stringVal = ""
                        }
                    }

                })
    }
    //centroid
    var graph = Array(1) { arrayListOf<Int>() }
    var n: Int = 0
    fun get_size(v: Int, p: Int, size: Array<Int>, graph: Array<ArrayList<Int>>, used: Array<Boolean>): Int {
        size[v] = 1;
        for (to in graph[v]) {
            if (!used[to] && to != p) {
                size[v] += get_size(to, v, size, graph, used);
            }
        }
        return size[v];
    }

    fun build(v: Int, last: Int, size: Array<Int>, graph: Array<ArrayList<Int>>, used: Array<Boolean>, parent: Array<Int>) {
        val sz_comp = get_size(v, -1, size, graph, used);
        val c = get_center(v, -1, sz_comp, size, graph, used);
        parent[c] = last;
        used[c] = true;
        for (to in graph[c]) {
            if (!used[to]) {
                build(to, c, size, graph, used, parent);
            }
        }
    }

    fun get_center(v: Int, p: Int, sz: Int, size: Array<Int>, graph: Array<ArrayList<Int>>, used: Array<Boolean>): Int {
        for (to in graph[v]) {
            if (!used[to] && to != p && 2 * size[to] > sz) {
                return get_center(to, v, sz, size, graph, used);
            }
        }
        return v;
    }
}
