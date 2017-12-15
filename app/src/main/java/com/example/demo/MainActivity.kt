package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import my.lib.SegmentTree


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }


    val mutListArray = mutableListOf<Int>()
    var segmentTree = SegmentTree()
    var n = 0


    fun createSegmentTree(view: View) {
        val textArray: TextView = findViewById(R.id.array)
        if(textArray.text != null){
            if(textArray.text.toString()[0] != 'A') {
                val listArray = textArray.text.toString().split("\u0020")

                for (it in listArray) {
                    mutListArray.add(it.toInt())
                }
                n = mutListArray.size

                segmentTree = SegmentTree(mutListArray)
                textArray.text = "Array is ${textArray.text}"
            }
        }
    }



    fun query_(view: View) {
        val textAnswer: TextView = findViewById(R.id.answer)
        textAnswer.text = null

        val textQuery: TextView = findViewById(R.id.forQuery)
        if (textQuery.text != null) {
            val listQuery = textQuery.text.toString().split("\u0020")
            textQuery.text = null

            val a = listQuery[0].toInt()
            val b = listQuery[1].toInt()
            textAnswer.text = "Max element between $a and $b is ${segmentTree.query(a, b).toString()}"
        }
    }



    fun modify_(view: View){
        val textModify: TextView = findViewById(R.id.forModify)
        if(textModify.text != null) {
            val listModify = textModify.text.toString().split("\u0020")
            textModify.text = null

            val index = listModify[0].toInt()
            val value = listModify[1].toInt()

            segmentTree.modify(index, value)
            mutListArray[index] = value

            val textArray: TextView = findViewById(R.id.array)
            textArray.text = null
            textArray.text = "Array is ${mutListArray.joinToString(" ").substring(0, 2*n - 1)}"
        }
    }
}
