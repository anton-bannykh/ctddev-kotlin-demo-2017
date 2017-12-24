package com.example.demo

import android.graphics.Typeface
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.util.TypedValue
import android.view.Gravity
import android.widget.TextView
import my.lib.Graph

class DetailActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            myContraintLayout(R.id.detailsLayoutId) {
                textView(R.id.headTextView2) {
                    width = dp(366)
                    height = dp(50)
                    text = resources.getText(R.string.result_of_scc_algorithm)
                    gravity = Gravity.CENTER
                    setTextColor(R.style.niceBlue)
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
                    setTypeface(null, Typeface.BOLD)
                    leftMargin(ConstraintSet.LEFT, dp(8))
                    rightMargin(ConstraintSet.RIGHT, dp(8))
                    topMargin(ConstraintSet.TOP, dp(8))
                }
                textView(R.id.resultTextView) {
                    width = dp(337)
                    height = dp(391)
                    maxLines = 10000000
                    setTextSize(TypedValue.COMPLEX_UNIT_SP, 17.0f)
                    setTextColor(R.style.anotherNiceBlue)
                    leftMargin(ConstraintSet.LEFT, dp(8))
                    rightMargin(ConstraintSet.RIGHT, dp(8))
                    topMargin(ConstraintSet.BOTTOM, R.id.headTextView2, dp(8))
                    bottomMargin(ConstraintSet.BOTTOM, dp(32))
                }
            }
        )

        val graph = intent.getSerializableExtra("graph") as Graph

        val resultTextView = findViewById<TextView>(R.id.resultTextView)

        val sccResult = graph.getScc()

        val stringBuilder = StringBuilder()

        for (i in 0 until graph.n) {
            val ver = i + 1
            val compId = sccResult[i]
            stringBuilder.append("Вершина $ver находится в $compId-й компоненте сильной связности").append('\n')
        }

        resultTextView.text = stringBuilder.toString()
    }

    private fun dp(value: Int) = (this.applicationContext.resources.displayMetrics.density * value).toInt()
}
