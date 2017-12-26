package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet.*
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import my.lib.create

class MainActivity : AppCompatActivity() {
    var n = 0
    var array = Array<Int>(1, { 0 })
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(constraintLayout(R.layout.activity_main){
            editText(R.id.count) {
                isCursorVisible = false
                setEms(10)
                setSingleLine(false)
                leftMargin(R.layout.activity_main, START, 0)
                topMargin(R.id.submitCount, BOTTOM, 0)
            }
            button(R.id.submitN) {
                text = "Задать"
                rightMargin(R.layout.activity_main, END, 0)
                leftMargin(R.id.count, END, 0)
                topMargin(R.id.submitCount, BOTTOM, 0)
            }
            editText(R.id.readArray){
                setEms(10)
                leftMargin(R.layout.activity_main, START, 0)
                topMargin(R.id.headArray, BOTTOM, 0)
            }
            button(R.id.submitArray){
                text = "Задать"
                rightMargin(R.layout.activity_main, END, 0)
                leftMargin(R.id.readArray, END, 0)
                topMargin(R.id.headArray, BOTTOM, 0)
            }
            button(R.id.startQuickSort){
                text = "Отсортировать массив"
                rightMargin(R.layout.activity_main, END, 0)
                leftMargin(R.layout.activity_main, START, 0)
                topMargin(R.id.readArray, BOTTOM, 0)
            }
            textView(R.id.answer){
                text = getString(R.string.answer)
                rightMargin(R.layout.activity_main, END, 0)
                leftMargin(R.layout.activity_main, START, 0)
                topMargin(R.id.startQuickSort, BOTTOM, 0)
            }
            textView(R.id.submitCount){
                text = "Введите кол-во чисел"
                leftMargin(R.layout.activity_main, START, 0)
                topMargin(R.layout.activity_main, TOP, 0)
            }
            textView(R.id.headArray){
                text = "Введите массив"
                leftMargin(R.layout.activity_main, START, 0)
                topMargin(R.id.count, BOTTOM, 0)
            }
        })
        var count = findViewById<EditText>(R.id.count)
        var readArray = findViewById<EditText>(R.id.readArray)
        var answer = findViewById<TextView>(R.id.answer)
        fun readN() {
            n = count.text.toString().toInt()
        }

        fun readArray() {
            val arrayString = readArray.text.toString().split(" ")
            array = Array<Int>(arrayString.size, { i -> arrayString[i].toInt() })
        }

        fun startQuickSort() {
            val unsortedArray = array
            var unsortedArrayString = ""
            for (i in (0..(n - 1))) {
                unsortedArrayString += unsortedArray[i].toString() + " "
            }
            array = create(n, array)
            val sortedArray = array
            var sortedArrayString = ""
            for (i in (0..(n - 1))) {
                sortedArrayString += sortedArray[i].toString() + " "
            }
            answer.text = getString(R.string.answer, unsortedArrayString, sortedArrayString)
        }
        findViewById<Button>(R.id.submitN).setOnClickListener { readN() }
        findViewById<Button>(R.id.submitArray).setOnClickListener{ readArray() }
        findViewById<Button>(R.id.startQuickSort).setOnClickListener { startQuickSort() }
    }

}
