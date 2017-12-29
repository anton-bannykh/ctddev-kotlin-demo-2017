package com.example.demo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.solve
import my.lib.Edge
import java.util.ArrayList

fun String.toast(context: Context) {
    Toast.makeText(context, this, Toast.LENGTH_SHORT).show()
}

class MainActivity() : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var addEdge: Button = findViewById(R.id.button2)
        var getFlow: Button = findViewById(R.id.button3)
        var clearData: Button = findViewById(R.id.button)

        var result: TextView = findViewById(R.id.textView6)

        var vertex: EditText = findViewById(R.id.editText5)
        var a: EditText = findViewById(R.id.editText7)
        var b: EditText = findViewById(R.id.editText)
        var weight: EditText = findViewById(R.id.editText2)

        var edges = ArrayList<Edge>()
        var n = 0

        fun updateVertexAmount() {
            if (vertex.text.toString().isEmpty())
                return@updateVertexAmount
            n = vertex.text.toString().toInt()
        }

        getFlow.setOnClickListener({
            updateVertexAmount()
            if (n == 0) {
                "Введите число вершин".toast(this)
                return@setOnClickListener
            }
            result.text = "Поток равен: " + solve(n, edges.toTypedArray()).toString()
        })

        addEdge.setOnClickListener({
            updateVertexAmount()
            if (n == 0) {
                "Введите число вершин".toast(this)
                return@setOnClickListener
            }

            var aString = a.text.toString()
            var bString = b.text.toString()
            var weightString = weight.text.toString()

            if (aString.isEmpty() || bString.isEmpty() || weightString.isEmpty()) {
                "Заполните необходимые поля".toast(this)
                return@setOnClickListener
            }

            var aValue = aString.toInt()
            var bValue = bString.toInt()
            var weightValue = weightString.toInt()

            if (aValue < n && bValue < n && weightValue < 1000000) {
                edges.add(Edge(aValue, bValue, weightValue))
            } else {
                "Введите корректные значения".toast(this)
            }
        })

        clearData.setOnClickListener({
            n = 0
            edges.clear()

            vertex.text.clear()
            a.text.clear()
            b.text.clear()
            weight.text.clear()
        })
    }
}
