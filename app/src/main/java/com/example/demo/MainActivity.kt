package com.example.demo

import android.content.res.Resources
import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.RelativeLayout
import java.util.Random
import android.widget.Toast
import java.lang.Math.min

class MainActivity : AppCompatActivity() {
    private var dst: Array<Array<Int>>? = null

    fun Int.toDp(): Int = (this * Resources.getSystem().displayMetrics.density).toInt()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //setContentView(R.layout.activity_main)

        var myLayout = RelativeLayout(this)
        var gButton = Button(this)
        gButton.id = 1
        gButton.setText("GENERATE")
        var generateBDetails = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        myLayout.addView(gButton, generateBDetails)

        var rButton = Button(this)
        rButton.setText("FIND DISTANCE")
        var resultBDetails = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        resultBDetails.addRule(RelativeLayout.RIGHT_OF, gButton.id)
        myLayout.addView(rButton, resultBDetails)

        var randomTestText = TextView(this)
        randomTestText.setTextSize(30f)
        randomTestText.setTextColor(Color.BLACK)
        var randomTestTextDetails = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        randomTestTextDetails.addRule(RelativeLayout.BELOW, gButton.id)
        myLayout.addView(randomTestText, randomTestTextDetails)

        var from = EditText(this)
        from.setText("1")
        from.setEms(10)
        from.id = 2
        var fromDetails = RelativeLayout.LayoutParams(105.toDp(), 46.toDp())
        fromDetails.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        fromDetails.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        fromDetails.addRule(RelativeLayout.ALIGN_PARENT_START)
        myLayout.addView(from, fromDetails)

        var to = EditText(this)
        to.setText("1")
        to.setEms(10)
        to.id = 3
        var toDetails = RelativeLayout.LayoutParams(105.toDp(), 46.toDp())
        toDetails.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        toDetails.addRule(RelativeLayout.RIGHT_OF, from.id)
        myLayout.addView(to, toDetails)

        var rText = TextView(this)
        rText.id = 4
        rText.setTextSize(35f)
        rText.setTextColor(Color.BLACK)
        rText.setText("0")
        var rTextDetails = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.MATCH_PARENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        rTextDetails.addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
        rTextDetails.addRule(RelativeLayout.RIGHT_OF, to.id)
        myLayout.addView(rText, rTextDetails)

        var text1 = TextView(this)
        text1.id = 5
        text1.setTextSize(20f)
        text1.setTextColor(Color.BLACK)
        text1.setText("From")
        var text1Details = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        text1Details.addRule(RelativeLayout.ABOVE, from.id)
        text1Details.addRule(RelativeLayout.ALIGN_PARENT_LEFT)
        myLayout.addView(text1, text1Details)

        var text2 = TextView(this)
        text2.id = 6
        text2.setTextSize(20f)
        text2.setTextColor(Color.BLACK)
        text2.setText("To")
        var text2Details = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        text2Details.addRule(RelativeLayout.ABOVE, to.id)
        text2Details.addRule(RelativeLayout.RIGHT_OF, text1.id)
        text2Details.addRule(RelativeLayout.ALIGN_START, to.id)
        myLayout.addView(text2, text2Details)

        var text3 = TextView(this)
        text3.setTextSize(20f)
        text3.setTextColor(Color.BLACK)
        text3.setText("Result")
        var text3Details = RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT,
                RelativeLayout.LayoutParams.WRAP_CONTENT
        )
        text3Details.addRule(RelativeLayout.ABOVE, rText.id)
        text3Details.addRule(RelativeLayout.RIGHT_OF, text2.id)
        text3Details.addRule(RelativeLayout.ALIGN_START, rText.id)
        myLayout.addView(text3, text3Details)

        setContentView(myLayout)

        val test = randomTestText
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
