package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import kotlinx.android.synthetic.*
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Math.min
import java.util.*
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    private var dst: Array<Array<Int>>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)


        val gButton = findViewById<Button>(R.id.gButton)
        val rButton = findViewById<Button>(R.id.rButton)
        val rText = findViewById<TextView>(R.id.rText)
        val from = findViewById<EditText>(R.id.From)
        val to = findViewById<EditText>(R.id.To)
        var test = findViewById<TextView>(R.id.randomTestText)
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
                Toast.makeText(this, "No graph generated", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            } else if (from.text.toString().toInt() > graphSize || to.text.toString().toInt() > graphSize) {
                Toast.makeText(this, "Invalid vertices FROM or TO", Toast.LENGTH_SHORT).show()
                return@setOnClickListener
            }

            rText.text = getDist(from.text.toString().toInt() - 1, to.text.toString().toInt() - 1).toString()
        }
    }

    fun floyd(d: Array<Array<Int>>): Array<Array<Int>> {
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
