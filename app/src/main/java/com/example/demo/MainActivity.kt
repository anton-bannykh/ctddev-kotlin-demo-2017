package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import my.lib.getLIS

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val getNumbers: EditText = findViewById(R.id.editText1)
        val calculate: Button = findViewById(R.id.button1)
        val result: TextView = findViewById(R.id.textView1)

        calculate.setOnClickListener({
            val b : List <String> = getNumbers.text.toString().split(" ").filter { !it.isEmpty() }
            val a = IntArray(b.size)

            for(i in 0 until b.size) {
                a[i] = b[i].toInt()
            }

            val answer = "Ответ: " + getLIS(a).toString()
            result.setText(answer)

        })
    }
}
