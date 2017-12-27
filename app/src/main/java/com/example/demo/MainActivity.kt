package com.example.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import my.lib.solve

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(constraintLayout(R.id.layout) {
            textView(R.id.SizeOf) {
                text = "Size of knapsack"
                width = dp(154)
                height = dp(55)
                topMargin(R.id.layout, TOP, dp(16))
                leftMargin(R.id.layout, LEFT, dp(28))
                textSize = 18f
            }

            editText(R.id.sizeIn) {
                width = dp(139)
                height = dp(47)
                leftMargin(R.id.SizeOf, RIGHT, dp(8))
                topMargin(R.id.layout, TOP, dp(16))
                inputType = 2
                textSize = 14f
            }

            textView(R.id.weightOut) {
                width = dp(139)
                height = dp(42)
                topMargin(R.id.layout, TOP, dp(80))
                leftMargin(R.id.layout, LEFT, dp(8))
                text = "Size"
                textSize = 20f
            }

            editText(R.id.weightIn) {
                width = dp(134)
                height = dp(45)
                leftMargin(R.id.weightOut, RIGHT, dp(8))
                topMargin(R.id.sizeIn, BOTTOM, dp(8))
                inputType = 2
            }

            textView(R.id.priceOut) {
                width = dp(168)
                height = dp(46)
                topMargin(R.id.weightOut, BOTTOM, dp(8))
                leftMargin(R.id.layout, LEFT, dp(8))
                text = "Price"
                textSize = 20f
            }


            editText(R.id.priceIn) {
                width = dp(136)
                height = dp(39)
                leftMargin(R.id.priceOut, RIGHT, dp(16))
                topMargin(R.id.weightIn, BOTTOM, dp(8))
                inputType = 2
            }

            textView(R.id.knapsack) {
                width = dp(121)
                height = dp(260)
                topMargin(R.id.priceOut, BOTTOM, dp(8))
                leftMargin(R.id.layout, LEFT, dp(8))
                text = "Things:\n"
                textSize = 14f
            }

            button(R.id.add, { add() }) {
                text = "Add"
                topMargin(R.id.priceOut, BOTTOM, dp(32))
                rightMargin(R.id.layout, RIGHT, dp(8))
                leftMargin(R.id.knapsack, RIGHT, dp(8))
            }

            button(R.id.getAnswer, { ans() }) {
                text = "Answer"
                topMargin(R.id.add, BOTTOM, dp(32))
                rightMargin(R.id.layout, RIGHT, dp(8))
                leftMargin(R.id.knapsack, RIGHT, dp(8))
            }

            textView(R.id.answer) {
                width = dp(165)
                height = dp(156)
                topMargin(R.id.getAnswer, BOTTOM, dp(8))
                leftMargin(R.id.knapsack, RIGHT, dp(16))
                bottomMargin(R.id.layout, BOTTOM, dp(8))
                text = "Answer\n"
                textSize = 14f
            }
        })
    }

    private val editV = findViewById<EditText>(R.id.sizeIn)
    private val editSize = findViewById<EditText>(R.id.weightIn)
    private val editPrice = findViewById<EditText>(R.id.priceIn)
    private val textThings = findViewById<TextView>(R.id.knapsack)
    private val textAns = findViewById<TextView>(R.id.answer)
    private var lastAction = false
    private var k = 0
    private val price = arrayListOf<Int>()
    private val weight = arrayListOf<Int>()

    fun add() {
        if (lastAction) {
            textAns.text = "Answer:\n"
            textThings.text = "Things:\n"
        }
        val p = editPrice.text
        val w = editSize.text

        if (p != null && w != null)
            try {
                lastAction = false
                weight.add(Integer.parseInt(w.toString()))
                price.add(Integer.parseInt(p.toString()))
                k++
                editPrice.setText("")
                editSize.setText("")
                val s = "$k) w:${weight.last()} p:${price.last()} \n"
                textThings.text = textThings.text.toString().plus(s)
            } catch (e: java.lang.Exception) {
                Toast.makeText(this, "Please, correct weight or price", Toast.LENGTH_SHORT).show()
            }
    }

    fun ans() {
        val v = editV.text
        if (v == null || k == 0 || lastAction) {
            Toast.makeText(this, "Check input", Toast.LENGTH_LONG).show()
        } else try {
            lastAction = true
            val W = Integer.parseInt(v.toString())
            val ans = solve(k, W, weight.toIntArray(), price.toIntArray())
            price.clear()
            weight.clear()
            k = 0
            var answer = "take:"

            for (i in ans) {
                answer += "$i "
            }
            if (ans.isEmpty())
                answer += "nothing"
            textAns.text = textAns.text.toString().plus(answer)
        } catch (e: Exception) {
            Toast.makeText(this, "Check input1", Toast.LENGTH_LONG).show()
        }
    }
}
