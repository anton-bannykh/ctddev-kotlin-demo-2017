package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_main.editText
import kotlinx.android.synthetic.main.activity_main.button2
import kotlinx.android.synthetic.main.activity_main.listComps
import kotlinx.android.synthetic.main.activity_main.listEdges
import kotlinx.android.synthetic.main.activity_main.spinner
import kotlinx.android.synthetic.main.activity_main.spinner2
import my.lib.components

class MainActivity : AppCompatActivity() {
    var graph = ArrayList<ArrayList<Int>>()
    var edges: ArrayAdapter<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val n = editText.text.toString().toInt()
            graph = ArrayList()
            edges = ArrayAdapter(this, android.R.layout.simple_spinner_item, ArrayList())
            listEdges.adapter = edges
            for (i in 1..n) {
                graph.add(ArrayList())
            }
            spinner.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Array(n, { "${it + 1}" }))
            spinner2.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Array(n, { "${it + 1}" }))
        }
        button2.setOnClickListener {
            val from = spinner.selectedItem.toString().toInt() - 1
            val to = spinner2.selectedItem.toString().toInt() - 1
            graph[from].add(to)
            edges?.add("${from + 1} -> ${to + 1}")
            val comps = components(graph)
            listComps.adapter = ArrayAdapter(this, android.R.layout.simple_spinner_item, Array(comps.size, { "${it + 1} <-> ${comps[it]}" }))

        }
    }
}
