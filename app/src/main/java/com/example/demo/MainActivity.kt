package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.solve
import dsl.constraintLayout
import dsl.onCLick

class MainActivity : AppCompatActivity() {
    var n1: Int = 0
    var ccc: Int = 0
    var nn = mutableListOf<MutableList<Int>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(R.id.layoutId) {
                    editText(R.id.editTextVertices) {
                        width = dp(300)
                        hint = "enter number of vertices"
                        topMargin(R.id.layoutId, TOP, dp(8))
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                        rightMargin(R.id.layoutId, LEFT, dp(8))

                    }

                    button(R.id.buttonAddgraf) {
                        topMargin(R.id.editTextVertices, BOTTOM, dp(8))
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                        rightMargin(R.id.layoutId, LEFT, dp(8))
                        text = "create graf"
                        onCLick {
                            onCreateGraph()
                        }
                    }

                    textView(R.id.textView) {
                        topMargin(R.id.buttonAddgraf, BOTTOM, dp(16))
                        width = dp(190)
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                        rightMargin(R.id.layoutId, LEFT, dp(8))
                        text = "add n - 1 edges"
                    }

                    editText(R.id.editText3) {
                        hint = "from"
                        width = dp(70)
                        height = dp(80)
                        topMargin(R.id.textView, TOP, dp(24))
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                    }

                    editText(R.id.editText4) {
                        hint = "to"
                        width = dp(70)
                        height = dp(80)
                        topMargin(R.id.textView, TOP, dp(24))
                        leftMargin(R.id.editText3, RIGHT, dp(8))
                    }

                    editText(R.id.editText5) {
                        hint = "weight"
                        width = dp(70)
                        height = dp(80)
                        topMargin(R.id.textView, TOP, dp(24))
                        leftMargin(R.id.editText4, RIGHT , dp(8))
                    }

                    button(R.id.buttonAdd1) {
                        width = dp(70)
                        height = dp(80)
                        topMargin(R.id.textView, BOTTOM, dp(0))
                        leftMargin(R.id.editText5, RIGHT , dp(8))
                        text = "add edge"
                        onCLick {
                            onAddEdge()
                        }
                    }

                    textView(R.id.textView1) {
                        width = dp(200)
                        topMargin(R.id.buttonAdd1, BOTTOM, dp(24))
                        text = "enter your request"
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                        rightMargin(R.id.layoutId, LEFT, dp(8))
                    }

                    editText(R.id.editText6) {
                        hint = "from"
                        width = dp(80)
                        height = dp(80)
                        topMargin(R.id.textView1, TOP, dp(24))
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                    }

                    editText(R.id.editText7) {
                        hint = "to"
                        width = dp(80)
                        height = dp(80)
                        topMargin(R.id.textView1, TOP, dp(24))
                        leftMargin(R.id.editText6, RIGHT, dp(8))
                    }

                    button(R.id.buttonAdd) {
                        width = dp(120)
                        height = dp(80)
                        topMargin(R.id.textView1, BOTTOM, dp(0))
                        leftMargin(R.id.editText7, RIGHT, dp(8))
                        text = "Show Answer"
                        onCLick {
                            showAnswer()
                        }
                    }

                    textView(R.id.textView3) {
                        width = dp(280)
                        topMargin(R.id.buttonAdd, BOTTOM, dp(24))
                        hint = "here will be your answer"
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                        rightMargin(R.id.layoutId, LEFT, dp(8))
                    }
                }
        )
    }

    fun onAddEdge() {
        val from = editText3.text.toString().toInt()
        val to = editText4.text.toString().toInt()
        val weight = editText5.text.toString().toInt()
        val a = mutableListOf(from, to, weight)
        nn.add(a)
        ccc += 1
        var z: Int = n1 - ccc - 1
        textView.text = "add $z more edges"
        editText3.text.clear()
        editText4.text.clear()
        editText5.text.clear()
    }

    fun onCreateGraph() {
        ccc = 0
        nn.clear()
        n1 = editTextVertices.text.toString().toInt()
        var zz = n1 - 1
        textView.text = "add $zz more edges"
    }

    fun showAnswer() {
        val from = editText6.text.toString().toInt()
        val to = editText7.text.toString().toInt()
        val p1 = Pair(from, to)
        val rez = solve(n1, nn, p1)
        textView3.text = rez.toString()
        editText6.text.clear()
        editText7.text.clear()
    }
}
