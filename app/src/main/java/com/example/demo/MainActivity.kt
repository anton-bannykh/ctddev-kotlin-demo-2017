package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*
import minCostAssignment
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private fun showError(s: String) {
        Toast.makeText(this, s, Toast.LENGTH_SHORT).show()
    }

    fun onBtnClick(view: View) {
        output.text = ""
        val n = inputN.text.toString().toIntOrNull()
        if (n == null || n < 0) {
            showError("n must be a non-negative integer")
            return
        }
        val s = inputA.text.toString().split("\\s+".toRegex()).filter{it != ""}.map {
            val x = it.toIntOrNull()
            if (x == null) {
                showError("values must be integers")
                return
            } else {
                x
            }
        }
        if (s.size != n*n) {
            showError("there must me exactly ${n*n} values, but ${s.size} found")
            return
        }
        val a = List(n, {i -> List(n, {j -> s[i*n + j]})})
        val res = minCostAssignment(a)
        output.text = "Result = $res"
    }

    private val random = Random()
    fun onGenClick(view: View) {
        val n = inputN.text.toString().toIntOrNull()
        if (n == null || n < 0) {
            showError("n must be a non-negative integer")
            return
        }
        inputA.setText(generateSequence {
            generateSequence { random.nextInt(201) - 100 }.take(n).joinToString(" ")
        }.take(n).joinToString("\n"))
    }
}
