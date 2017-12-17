package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import my.lib.condensate
import my.lib.generateGraph

class MainActivity : AppCompatActivity() {
    var adjList: TextView? = null
    var graph: Array<ArrayList<Int>>? = null
    var n: Int = 0
    var m: Int = 0
    fun print_graph(graph: Array<ArrayList<Int>>) {
        adjList!!.setText("")
        for (i in graph.indices) {
            adjList!!.append("${i + 1}: ")
            for (v in graph[i]) {
                adjList!!.append("${v + 1} ")
            }
            adjList!!.append("\n")
        }
    }

    fun print_graph(graph: ArrayList<ArrayList<Int>>) {
        adjList!!.setText("")
        for (i in graph.indices) {
            adjList!!.append("${i + 1}: ")
            for (v in graph[i]) {
                adjList!!.append("${v + 1} ")
            }
            adjList!!.append("\n")
        }
    }

    fun try_to_generate() {
        var myEditTextView = findViewById<EditText>(R.id.numOfVertices)
        n = myEditTextView.text.toString().toInt()
        myEditTextView = findViewById<EditText>(R.id.numOfEdges)
        m = myEditTextView.text.toString().toInt()

        if (n >= 1 && n <= 10 && m >= 1 && m <= 40) {
            graph = generateGraph(n, m)
            adjList = findViewById<TextView>(R.id.adjacencyList)
            print_graph(graph!!)
        }
    }

    val clickListener = OnClickListener { view ->
        when (view.getId()) {
            R.id.generateButton -> try_to_generate()
            R.id.gettingCC -> {
                adjList = findViewById<TextView>(R.id.connectedComponents)
                print_graph(condensate(graph!!))
            }
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val generateButton = findViewById<Button>(R.id.generateButton)
        generateButton.setOnClickListener(clickListener)
        val gettingCCButton = findViewById<Button>(R.id.gettingCC)
        gettingCCButton.setOnClickListener(clickListener)
    }
}