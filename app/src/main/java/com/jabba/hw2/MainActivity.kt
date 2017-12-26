package com.jabba.hw2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.widget.TextView
import java.util.*

class MainActivity : AppCompatActivity() {
    var g = mutableListOf<Pair<Int, Int>>()
    var n = 0
    var root = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = constraintBuilder (R.id.layout, this) {
            val addEdge = addTextView(R.id.addEdge) {
                setText(R.string.enterEdges)
                textSize = 18f
                connect (R.id.addEdge, LEFT, R.id.layout, LEFT, 16)
                connect (R.id.addEdge, TOP, R.id.layout, TOP, 16)
            }

            val from1 = addTextView(R.id.from1) {
                setText(R.string.from)
                textSize = 30f
                width = toDp(52)
                height = toDp(44)
                connect (R.id.from1, LEFT, R.id.layout, LEFT, 16)
                connect (R.id.from1, TOP, R.id.addEdge, BOTTOM, 16)
            }
            val edgeFrom = addEditText(R.id.edgeFrom) {
                setText("")
                width = toDp(66)
                inputType = InputType.TYPE_CLASS_NUMBER
                connect (R.id.edgeFrom, LEFT, R.id.from1, RIGHT, 16)
                connect (R.id.edgeFrom, BOTTOM, R.id.from1, BOTTOM, 0)
                connect (R.id.edgeFrom, TOP, R.id.addEdge, BOTTOM, 16)
            }
            val to1 = addTextView(R.id.to1) {
                setText(R.string.to)
                textSize = 30f
                height = toDp(44)
                connect (R.id.to1, LEFT, R.id.edgeFrom, RIGHT, 16)
                connect (R.id.to1, TOP, R.id.addEdge, BOTTOM, 16)
                connect (R.id.to1, BOTTOM, R.id.edgeFrom, BOTTOM, 0)
            }
            val edgeTo = addEditText(R.id.edgeTo) {
                setText("")
                width = toDp(66)
                inputType = InputType.TYPE_CLASS_NUMBER
                connect (R.id.edgeTo, LEFT, R.id.to1, RIGHT, 16)
                connect (R.id.edgeTo, BOTTOM, R.id.to1, BOTTOM, 0)
                connect (R.id.edgeTo, TOP, R.id.addEdge, BOTTOM, 16)
            }
            val addEdgeButton = addButton(R.id.addEdgeButton) {
                text = getString(R.string.addEdge)
                textSize = 14f
                connect(R.id.addEdgeButton, RIGHT, R.id.layout, RIGHT, 16)
                connect(R.id.addEdgeButton, TOP, R.id.addEdge, BOTTOM, 16)
                connect(R.id.addEdgeButton, LEFT, R.id.edgeTo, RIGHT, 16)
            }
            addEdgeButton.setOnClickListener {
                if (edgeFrom.text.toString().length != 0 && edgeTo.text.toString().length != 0) {
                    val from = Integer.parseInt(edgeFrom.text.toString())
                    val to = Integer.parseInt(edgeTo.text.toString())
                    edgeFrom.setText("")
                    edgeTo.setText("")
                    val s: TextView = findViewById(R.id.addedEdgesList)
                    val rs: TextView = findViewById(R.id.resList)
                    if (from > 0 && to > 0) {
                        val max = Math.max(from, to)
                        if (n < max) n = max
                        if (!g.contains(Pair(from, to)) && !g.contains(Pair(to, from))) {
                            g.add(Pair(from, to))
                            s.text = s.text.toString() + "($from; $to), "
                        }
                        rs.text = dfsIterative(root, n, g).toString()
                    }
                }
            }

            val removeEdge = addTextView(R.id.removeEdge) {
                setText(R.string.removeEdges)
                textSize = 18f
                connect (R.id.removeEdge, LEFT, R.id.layout, LEFT, 16)
                connect (R.id.removeEdge, TOP, R.id.from1, BOTTOM, 24)
            }

            val from2 = addTextView(R.id.from2) {
                setText(R.string.from)
                textSize = 30f
                width = toDp(52)
                height = toDp(44)
                connect (R.id.from2, LEFT, R.id.layout, LEFT, 16)
                connect (R.id.from2, TOP, R.id.removeEdge, BOTTOM, 16)
            }
            val edgeFromDelete = addEditText(R.id.edgeFromDelete) {
                setText("")
                width = toDp(66)
                inputType = InputType.TYPE_CLASS_NUMBER
                connect (R.id.edgeFromDelete, LEFT, R.id.from2, RIGHT, 16)
                connect (R.id.edgeFromDelete, BOTTOM, R.id.from2, BOTTOM, 0)
                connect (R.id.edgeFromDelete, TOP, R.id.removeEdge, BOTTOM, 16)
            }
            val to2 = addTextView(R.id.to2) {
                setText (R.string.to)
                textSize = 30f
                height = toDp(44)
                connect (R.id.to2, LEFT, R.id.edgeFromDelete, RIGHT, 16)
                connect (R.id.to2, TOP, R.id.removeEdge, BOTTOM, 16)
                connect (R.id.to2, BOTTOM, R.id.edgeFromDelete, BOTTOM, 0)
            }
            val edgeToDelete = addEditText(R.id.edgeToDelete) {
                setText("")
                width = toDp(66)
                inputType = InputType.TYPE_CLASS_NUMBER
                connect (R.id.edgeToDelete, LEFT, R.id.to2, RIGHT, 16)
                connect (R.id.edgeToDelete, BOTTOM, R.id.to2, BOTTOM, 0)
                connect (R.id.edgeToDelete, TOP, R.id.removeEdge, BOTTOM, 16)
            }
            val removeEdgeButton = addButton(R.id.removeEdgeButton) {
                text = getString(R.string.removeEdge)
                textSize = 14f
                connect(R.id.removeEdgeButton, RIGHT, R.id.layout, RIGHT, 16)
                connect(R.id.removeEdgeButton, TOP, R.id.removeEdge, BOTTOM, 16)
                connect(R.id.removeEdgeButton, LEFT, R.id.edgeToDelete, RIGHT, 16)
            }
            removeEdgeButton.setOnClickListener {
                if (edgeFromDelete.text.toString().length != 0 && edgeToDelete.text.toString().length != 0) {
                    val from = Integer.parseInt(edgeFromDelete.text.toString())
                    val to = Integer.parseInt(edgeToDelete.text.toString())
                    edgeFromDelete.setText("")
                    edgeToDelete.setText("")
                    val s: TextView = findViewById(R.id.addedEdgesList)
                    val rs: TextView = findViewById(R.id.resList)
                    if (from > 0 && to > 0) {
                        if (g.contains(Pair(from, to))) {
                            s.text = s.text.toString().replace("($from; $to), ", "")
                            g.remove(Pair(from, to))
                        }
                        if (g.contains(Pair(to, from))) {
                            s.text = s.text.toString().replace("($to; $from), ", "")
                            g.remove(Pair(to, from))
                        }
                        rs.text = dfsIterative(root, n, g).toString()
                    }
                }
            }

            val root1 = addTextView(R.id.root) {
                setText(R.string.root)
                textSize = 18f
                height = toDp(29)
                connect (R.id.root, LEFT, R.id.layout, LEFT, 16)
                connect (R.id.root, TOP, R.id.from2, BOTTOM, 30)
            }
            val setRoot = addEditText(R.id.setRoot) {
                setText("")
                inputType = InputType.TYPE_CLASS_NUMBER
                width = toDp(66)
                connect (R.id.setRoot, LEFT, R.id.root, RIGHT, 16)
                connect (R.id.setRoot, TOP, R.id.edgeToDelete, BOTTOM, 16)
            }
            setRoot.onFocusChangeListener = object: View.OnFocusChangeListener {
                override fun onFocusChange(v: View, b: Boolean) {
                    val rs: TextView = findViewById(R.id.resList)
                    if (!setRoot.text.toString().isEmpty()) {
                        if (Integer.parseInt(setRoot.text.toString()) != 0) root = Integer.parseInt(setRoot.text.toString())
                        if (root > n) n = root
                        setRoot.setText("")
                        if (!g.isEmpty()) rs.text = dfsIterative(root, n, g).toString()
                    }
                }
            }

            val addedEdges = addTextView(R.id.addedEdges) {
                setText(R.string.addedEdges)
                textSize = 18f
                connect (R.id.addedEdges, LEFT, R.id.layout, LEFT, 16)
                connect (R.id.addedEdges, TOP, R.id.root, BOTTOM, 30)
            }

            val addedEdgesList = addTextView(R.id.addedEdgesList) {
                text = ""
                height = toDp(62)
                width = getContext().getResources().getDisplayMetrics().widthPixels - toDp(32)
                val s = ScrollingMovementMethod()
                movementMethod = s
                textSize = 18f
                connect (R.id.addedEdgesList, LEFT, R.id.layout, LEFT, 16)
                connect (R.id.addedEdgesList, RIGHT, R.id.layout, RIGHT, 16)
                connect (R.id.addedEdgesList, TOP, R.id.addedEdges, BOTTOM, 16)
            }

            val res = addTextView(R.id.res) {
                setText(R.string.res)
                textSize = 18f
                connect (R.id.res, LEFT, R.id.layout, LEFT, 16)
                connect (R.id.res, TOP, R.id.addedEdgesList, BOTTOM, 30)
            }

            val resList = addTextView(R.id.resList) {
                text = ""
                val s = ScrollingMovementMethod()
                movementMethod = s
                height = toDp(42)
                width = getContext().getResources().getDisplayMetrics().widthPixels - toDp(32)
                textSize = 18f
                connect (R.id.resList, LEFT, R.id.layout, LEFT, 16)
                connect (R.id.resList, RIGHT, R.id.layout, RIGHT, 16)
                connect (R.id.resList, TOP, R.id.res, BOTTOM, 16)
            }
        }
        setContentView(layout)
        //setContentView(R.layout.activity_main)

    }


    fun dfsIterative (root: Int, n: Int, graph: List<Pair<Int, Int>>): MutableList<Int> {
        //graph is defined by its edges; the returned list is the order in which the vertices are visited
        //if a vertex has multiple neighbors, they are visited in an ascending order
        val s = Stack<Int>()
        val g = mutableListOf<MutableList<Int>>()
        for (i in 0..n - 1) g.add(mutableListOf())
        for (i in graph.indices) {
            g[graph[i].first - 1].add(graph[i].second - 1)
            g[graph[i].second - 1].add(graph[i].first - 1)
        }
        for (i in 0..n - 1) g[i].sortDescending()
        val res = mutableListOf<Int>()
        val v = BooleanArray (n, { false })
        s.add(root - 1)
        while (!s.empty()) {
            val e = s.pop()
            if (!v[e]) {
                v[e] = true
                res.add(e + 1)
            }
            for (i in g[e]) {
                if (!v[i]) s.add(i)
            }
        }
        return res
    }

}
