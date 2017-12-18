package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.math.min
import my.lib.random
import my.lib.genTest
import my.lib.maxFlow
import dsl.constraintLayout
import dsl.onCLick

class MainActivity : AppCompatActivity() {
    var TextView.value: Int
        get() = text.toString().toInt()
        set(x) {
            text = x.toString()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(R.id.layoutId) {
                    textView(R.id.verticesText) {
                        text = "Number of vertices:"
                        topMargin(R.id.layoutId, TOP, dp(24))
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                    }

                    textView(R.id.edgesText) {
                        text = "Number of edges:"
                        topMargin(R.id.verticesText, BOTTOM, dp(16))
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                    }

                    textView(R.id.capacityText) {
                        text = "Max capacity:"
                        topMargin(R.id.edgesText, BOTTOM, dp(16))
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                    }

                    val vertices = number(R.id.verticesNumber) {
                        topMargin(R.id.layoutId, TOP, dp(24))
                        rightMargin(R.id.layoutId, RIGHT, dp(16))
                    }

                    val edges = number(R.id.edgesNumber) {
                        topMargin(R.id.verticesNumber, BOTTOM, dp(16))
                        rightMargin(R.id.layoutId, RIGHT, dp(16))
                    }

                    val capacity = number(R.id.capacityNumber) {
                        topMargin(R.id.edgesNumber, BOTTOM, dp(16))
                        rightMargin(R.id.layoutId, RIGHT, dp(16))
                    }

                    val ans = number(R.id.flowNumber) {
                        bottomMargin(R.id.layoutId, BOTTOM, dp(52))
                        center(R.id.layoutId)
                    }

                    textView(R.id.flowText) {
                        text = "Maximum Flow"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        bottomMargin(R.id.flowNumber, TOP, 0)
                        center(R.id.layoutId)
                    }

                    button(R.id.startButton) {
                        topMargin(R.id.capacityNumber, BOTTOM, dp(32))
                        rightMargin(R.id.layoutId, RIGHT, dp(44))
                        text = "START"

                        onCLick {
                            val n = vertices.value
                            val m = edges.value
                            val data = genTest(n, m, capacity.value)
                            val flows = maxFlow(n, m, data.v, data.u, data.c, 0, n - 1)
                            var res = 0
                            data.v.forEachIndexed { ind, ver -> if (ver == 1) res += flows[ind] }
                            ans.value = res
                        }
                    }

                    button(R.id.randButton) {
                        topMargin(R.id.startButton, TOP, 0)
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                        rightMargin(R.id.startButton, LEFT, dp(8))
                        text = "RANDOM"

                        onCLick {
                            makeRandomInput()
                        }
                    }
                }.layout
        )
        makeRandomInput()
    }

    private fun makeRandomInput() {
        val n = (2..10).random()
        findViewById<EditText>(R.id.verticesNumber).value = n
        findViewById<EditText>(R.id.edgesNumber).value = (1..min(50, n * (n - 1))).random()
        findViewById<EditText>(R.id.capacityNumber).value = (1..100).random()
    }
}
