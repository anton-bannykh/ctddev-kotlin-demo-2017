package com.example.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import my.lib.EulerTourTree
import java.lang.Integer.parseInt
import java.lang.String.format


class MainActivity : AppCompatActivity() {

    private var currentTree: EulerTourTree = EulerTourTree(0)

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val answerField: TextView = findViewById(R.id.answer)
        val treeField: TextView = findViewById(R.id.tree)

        val nodesCount: EditText = findViewById(R.id.nodes_count_input)
        val buildButton: Button = findViewById(R.id.build)

        buildButton.setOnClickListener(View.OnClickListener() {
            fun onClick(v: View) {
                currentTree = EulerTourTree(parseInt(nodesCount.getText().toString()))
                refreshOutput(treeField)
            }
        })

        val firstNode: EditText = findViewById(R.id.first_node_input)
        val secondNode: EditText = findViewById(R.id.second_node_input)

        val linkButton: Button = findViewById(R.id.link)
        val cutButton: Button = findViewById(R.id.cut)
        val connectedButton: Button = findViewById(R.id.connected)
        val sizeButton: Button = findViewById(R.id.size)

        linkButton.setOnClickListener(View.OnClickListener() {
            fun onClick(v: View) {
                currentTree.link(parseInt(firstNode.getText().toString()),
                        parseInt(secondNode.getText().toString()))
                refreshOutput(treeField)
            }
        })

        cutButton.setOnClickListener(View.OnClickListener() {
            fun onClick(v: View) {
                currentTree.cut(parseInt(firstNode.getText().toString()),
                        parseInt(secondNode.getText().toString()))
                refreshOutput(treeField)
            }
        })

        connectedButton.setOnClickListener(View.OnClickListener() {
            fun onClick(v: View) {
                val firstNode: Int = parseInt(firstNode.getText().toString())
                val secondNode: Int = parseInt(secondNode.getText().toString())
                if (currentTree.connected(firstNode, secondNode)) {
                    answerField.setText(format("%d and %d are connected", firstNode, secondNode))
                } else {
                    answerField.setText(format("%d and %d are not connected", firstNode, secondNode))
                }
            }
        })

        sizeButton.setOnClickListener(View.OnClickListener() {
            fun onClick(v: View) {
                answerField.setText(currentTree.sizeOfNodesComponent(parseInt(firstNode.getText().toString())))
            }
        })
    }

    private fun refreshOutput(treeField: TextView) {
        if (currentTree.size == 0) {
            treeField.setText(0)
            return
        }

        var tree = ""
        var wasVisited: Array<Boolean> = Array(currentTree.size + 1, { false })
        for (i in 1..currentTree.size + 1) {
            if (!wasVisited[i]) {
                tree += i
                for (j in i + 1..currentTree.size + 1) {
                    if (currentTree.connected(i, j)) {
                        tree += format(" <-> %d", j)
                    }
                }
                tree += "\n"
            }
        }
        tree = tree.trim()
        treeField.setText(tree)
    }
}
