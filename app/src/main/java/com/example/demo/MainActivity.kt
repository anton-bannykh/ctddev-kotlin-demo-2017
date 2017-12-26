package com.example.test.myapplication

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import my.lib.nth_element
import android.text.TextUtils.split
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
                constraintLayout(R.id.layoutId) {
                    val defaultMargin = 20;
                    textView(R.id.arrayDescription) {
                        text = "Type in your array"
                        topMargin(R.id.layoutId, TOP, dp(defaultMargin))
                        horizontalCenter(R.id.layoutId)
                    }
                    val initArray = textEdit(R.id.inputArray) {
                        topMargin(R.id.arrayDescription, BOTTOM, dp(defaultMargin / 2))
                        horizontalCenter(R.id.layoutId)
                    }
                    textView(R.id.intDescription) {
                        text = "Type in your number"
                        topMargin(R.id.inputArray, BOTTOM, dp(defaultMargin))
                        horizontalCenter(R.id.layoutId)
                    }
                    val initInt = textEdit(R.id.inputInt) {
                        topMargin(R.id.intDescription, BOTTOM, dp(defaultMargin / 2))
                        horizontalCenter(R.id.layoutId)
                    }
                    textView(R.id.resultDescription) {
                        text = "That's your answer"
                        topMargin(R.id.appButton, BOTTOM, dp(defaultMargin))
                        horizontalCenter(R.id.layoutId)
                    }
                    val ans = textView(R.id.appResult) {
                        topMargin(R.id.resultDescription, BOTTOM, dp(defaultMargin / 2))
                        horizontalCenter(R.id.layoutId)
                    }
                    button(R.id.appButton) {
                        topMargin(R.id.inputInt, BOTTOM, dp(defaultMargin))
                        horizontalCenter(R.id.layoutId)
                        text = "Solve"
                    }
                }
        )
        val button = findViewById(R.id.appButton) as Button
        button.setOnClickListener {
            val edit =  findViewById(R.id.inputArray) as EditText
            val input = edit.getText().toString().split(" ").map(String::toInt).toTypedArray()
            val edit_n = findViewById(R.id.inputInt) as EditText
            val input_n = edit_n.getText().toString().toInt()
            val view_text = findViewById(R.id.appResult) as TextView
            view_text.setText(nth_element(input, input_n).toString())
        }
    }
}
