package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View.OnClickListener
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import my.lib.condensate
import my.lib.generateGraph
import dsl.constraintLayout
import dsl.onCLick

class MainActivity : AppCompatActivity() {
    var adjList: TextView? = null
    var graph: Array<ArrayList<Int>>? = null
    var n: Int = 0
    var m: Int = 0
    fun print_graph(graph: Array<ArrayList<Int>>) {
        adjList = findViewById<TextView>(R.id.graphText)
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
        adjList = findViewById<TextView>(R.id.connectCompText)
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
        var myEditTextView = findViewById<EditText>(R.id.verticesNumber)
        n = myEditTextView.text.toString().toInt()
        myEditTextView = findViewById<EditText>(R.id.edgesNumber)
        m = myEditTextView.text.toString().toInt()

        if (n >= 1 && n <= 10 && m >= 1 && m <= 40) {
            graph = generateGraph(n, m)
            print_graph(graph!!)
        }
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(R.id.layoutId){
                    textView(R.id.verticesLabel){
                        text = "Number of vertices (1-10):"
                        topMargin(R.id.layoutId, TOP, scale(30))
                        leftMargin(R.id.layoutId, LEFT, scale(10))
                    }

                    textView(R.id.edgesLabel){
                        text = "Number of edges (1-40):"
                        topMargin(R.id.verticesLabel, BOTTOM, scale(40))
                        leftMargin(R.id.layoutId, LEFT, scale(10))
                    }

                    editText(R.id.verticesNumber){
                        topMargin(R.id.layoutId, TOP, scale(15))
                        rightMargin(R.id.layoutId, RIGHT, scale(15))
                        height = scale(40)
                        width = scale(100)
                        inputType = 2
                    }

                    editText(R.id.edgesNumber){
                        topMargin(R.id.verticesNumber, BOTTOM, scale(15))
                        rightMargin(R.id.layoutId, RIGHT, scale(15))
                        height = scale(40)
                        width = scale(100)
                        inputType = 2
                    }

                    textView(R.id.graphText){
                        text = ""
                        topMargin(R.id.edgesLabel, BOTTOM, scale(15))
                        leftMargin(R.id.layoutId, LEFT, scale(10))
                    }

                    textView(R.id.connectCompText){
                        text = ""
                        topMargin(R.id.graphText, BOTTOM, scale(30))
                        leftMargin(R.id.layoutId, LEFT, scale(10))
                    }

                    button(R.id.countButton){
                        text = "Find CC"
                        topMargin(R.id.edgesNumber, BOTTOM, scale(20))
                        rightMargin(R.id.layoutId, RIGHT, scale(10))
                        onCLick {
                            if(graph != null){
                                print_graph(condensate(graph!!))
                            }
                        }
                    }
                    button(R.id.generateButton){
                        text = "Generate"
                        topMargin(R.id.edgesNumber, BOTTOM, scale(20))
                        rightMargin(R.id.countButton, LEFT, scale(10))
                        onCLick {
                            try_to_generate()
                        }
                    }

                }

        )
    }
}