package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import my.lib.Graph

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_details)

        val graph = intent.getSerializableExtra("graph") as Graph

        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        val sccResult = graph.getScc()

        val stringBuilder = StringBuilder()

        for (i in 0 until graph.n) {
            val ver = i + 1
            val compId = sccResult[i]
            stringBuilder.append("Вершина $ver находится в $compId-й компоненте сильной связности").append('\n')
        }

        resultTextView.text = stringBuilder.toString()
    }
}
