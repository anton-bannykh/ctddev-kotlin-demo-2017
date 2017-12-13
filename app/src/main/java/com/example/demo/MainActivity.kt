package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import my.lib.minSpanningTreeWeight
import my.lib.Edge

class MainActivity : AppCompatActivity() {
    val graph = ArrayList<Edge>()
    var n = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addEdge (view: View) {
        val vertexA = findViewById<EditText>(R.id.editText4)
        val vertexB = findViewById<EditText>(R.id.editText6)
        val edgeWeight = findViewById<EditText>(R.id.editText5)
        graph.add(Edge(vertexA.getText().toString().toInt(),
                vertexB.getText().toString().toInt(),
                edgeWeight.getText().toString().toInt()))
    }

    fun getAns (view: View) {
        val ans = findViewById<TextView>(R.id.textView2)
        ans.text =  minSpanningTreeWeight(n, graph).toString()
    }

    fun deleteGraph (view: View) {
        graph.clear()
    }

    fun numOfEdges (view: View) {
        val num = findViewById<EditText>(R.id.editText)
        n = num.getText().toString().toInt()
    }
}
