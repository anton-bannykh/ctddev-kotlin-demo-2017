package com.example.demo

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import my.lib.dijkstra
import my.lib.random

class MainActivity : AppCompatActivity() {

    private var size = 0
    private var from = 0

    private var messageError: Toast? = null
    private var messageAdded: Toast? = null
    private var messageSuccess: Toast? = null
    private var edges: Array<ArrayList<Pair<Int, Long>>>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(constraintLayout(R.id.layout) {
            editText(R.id.inputSize) {
                hint = getString(R.string.enterSize)
                textSize = 16f
                inputType = 2
                width = dp(90)
                height = dp(50)
                margin(TOP, R.id.layout, TOP, 16)
                margin(LEFT, R.id.layout, LEFT, 16)
            }

            editText(R.id.inputVertex) {
                hint = getString(R.string.enterVertex)
                textSize = 16f
                inputType = 2
                width = dp(100)
                height = dp(50)
                margin(TOP, R.id.layout, TOP, 16)
                margin(RIGHT, R.id.btnSetSize, LEFT, 32)
            }

            button(R.id.btnSetSize, { setSize() }) {
                text = getString(R.string.btnInput)
                textSize = 14f
                width = dp(85)
                height = dp(50)
                margin(TOP, R.id.layout, TOP, 16)
                margin(RIGHT, R.id.layout, RIGHT, 32)
            }

            editText(R.id.inputFrom) {
                hint = getString(R.string.inputFrom)
                textSize = 18f
                inputType = 2
                width = dp(50)
                height = dp(50)
                margin(TOP, R.id.inputSize, BOTTOM, 0)
                margin(LEFT, R.id.layout, LEFT, 16)
            }

            editText(R.id.inputTo) {
                hint = getString(R.string.inputTo)
                textSize = 18f
                inputType = 2
                width = dp(50)
                height = dp(50)
                margin(TOP, R.id.inputSize, BOTTOM, 0)
                margin(LEFT, R.id.inputFrom, RIGHT, 6)
                margin(RIGHT, R.id.inputWeight, LEFT, 16)
            }

            editText(R.id.inputWeight) {
                hint = getString(R.string.inputWeight)
                textSize = 18f
                inputType = 2
                width = dp(85)
                height = dp(50)
                margin(TOP, R.id.inputSize, BOTTOM, 0)
                margin(RIGHT, R.id.btnAdd, LEFT, 30)
            }

            button(R.id.btnAdd, { addEdge() }) {
                text = getString(R.string.btnAdd)
                textSize = 24f
                width = dp(50)
                height = dp(50)
                margin(TOP, R.id.inputSize, BOTTOM, 0)
                margin(RIGHT, R.id.layout, RIGHT, 32)
            }

            textView(R.id.ansDescription) {
                text = getString(R.string.startAnsMessage)
                textSize = 20f
                textColor = Color.BLACK
                width = dp(230)
                height = dp(50)
                margin(TOP, R.id.inputFrom, BOTTOM, 8)
                margin(LEFT, R.id.layout, LEFT, 16)
            }

            textView(R.id.distShower) {
                text = getString(R.string.ansText)
                textSize = 24f
                textColor = Color.BLACK
                width = dp(230)
                height = dp(350)
                margin(TOP, R.id.ansDescription, BOTTOM, 16)
                margin(LEFT, R.id.layout, LEFT, 16)
                margin(BOTTOM, R.id.layout, BOTTOM, 50)
            }

            button(R.id.btnCreate, { createGraph() }) {
                text = getString(R.string.btnCreate)
                textSize = 18f
                width = dp(115)
                height = dp(75)
                margin(TOP, R.id.btnAdd, BOTTOM, 75)
                margin(LEFT, R.id.distShower, RIGHT, 8)
                margin(RIGHT, R.id.layout, RIGHT, 30)
            }

            button(R.id.btnRandom, { randomCreate() }) {
                text = getString(R.string.btnRandom)
                textSize = 18f
                width = dp(115)
                height = dp(75)
                margin(TOP, R.id.btnCreate, BOTTOM, 32)
                margin(LEFT, R.id.distShower, RIGHT, 8)
                margin(RIGHT, R.id.layout, RIGHT, 30)
            }

            button(R.id.btnClear, { clear() }) {
                text = getString(R.string.btnClear)
                textSize = 24f
                width = dp(115)
                height = dp(75)
                margin(TOP, R.id.btnRandom, BOTTOM, 32)
                margin(LEFT, R.id.distShower, RIGHT, 8)
                margin(RIGHT, R.id.layout, RIGHT, 30)
                margin(BOTTOM, R.id.layout, BOTTOM, 100)
            }
        })

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

    private fun setSize() {
        val inputSizeView = findViewById<EditText>(R.id.inputSize)
        val inputVertexView = findViewById<EditText>(R.id.inputVertex)
        if (!inputSizeView.text.isEmpty() && !inputVertexView.text.isEmpty()) {
            size = inputSizeView.intText
            from = inputVertexView.intText - 1
            if (size > 0 && from in 0 until size) {
                findViewById<TextView>(R.id.btnSetSize).isEnabled = false
                edges = Array(size, { ArrayList<Pair<Int, Long>>() })
                messageSuccess?.show()
            } else messageError?.show()
            inputSizeView.text = null
            inputVertexView.text = null
        }
    }

    private fun addEdge() {
        val inputFromView = findViewById<EditText>(R.id.inputFrom)
        val inputToView = findViewById<EditText>(R.id.inputTo)
        val inputWeightView = findViewById<EditText>(R.id.inputWeight)
        if (!inputFromView.text.isEmpty() && !inputToView.text.isEmpty() && !inputWeightView.text.isEmpty()) {
            val from = inputFromView.intText
            val to = inputToView.intText
            val weight = inputWeightView.longText

            if (from in 1..size && to in 1..size) {
                edges!![from - 1].add(Pair(to - 1, weight))
                edges!![to - 1].add(Pair(from - 1, weight))
                messageAdded?.show()
            } else {
                messageError?.show()
            }
            inputFromView.text = null
            inputToView.text = null
            inputWeightView.text = null
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


        val distShowerView = findViewById<TextView>(R.id.distShower)
        findViewById<TextView>(R.id.ansDescription).text =
                getString(R.string.ansMessage, from + 1)
        distShowerView.movementMethod = ScrollingMovementMethod()
        distShowerView.text = text
    }

    private fun createGraph() {
        create()
    }

    private fun randomCreate() {
        findViewById<TextView>(R.id.btnCreate).isEnabled = false
        findViewById<TextView>(R.id.btnSetSize).isEnabled = false
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

    private fun clear() {
        size = 0
        findViewById<TextView>(R.id.btnSetSize).isEnabled = true
        findViewById<TextView>(R.id.btnCreate).isEnabled = true
        findViewById<TextView>(R.id.ansDescription).text = null
        findViewById<TextView>(R.id.distShower).text = null
    }
}
