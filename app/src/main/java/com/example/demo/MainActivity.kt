package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import my.lib.binSearch

class MainActivity : AppCompatActivity() {
    private lateinit var btnAdd:Button
    private lateinit var editTextAddNumber:EditText
    private lateinit var showArrayTextView:TextView
    private lateinit var btnFind:Button
    private lateinit var editTextFind:EditText
    private lateinit var showFind:TextView
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btnAdd = findViewById(R.id.button_add)
        editTextAddNumber = findViewById(R.id.editTextAddNumber)
        showArrayTextView = findViewById(R.id.textView_showArray)
        btnFind = findViewById(R.id.button_find)
        editTextFind = findViewById(R.id.editTextFind)
        showFind = findViewById(R.id.PosView)
    }

    private var a = ArrayList<Int> ()

    fun onAddNumber(view:View){
        val inputText:String = editTextAddNumber.text.toString()
        if(inputText==""){
            Toast.makeText(applicationContext,"Введите число",Toast.LENGTH_SHORT).show()
            editTextAddNumber.text.clear()
            return
        }
        if (!a.isEmpty()) {
            if (inputText.toInt() <= a[a.lastIndex]) {
                Toast.makeText(applicationContext, "Введите большее число", Toast.LENGTH_SHORT).show()
                editTextAddNumber.text.clear()
                return
            }
        }
        val number:Int = editTextAddNumber.text.toString().toInt()
        a.add(number)
        showArrayTextView.text = a.toString()
        editTextAddNumber.text.clear()
    }

    fun onFind(view: View) {
        val input = editTextFind.text.toString()
        if(input==""){
            Toast.makeText(applicationContext,"Введите число",Toast.LENGTH_SHORT).show()
            editTextFind.text.clear()
            return
        }
        val bin = binSearch(a, 0, a.size - 1, input.toInt())
        if (bin == a.size + 1) {
            Toast.makeText(applicationContext, "Числа не существует", Toast.LENGTH_SHORT).show()
            editTextFind.text.clear()
            return
        }
        showFind.text = bin.toString()
        editTextFind.text.clear()

    }
}
