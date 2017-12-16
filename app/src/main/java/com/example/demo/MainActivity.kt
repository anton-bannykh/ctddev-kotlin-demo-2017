package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import my.lib.matrixPower

class MainActivity : AppCompatActivity() {

    fun solver(v: View) {

        val tVert: TextView = findViewById(R.id.vert)
        val vers: Int = tVert.text.toString().toInt()

        val tLength: TextView = findViewById(R.id.length)
        val k = tLength.text.toString().toLong()

        val tForEdges: TextView = findViewById(R.id.forEdges)
        val listOfEdges = tForEdges.text.toString().split("\u0020", "\u000A").filter { it != "" } //000A

        val g = Array(vers, { IntArray(vers) })
        val modulo: Int = 115249

        var i: Int = 0
        while (i < listOfEdges.size) {
            val fromEdge: Int = listOfEdges[i].toInt() - 1
            val toEdge: Int = listOfEdges[i + 1].toInt() - 1
            i += 2
            g[fromEdge][toEdge] += 1
        }

        val answer = matrixPower(g, k, modulo)

        var toShow = ""
        for (line in answer) {
            toShow += line.joinToString(" ") + "\n"
        }

        val tNewPlace: TextView = findViewById(R.id.forAnswer)
        tNewPlace.text = toShow
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

}
