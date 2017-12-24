package com.example.demo

import DSL.constraintLayout
import DSL.LEFTBOUND
import DSL.RIGHTBOUND
import DSL.LOWERBOUND
import DSL.UPPERBOUND
import android.nfc.FormatException
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView

import my.lib.Graph
import my.lib.INF
import my.lib.johnson

import android.text.InputType

class MainActivity : AppCompatActivity() {
    private var n: Int? = null
    private var graph: Graph? = null
    private var dist: Array<Array<Int>>? = null
    private var counted: Boolean = false

    private var TextView.intValue: Int
        get() = Integer.parseInt(text.toString())
        set(value) {
            text = value.toString()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(

                constraintLayout(R.id.layoutId) {
                    val numberOfVertex = editText(R.id.numberOfVertex, 218, 42, 18f,
                            InputType.TYPE_CLASS_NUMBER, getString(R.string.enterCount)) {
                        makeConstraint(UPPERBOUND, R.id.layoutId, UPPERBOUND, 8)
                    }

                    val answerShower = textView(R.id.answerShower, 350, 80,
                            24f, getString(R.string.answer)) {
                        makeConstraint(LOWERBOUND, R.id.layoutId, LOWERBOUND, 8)
                        makeConstraint(LEFTBOUND, R.id.layoutId, LEFTBOUND, 8)
                    }

                    val graphCreator = button(R.id.submitCount, 109, 42,
                            getString(R.string.submitCount)) {
                        makeConstraint(LEFTBOUND, R.id.numberOfVertex, RIGHTBOUND, 48)
                        makeConstraint(UPPERBOUND, R.id.layoutId, UPPERBOUND, 8)

                        setListener {
                            try {
                                n = numberOfVertex.intValue
                                graph = Graph(n!! + 1, { _ -> ArrayList() })
                                dist = null
                                counted = false
                                answerShower.text = getString(R.string.graphCreation)
                            } catch (e: Exception) {
                                answerShower.text = getString(R.string.incorrectVertexNumber)
                            } finally {
                                numberOfVertex.text = null
                            }
                        }
                    }

                    val edgeAdderV = editText(R.id.edgeAdderV, 218, 42, 18f,
                            InputType.TYPE_CLASS_NUMBER, getString(R.string.firstVertex)) {
                        makeConstraint(UPPERBOUND, R.id.numberOfVertex, LOWERBOUND, 8)
                    }

                    val edgeAdderU = editText(R.id.edgeAdderU, 218, 42, 18f,
                            InputType.TYPE_CLASS_NUMBER, getString(R.string.secondVertex)) {
                        makeConstraint(UPPERBOUND, R.id.edgeAdderV, LOWERBOUND, 8)
                    }

                    val edgeAdderW = editText(R.id.edgeAdderW, 218, 42, 18f,
                            InputType.TYPE_NUMBER_FLAG_SIGNED, getString(R.string.weight)) {
                        makeConstraint(UPPERBOUND, R.id.edgeAdderU, LOWERBOUND, 8)
                    }

                    val edgeAdder = button(R.id.edgeAdder, 109, 42,
                            getString(R.string.addEdge)) {
                        makeConstraint(LEFTBOUND, R.id.edgeAdderU, RIGHTBOUND, 48)
                        makeConstraint(UPPERBOUND, R.id.submitCount, UPPERBOUND, 52)

                        setListener {
                            if (graph == null || n == null) {
                                answerShower.text = getString(R.string.graphNotCreated)
                            }
                            else try {
                                val v = edgeAdderV.intValue
                                val u = edgeAdderU.intValue
                                val w = edgeAdderW.intValue
                                if (v !in 1..n!! || u !in 1..n!!) {
                                    throw FormatException()
                                }
                                graph!![v].add(Pair(u, w))
                                answerShower.text = getString(R.string.newEdgeAdded)
                            } catch (e: Exception) {
                                answerShower.text = getString(R.string.incorrectInput)
                            } finally {
                                edgeAdderV.text = null
                                edgeAdderU.text = null
                                edgeAdderW.text = null
                            }
                        }
                    }

                    val calculationStarter = button(R.id.calculationStarter, 350, 42,
                            getString(R.string.calculate)) {
                        makeConstraint(LEFTBOUND, R.id.layoutId, LEFTBOUND, 8)
                        makeConstraint(UPPERBOUND, R.id.edgeAdderW, LOWERBOUND, 8)

                        setListener {
                            if (graph == null || n == null) {
                                answerShower.text = getString(R.string.graphNotCreated)
                            }
                            else {
                                for (x in 1..n!!) {
                                    graph!![0].add(Pair(x, 0))
                                }
                                dist = johnson(graph!!)
                                counted = true
                                answerShower.text = getString(R.string.distancesCalculated)
                            }
                        }
                    }

                    val firstVertex = editText(R.id.firstVertex, 218, 42, 18f,
                            InputType.TYPE_CLASS_NUMBER, getString(R.string.V)) {
                        makeConstraint(UPPERBOUND, R.id.calculationStarter, LOWERBOUND, 8)
                    }

                    val secondVertex = editText(R.id.secondVertex, 218, 42, 18f,
                            InputType.TYPE_CLASS_NUMBER, getString(R.string.U)) {
                        makeConstraint(UPPERBOUND, R.id.firstVertex, LOWERBOUND, 8)
                    }

                    fun nullVertex() {
                        firstVertex.text = null
                        secondVertex.text = null
                    }

                    val distShower = button(R.id.distShower, 120, 85,
                            getString(R.string.shower)) {
                        makeConstraint(LEFTBOUND, R.id.firstVertex, RIGHTBOUND, 8)
                        makeConstraint(UPPERBOUND, R.id.calculationStarter, LOWERBOUND, 20)

                        setListener {
                            if (graph == null || n == null) {
                                nullVertex()
                                answerShower.text = getString(R.string.graphNotCreated)
                            }
                            else if (!counted) {
                                answerShower.text = getString(R.string.notCalculated)
                                nullVertex()
                            }
                            else try {
                                val v = firstVertex.intValue
                                val u = secondVertex.intValue
                                if (v !in 1..n!! || u !in 1..n!!) {
                                    throw FormatException()
                                }
                                if (dist == null) {
                                    answerShower.text = getString(R.string.negativeCycle)
                                } else {
                                    val answer = dist!![v - 1][u - 1]
                                    if (answer != INF) {
                                        answerShower.intValue = answer
                                    } else {
                                        answerShower.text = getString(R.string.INF)
                                    }
                                }
                            } catch (e: Exception) {
                                answerShower.text = getString(R.string.incorrectInput)
                            } finally {
                                nullVertex()
                            }
                        }
                    }
                }
        )

        //setContentView(R.layout.activity_main)
    }

