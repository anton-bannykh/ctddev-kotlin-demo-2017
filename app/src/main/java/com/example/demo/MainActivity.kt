package com.example.demo

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import ru.walingar.kmp.searchSubstring

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    private val TextView.stringValue
        get() = text.toString()

    private val TextView.isEmpty
        get() = this.stringValue == ""

    @SuppressLint("SetTextI18n")
    fun mainSearchSubstring(view: View) {
        if (stringEditText.isEmpty || substringEditText.isEmpty) {
            answerTextView.text = "One of the strings is empty"
            return
        }

        val answer = searchSubstring(
                stringEditText.stringValue,
                substringEditText.stringValue
        )
        when (answer) {
            -1 -> answerTextView.text = "Substring is not contained in the string"
            else -> answerTextView.text = "First occurrence is " + answer.toString()
        }
    }
}
