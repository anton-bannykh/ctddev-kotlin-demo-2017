package com.example.test.newapp

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.TextUtils.split
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text
import java.util.Random

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById(R.id.button) as Button
        button.setOnClickListener {
            val edit =  findViewById(R.id.editText) as EditText
            val input = edit.getText().toString().split(" ").map(String::toInt).toTypedArray()
            val edit_n = findViewById(R.id.editText2) as EditText
            val input_n = edit_n.getText().toString().toInt()
            val view_text = findViewById(R.id.textView2) as TextView
            view_text.setText(nth_element(input, input_n).toString())
        }
    }
    fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start

    fun Array<Int>.swap(index1: Int, index2: Int) {
        val tmp = this[index1]
        this[index1] = this[index2]
        this[index2] = tmp
    }

    fun partition(arr: Array<Int>, left: Int, right: Int) : Int {
        if (left != right) {
            arr.swap(left + (0..right-left).random(), right)
        }
        var lastElementValue = arr[right]
        var i = left - 1
        for (j in left..right) {
            if (arr[j] <= lastElementValue) {
                i++
                arr.swap(i, j)
            }
        }
        return i
    }

    fun nth_element(array: Array<Int>, ind : Int) : Int {
        var left = 0
        var right = array.size - 1
        var index = ind - 1
        while (true) {
            var mid = partition(array, left, right)
            if (mid == index) {
                return array[mid]
            } else if (index < mid) {
                right = mid - 1
            } else {
                left = mid + 1
            }
        }
    }
}