    /*fun createGraph(view: View) {
        try {
            n = numberOfVertex_XML.intValue
            graph = Graph(n!! + 1, { _ -> ArrayList() })
            dist = null
            counted = false
            numberOfVertex_XML.text = null
            answerShower_XML.text = getString(R.string.graphCreation)
        } catch (e: Exception) {
            answerShower_XML.text = getString(R.string.incorrectVertexNumber)
        }
    }

    fun addEdge(view: View) {
        if (graph == null || n == null) {
            answerShower_XML.text = getString(R.string.graphNotCreated)
            return
        }
        try {
            val v = edgeAdderV_XML.intValue
            val u = edgeAdderU_XML.intValue
            val w = edgeAdderW_XML.intValue
            edgeAdderV_XML.text = null
            edgeAdderU_XML.text = null
            edgeAdderW_XML.text = null
            if (v !in 1..n!! || u !in 1..n!!) {
                throw FormatException()
            }
            graph!![v].add(Pair(u, w))
            answerShower_XML.text = getString(R.string.newEdgeAdded)
        } catch (e: Exception) {
            answerShower_XML.text = getString(R.string.incorrectInput)
        }
    }

    fun calculateDists(view: View) {
        if (graph == null || n == null) {
            answerShower_XML.text = getString(R.string.graphNotCreated)
            return
        }
        for (x in 1..n!!) {
            graph!![0].add(Pair(x, 0))
        }
        dist = johnson(graph!!)
        counted = true
        answerShower_XML.text = getString(R.string.distancesCalculated)
    }

    fun showDist(view: View) {
        if (graph == null || n == null) {
            answerShower_XML.text = getString(R.string.graphNotCreated)
            return
        }
        if (!counted) {
            answerShower_XML.text = getString(R.string.notCalculated)
            firstVertex_XML.text = null
            secondVertex_XML.text = null
            return
        }
        try {
            val v = firstVertex_XML.intValue
            val u = secondVertex_XML.intValue
            firstVertex_XML.text = null
            secondVertex_XML.text = null
            if (v !in 1..n!! || u !in 1..n!!) {
                throw FormatException()
            }
            if (dist == null) {
                answerShower_XML.text = getString(R.string.negativeCycle)
            } else {
                val answer = dist!![v - 1][u - 1]
                if (answer != INF) {
                    answerShower_XML.intValue = answer
                } else {
                    answerShower_XML.text = getString(R.string.INF)
                }
            }
        } catch (e: Exception) {
            answerShower_XML.text = getString(R.string.incorrectInput)
        }
    }*/
}
