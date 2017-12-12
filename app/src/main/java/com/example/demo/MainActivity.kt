package com.example.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val buildButton: Button = findViewById(R.id.build)
        val nodesCount: EditText = findViewById(R.id.nodes_count)

        val linkButton: Button = findViewById(R.id.link)
        val cutButton: Button = findViewById(R.id.cut)
        val connectedButton: Button = findViewById(R.id.connected)

        val firstNode: EditText = findViewById(R.id.first_node_input)
        val secondNode: EditText = findViewById(R.id.second_node_input)

    }
}
