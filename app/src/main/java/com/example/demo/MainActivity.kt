package com.example.demo

import android.content.Context
import android.content.res.Resources
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet.TOP
import android.support.constraint.ConstraintSet.BOTTOM
import android.text.InputType
import android.view.Gravity
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import android.widget.Button
import android.widget.LinearLayout

import com.example.demo.dsl.ui
import my.lib.getMaxMatching

class MainActivity : AppCompatActivity() {

    private fun dpToPx(dp: Int): Int = ((dp * Resources.getSystem().displayMetrics.density).toInt())

    private val EditText.int: Int
        get() = ("0" + text.toString()).toInt()

    private fun String.toast(context: Context) = Toast.makeText(context, this, Toast.LENGTH_SHORT).show()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        ui(this.findViewById(android.R.id.content)) {
            constraintLayout {
                layout.id = R.id.constraint_layout
                layout.setPadding(dpToPx(8), dpToPx(8), dpToPx(8), dpToPx(8))

                val params = LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT)
                params.weight = 1f
                params.gravity = Gravity.CENTER

                val params2 = LinearLayout.LayoutParams(params)
                params2.weight = 2f

                linearLayout {
                    layout.id = R.id.top_layout_1
                    (layout as LinearLayout).weightSum = 4f
                    textView {
                        layoutParams = params2
                        text = getString(R.string.parts_size)
                    }
                    editText {
                        id = R.id.number_n
                        layoutParams = params
                        setEms(10)
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }
                    editText {
                        id = R.id.number_m
                        layoutParams = params
                        setEms(10)
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }
                }

                linearLayout {
                    layout.id = R.id.top_layout_2
                    layout.setPadding(0, 0, 0, dpToPx(4))
                    (layout as LinearLayout).weightSum = 4f
                    textView {
                        layoutParams = params2
                        text = getString(R.string.edge_val)
                    }
                    editText {
                        id = R.id.edge_u
                        setEms(10)
                        layoutParams = params
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }
                    editText {
                        id = R.id.edge_v
                        setEms(10)
                        layoutParams = params
                        inputType = InputType.TYPE_CLASS_NUMBER
                    }
                }

                linearLayout {
                    (layout as LinearLayout).orientation = LinearLayout.HORIZONTAL
                    layout.id = R.id.linear_layout
                    layout.gravity = Gravity.CENTER
                    layout.weightSum = 3f

                    button {
                        id = R.id.button_refresh
                        layoutParams = params
                        text = getString(R.string.refresh)
                    }
                    button {
                        id = R.id.button_add
                        layoutParams = params
                        text = getString(R.string.add_edge)
                    }
                    button {
                        id = R.id.button_remove
                        layoutParams = params
                        text = getString(R.string.edge_remove)
                    }
                }

                customView(GraphView(applicationContext)) {
                    id = R.id.graph_view
                    layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, 0)
                }

                button {
                    id = R.id.find
                    text = getString(R.string.find_matching)
                }

                constrain {
                    centerHorizontally(R.id.find, R.id.constraint_layout)
                    connect(R.id.find, BOTTOM, R.id.constraint_layout, BOTTOM)
                    connect(R.id.graph_view, BOTTOM, R.id.find, TOP)
                    connect(R.id.graph_view, TOP, R.id.linear_layout, BOTTOM)
                    connect(R.id.linear_layout, TOP, R.id.top_layout_2, BOTTOM)
                    connect(R.id.top_layout_2, TOP, R.id.top_layout_1, BOTTOM)
                    connect(R.id.top_layout_1, TOP, R.id.constraint_layout, TOP)
                }
            }
        }

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
