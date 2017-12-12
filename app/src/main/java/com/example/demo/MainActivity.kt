package com.example.maxim.kruskal

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kruskal.Edge
import kruskal.getWeightOfMST

class MainActivity : AppCompatActivity() {

    lateinit var vertecesTextEditor: EditText
    lateinit var uTextEditor: EditText
    lateinit var vTextEditor: EditText
    lateinit var wTextEditor: EditText
    lateinit var resultTextView: TextView
    var n = 0
    val l = ArrayList<Edge>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        vertecesTextEditor = findViewById(R.id.verteces_input)
        uTextEditor = findViewById(R.id.u_input)
        vTextEditor = findViewById(R.id.v_input)
        wTextEditor = findViewById(R.id.w_input)
        resultTextView = findViewById(R.id.tv_result_output)
    }

    fun click1(v: View) {
        n = vertecesTextEditor.text.toString().toInt()
    }

    fun click2(v: View) {
        l.add(Edge(uTextEditor.text.toString().toInt(),
                vTextEditor.text.toString().toInt(),
                wTextEditor.text.toString().toInt()))
    }

    fun click3(v: View) {
        resultTextView.setText(getWeightOfMST(n, l.toTypedArray()).toString())
    }
}
