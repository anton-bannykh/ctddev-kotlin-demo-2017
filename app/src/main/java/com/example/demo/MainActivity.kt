package com.example.demo

import android.nfc.FormatException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.Graph
import my.lib.INF
import my.lib.johnson

class MainActivity : AppCompatActivity() {
    private var n: Int? = null
    private var graph: Graph? = null
    private var dist: Array<Array<Int>>? = null
    private var counted: Boolean = false

    private var TextView.intValue: Int
        get() = Integer.parseInt(text.toString())
        set(value) {
            text = value.toString()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun createGraph(view: View) {
        try {
            n = numberOfVertex.intValue
            graph = Graph(n!! + 1, { _ -> ArrayList() })
            dist = null
            counted = false
            numberOfVertex.text = null
            answerShower.text = getString(R.string.graphCreation)
        } catch (e: Exception) {
            answerShower.text = getString(R.string.incorrectVertexNumber)
        }
    }

    fun addEdge(view: View) {
        if (graph == null || n == null) {
            answerShower.text = getString(R.string.graphNotCreated)
            return
        }
        try {
            val v = edgeAdderV.intValue
            val u = edgeAdderU.intValue
            val w = edgeAdderW.intValue
            edgeAdderV.text = null
            edgeAdderU.text = null
            edgeAdderW.text = null
            if (v !in 1..n!! || u !in 1..n!!) {
                throw FormatException()
            }
            graph!![v].add(Pair(u, w))
            answerShower.text = getString(R.string.newEdgeAdded)
        } catch (e: Exception) {
            answerShower.text = getString(R.string.incorrectInput)
        }
    }

    fun calculateDists(view: View) {
        if (graph == null || n == null) {
            answerShower.text = getString(R.string.graphNotCreated)
            return
        }
        for (x in 1..n!!) {
            graph!![0].add(Pair(x, 0))
        }
        dist = johnson(graph!!)
        counted = true
        answerShower.text = getString(R.string.distancesCalculated)
    }

    fun showDist(view: View) {
        if (graph == null || n == null) {
            answerShower.text = getString(R.string.graphNotCreated)
            return
        }
        if (!counted) {
            answerShower.text = getString(R.string.notCalculated)
            firstVertex.text = null
            secondVertex.text = null
            return
        }
        try {
            val v = firstVertex.intValue
            val u = secondVertex.intValue
            firstVertex.text = null
            secondVertex.text = null
            if (v !in 1..n!! || u !in 1..n!!) {
                throw FormatException()
            }
            if (dist == null) {
                answerShower.text = getString(R.string.negativeCycle)
            } else {
                val answer = dist!![v - 1][u - 1]
                if (answer != INF) {
                    answerShower.intValue = answer
                } else {
                    answerShower.text = getString(R.string.INF)
                }
            }
        } catch (e: Exception) {
            answerShower.text = getString(R.string.incorrectInput)
        }
    }
}
