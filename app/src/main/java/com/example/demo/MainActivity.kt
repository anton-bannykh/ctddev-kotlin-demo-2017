package com.example.demo


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.view.View
import android.widget.TextView
import dsl.makeLayout



class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(
                makeLayout(R.id.Layout) {

                    editText(R.id.input_field) {
                        topMargin(R.id.heading, _BOTTOM, 8)
                        leftMargin(R.id.Layout, _LEFT, 8)
                        rightMargin(R.id.Layout, _RIGHT, 8)
                        height = toDP(50)
                        width = toDP(300)
                        textSize = 26f

                    }

                    button(R.id.run){
                        topMargin(R.id.input_field, _BOTTOM, 8)
                        leftMargin(R.id.Layout, _LEFT, 8)
                        rightMargin(R.id.Layout, _RIGHT, 8)
                        text = "Run"
                        onClick({input(  )})
                        width = 167
                        height = 37
                    }

                    textView(R.id.answer){
                        bottomMargin(R.id.Layout, _TOP, 204)
                        leftMargin(R.id.Layout, _LEFT, 8)
                        rightMargin(R.id.Layout, _RIGHT, 8)
                        //topMargin(R.id.run, _TOP)
                        text = ""
                        textSize = 32f
                    }

                    textView(R.id.heading){
                        topMargin(R.id.Layout, _TOP, 84)
                        leftMargin(R.id.Layout, _LEFT, 8)
                        rightMargin(R.id.Layout, _RIGHT, 8)
                        bottomMargin(R.id.Layout, _BOTTOM, 300)
                        text = "Enter the sequence below"
                        textSize = 26f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                        /*linkAB(_RIGHT, _RIGHT, R.id.heading,  R.layout.activity_main,8)
                        linkAB(_LEFT, _LEFT,   R.id.heading,R.layout.activity_main,8)
                        linkAB(_TOP, _TOP,  R.id.heading,  R.layout.activity_main,84)
                        linkAB(_BOTTOM, _BOTTOM, R.id.heading,  R.layout.activity_main, 0)*/

                    }
                }
        )
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

    private fun cbs(s: String): Boolean {
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

    fun input() {
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
