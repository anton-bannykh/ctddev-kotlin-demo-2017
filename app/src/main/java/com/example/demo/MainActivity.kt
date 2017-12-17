package com.example.mcyoba.cbs

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import com.example.demo.R

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    class Stack<T> {
        val elements: MutableList<T> = mutableListOf()
        fun isEmpty() = elements.isEmpty()
        fun count() = elements.size
        fun push(item: T) = elements.add(item)
        fun pop(): T? {
            val item = elements.lastOrNull()
            if (!isEmpty()) {
                elements.removeAt(elements.size - 1)
            }
            return item
        }

        fun peek(): T? = elements.lastOrNull()

    }

    fun cbs(s: String): Boolean {
        val st = Stack<Char>()
        var flag = false
        for (i in s) {
            if (i == '(')
                st.push(i)
            else {
                if (st.isEmpty()) {
                    flag = true
                    break
                } else
                    st.pop()

            }
        }
        return (st.isEmpty() && !flag)

    }

    fun input(view: View) {
        val inputText = findViewById<View>(R.id.input_field) as TextView
        val sequence = inputText.text.toString()
        inputText.text = ""
        val outputText = findViewById<View>(R.id.answer) as TextView
        if (cbs(sequence)) {
            outputText.text = getString(R.string.Corr)

        } else {
            outputText.text = getString(R.string.False)
        }
    }

}
