package com.example.sun.centroiddecomposition

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var graph = Array(1){arrayListOf<Int>()}
    var n: Int = 0
    fun createGraph(view: View){
        val nText : TextView = findViewById(R.id.N)
        n = nText.text.toString().toInt()
        nText.text = null
        graph = Array(n + 1){ arrayListOf<Int>()}
    }

    fun addEdge(view: View) {
        val edgeText: TextView = findViewById(R.id.edge)
        val array = edgeText.text.toString().split("\u0020")
        val from = array[0].toInt()
        val to = array[1].toInt()
        graph[from].add(to)
        graph[to].add(from)
        edgeText.text = null
    }
    //centroid
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


    fun output_parent(view: View, n: Int, parent: Array<Int>) {
        val nAns : TextView = findViewById(R.id.answer)
        nAns.text = parent.drop(1).joinToString(" ")
    }

    fun get_center(v: Int, p: Int, sz: Int, size: Array<Int>, graph: Array<ArrayList<Int>>, used: Array<Boolean>): Int {
        for (to in graph[v]) {
            if (!used[to] && to != p && 2 * size[to] > sz) {
                return get_center(to, v, sz, size, graph, used);
            }
        }
        return v;
    }

    fun mainly(view: View) {
        val used = Array(n + 1, { false })
        val parent = Array(n + 1) { 0 }
        val size = Array(n + 1, { 0 })
        build(1, 0, size, graph, used, parent)
        output_parent(view, n, parent)
    }
    fun start(view: View){
        graph = Array(1){arrayListOf<Int>()}
        n = 0
        val ans : TextView = findViewById(R.id.answer)
        ans.text = null
    }
}
