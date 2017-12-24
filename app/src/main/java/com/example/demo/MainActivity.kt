package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import my.lib.Graph
import android.content.Intent
import android.graphics.Typeface
import android.support.constraint.ConstraintSet
import android.text.InputType
import android.util.TypedValue
import android.view.Gravity
import android.view.ViewGroup
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    private var graph: Graph? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                myContraintLayout(R.id.layoutId) {
                    textView(R.id.headerTextView1) {
                        width = dp(366)
                        height = dp(50)
                        text = resources.getText(R.string.headerText)
                        leftMargin(ConstraintSet.LEFT, dp(8))
                        rightMargin(ConstraintSet.RIGHT, dp(8))
                        topMargin(ConstraintSet.TOP, dp(8))
                        setTextColor(R.style.niceBlue)
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
                        setTypeface(null, Typeface.BOLD)
                        gravity = Gravity.CENTER
                    }
                    textView(R.id.enterVerticesLabel) {
                        width = dp(368)
                        height = dp(28)
                        leftMargin(ConstraintSet.LEFT, dp(8))
                        rightMargin(ConstraintSet.RIGHT, dp(8))
                        topMargin(ConstraintSet.BOTTOM, R.id.headerTextView1, dp(8))
                        setTextColor(R.style.anotherNiceBlue)
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 17.0f)
                        text = resources.getText(R.string.amountOfVersInGraph)
                        gravity = Gravity.CENTER
                    }
                   editText(R.id.vertexAmount) {
                        width = dp(230)
                        height = dp(38)
                        setEms(10)
                        leftMargin(ConstraintSet.LEFT, R.id.enterVerticesLabel, dp(8))
                        rightMargin(ConstraintSet.RIGHT, R.id.enterVerticesLabel, dp(8))
                        topMargin(ConstraintSet.BOTTOM, R.id.enterVerticesLabel, dp(8))
                        inputType = InputType.TYPE_CLASS_NUMBER
                        gravity = Gravity.CENTER
                    }
                    button(R.id.button) {
                        width = dp(130)
                        height = dp(40)
                        text = resources.getText(R.string.create_graph)
                        leftMargin(ConstraintSet.LEFT, R.id.vertexAmount, dp(8))
                        rightMargin(ConstraintSet.RIGHT, R.id.vertexAmount, dp(8))
                        topMargin(ConstraintSet.BOTTOM, R.id.vertexAmount, dp(8))
                        setBackgroundColor(R.style.anotherNiceBlue)
                    }
                    textView(R.id.textView2) {
                        val params = layoutParams
                        params.height = ViewGroup.LayoutParams.WRAP_CONTENT
                        params.width = ViewGroup.LayoutParams.WRAP_CONTENT
                        layoutParams = params
                        text = resources.getText(R.string.enter_vertices_numbers)
                        gravity = Gravity.CENTER
                        setTextColor(R.style.anotherNiceBlue)
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 17.0f)
                        leftMargin(ConstraintSet.LEFT, dp(8))
                        rightMargin(ConstraintSet.RIGHT, dp(8))
                        topMargin(ConstraintSet.BOTTOM, R.id.button, dp(8))
                    }
                    editText(R.id.vertexU) {
                        width = dp(168)
                        height = dp(50)
                        setEms(10)
                        setHint(R.string.number_of_vertex_u)
                        inputType = InputType.TYPE_CLASS_NUMBER
                        leftMargin(ConstraintSet.LEFT, R.id.textView2, dp(8))
                        topMargin(ConstraintSet.BOTTOM, R.id.textView2, dp(8))
                    }
                    editText(R.id.vertexV) {
                        width = dp(186)
                        height = dp(51)
                        setEms(10)
                        setHint(R.string.number_of_vertex_v)
                        inputType = InputType.TYPE_CLASS_NUMBER
                        leftMargin(ConstraintSet.RIGHT, R.id.textView2, dp(8))
                        rightMargin(ConstraintSet.RIGHT, R.id.vertexU,  dp(8))
                        topMargin(ConstraintSet.BOTTOM, R.id.textView2, dp(8))
                    }
                    button(R.id.addEdgeButton) {
                        width = dp(236)
                        height = dp(37)
                        text = resources.getText(R.string.add_edge)
                        setBackgroundColor(R.style.anotherNiceBlue)
                        leftMargin(ConstraintSet.LEFT, dp(8))
                        rightMargin(ConstraintSet.RIGHT,  dp(8))
                        topMargin(ConstraintSet.BOTTOM, R.id.vertexU, dp(8))
                    }
                    button(R.id.runAlgorithmButton) {
                        width = dp(252)
                        height = dp(48)
                        text = resources.getText(R.string.run_algorithm)
                        setBackgroundColor(R.style.anotherNiceBlue)
                        leftMargin(ConstraintSet.LEFT, dp(8))
                        rightMargin(ConstraintSet.RIGHT,  dp(8))
                        topMargin(ConstraintSet.BOTTOM, R.id.addEdgeButton, dp(32))
                    }
                }
        )

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

    private fun dp(value: Int) = (this.applicationContext.resources.displayMetrics.density * value).toInt()

    private fun runAlgorithm() {
        val intent = Intent(this, DetailActivity::class.java)
        intent.putExtra("graph", graph)
        startActivity(intent)
    }
}
