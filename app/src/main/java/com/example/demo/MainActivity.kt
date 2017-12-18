package com.example.demo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import my.lib.getMaxMatching

val EditText.int: Int
    get() = ("0" + text.toString()).toInt()

fun String.toast(context: Context) {
    Toast.makeText(context, this,
            Toast.LENGTH_SHORT).show()
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val graph = findViewById<GraphView>(R.id.graph_view)
        val buttonRefresh = findViewById<Button>(R.id.button_refresh)
        val buttonAdd = findViewById<Button>(R.id.button_add)
        val buttonRemove = findViewById<Button>(R.id.button_remove)
        val buttonFind = findViewById<Button>(R.id.find)
        val number_n = findViewById<EditText>(R.id.number_n)
        val number_m = findViewById<EditText>(R.id.number_m)
        val edge_u = findViewById<EditText>(R.id.edge_u)
        val edge_v = findViewById<EditText>(R.id.edge_v)

        buttonRefresh.setOnClickListener({
            if (number_n.int < 1 || number_m.int < 1) {
                "Размер доли не может быть меньше 1".toast(this)
                return@setOnClickListener
            }
            if (number_n.int > graph.recommendedNum || number_m.int > graph.recommendedNum) {
                "Граф не помещается на экран".toast(this)
                return@setOnClickListener
            }
            graph.leftSideNum = number_n.int
            graph.rightSideNum = number_m.int
            graph.edges = (graph.edges.filter { pair -> pair.first in 1..number_n.int && pair.second in 1..number_m.int }).toMutableList()
            graph.matching = listOf()
            graph.invalidate()
        })

        buttonAdd.setOnClickListener({
            if (edge_u.int < 1 || edge_u.int > graph.leftSideNum ||
                    edge_v.int < 1 || edge_v.int > graph.rightSideNum) {
                "Невозможно добавить такое ребро".toast(this)
                return@setOnClickListener
            }
            if (graph.edges.contains(edge_u.int to edge_v.int)) {
                "Это ребро уже существует".toast(this)
                return@setOnClickListener
            }
            graph.edges.add(edge_u.int to edge_v.int)
            graph.matching = listOf()
            graph.invalidate()
        })

        buttonRemove.setOnClickListener({
            if (!graph.edges.contains(edge_u.int to edge_v.int)) {
                "Это ребро не существует".toast(this)
                return@setOnClickListener
            }
            graph.edges.remove(edge_u.int to edge_v.int)
            graph.matching = listOf()
            graph.invalidate()
        })

        buttonFind.setOnClickListener({
            graph.matching = getMaxMatching(number_n.int, number_m.int, graph.edges)
            graph.invalidate()
        })
    }
}
