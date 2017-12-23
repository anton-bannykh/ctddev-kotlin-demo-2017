package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import my.lib.nextPerm
import android.view.View
import android.widget.TextView
import android.widget.EditText

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun сlickButton(view: View)
    {
        var editText1: EditText = findViewById(R.id.editText1)
        var textView: TextView = findViewById(R.id.textView)
        var a = mutableListOf<Int>()
        val parts = (editText1.getText().toString()).split("\u0020")
        for (j in parts.indices) {
            if (parts[j].compareTo("") > 0) {
                a.add(Integer.parseInt(parts[j]))
            }
        }

        textView.text = nextPerm(a).toString()
    }
}
