package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.solve

class MainActivity : AppCompatActivity() {
    var n1: Int = 0
    var ccc: Int = 0
    var nn = mutableListOf<MutableList<Int>>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
    }

    fun onCreateGraph() {
        n1 = editTextVertices.text.toString().toInt()
    }

    fun showAnswer() {
        val from = editText6.text.toString().toInt()
        val to = editText7.text.toString().toInt()
        val p1 = Pair(from, to)
        val rez = solve(n1, nn, p1)
        textView3.text = rez.toString()
    }
}
