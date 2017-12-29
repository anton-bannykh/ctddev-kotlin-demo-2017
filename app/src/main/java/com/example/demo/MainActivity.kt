package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.TextView
import my.lib.SegmentTree

class MainActivity : AppCompatActivity() {
    private fun dp(value: Int) = (this.applicationContext.resources.displayMetrics.density * value).toInt()
    private fun sp(value: Int) = (resources.configuration.fontScale * value)

    private var tree = SegmentTree(1)

    private fun visibleQueries(visible: Int) {
        findViewById<TextView>(R.id.queries_view).visibility = visible
        findViewById<TextView>(R.id.set_edit1).visibility = visible
        findViewById<TextView>(R.id.set_edit2).visibility = visible
        findViewById<TextView>(R.id.set_result_view).visibility = visible
        findViewById<TextView>(R.id.set_view).visibility = visible
        findViewById<TextView>(R.id.set_button).visibility = visible
        findViewById<TextView>(R.id.get_edit1).visibility = visible
        findViewById<TextView>(R.id.get_edit2).visibility = visible
        findViewById<TextView>(R.id.get_result_view).visibility = visible
        findViewById<TextView>(R.id.get_view).visibility = visible
        findViewById<TextView>(R.id.get_button).visibility = visible
    }

    private fun createTree() {
        try {
            tree = SegmentTree(findViewById<EditText>(R.id.size_edit).text.toString().toInt())
            findViewById<TextView>(R.id.build_result_view).text = resources.getText(R.string.tree_created)
            findViewById<TextView>(R.id.build_result_view).setTextColor(resources.getColor(R.color.green))
            visibleQueries(View.VISIBLE)
        } catch (t: Throwable) {
            findViewById<TextView>(R.id.build_result_view).text = resources.getText(R.string.wrong_size)
            findViewById<TextView>(R.id.build_result_view).setTextColor(resources.getColor(R.color.red))
            visibleQueries(View.INVISIBLE)
        }
    }

    private fun processSet() {
        try {
            val pos = findViewById<EditText>(R.id.set_edit1).text.toString().toInt()
            val x = findViewById<EditText>(R.id.set_edit2).text.toString().toInt()
            if (pos < 0 || pos >= tree.size()) {
                throw RuntimeException("set query bad arguments")
            }
            tree[pos] = x
            findViewById<TextView>(R.id.set_result_view).text = x.toString() + " " + resources.getText(R.string.set).toString() + " " +
                    resources.getText(R.string.on_position).toString() + " " + pos.toString()
            findViewById<TextView>(R.id.set_result_view).setTextColor(resources.getColor(R.color.green))
        } catch (t: Throwable) {
            findViewById<TextView>(R.id.set_result_view).text = resources.getText(R.string.wrong_parameters)
            findViewById<TextView>(R.id.set_result_view).setTextColor(resources.getColor(R.color.red))
        }
    }

