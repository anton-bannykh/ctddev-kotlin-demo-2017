package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import my.lib.BinarySearch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val edit1 = findViewById<EditText>(R.id.editText)
        val edit2 = findViewById<EditText>(R.id.editText2)
        val buttonAdd = findViewById<Button>(R.id.button)
        val buttonAns = findViewById<Button>(R.id.button2)
        val textArr = findViewById<TextView>(R.id.textView2)
        val textAns = findViewById<TextView>(R.id.textView4)
        val clear = findViewById<Button>(R.id.button3)

        var clean = true
        val arr = ArrayList<Int>()
        buttonAdd.setOnClickListener {
            if (clean) {
                val x = Integer.parseInt(edit1.text.toString())
                if (!arr.isEmpty() && arr.last() > x) {
                    Toast.makeText(this, "Введите x побольше", Toast.LENGTH_SHORT).show()
                    edit1.setText("")
                } else {
                    edit1.setText("")
                    arr.add(x)
                    textArr.text = textArr.text.toString().plus(" $x")
                }
            }
        }

        buttonAns.setOnClickListener {
            if (arr.isEmpty()) {
                Toast.makeText(this, "Сначала введите массив", Toast.LENGTH_SHORT).show()
            } else {
                val x = Integer.parseInt(edit2.text.toString())
                val ans = BinarySearch(arr.toIntArray(), x)
                textAns.text = "$ans"
            }
            clean = false
        }

        clear.setOnClickListener {
            clean = true
            textAns.text = ""
            textArr.text = ""
            edit2.setText("")
            arr.clear()
        }
    }

}
