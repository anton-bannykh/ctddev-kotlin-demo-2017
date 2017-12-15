package com.example.demo

import android.os.AsyncTask
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import my.lib.topsort

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        findViewById<Button>(R.id.my_button).setOnClickListener({ doSMTH(it) })
        findViewById<Button>(R.id.button_graph).setOnClickListener({ setEdges(it) })
        findViewById<Button>(R.id.button_topsort).setOnClickListener({ topological_sort(it) })
    }

    var n: Int = 0
    var m: Int = 0
    var color = IntArray(0)
    var topol = IntArray(0)
    var graph = Array<ArrayList<Int>>(0, { i -> ArrayList() })

    val text_request by lazy { findViewById<TextView>(R.id.text_request) }
    val text_topol by lazy { findViewById<TextView>(R.id.text_topol) }
    val text_v by lazy { findViewById<TextView>(R.id.text_v) }
    val text_u by lazy { findViewById<TextView>(R.id.text_u) }
    val edge_number by lazy { findViewById<TextView>(R.id.edge_number) }

    val editText_u by lazy { findViewById<EditText>(R.id.editText_u) }
    val editText_v by lazy { findViewById<EditText>(R.id.editText_v) }
    val text_n by lazy { findViewById<EditText>(R.id.editText_n) }
    val text_m by lazy { findViewById<EditText>(R.id.editText_m) }

    val button_graph by lazy { findViewById<Button>(R.id.button_graph) }
    val button_topsort by lazy { findViewById<Button>(R.id.button_topsort) }

    public fun makeVisibility(vis: Int) {
        edge_number.visibility = vis
        text_request.visibility = vis
        text_v.visibility = vis
        text_u.visibility = vis
        editText_u.visibility = vis
        editText_v.visibility = vis
        button_graph.visibility = vis
    }

    public fun doSMTH(view: View) {
        if (view.width < 0) return
        val newN = Integer.parseInt(text_n.text.toString())
        n = newN
        color = IntArray(n)
        topol = IntArray(n)
        graph = Array<ArrayList<Int>>(n, { i -> ArrayList() })
        val newM = Integer.parseInt(text_m.text.toString())
        m = newM
        val request = findViewById<TextView>(R.id.text_request)
        val st: String = "Enter " + newM.toString() + " edges"
        request.text = st
        makeVisibility(View.VISIBLE)
        text_topol.visibility = View.INVISIBLE
        i = 1
    }

    var i: Int = 1

    public fun setEdges(view: View) {
        if (view.width < 0) return
        if (i == m) {
            makeVisibility(View.INVISIBLE)
            button_topsort.visibility = View.VISIBLE
        }
        val text_v = findViewById<EditText>(R.id.editText_v)
        val text_u = findViewById<EditText>(R.id.editText_u)
        val v = Integer.parseInt(text_v.text.toString())
        val u = Integer.parseInt(text_u.text.toString())
        i++
        val str = i.toString() + " edge:"
        edge_number.text = str
        text_u.text.clear()
        text_v.text.clear()
        graph[v].add(u)
    }

    fun topological_sort(view: View) {
        if (view.width < 0) return
        TopSort().execute()
    }

    inner class TopSort : AsyncTask<Void, Void, Boolean>() {
        override fun doInBackground(vararg params: Void?) = topsort(color, graph, topol)
        override fun onPostExecute(result: Boolean) {
            var s = ""
            var s1 = ""
            if (!result) {
                s = "Graph is cyclic"
            } else {
                s = "Old number -> new number"
                for (i in 0..topol.size - 1)
                    s1 += i.toString() + " -> " + (topol[i] - 1) + "; "
                text_topol.text = s1
                text_topol.visibility = View.VISIBLE
            }
            text_request.text = s
            text_request.visibility = View.VISIBLE
            edge_number.text = getString(R.string.first)
            button_topsort.visibility = View.INVISIBLE
            text_n.text.clear()
            text_m.text.clear()
        }
    }
}
