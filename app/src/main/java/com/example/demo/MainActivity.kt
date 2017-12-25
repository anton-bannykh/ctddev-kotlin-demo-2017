package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import my.lib.quickSort

class MainActivity : AppCompatActivity() {

    private lateinit var input:EditText
    private lateinit var button:Button
    private lateinit var display:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        button = findViewById<Button>(R.id.button)
        input = findViewById<EditText>(R.id.input)
        display = findViewById<TextView>(R.id.display)
    }


    var array = ArrayList<Int> ()

    fun addNumbers(view: View) {
        val num: Int = input.text.toString().toInt()
        array.add(num)
        display.text = array.toString()
        input.text.clear()
    }

    fun sort(view: View) {
        quickSort(array)
        display.text = array.toString()
    }

}
