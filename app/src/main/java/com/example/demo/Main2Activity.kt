package com.example.demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.LinearLayout
import android.widget.TextView
import my.lib.Quicksort

class Main2Activity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main2)
        val linearL: LinearLayout = LinearLayout(this)
        linearL.orientation = LinearLayout.VERTICAL
        val lPar: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        setContentView(linearL, lPar)
        val ed = TextView(this)
        linearL.addView(ed)
        val out = TextView(this)
        linearL.addView(out)

        //val ed = findViewById<TextView>(R.id.ed)
        val num = intent.getIntegerArrayListExtra("NUM_ARR").toIntArray()
        ed.text = "Исходный массив: " + num.contentToString()
        Quicksort(num)
        //val out = findViewById<TextView>(R.id.vivod)
        out.text = "Сортированный массив: " + num.contentToString()

    }
}
