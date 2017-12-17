package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.*

class MainActivity : AppCompatActivity() {
    var n = 0
    var array = Array<Int>(1, {0})
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun readN(view: View) {
        n = count.text.toString().toInt()
    }

    fun readArray(view: View) {
        val arrayString = readArray.text.toString().split(" ")
        array = Array<Int>(arrayString.size, {i -> arrayString[i].toInt()})
    }
    fun startQuickSort(view: View) {
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

}
