package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import my.lib.createFenwickTree
import my.lib.getElement
import my.lib.updateAtSegment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)

        val arrayField = findViewById<EditText>(R.id.arrayField)
        val fromField = findViewById<EditText>(R.id.fromField)
        val toField = findViewById<EditText>(R.id.toField)
        val addValueField = findViewById<EditText>(R.id.addValueField)
        val indexField = findViewById<EditText>(R.id.indexField)

        fun initFenwickWithArray(array: List<String>, fenwick: IntArray) {
            for (i in 0 until array.size) {
                updateAtSegment(i, i, Integer.parseInt(array[i]), fenwick)
            }
        }

        val array = arrayField.text.split(" ")

        val fenwickTree = createFenwickTree(array.size)
        initFenwickWithArray(array, fenwickTree)

        val addRequestButton = findViewById<Button>(R.id.addRequest)
        addRequestButton.setOnClickListener {
            updateAtSegment(Integer.parseInt(fromField.text.toString()),
                    Integer.parseInt(toField.text.toString()),
                    Integer.parseInt(addValueField.text.toString()), fenwickTree)
        }

        val getButton = findViewById<Button>(R.id.getRequest)
        getButton.setOnClickListener {
            val index = Integer.parseInt(indexField.text.toString())
            val valueAtIndex = getElement(index, fenwickTree)
            Toast.makeText(this, "Значение в индексе $index = $valueAtIndex", Toast.LENGTH_SHORT).show()
        }
    }
}
