package com.example.jetbrains.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import com.example.jetbrains.myapplication.exceptions.GraphGenerationException
import graph.Graph
import graph.algorithms.findMinPathCoverageInOriented
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.experimental.async

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var currentGraph: Graph? = null
        fun parseGraph(n: Int, m: Int) {
            currentGraph = Graph.randomAcyclicGraph(n, m)?.also { g ->
                println("randomAcyclicGraph is built")
                graphView.linesText = mutableListOf(listOf(n, m))
                        .also {
                            it.addAll(g.edges.map {
                                listOf(it.from, it.to)
                            })
                        }
            }
        }
        genButton.setOnClickListener {
            val (n, m) = try {
                Pair("${vertexView.text}".toInt(), "${edgeView.text}".toInt())
            } catch (e: Exception) {
                showSimpleAlert("please, enter integer numbers")
                Pair(2, 1)
            }
            try {
                parseGraph(n, m)
            } catch (e: GraphGenerationException) {
                showSimpleAlert(e.message ?: "ups, smth. went wrong, use ios instead of android!!!")
            }
        }
        solveButton.setOnClickListener {
            if (currentGraph == null) {
                val lines = graphView.linesText
                val (n, m) = lines[0].also { lines.drop(1) }
                parseGraph(n, m)
            }
            async {
                currentGraph?.let {
                    answerView.linesText = it
                            .findMinPathCoverageInOriented()
                            .map { path ->
                                mutableListOf<Int>().also {
                                    path.edges.forEach { e ->
                                        it.add(e.from)
                                        it.add(e.to)
                                    }
                                }
                            }
                }
            }
        }
        clearButton.setOnClickListener {
            graphView.setText("")
            answerView.setText("")
            currentGraph = null
        }
    }

    fun showSimpleAlert(text: String) = AlertDialog.Builder(this)
            .setMessage(text)
            .create()
            .show()

    var EditText.linesText: List<List<Int>>
        get() = this.text.lines().map { it.split(" ").map(String::toInt) }
        set(list) = this.setText(list.map { it.joinToString(" ") }.joinToString("\n"))
}
