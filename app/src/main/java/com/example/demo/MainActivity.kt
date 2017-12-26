package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import dsl.makeLayout
import my.lib.TwoSat


class MainActivity : AppCompatActivity() {

    lateinit var numOfVar: EditText
    lateinit var solveButton: Button
    lateinit var inputText: EditText
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                makeLayout(R.id.Layout) {

                    editText(R.id.editText2) {
                        topMargin(R.id.Layout, TOP, 24)
                        leftMargin(R.id.Layout, LEFT, 8)
                        rightMargin(R.id.Layout, RIGHT, 8)
                        width = 1000
                        height = 200
                        hint = "Number of variables"
                        textSize = 26f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }

                    editText(R.id.editText) {
                        topMargin(R.id.editText2, BOTTOM, 32)
                        leftMargin(R.id.Layout, LEFT, 8)
                        rightMargin(R.id.Layout, RIGHT, 8)
                        width = 1000
                        height = 200
                        hint = "Input function"
                        textSize = 26f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }

                    button(R.id.button) {
                        topMargin(R.id.editText, BOTTOM, 20)
                        leftMargin(R.id.Layout, LEFT, 8)
                        rightMargin(R.id.Layout, RIGHT, 8)
                        width = 400
                        height = 100
                        text = "Solve"
                        onClick { solve() }
                    }

                    viewText(R.id.textView2) {
                        topMargin(R.id.button, BOTTOM, 100)
                        bottomMargin(R.id.Layout, BOTTOM, 8)
                        leftMargin(R.id.Layout, LEFT, 8)
                        rightMargin(R.id.Layout, RIGHT, 8)
                        width = 1000
                        height = 800
                        textSize = 26f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }
                }
        )

        numOfVar = findViewById(R.id.editText2)
        solveButton = findViewById(R.id.button)
        inputText = findViewById(R.id.editText)
        result = findViewById(R.id.textView2)

    }

    fun solve() {
        result.text = TwoSat().twoSat(numOfVar.text.toString().toInt(), inputText.text.toString())
    }


}
