package com.example.demo

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

class Main3Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val view = constraintLayout {
            id = R.id.constLay3
            textView {
                id = R.id.help
                text = resources.getString(R.string.helpMessage)
                textSize = 24f
                height = "match"
                width = "match"
                textColor = Color.BLACK
                margins {
                    marginTop = 32
                    marginStart = 16
                    marginEnd = 16
                    marginBottom = 8
                }
            }
        }
        setContentView(view)
    }
}