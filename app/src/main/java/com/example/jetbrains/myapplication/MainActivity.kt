package com.example.jetbrains.myapplication

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.jetbrains.myapplication.exceptions.GraphGenerationException
import com.example.jetbrains.myapplication.extensions.activitybuilder.*
import graph.Graph
import graph.algorithms.findMinPathCoverageInOriented
import kotlinx.coroutines.experimental.async

class MainActivity : AppCompatActivity() {

    private val vertex_view_id = 5121231
    private val edge_view_id = 513241
    private val graph_view_id = 51415151
    private val answer_view_id = 51513412
    private val gen_button_id = 51651241
    private val solve_button_id = 517513
    private val clear_button_id = 51865111

    private val vertexView: TextView by lazy { findViewById<TextView>(vertex_view_id) }
    private val edgeView: TextView by lazy { findViewById<TextView>(edge_view_id) }
    private val graphView: EditText by lazy { findViewById<EditText>(graph_view_id) }
    private val answerView: EditText by lazy { findViewById<EditText>(answer_view_id) }
    private val genButton: Button by lazy { findViewById<Button>(gen_button_id) }
    private val solveButton: Button by lazy { findViewById<Button>(solve_button_id) }
    private val clearButton: Button by lazy { findViewById<Button>(clear_button_id) }

    @SuppressLint("SetTextI18n", "ResourceType")
    fun drawView() {
//        val layout = LinearLayout(this)
//        layout.orientation = LinearLayout.VERTICAL
//        layout.layoutParams = ViewGroup.LayoutParams(
//                LinearLayout.LayoutParams.FILL_PARENT,
//                LinearLayout.LayoutParams.FILL_PARENT
//        )
//        val button = Button(this)
//        button.text = "OK!"
//        button.layoutParams = ViewGroup.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        val button2 = Button(this)
//        button2.text = "OK2!"
//        button2.layoutParams = ViewGroup.LayoutParams(
//                LinearLayout.LayoutParams.WRAP_CONTENT,
//                LinearLayout.LayoutParams.WRAP_CONTENT
//        )
//        layout.addView(button)
//        layout.addView(button2)
//        this.addContentView(
//                layout,
//                layout.layoutParams
//        )
        linearLayout {
            orientation = LinearLayout.VERTICAL
            wrapContent()
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                wrapContent()
                label {
                    text = "vertex number :"
                    wrapContent()
                }
                textEdit {
                    id = vertex_view_id
                }
                label {
                    text = "edge number :"
                    wrapContent()
                }
                textEdit {
                    id = edge_view_id
                }
            }
            textEdit {
                id = answer_view_id
            }
            textEdit {
                id = graph_view_id
            }
            linearLayout {
                orientation = LinearLayout.HORIZONTAL
                wrapContent()
                button {
                    text = "generate"
                    id = gen_button_id
                    wrapContent()
                }
                button {
                    text = "solve"
                    id = solve_button_id
                    wrapContent()
                }
                button {
                    text = "clear"
                    id = clear_button_id
                    wrapContent()
                }
            }
        }
    }

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        drawView()
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
