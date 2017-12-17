package com.example.demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import my.lib.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editV = findViewById<EditText>(R.id.editText)
        val editSize = findViewById<EditText>(R.id.editText2)
        val editPrice = findViewById<EditText>(R.id.editText3)
        val textThings = findViewById<TextView>(R.id.textView4)
        val textAns = findViewById<TextView>(R.id.textView5)

        val buttonAns = findViewById<View>(R.id.button) as Button
        val buttonAdd = findViewById<View>(R.id.button2) as Button

        var lastAction = false
        var k = 0
        val price = arrayListOf<Int>()
        val weight = arrayListOf<Int>()
        buttonAdd.setOnClickListener {
            if (lastAction) {
                textAns.text = "answer:\n"
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
        buttonAns.setOnClickListener {
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
                textAns.text = textAns.text.toString().plus(answer)
            } catch (e: Exception) {
                Toast.makeText(this, "Check input1", Toast.LENGTH_LONG).show()
            }
        }

    }
}
