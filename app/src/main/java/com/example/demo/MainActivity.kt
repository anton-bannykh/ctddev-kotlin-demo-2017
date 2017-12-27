package com.example.demo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
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
        setContentView(layout(R.id.main_layout) {
            addTextView(R.id.nodes_count) {
                width = matchParent()
                height = convertIntToDp(20)
                text = resources.getText(R.string.node_count)
            }

            addEditText(R.id.nodes_count_input) {
                width = matchParent()
                height = convertIntToDp(40)
                textSize = 18f
                setText(R.string.zero)
            }

            addButton(R.id.build) {
                width = matchParent()
                height = convertIntToDp(40)
                text = resources.getText(R.string.build)
                textSize = 15f
            }

            addTextView(R.id.first_node_hint) {
                width = matchParent()
                height = convertIntToDp(20)
                text = resources.getText(R.string.first_node)
            }

            addEditText(R.id.first_node_input) {
                width = matchParent()
                height = convertIntToDp(40)
                textSize = 18f
                setText(R.string.zero)
            }

            addTextView(R.id.second_node_hint) {
                width = matchParent()
                height = convertIntToDp(20)
                text = resources.getText(R.string.second_node)
            }

            addEditText(R.id.second_node_input) {
                width = matchParent()
                height = convertIntToDp(40)
                textSize = 18f
                setText(R.string.zero)
            }

            addButton(R.id.link) {
                width = matchParent()
                height = convertIntToDp(20)
                text = resources.getText(R.string.link)
                textSize = 15f
                setTextColor(Color.GREEN)
                setBackgroundColor(Color.GRAY)

            }

            addButton(R.id.cut) {
                width = matchParent()
                height = convertIntToDp(20)
                text = resources.getText(R.string.cut)
                textSize = 15f
                setTextColor(Color.RED)
                setBackgroundColor(Color.GRAY)

            }

            addButton(R.id.connected) {
                width = matchParent()
                height = convertIntToDp(20)
                text = resources.getText(R.string.connected)
                textSize = 15f
                setTextColor(Color.BLUE)
                setBackgroundColor(Color.GRAY)

            }

            addButton(R.id.size) {
                width = matchParent()
                height = convertIntToDp(20)
                text = resources.getText(R.string.size)
                textSize = 15f
                setTextColor(Color.BLUE)
                setBackgroundColor(Color.GRAY)

            }

            addTextView(R.id.answer) {
                width = matchParent()
                height = convertIntToDp(20)
                text = resources.getText(R.string.answer_tip)
                setTextSize(17f)
            }

            addTextView(R.id.connections) {
                width = matchParent()
                height = convertIntToDp(80)
                text = resources.getText(R.string.connections_tip)
                setTextSize(17f)
            }

            addTextView(R.id.tree) {
                width = matchParent()
                height = convertIntToDp(80)
                text = resources.getText(R.string.tree_tip)
                setTextSize(17f)
            }
        })

        val answerField: TextView = findViewById(R.id.answer)

        val nodesCount: EditText = findViewById(R.id.nodes_count_input)
        val buildButton: Button = findViewById(R.id.build)

        buildButton.setOnClickListener {
            currentTree = EulerTourTree(parseInt(nodesCount.text.toString()))
            refreshOutput()
        }

        val firstNode: EditText = findViewById(R.id.first_node_input)
        val secondNode: EditText = findViewById(R.id.second_node_input)

        val linkButton: Button = findViewById(R.id.link)
        val cutButton: Button = findViewById(R.id.cut)
        val connectedButton: Button = findViewById(R.id.connected)
        val sizeButton: Button = findViewById(R.id.size)

        linkButton.setOnClickListener {
            val firstNode = parseInt(firstNode.text.toString())
            val secondNode = parseInt(secondNode.text.toString())
            if (firstNode <= currentTree.size && secondNode <= currentTree.size) {
                currentTree.link(firstNode, secondNode)
                refreshOutput()
            }
        }

        cutButton.setOnClickListener {
            val firstNode = parseInt(firstNode.text.toString())
            val secondNode = parseInt(secondNode.text.toString())
            if (firstNode <= currentTree.size && secondNode <= currentTree.size) {
                currentTree.cut(firstNode, secondNode)
                refreshOutput()
            }
        }

        connectedButton.setOnClickListener {
            val firstNode = parseInt(firstNode.text.toString())
            val secondNode = parseInt(secondNode.text.toString())
            if (firstNode <= currentTree.size && secondNode <= currentTree.size) {
                if (currentTree.connected(firstNode, secondNode)) {
                    answerField.text = format("%d and %d are connected", firstNode, secondNode)
                } else {
                    answerField.text = format("%d and %d are not connected", firstNode, secondNode)
                }
            }
        }

        sizeButton.setOnClickListener {
            val firstNode = parseInt(firstNode.text.toString())
            if (firstNode <= currentTree.size) {
                answerField.text = format("Size of %d's component is %d", firstNode,
                        currentTree.sizeOfNodesComponent(firstNode))
            }
        }
    }

    private fun convertIntToDp(value: Int): Int {
        return (this.applicationContext.resources.displayMetrics.density * value).toInt()
    }

    private fun matchParent(): Int {
        return convertIntToDp(this.applicationContext.resources.displayMetrics.widthPixels)
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
        treeField.text = tree
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
        connectionsField.text = connections
    }
}
