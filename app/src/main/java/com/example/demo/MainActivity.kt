package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.solve

class MainActivity : AppCompatActivity() {
    var edges = Array<ArrayList<Int>>(0, {arrayListOf()})
    var size = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createGraph(view: View) {
        try {
            size = sizeBox.text.toString().toInt()
            edges = Array<ArrayList<Int>>(size, { arrayListOf()})
            sizeBox.text = null
        } catch (e: Throwable) {
            Toast.makeText(this, "Check size input", Toast.LENGTH_SHORT).show()
        }
    }

    fun addEdge(view: View) {
        try {
            val from = fromBox.text.toString().toInt() - 1
            val to = toBox.text.toString().toInt() - 1
            if (to < 0 || to >= size) throw Exception()
            edges[from].add(to)
            fromBox.text = null
            toBox.text = null
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

    fun getAnswer(view: View) {
        val answer = solve(edges, edges.size)

        answerTextView.text = if (answer == null) "Graph is not Eulerian" else answer._toString()
    }
}
