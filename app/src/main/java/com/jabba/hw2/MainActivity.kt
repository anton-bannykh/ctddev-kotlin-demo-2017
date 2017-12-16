package com.jabba.hw2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*

class MainActivity : AppCompatActivity() {
    var g = mutableListOf<Pair<Int, Int>>()
    var n = 0
    var root = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addEdge.setOnClickListener {
            if (edgeFrom.text.toString().length != 0 && edgeTo.text.toString().length != 0) {
                val from = Integer.parseInt(edgeFrom.text.toString())
                val to = Integer.parseInt(edgeTo.text.toString())
                edgeFrom.setText("");
                edgeTo.setText("");
                if (from > 0 && to > 0) {
                    val max = Math.max(from, to)
                    if (n < max) n = max
                    if (!g.contains(Pair(from, to)) && !g.contains(Pair(to, from))) {
                        g.add(Pair(from, to))
                        addedRibs.setText(addedRibs.text.toString() + "($from; $to), ")
                    }
                    result.setText(dfsIterative(root, n, g).toString())
                }
            }
        }
        removeEdge.setOnClickListener {
            if (edgeFromDelete.text.toString().length != 0 && edgeToDelete.text.toString().length != 0) {
                val from = Integer.parseInt(edgeFromDelete.text.toString())
                val to = Integer.parseInt(edgeToDelete.text.toString())
                edgeFromDelete.setText("");
                edgeToDelete.setText("");
                if (from > 0 && to > 0) {
                    if (g.contains(Pair(from, to))) {
                        addedRibs.setText(addedRibs.text.toString().replace("($from; $to), ", ""))
                        g.remove(Pair(from, to))
                    }
                    if (g.contains(Pair(to, from))) {
                        addedRibs.setText(addedRibs.text.toString().replace("($to; $from), ", ""))
                        g.remove(Pair(to, from))
                    }
                    result.setText(dfsIterative(root, n, g).toString())
                }
            }
        }
        setRoot.setOnFocusChangeListener (object: View.OnFocusChangeListener {
            override fun onFocusChange(v: View, b: Boolean) {
                if (!setRoot.text.toString().isEmpty()) {
                    if (Integer.parseInt(setRoot.text.toString()) != 0) root = Integer.parseInt(setRoot.text.toString())
                    if (root > n) n = root
                    setRoot.setText("")
                    if (!g.isEmpty()) result.setText(dfsIterative(root, n, g).toString())
                }
            }
        })
    }


    fun dfsIterative (root: Int, n: Int, graph: List<Pair<Int, Int>>): MutableList<Int> {
        //graph is defined by its edges; the returned list is the order in which the vertices are visited
        //if a vertex has multiple neighbors, they are visited in an ascending order
        val s = Stack<Int>()
        val g = mutableListOf<MutableList<Int>>()
        for (i in 0..n - 1) g.add(mutableListOf())
        for (i in graph.indices) {
            g[graph[i].first - 1].add(graph[i].second - 1)
            g[graph[i].second - 1].add(graph[i].first - 1)
        }
        for (i in 0..n - 1) g[i].sortDescending()
        val res = mutableListOf<Int>()
        val v = BooleanArray (n, { false })
        s.add(root - 1)
        while (!s.empty()) {
            val e = s.pop()
            if (!v[e]) {
                v[e] = true
                res.add(e + 1)
            }
            for (i in g[e]) {
                if (!v[i]) s.add(i)
            }
        }
        return res
    }

}
