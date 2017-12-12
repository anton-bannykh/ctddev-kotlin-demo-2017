package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.content.Intent
import my.lib.sumFun

class MainActivity : AppCompatActivity() {

    private var numOfVertices = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    }

    fun readNumOfVertices(view : View) {
        val showInpEditText = findViewById<View>(R.id.plain_text_input) as EditText
        val numOfVerticesString = showInpEditText.text.toString()
        if (numOfVerticesString == "") {
            showInpEditText.hint = "Please enter num of vertices"
        } else {
            numOfVertices = Integer.parseInt(numOfVerticesString)
            if (numOfVertices == 0) {
                showInpEditText.hint = "Number must be greater than zero."
                showInpEditText.text = null
                numOfVertices = 1
            }
        }

    }
    fun readSequenceOfVertices(view : View) {
        val showInpEditText = findViewById<View>(R.id.vertices_sequence_input) as EditText
        val verticesSequenceString = showInpEditText.text.toString()
        verticesSequenceString.trim()
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        intent.putExtra("seq", verticesSequenceString)
        intent.putExtra("num", numOfVertices)
        startActivity(intent)

    }

    fun test() = sumFun(1, 2, 3)

}
