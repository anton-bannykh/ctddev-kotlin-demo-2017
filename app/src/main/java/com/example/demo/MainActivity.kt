package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.suffixArray
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        textView.movementMethod = ScrollingMovementMethod()
        button.setOnClickListener {
            try {
                val str: String = editText.text.toString()
                val temp = suffixArray(str.toLowerCase())
                textView2.text = Arrays.toString(temp)
                val s = StringBuilder()
                for (i in temp) s.append(str.subSequence(i, str.length)).append("\n")
                textView.text = s
            } catch (ex: Exception) {
                textView2.text = "unsupported string"
                textView.text = ""
            }
        }
    }

}
