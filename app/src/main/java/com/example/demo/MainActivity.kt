package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import my.lib.sort_

class MainActivity : AppCompatActivity() {
    val ms = intArrayOf(1, 4, 2, 5, 6, 4, -4, -43, 313, 242, 32, 42, 2, 4, 42)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        var tv = findViewById(R.id.tv) as TextView
        var text: String
        text = ""
        for (it in ms) {
            text += it
            text += " "
        }
        tv.setText(text)
        var btn_sort = findViewById(R.id.btn_sort) as Button
        btn_sort.setOnClickListener {
            sort_(ms)
            val sorted_ms = ms
            text = ""
            for (it in sorted_ms) {
                text += it
                text += ' '
            }
            tv.setText(text)
        }
    }

    //fun test() = sumFun(1, 2, 3)

}


