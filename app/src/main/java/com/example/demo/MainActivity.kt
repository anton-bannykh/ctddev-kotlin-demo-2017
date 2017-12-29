package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import my.lib.Shuffler
import java.util.Arrays
import kotlinx.android.synthetic.main.activity_main.editText
import kotlinx.android.synthetic.main.activity_main.button
import kotlinx.android.synthetic.main.activity_main.textView2

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        editText.setOnTouchListener({_: View, _: MotionEvent ->
            if (editText.text.toString() == getString(R.string.help1)) {
                editText.setText("")
                true
            } else {
                false
            }
        })
        button.setOnTouchListener({_: View, _: MotionEvent ->
            shuffleArray()
            true
        })
    }

    fun shuffleArray() {
        val message = editText.text.toString()
        val messages: List<String> = message.replace(",", " ").replace("(\\s)+".toRegex()," ").split(" ")
        var ints:List<Int> = List<Int>(0, {0})
        for (item in messages) {
            try {
                ints += Integer.parseInt(item)
            } catch (e: NumberFormatException) {
                break
            }
        }
        var result: String
        if (ints.size == messages.size)  {
            val shuffler = Shuffler<Int>()
            var intArray:Array<Int> = ints.toTypedArray()
            shuffler.shuffle(intArray)
            result = Arrays.toString(intArray)
        } else {
            result = "Wrong array format"
        }
        textView2.text = result
    }
}
