package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import my.lib.solve

class MainActivity : AppCompatActivity() {
    var edges = Array<ArrayList<Int>>(0, { arrayListOf() })
    var size = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(constraintLayout(R.id.layoutID) {
            editText(R.id.sizeBox) {
                hint = "enter size of graph"
                textSize = 30f
                inputType = 2
                width = dp(260)
                height = dp(60)
                leftMargin(R.id.layoutID, LEFT, dp(8))
                topMargin(R.id.layoutID, TOP, dp(16))
            }

            button(R.id.createGraphButton, { createGraph() }) {
                width = dp(88)
                height = dp(60)
                textSize = 20f
                text = "Create"
                leftMargin(R.id.sizeBox, RIGHT, dp(8))
                topMargin(R.id.layoutID, TOP, dp(16))

            }

            textView(R.id.textViewEdges) {
                width = dp(90)
                height = dp(40)
                text = "edges"
                textSize = 30f
                topMargin(R.id.sizeBox, BOTTOM, dp(24))
                leftMargin(R.id.layoutID, LEFT, dp(8))
            }

            editText(R.id.fromBox) {
                hint = "from"
                textSize = 30f
                inputType = 2
                width = dp(80)
                height = dp(60)
                leftMargin(R.id.textViewEdges, RIGHT, dp(8))
                topMargin(R.id.sizeBox, BOTTOM, dp(12))
            }

            editText(R.id.toBox) {
                hint = "to"
                textSize = 30f
                inputType = 2
                width = dp(80)
                height = dp(60)
                leftMargin(R.id.fromBox, RIGHT, dp(12))
                topMargin(R.id.sizeBox, BOTTOM, dp(12))
            }

            button(R.id.addEdgeButton, { addEdge() }) {
                width = dp(88)
                height = dp(60)
                textSize = 20f
                text = "add"
                leftMargin(R.id.toBox, RIGHT, dp(0))
                topMargin(R.id.sizeBox, BOTTOM, dp(12))

            }

            button(R.id.answerButton, { getAnswer() }) {
                width = dp(340)
                height = dp(60)
                textSize = 20f
                text = "get answer"
                leftMargin(R.id.layoutID, LEFT, dp(8))
                rightMargin(R.id.layoutID, RIGHT, dp(8))
                topMargin(R.id.fromBox, BOTTOM, dp(16))
            }

            textView(R.id.answerTextView) {
                width = dp(340)
                height = dp(300)
                text = ""
                textSize = 30f
                topMargin(R.id.answerButton, BOTTOM, dp(16))
                leftMargin(R.id.layoutID, LEFT, dp(8))
                rightMargin(R.id.layoutID, RIGHT, dp(8))
                bottomMargin(R.id.layoutID, BOTTOM, dp(16))
            }
        })

    }

    fun createGraph() {
        try {
            size = findViewById<EditText>(R.id.sizeBox).text.toString().toInt()
            edges = Array<ArrayList<Int>>(size, { arrayListOf() })
            findViewById<EditText>(R.id.sizeBox).text = null
        } catch (e: Throwable) {
            Toast.makeText(this, "Check size input", Toast.LENGTH_SHORT).show()
        }
    }

    fun addEdge() {
        try {
            val from = findViewById<EditText>(R.id.fromBox).text.toString().toInt() - 1
            val to = findViewById<EditText>(R.id.toBox).text.toString().toInt() - 1
            if (to < 0 || to >= size) throw Exception()
            edges[from].add(to)
            findViewById<EditText>(R.id.fromBox).text = null
            findViewById<EditText>(R.id.toBox).text = null
        } catch (e: Throwable) {
            Toast.makeText(this, "Check edge input", Toast.LENGTH_SHORT).show()
        }
    }

    fun ArrayList<Int>._toString(): String {
        if (this.size == 0) return ""

        val answer = StringBuilder((this[0] + 1).toString())

        for (i in 1..this.size - 1) {
            answer.append(" - " + (this[i] + 1).toString())
        }

        return answer.toString()
    }

    fun getAnswer() {
        val answer = solve(edges, edges.size)

        findViewById<TextView>(R.id.answerTextView).text = if (answer == null) "Graph is not Eulerian" else answer._toString()
    }
}
