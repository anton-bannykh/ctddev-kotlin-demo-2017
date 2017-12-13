package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.dijkstra
import my.lib.random

class MainActivity : AppCompatActivity() {

    private var size = 0
    private var from = 0

    private var messageError: Toast? = null
    private var messageAdded: Toast? = null
    private var messageSuccess: Toast? = null
    private  var edges: Array<ArrayList<Pair<Int, Long>>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        messageError = Toast.makeText(this,
                getString(R.string.inputIncorrect), Toast.LENGTH_SHORT)
        messageAdded = Toast.makeText(this,
                getString(R.string.btnAddDesc), Toast.LENGTH_SHORT)
        messageSuccess = Toast.makeText(this,
                getString(R.string.successSet), Toast.LENGTH_SHORT)
    }

    private var TextView.intText: Int
        get() = "$text".toInt()
        set(value) {
            this.text = "$value"
        }
    private var TextView.longText: Long
        get() = "$text".toLong()
        set(value) {
            this.text = "$value"
        }

    fun setSize(view: View) {
        if (!inputSize.text.isEmpty() && !inputVertex.text.isEmpty()) {
            size = inputSize.intText
            from = inputVertex.intText  - 1
            if (size > 0 && from in 0 until size) {
                btnSetSize.isEnabled = false
                edges = Array(size, { ArrayList<Pair<Int, Long>>() })
                messageSuccess?.show()
            } else messageError?.show()
            inputSize.text = null
            inputVertex.text = null
        }
    }

    fun addEdge(view: View) {
        if (!inputFrom.text.isEmpty() && !inputTo.text.isEmpty() && !inputWeight.text.isEmpty()) {
            val from = inputFrom.intText
            val to  = inputTo.intText
            val weight = inputWeight.longText

            if (from in 1..size && to in 1..size) {
                edges!![from - 1].add(Pair(to - 1, weight))
                edges!![to - 1].add(Pair(from - 1, weight))
                messageAdded?.show()
            } else {
                messageError?.show()
            }
            inputFrom.text = null
            inputTo.text = null
            inputWeight.text = null
        }
    }

    private fun create() {
        val dist = dijkstra(from, edges!!)

        val INF = 1_000_000_000_000
        val text = StringBuilder()
        for (i in dist.indices) {
            if (dist[i] == INF) {
                text.append(getString(R.string.infinity, i + 1))
            } else {
                text.append(getString(R.string.showDist, i + 1, dist[i]))
            }
        }

        ansDescription.text = getString(R.string.ansMessage, from + 1)
        distShower.movementMethod = ScrollingMovementMethod()
        distShower.text = text
    }

    fun createGraph(view: View) {
        create()
    }

    fun randomCreate(view: View) {
        btnCreate.isEnabled = false
        btnSetSize.isEnabled = false
        size = (10..30).random()
        from = (0..size).random()

        fun generateGraph() {
            edges = Array(size, { ArrayList<Pair<Int, Long>>() })
            for (i in 0..(size - 1)) {
                for (j in (i + 1)..(size - 1)) {
                    val edgeExist = ((1..5).random() > 1)
                    if (edgeExist) {
                        edges!![i].add(Pair(j, (1..1_000).random().toLong()))
                        edges!![j].add(Pair(i, (1..1_000).random().toLong()))
                    }
                }
            }
        }

        generateGraph()
        create()
    }

    fun clearList(view: View) {
        size = 0
        btnSetSize.isEnabled = true
        btnCreate.isEnabled = true
        ansDescription.text = null
        distShower.text = null
    }
}
