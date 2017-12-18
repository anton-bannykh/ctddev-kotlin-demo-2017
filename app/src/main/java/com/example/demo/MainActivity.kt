package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import my.lib.SplayTree

//import my.lib.sumFun

class MainActivity : AppCompatActivity() {

    private lateinit var addValueField : EditText
    private lateinit var checkValueField : EditText
    private lateinit var delValueField: EditText
    private lateinit var answerField : TextView

    private var tree = SplayTree<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addValueField = findViewById(R.id.editText3)
        checkValueField = findViewById(R.id.editText)
        delValueField = findViewById(R.id.editText2)
        answerField = findViewById(R.id.textView4)

        val btnAdd = findViewById<Button>(R.id.button4)
        val btnDel = findViewById<Button>(R.id.button)
        val btnCheck = findViewById<Button>(R.id.button3)

        btnAdd.setOnClickListener { addElement() }
        btnDel.setOnClickListener { delElement() }
        btnCheck.setOnClickListener { checkElement() }
    }

    fun addElement() {
        if (addValueField.text.isEmpty()) return
        val x : Int = addValueField.text.toString().toInt()
        tree.put(x)
        val s = x.toString() + " was added to Splay tree"
        answerField.text = s
    }

    fun delElement() {
        if (addValueField.text.isEmpty()) return
        val x : Int = delValueField.text.toString().toInt()
        tree.del(x)
        val s = x.toString() + " was erased from Splay tree"
        answerField.text = s
    }

    fun checkElement() {
        if (addValueField.text.isEmpty()) return
        val x : Int = checkValueField.text.toString().toInt()
        answerField.text = if (tree.contains(x)) "Splay tree contains element " + x.toString() else "Splay tree doesn't contain element " + x.toString()
    }

//    fun test() = sumFun(1, 2, 3)
}
