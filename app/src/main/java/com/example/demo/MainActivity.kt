package com.example.demo

import android.content.Intent
import android.graphics.Color
import android.graphics.Typeface

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout(this, R.layout.activity_main) {
            layout.setBackgroundColor(resources.getColor(R.color.screenBackground))
            textView(R.id.textViewInfo) {
                text = getString(R.string.main_head)
                textSize = 14f
                setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
                setTextColor(Color.WHITE)
                connectFromAToB(TOP, TOP, R.id.textViewInfo, R.layout.activity_main, 25)
                connectFromAToB(START, START, R.id.textViewInfo, R.layout.activity_main)
                connectFromAToB(END, END, R.id.textViewInfo, R.layout.activity_main)
            }
            textView(R.id.textViewM) {
                text = "0"
                textSize = 72f
                setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
                setTextColor(Color.WHITE)
                connectFromAToB(TOP, BOTTOM, R.id.textViewM, R.id.textViewInfo, 50)
                connectFromAToB(START, START, R.id.textViewM, R.id.middle_button)
                connectFromAToB(END, END, R.id.textViewM, R.id.middle_button)

            }
            textView(R.id.textViewN) {
                text = "0"
                textSize = 72f
                setTypeface(Typeface.SANS_SERIF, Typeface.BOLD)
                setTextColor(Color.WHITE)
                connectFromAToB(TOP, BOTTOM, R.id.textViewN, R.id.textViewInfo, 50)
                connectFromAToB(START, START, R.id.textViewN, R.id.left_button)
                connectFromAToB(END, END, R.id.textViewN, R.id.left_button)
            }
            button(R.id.left_button) {
                text = "Inc N"
                connectFromAToB(TOP, BOTTOM, R.id.left_button, R.id.textViewN, 50)
                connectFromAToB(START, START, R.id.left_button, R.layout.activity_main, 20)
            }
            button(R.id.middle_button) {
                text = "Inc M"
                connectFromAToB(TOP, BOTTOM, R.id.middle_button, R.id.textViewM, 50)
                connectFromAToB(START, START, R.id.middle_button, R.layout.activity_main)
                connectFromAToB(END, END, R.id.middle_button, R.layout.activity_main)
            }
            button(R.id.right_button) {
                text = "Start"
                connectFromAToB(TOP, BOTTOM, R.id.right_button, R.id.textViewM, 50)
                connectFromAToB(END, END, R.id.right_button, R.layout.activity_main, 20)
            }
        })
        var textViewN = findViewById<TextView>(R.id.textViewN)
        var textViewM = findViewById<TextView>(R.id.textViewM)
        var buttonN = findViewById<Button>(R.id.left_button)
        var buttonM = findViewById<Button>(R.id.middle_button)
        var buttonStart = findViewById<Button>(R.id.right_button)
        fun nextN() {
            textViewN.text = (textViewN.text.toString().toInt() + 1).toString()
        }

        fun nextM() {
            textViewM.text = (textViewM.text.toString().toInt() + 1).toString()
        }

        fun startRandomTest() {
            val randomIntent = Intent(this, SecondActivity::class.java)
            val countM = textViewM.text.toString().toInt()
            val countN = textViewN.text.toString().toInt()
            if (countM <= 0 || countN <= 0) {
                val toast = Toast.makeText(this, "N or M is Uncorrect", Toast.LENGTH_SHORT)
                toast.show()
                return
            }
            randomIntent.putExtra("countN", countN)
            randomIntent.putExtra("countM", countM)
            startActivity(randomIntent)
        }
        buttonN.setOnClickListener { nextN() }
        buttonM.setOnClickListener { nextM() }
        buttonStart.setOnClickListener { startRandomTest() }
    }

}
