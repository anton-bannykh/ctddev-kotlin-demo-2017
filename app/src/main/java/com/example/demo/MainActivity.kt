package com.example.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import my.lib.EulerTourTree
import java.lang.Integer.parseInt
import java.lang.String.format
import java.lang.String.valueOf

class MainActivity : AppCompatActivity() {

    private var currentTree: EulerTourTree = EulerTourTree(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val answerField: TextView = findViewById(R.id.answer)

        val nodesCount: EditText = findViewById(R.id.nodes_count_input)
        val buildButton: Button = findViewById(R.id.build)

        buildButton.setOnClickListener {
            currentTree = EulerTourTree(parseInt(nodesCount.getText().toString()))
            refreshOutput()
        }

        val firstNode: EditText = findViewById(R.id.first_node_input)
        val secondNode: EditText = findViewById(R.id.second_node_input)

        val linkButton: Button = findViewById(R.id.link)
        val cutButton: Button = findViewById(R.id.cut)
        val connectedButton: Button = findViewById(R.id.connected)
        val sizeButton: Button = findViewById(R.id.size)

        linkButton.setOnClickListener {
            currentTree.link(parseInt(firstNode.getText().toString()),
                    parseInt(secondNode.getText().toString()))
            refreshOutput()
        }

        cutButton.setOnClickListener {
            currentTree.cut(parseInt(firstNode.getText().toString()),
                    parseInt(secondNode.getText().toString()))
            refreshOutput()
        }

        connectedButton.setOnClickListener {
            val firstNode: Int = parseInt(firstNode.getText().toString())
            val secondNode: Int = parseInt(secondNode.getText().toString())
            if (currentTree.connected(firstNode, secondNode)) {
                answerField.setText(format("%d and %d are connected", firstNode, secondNode))
            } else {
                answerField.setText(format("%d and %d are not connected", firstNode, secondNode))
            }

        }

        sizeButton.setOnClickListener {
            answerField.setText(valueOf(currentTree.sizeOfNodesComponent(parseInt(firstNode.getText()
                    .toString()))))
        }
    }

    private fun refreshOutput() {
        refreshTree()
        refreshConnections()
    }

    private fun refreshTree() {
        val treeField: TextView = findViewById(R.id.tree)

        if (currentTree.size == 0) {
            return
        }

        var tree = ""
        var wasVisited: Array<Boolean> = Array(currentTree.size + 1, { false })
        for (i in 1..currentTree.size) {
            if (!wasVisited[i]) {
                wasVisited[i] = true
                tree += i
                for (j in i + 1..currentTree.size) {
                    if (currentTree.connected(i, j)) {
                        tree += format(" <~> %d", j)
                        wasVisited[j] = true
                    }
                }
                tree += "\n"
            }
        }
        tree = tree.trim()
        treeField.setText(tree)
    }

    private fun refreshConnections() {
        val connectionsField: TextView = findViewById(R.id.connections)

        if (currentTree.size == 0) {
            return
        }

        var connections = ""
        val edges: Array<String> = Array(currentTree.size + 1, { i -> format("%d is connected to:", i) })
        for (i in 1..currentTree.size) {
            for (j in 1..currentTree.size) {
                if (currentTree.directlyConnected(i, j)) {
                    edges[i] += format(" %d", j)
                }
            }
            connections += format("%s\n", edges[i])
        }
        connections = connections.trim()
        connectionsField.setText(connections)
    }
}
