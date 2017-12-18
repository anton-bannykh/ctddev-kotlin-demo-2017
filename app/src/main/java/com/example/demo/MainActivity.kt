package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.math.min
import my.lib.random
import my.lib.genTest
import my.lib.maxFlow
import dsl.constraintLayout

class MainActivity : AppCompatActivity() {
    var TextView.value: Int
        get() = text.toString().toInt()
        set(x) {
            text = x.toString()
        }

    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM

    val layoutId = 1
    val text1Id = 11
    val text2Id = 12
    val text3Id = 13
    val text4Id = 14
    val number1Id = 21
    val number2Id = 22
    val number3Id = 23
    val number4Id = 24
    val startId = 31
    val randId = 32

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(layoutId) {
                    textView(text1Id) {
                        text = "Number of vertices:"
                        topMargin(layoutId, TOP, dp(24))
                        leftMargin(layoutId, LEFT, dp(8))
                    }

                    textView(text2Id) {
                        text = "Number of edges:"
                        topMargin(text1Id, BOTTOM, dp(16))
                        leftMargin(layoutId, LEFT, dp(8))
                    }

                    textView(text3Id) {
                        text = "Max capacity:"
                        topMargin(text2Id, BOTTOM, dp(16))
                        leftMargin(layoutId, LEFT, dp(8))
                    }

                    number(number1Id) {
                        topMargin(layoutId, TOP, dp(24))
                        rightMargin(layoutId, RIGHT, dp(16))
                    }

                    number(number2Id) {
                        topMargin(number1Id, BOTTOM, dp(16))
                        rightMargin(layoutId, RIGHT, dp(16))
                    }

                    number(number3Id) {
                        topMargin(number2Id, BOTTOM, dp(16))
                        rightMargin(layoutId, RIGHT, dp(16))
                    }

                    number(number4Id) {
                        bottomMargin(layoutId, BOTTOM, dp(52))
                        center(layoutId)
                    }

                    textView(text4Id) {
                        text = "Maximum Flow"
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        bottomMargin(number4Id, TOP, 0)
                        center(layoutId)
                    }

                    button(startId) {
                        topMargin(number3Id, BOTTOM, dp(32))
                        rightMargin(layoutId, RIGHT, dp(44))
                        text = "START"

                        onCLick {
                            val n = this@MainActivity.findViewById<EditText>(number1Id).value
                            val m = this@MainActivity.findViewById<EditText>(number2Id).value
                            val data = genTest(n, m, this@MainActivity.findViewById<EditText>(number3Id).value)
                            val flows = maxFlow(n, m, data.v, data.u, data.c, 0, n - 1)
                            var res = 0
                            data.v.forEachIndexed { ind, ver -> if (ver == 1) res += flows[ind] }
                            this@MainActivity.findViewById<EditText>(number4Id).value = res
                        }
                    }

                    button(randId) {
                        topMargin(startId, TOP, 0)
                        leftMargin(layoutId, LEFT, dp(8))
                        rightMargin(startId, LEFT, dp(8))
                        text = "RANDOM"

                        onCLick {
                            makeRandomInput()
                        }
                    }
                }
        )
        makeRandomInput()
    }

    private fun makeRandomInput() {
        val n = (2..10).random()
        findViewById<EditText>(number1Id).value = n
        findViewById<EditText>(number2Id).value = (1..min(50, n * (n - 1))).random()
        findViewById<EditText>(number3Id).value = (1..100).random()
    }
}
