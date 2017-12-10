package com.example.demo

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import my.lib.Quicksort

class Main2Activity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val ed = findViewById<TextView>(R.id.ed)
        val num = intent.getIntegerArrayListExtra("NUM_ARR").toIntArray()
        ed.text = "Исходный массив: " + num.contentToString()
        Quicksort(num)
        val out = findViewById<TextView>(R.id.vivod)
        out.text = "Сортированный массив: " + num.contentToString()
    }
}
