package com.example.demo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import dsl.constraintLayout
import dsl.onCLick
import my.lib.matrixPower

class MainActivity : AppCompatActivity() {
    var TextView.value: String
        get() {
            return text.toString()
        }
        set(x) {
            text = x
        }


    private fun solver(vertices: Int, k: Int, listOfEdges: List<String>): String {

        val g = Array(vertices, { IntArray(vertices) })
        val modulo: Int = 115249

        var i: Int = 0
        while (i < listOfEdges.size) {
            val fromEdge: Int = listOfEdges[i].toInt() - 1
            val toEdge: Int = listOfEdges[i + 1].toInt() - 1
            i += 2
            g[fromEdge][toEdge] += 1
        }
        val kk: Long = k.toLong()
        val answer = matrixPower(g, kk, modulo)

        var toShow = ""
        for (line in answer) {
            toShow += (line.joinToString(" ") + "\n")
        }
        return toShow
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(R.id.layoutId) {
                    textView(R.id.title) {
                        text = "List of Edges"
                        topMargin(R.id.length, TOP, dp(52))
                    }

                    val vers = editText(R.id.vert) {
                        topMargin(R.id.layoutId, TOP, dp(52))
                        hint = "Number of vertices"
                    }

                    val k = editText(R.id.length) {
                        topMargin(R.id.vert, TOP, dp(52))
                        hint = "Length"
                    }

                    val listOfEdges = editTextMulti(R.id.listEdges) {
                        topMargin(R.id.length, TOP, dp(52))
                    }

                    val answer = editTextMulti(R.id.forAnswer) {
                        topMargin(R.id.solve, TOP, dp(52))
                    }

                    button(R.id.solve) {
                        topMargin(R.id.listEdges, BOTTOM, dp(52))
                        text = "Show answer"

                        onCLick {
                            val v = vers.value.toInt()
                            val kk = k.value.toInt()
                            val setE = listOfEdges.value.split("\u0020", "\u000A").filter{it != ""}
                            answer.value = solver(v, kk, setE)
                        }
                    }
                }
        )
    }
}