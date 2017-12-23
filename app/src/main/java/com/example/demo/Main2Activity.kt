package com.example.demo

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.TextView

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = constraintLayout {
            id = R.id.constLay2
            textView {
                id = R.id.answer
                text = "R"
                textSize = 72f
                height = "wrap"
                width = "wrap"
                textColor = Color.BLACK
                margins {
                    marginTop = 8
                    marginStart = 8
                    marginEnd = 8
                    marginBottom = 8
                }
            }
        }
        setContentView(view)
        getAnswer()
    }

    companion object {
        const val save = "0"
    }

    fun getAnswer() {
        findViewById<TextView>(R.id.answer).text = intent.getStringExtra(save)
    }
}