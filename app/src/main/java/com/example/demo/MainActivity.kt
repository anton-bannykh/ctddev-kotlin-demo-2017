package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.Toast
import heapSort
import kotlinx.android.synthetic.main.activity_main.editText
import kotlinx.android.synthetic.main.activity_main.editText2
import kotlinx.android.synthetic.main.activity_main.sortOff
import java.util.Arrays

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText.setOnTouchListener({ view: View, motionEvent: MotionEvent ->
            if (editText.text.toString() == getString(R.string.sortoff_text)) {
                editText.setText("")
                true
            } else
                false
        })
        sortOff.setOnTouchListener({ view: View, motionEvent: MotionEvent ->
            val ss = editText.text.toString().trim()
            val arr = ss.split("\\s+".toRegex())
            val arrInt = Array<Int>(arr.size, { _ -> 0 })
            if (arr.size == 1 && arr[0].isEmpty()) {
                editText2.setText(getString(R.string.empty_input))
                Toast.makeText(this, getString(R.string.empty_input), Toast.LENGTH_SHORT).show()
            } else {
                try {
                    for ((i, s) in arr.withIndex()) {
                        arrInt[i] = s.toInt()
                    }
                } catch (e: Exception) {
                    e.printStackTrace()
                    editText2.setText(getString(R.string.invalid_input))
                    Toast.makeText(this, getString(R.string.invalid_input), Toast.LENGTH_SHORT).show()
                    return@setOnTouchListener true
                }
                heapSort(arrInt)
                editText2.setText(Arrays.toString(arrInt))
            }
            true
        })
    }
}
