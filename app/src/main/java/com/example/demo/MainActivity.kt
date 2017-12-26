package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import my.lib.TwoSat


class MainActivity : AppCompatActivity() {

    lateinit var numOfVar: EditText
    lateinit var solveButton: Button
    lateinit var inputText: EditText
    lateinit var result: TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        numOfVar = findViewById(R.id.editText2)
        solveButton = findViewById(R.id.button)
        inputText = findViewById(R.id.editText)
        result = findViewById(R.id.textView2)

    }

    fun solve(v: View) {
        result.text = TwoSat().twoSat(numOfVar.text.toString().toInt(), inputText.text.toString())
    }


}
