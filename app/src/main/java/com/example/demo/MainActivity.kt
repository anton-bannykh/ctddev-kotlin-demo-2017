package com.example.demo

import android.content.Context
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.view.inputmethod.InputMethodManager
import my.lib.Graph
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import dsl.createConstraintLayout


class MainActivity : AppCompatActivity() {
    private var myGraph: Graph? = null
    private lateinit var editTextNumberVertices: EditText
    private lateinit var editTextAddEdgeFrom: EditText
    private lateinit var editTextAddEdgeTo: EditText
    private lateinit var editTextAddEdgeWeight: EditText
    private lateinit var editTextFrom: EditText
    private lateinit var editTextTo: EditText
    private lateinit var textViewShowGraph: TextView
    private lateinit var textViewShowAnswer: TextView
    private var k = 0
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                createConstraintLayout(R.id.main_layout) {
                    editText(R.id.editTextNumbersVertices) {
                        hint = getString(R.string.size_of_your_graph)
                        margin(TOP, R.id.main_layout, TOP, 8)
                        margin(LEFT, R.id.main_layout, LEFT, 8)
                    }
                    button(R.id.buttonCreate) {
                        onMyClick { onCreateGraph() }
                        text = getString(R.string.create_new_graph)
                        margin(RIGHT, R.id.main_layout, RIGHT, 8)
                        margin(TOP, R.id.main_layout, TOP, 8)
                        margin(LEFT, R.id.editTextNumbersVertices, RIGHT, 8)
                    }
                    textView(R.id.textViewAddEdge) {
                        text = getString(R.string.textViewAddEdge)
                        margin(TOP, R.id.main_layout, TOP, 80)
                        margin(LEFT, R.id.main_layout, LEFT, 140)
                    }
                    editText(R.id.editTextFrom) {
                        hint = getString(R.string.fromHint)
                        width = dp(80)
                        margin(TOP, R.id.main_layout, TOP, 132)
                        margin(LEFT, R.id.main_layout, LEFT, 8)
                    }
                    editText(R.id.editTextTo) {
                        hint = getString(R.string.to)
                        width = dp(80)
                        margin(TOP, R.id.main_layout, TOP, 132)
                        margin(LEFT, R.id.editTextFrom, RIGHT, 8)
                    }
                    editText(R.id.editTextWeight) {
                        hint = getString(R.string.weight)
                        width = dp(80)
                        margin(TOP, R.id.main_layout, TOP, 132)
                        margin(LEFT, R.id.editTextTo, RIGHT, 8)
                    }
                    button(R.id.buttonAdd) {
                        onMyClick { onAddEdge() }
                        text = getString(R.string.add)
                        margin(TOP, R.id.main_layout, TOP, 132)
                        margin(RIGHT, R.id.main_layout, RIGHT, 8)
                    }
                    textView(R.id.textViewShowGraph) {
                        margin(TOP, R.id.main_layout, TOP, 196)
                        margin(LEFT, R.id.main_layout, LEFT, 8)
                    }
                    textView(R.id.textViewFindWay) {
                        text = getString(R.string.find_route)
                        margin(TOP, R.id.textViewShowGraph, BOTTOM, 38)
                        margin(LEFT, R.id.main_layout, LEFT, 140)
                    }
                    editText(R.id.editTextFromFind) {
                        hint = getString(R.string.fromHint)
                        width = dp(110)
                        margin(TOP, R.id.textViewFindWay, BOTTOM, 10)
                        margin(LEFT, R.id.main_layout, LEFT, 8)
                    }
                    editText(R.id.editTextToFind) {
                        hint = getString(R.string.to)
                        width = dp(110)
                        margin(TOP, R.id.textViewFindWay, BOTTOM, 10)
                        margin(LEFT, R.id.editTextFromFind, RIGHT, 8)
                    }
                    button(R.id.buttonFind) {
                        onMyClick { onFindRoute() }
                        text = getString(R.string.find)
                        width = dp(110)
                        margin(TOP, R.id.textViewFindWay, BOTTOM, 10)
                        margin(RIGHT, R.id.main_layout, RIGHT, 8)
                    }
                    textView(R.id.textViewAnswer) {
                        text = getString(R.string.answer)
                        margin(TOP, R.id.editTextToFind, BOTTOM, 10)
                        margin(LEFT, R.id.main_layout, LEFT, 140)
                    }
                    textView(R.id.textViewShowAnswer) {
                        margin(TOP, R.id.textViewAnswer, BOTTOM, 10)
                    }
                }
        )
        editTextNumberVertices = findViewById(R.id.editTextNumbersVertices)
        editTextAddEdgeFrom = findViewById(R.id.editTextFrom)
        editTextAddEdgeTo = findViewById(R.id.editTextTo)
        editTextAddEdgeWeight = findViewById(R.id.editTextWeight)
        editTextFrom = findViewById(R.id.editTextFromFind)
        editTextTo = findViewById(R.id.editTextToFind)
        textViewShowAnswer = findViewById(R.id.textViewShowAnswer)
        textViewShowGraph = findViewById(R.id.textViewShowGraph)
    }

    private fun onCreateGraph() {
        if (editTextNumberVertices.text != null) {
            myGraph = Graph(Integer.parseInt(editTextNumberVertices.text.toString()))
            textViewShowGraph.text = ""
            textViewShowAnswer.text = ""
        } else {
            Toast.makeText(this, getString(R.string.size_of_your_graph), Toast.LENGTH_SHORT).show()
        }
    }

    private fun onAddEdge() {
        val from = editTextAddEdgeFrom.text.toString()
        val to = editTextAddEdgeTo.text.toString()
        val weight = editTextAddEdgeWeight.text.toString()
        if (checkNull()) return
        if (from != "" && to != "" && weight != "") {
            val fromInt = Integer.parseInt(from)
            val toInt = Integer.parseInt(to)
            if (checkBigNumber(fromInt, toInt)) return
            myGraph!!.addEdge(fromInt - 1, toInt - 1, Integer.parseInt(weight))
            textViewShowGraph.append("$fromInt-(${Integer.parseInt(weight)})->$toInt ")
            k++
            if (k == 4) {
                textViewShowGraph.append("\n")
                k = 0
            }
            editTextAddEdgeTo.text.clear()
            editTextAddEdgeFrom.text.clear()
            editTextAddEdgeWeight.text.clear()
        } else {
            Toast.makeText(this, getString(R.string.enter_all_fields), Toast.LENGTH_SHORT).show()
        }
    }

    private fun checkBigNumber(a: Int, b: Int): Boolean {
        if (a > myGraph!!.n || b > myGraph!!.n) {
            Toast.makeText(applicationContext, getString(R.string.big_numbers), Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    private fun checkNull(): Boolean {
        if (myGraph == null) {
            Toast.makeText(applicationContext, getString(R.string.create_graph), Toast.LENGTH_SHORT).show()
            return true
        }
        return false
    }

    private fun onFindRoute() {
        val from = editTextFrom.text.toString()
        val to = editTextTo.text.toString()
        textViewShowAnswer.text = ""
        if (checkNull()) return
        if (from != "" && to != "") {
            val fromInt = Integer.parseInt(from)
            val toInt = Integer.parseInt(to)
            if (checkBigNumber(fromInt, toInt)) return
            val pair: Pair<IntArray?, ArrayList<ArrayList<Int>>> = myGraph!!.fordBellman(fromInt - 1)
            if (pair.first == null) {
                textViewShowAnswer.text = resources.getString(R.string.exists_negative_cycle)
                val negativeCycle = pair.second[0]
                textViewShowAnswer.append(getString(R.string.exists_negative_cycle))
                showInfo(negativeCycle)
                return
            }
            val answerArrayList = pair.second
            val routeList = answerArrayList[toInt - 1]
            val dist = pair.first
            if (dist!![toInt - 1] == Int.MAX_VALUE) {
                textViewShowAnswer.append(getString(R.string.no_route, fromInt, toInt))
                return
            }
            showInfo(routeList)
            textViewShowAnswer.append(" distance = ${dist[toInt - 1]}\n\n")
            for (i in 0 until dist.size) {
                textViewShowAnswer.append("${dist[i]} ")
            }
        } else {
            Toast.makeText(this, getString(R.string.enter_all_fields), Toast.LENGTH_SHORT).show()
        }
    }

    private fun showInfo(arrayList: ArrayList<Int>) {
        var ans = ""
        for (i in 0 until arrayList.size) {
            if (i == arrayList.size - 1) {
                ans += "${arrayList[i] + 1}"
                break
            }
            ans += ("${arrayList[i] + 1} -> ")
        }
        textViewShowAnswer.append(ans)
    }

}
