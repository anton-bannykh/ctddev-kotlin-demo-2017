package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import my.lib.Graph
import android.content.Intent
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var graph: Graph? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val vertexUTextField = findViewById<EditText>(R.id.vertexU)
        val vertexVTextField = findViewById<EditText>(R.id.vertexV)

        val addEdgeButton = findViewById<Button>(R.id.addEdgeButton)
        addEdgeButton.isEnabled = false

        val runAlgorithmButton = findViewById<Button>(R.id.runAlgorithmButton)
        runAlgorithmButton.isEnabled = false

        val vertexAmountTextField = findViewById<EditText>(R.id.vertexAmount)
        val createGraphButton = findViewById<Button>(R.id.button)
        createGraphButton.setOnClickListener {
            val n = Integer.parseInt(vertexAmountTextField.text.toString())
            graph = Graph(n)
            addEdgeButton.isEnabled = true
            runAlgorithmButton.isEnabled = true
            Toast.makeText(this, "Граф из $n вешрин был успешно создан!", Toast.LENGTH_SHORT).show()
        }

        addEdgeButton.setOnClickListener {
            val u = Integer.parseInt(vertexUTextField.text.toString())
            val v = Integer.parseInt(vertexVTextField.text.toString())
            graph!!.addEdge(u - 1, v - 1)
            Toast.makeText(this, "Ребро из $u в $v было успешно добавлено!", Toast.LENGTH_SHORT).show()
        }

        runAlgorithmButton.setOnClickListener {
            addEdgeButton.isEnabled = false
            runAlgorithmButton.isEnabled = false
            runAlgorithm()
        }
    }

    private fun runAlgorithm() {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("graph", graph)
        startActivity(intent)
    }
}
