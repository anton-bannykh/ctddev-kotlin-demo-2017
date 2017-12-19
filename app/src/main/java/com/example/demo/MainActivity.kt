package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import my.lib.lis
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button.setOnClickListener {
            val arrayString = str.text.split(" ")
            val seq = Array(arrayString.size, {i -> arrayString[i].toInt()})
            answer.text = lis(seq.size, seq).toString()
        }
    }
}
