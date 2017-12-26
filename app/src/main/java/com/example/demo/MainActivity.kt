package com.example.demo

import android.content.res.Resources
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View.generateViewId
import android.widget.*
import java.util.Random
import java.lang.Math.min

class MainActivity : AppCompatActivity() {
    private var dst: Array<Array<Int>>? = null

    val layoudId = generateViewId()
    val gButtonId = generateViewId()
    val rButtonId = generateViewId()
    val randomTestTextId = generateViewId()
    val fromId = generateViewId()
    val toId = generateViewId()
    val rTextId = generateViewId()
    val text1Id = generateViewId()
    val text2Id = generateViewId()
    val text3Id = generateViewId()

    fun Int.toDp(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)

        setContentView(relativeLayout(layoudId) {
            button(gButtonId, layoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT) {

            }) {
                text = "GENERATE"
            }
            button(rButtonId, layoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT) {
                addRule(RelativeLayout.RIGHT_OF, gButtonId)
            }) {
                text = "FIND DISTANCE"
            }
            textView(randomTestTextId, layoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT) {
                addRule(RelativeLayout.BELOW, gButtonId)
            }) {
                textSize = 30f
                setTextColor(Color.BLACK)
            }
            editText(fromId, layoutParams(105.toDp(), 46.toDp()) {
                addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                addRule(RelativeLayout.ALIGN_PARENT_LEFT)
                addRule(RelativeLayout.ALIGN_PARENT_START)
            }) {
                text = "1"
                setEms(10)
            }
            editText(toId, layoutParams(105.toDp(), 46.toDp()) {
                addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                addRule(RelativeLayout.RIGHT_OF, fromId)
            }) {
                text = "1"
                setEms(10)
            }
            textView(rTextId, layoutParams(RelativeLayout.LayoutParams.MATCH_PARENT, RelativeLayout.LayoutParams.WRAP_CONTENT) {
                addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                addRule(RelativeLayout.RIGHT_OF, toId)
            }) {
                textSize = 35f
                setTextColor(Color.BLACK)
                text = "0"
            }
            textView(text1Id, layoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT) {
                addRule(RelativeLayout.ABOVE, fromId)
                addRule(RelativeLayout.ALIGN_PARENT_LEFT)
            }) {
                textSize = 20f
                setTextColor(Color.BLACK)
                text = "From"
            }
            textView(text2Id, layoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT) {
                addRule(RelativeLayout.ABOVE, toId)
                addRule(RelativeLayout.RIGHT_OF, text1Id)
                addRule(RelativeLayout.ALIGN_START, toId)
            }) {
                textSize = 20f
                setTextColor(Color.BLACK)
                text = "To"
            }
            textView(text3Id, layoutParams(RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT) {
                addRule(RelativeLayout.ABOVE, rTextId)
                addRule(RelativeLayout.RIGHT_OF, text2Id)
                addRule(RelativeLayout.ALIGN_START, rTextId)
            }) {
                textSize = 20f
                setTextColor(Color.BLACK)
                text = "Result"
            }
        })

        val test: TextView = findViewById(randomTestTextId)
        val gButton: Button = findViewById(gButtonId)
        val rButton: Button = findViewById(rButtonId)
        val from: EditText = findViewById(fromId)
        val to: EditText = findViewById(toId)
        val rText: TextView = findViewById(rTextId)
        var s = ""
        test.text = s
        var haveGraph = false
        var graphSize = 0
        gButton.setOnClickListener {
            gButton.text = "Regenerate"
            val n = Random().nextInt(9) + 1
            val d = Array(n, { Array(n, { 0 }) })
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (i == j) {
                        d[i][j] = 0
                    } else {
                        d[i][j] = Random().nextInt(15) + 1
                    }
                }
            }
            haveGraph = true
            graphSize = n
            s = n.toString() + "\n" + Array(n, { i -> d[i].joinToString(" ", "", "") }).joinToString("\n", "", "")
            test.text = s
            dst = floyd(d)
        }
        rButton.setOnClickListener {
            if (!haveGraph) {
                var toast = Toast.makeText(this, "No graph generated", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                return@setOnClickListener
            } else if (from.text.toString().toInt() > graphSize || to.text.toString().toInt() > graphSize) {
                var toast = Toast.makeText(this, "Invalid vertices FROM or TO", Toast.LENGTH_SHORT)
                toast.setGravity(Gravity.CENTER, 0, 0)
                toast.show()
                return@setOnClickListener
            }

            rText.text = getDist(from.text.toString().toInt() - 1, to.text.toString().toInt() - 1).toString()
        }
    }

    private fun floyd(d: Array<Array<Int>>): Array<Array<Int>> {
        var n = d.size
        val inf = Int.MAX_VALUE
        for (i in 0 until n) {
            for (j in 0 until n) {
                if (d[i][j] == 0) {
                    if (i == j) {
                        d[i][j] = 0
                    } else {
                        d[i][j] = inf
                    }
                }
            }
        }
        for (k in 0 until n) {
            for (i in 0 until n) {
                for (j in 0 until n) {
                    if (d[i][k] < inf && d[k][j] < inf) {
                        d[i][j] = min(d[i][j], d[i][k] + d[k][j])
                    }
                }
            }
        }
        return d
    }

    private fun getDist(a: Int, b: Int) = dst!![a][b]
}