    private fun processGet() {
        try {
            val l = findViewById<EditText>(R.id.get_edit1).text.toString().toInt()
            val r = findViewById<EditText>(R.id.get_edit2).text.toString().toInt() + 1
            if (l < 0 || r < 0 || l >= tree.size() || r > tree.size() || l >= r) {
                throw RuntimeException("ask query bad arguments")
            }
            findViewById<TextView>(R.id.get_result_view).text = resources.getText(R.string.sum_is).toString() + " " + tree.ask(l, r).toString()
            findViewById<TextView>(R.id.get_result_view).setTextColor(resources.getColor(R.color.green))
        } catch (t: Throwable) {
            findViewById<TextView>(R.id.get_result_view).text = resources.getText(R.string.wrong_parameters)
            findViewById<TextView>(R.id.get_result_view).setTextColor(resources.getColor(R.color.red))
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout = myConstraintLayout(this, R.id.layout) {
            myTextView {
                id = R.id.enter_size_view
                text = resources.getText(R.string.enter_size)
                textSize = sp(20)
                connect(R.id.layout, ConstraintSet.LEFT, ConstraintSet.LEFT, dp(16))
                connect(R.id.layout, ConstraintSet.TOP, ConstraintSet.TOP, dp(16))
            }
            myEditText {
                id = R.id.size_edit
                inputType = InputType.TYPE_CLASS_NUMBER
                textSize = sp(14)
                hint = resources.getText(R.string.size)
                connect(R.id.layout, ConstraintSet.LEFT, ConstraintSet.LEFT, dp(16))
                connect(R.id.enter_size_view, ConstraintSet.TOP, ConstraintSet.BOTTOM)
            }
            myButton {
                id = R.id.build_button
                text = resources.getText(R.string.create_tree)
                connect(R.id.layout, ConstraintSet.LEFT, ConstraintSet.LEFT, dp(16))
                connect(R.id.size_edit, ConstraintSet.TOP, ConstraintSet.BOTTOM)
                setOnClickListener { createTree() }
            }
            myTextView {
                id = R.id.build_result_view
                textSize = sp(24)
                connect(R.id.layout, ConstraintSet.LEFT, ConstraintSet.LEFT, dp(16))
                connect(R.id.build_button, ConstraintSet.TOP, ConstraintSet.BOTTOM)
            }
            myTextView {
                id = R.id.queries_view
                text = resources.getText(R.string.queries)
                textSize = sp(24)
                connect(R.id.layout, ConstraintSet.LEFT, ConstraintSet.LEFT, dp(16))
                connect(R.id.build_result_view, ConstraintSet.TOP, ConstraintSet.BOTTOM, dp(16))
            }
            myTextView {
                id = R.id.set_view
                text = resources.getText(R.string.set_value)
                textSize = sp(20)
                connect(R.id.layout, ConstraintSet.LEFT, ConstraintSet.LEFT, dp(16))
                connect(R.id.queries_view, ConstraintSet.TOP, ConstraintSet.BOTTOM)
            }
            myEditText {
                id = R.id.set_edit1
                inputType = InputType.TYPE_CLASS_NUMBER
                textSize = sp(14)
                hint = resources.getText(R.string.position)
                connect(R.id.layout, ConstraintSet.LEFT, ConstraintSet.LEFT, dp(16))
                connect(R.id.set_view, ConstraintSet.TOP, ConstraintSet.BOTTOM)
            }
            myEditText {
                id = R.id.set_edit2
                inputType = InputType.TYPE_CLASS_NUMBER
                textSize = sp(14)
                hint = resources.getText(R.string.value)
                connect(R.id.set_edit1, ConstraintSet.LEFT, ConstraintSet.RIGHT, dp(16))
                connect(R.id.set_edit1, ConstraintSet.BOTTOM, ConstraintSet.BOTTOM)
            }
            myButton {
                id = R.id.set_button
                text = resources.getText(R.string.process)
                textSize = sp(16)
                connect(R.id.layout, ConstraintSet.LEFT, ConstraintSet.LEFT, dp(16))
                connect(R.id.set_edit1, ConstraintSet.TOP, ConstraintSet.BOTTOM)
                setOnClickListener { processSet() }
            }
            myTextView {
                id = R.id.set_result_view
                textSize = sp(24)
                connect(R.id.layout, ConstraintSet.LEFT, ConstraintSet.LEFT, dp(16))
                connect(R.id.set_button, ConstraintSet.TOP, ConstraintSet.BOTTOM)
            }
            myTextView {
                id = R.id.get_view
                text = resources.getText(R.string.get_value)
                textSize = sp(20)
                connect(R.id.layout, ConstraintSet.LEFT, ConstraintSet.LEFT, dp(16))
                connect(R.id.set_result_view, ConstraintSet.TOP, ConstraintSet.BOTTOM, dp(16))
            }
            myEditText {
                id = R.id.get_edit1
                inputType = InputType.TYPE_CLASS_NUMBER
                textSize = sp(14)
                hint = resources.getText(R.string.from)
                connect(R.id.layout, ConstraintSet.LEFT, ConstraintSet.LEFT, dp(16))
                connect(R.id.get_view, ConstraintSet.TOP, ConstraintSet.BOTTOM)
            }
            myEditText {
                id = R.id.get_edit2
                inputType = InputType.TYPE_CLASS_NUMBER
                textSize = sp(14)
                hint = resources.getText(R.string.to)
                connect(R.id.get_edit1, ConstraintSet.LEFT, ConstraintSet.RIGHT, dp(16))
                connect(R.id.get_edit1, ConstraintSet.BOTTOM, ConstraintSet.BOTTOM)
            }
            myButton {
                id = R.id.get_button
                text = resources.getText(R.string.process)
                textSize = sp(16)
                connect(R.id.layout, ConstraintSet.LEFT, ConstraintSet.LEFT, dp(16))
                connect(R.id.get_edit1, ConstraintSet.TOP, ConstraintSet.BOTTOM)
                setOnClickListener { processGet() }
            }
            myTextView {
                id = R.id.get_result_view
                textSize = sp(24)
                connect(R.id.layout, ConstraintSet.LEFT, ConstraintSet.LEFT, dp(16))
                connect(R.id.get_button, ConstraintSet.TOP, ConstraintSet.BOTTOM)
            }
        }
        setContentView(layout.toConstraintLayout())
        visibleQueries(View.INVISIBLE)
    }
}
