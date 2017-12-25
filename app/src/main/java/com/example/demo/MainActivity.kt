package com.example.demo

import android.graphics.Typeface
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

import my.lib.SplayTree

class MainActivity : AppCompatActivity() {

    private fun dp(value: Int) = (this.applicationContext.resources.displayMetrics.density * value).toInt()
    private fun sp(value: Int) = (getResources().getConfiguration().fontScale * value)

    private lateinit var addValueField : EditText
    private lateinit var checkValueField : EditText
    private lateinit var delValueField: EditText
    private lateinit var answerField : TextView

    private var tree = SplayTree<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(constraintLayout(R.id.layout) {
            textView(R.id.textView3) {
                width = dp(100)
                height = dp(40)
                leftMargin(R.id.layout, ConstraintSet.START, dp(28))
                topMargin(R.id.layout, ConstraintSet.TOP, dp(48))
                text = resources.getText(R.string.enter_value)
            }
            editText(R.id.editText3) {
                width = dp(150)
                height = dp(40)
                setEms(10)
                inputType = InputType.TYPE_CLASS_NUMBER
                labelFor = R.id.editText3
                rightMargin(R.id.layout, ConstraintSet.END, dp(24))
                topMargin(R.id.layout, ConstraintSet.TOP, dp(48))
            }
            button(R.id.button4) {
                width = dp(100)
                height = dp(40)
                text = resources.getText(R.string.add_value)
                topMargin(R.id.layout, ConstraintSet.TOP, dp(88))
                leftMargin(R.id.layout, ConstraintSet.START, dp(8))
                rightMargin(R.id.layout, ConstraintSet.END, dp(8))
            }

            textView(R.id.textView2) {
                width = dp(100)
                height = dp(40)
                leftMargin(R.id.layout, ConstraintSet.START, dp(28))
                topMargin(R.id.textView3, ConstraintSet.BOTTOM, dp(60))
                text = resources.getText(R.string.enter_value)
            }
            editText(R.id.editText2) {
                width = dp(150)
                height = dp(40)
                setEms(10)
                inputType = InputType.TYPE_CLASS_NUMBER
                labelFor = R.id.editText2
                rightMargin(R.id.layout, ConstraintSet.END, dp(24))
                topMargin(R.id.editText3, ConstraintSet.BOTTOM, dp(60))
            }
            button(R.id.button) {
                width = dp(100)
                height = dp(40)
                text = resources.getText(R.string.erase)
                topMargin(R.id.button4, ConstraintSet.BOTTOM, dp(60))
                leftMargin(R.id.layout, ConstraintSet.START, dp(8))
                rightMargin(R.id.layout, ConstraintSet.END, dp(8))
            }

            textView(R.id.textView) {
                width = dp(100)
                height = dp(40)
                leftMargin(R.id.layout, ConstraintSet.START, dp(28))
                topMargin(R.id.textView2, ConstraintSet.BOTTOM, dp(60))
                text = resources.getText(R.string.enter_value)
            }
            editText(R.id.editText) {
                width = dp(150)
                height = dp(40)
                setEms(10)
                inputType = InputType.TYPE_CLASS_NUMBER
                labelFor = R.id.editText
                rightMargin(R.id.layout, ConstraintSet.END, dp(24))
                topMargin(R.id.editText2, ConstraintSet.BOTTOM, dp(60))
            }
            button(R.id.button3) {
                width = dp(100)
                height = dp(40)
                text = resources.getText(R.string.check)
                topMargin(R.id.button, ConstraintSet.BOTTOM, dp(60))
                leftMargin(R.id.layout, ConstraintSet.START, dp(8))
                rightMargin(R.id.layout, ConstraintSet.END, dp(8))
            }

            textView(R.id.textView4) {
                width = dp(170)
                height = dp(55)
                textSize = sp(14)
                setTypeface(null, Typeface.BOLD)
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                leftMargin(R.id.layout, ConstraintSet.START, dp(0))
                rightMargin(R.id.layout, ConstraintSet.END, dp(0))
                bottomMargin(R.id.layout, ConstraintSet.BOTTOM, dp(8))
                topMargin(R.id.button3, ConstraintSet.BOTTOM, dp(8))
                text = resources.getText(R.string.enter_value)
            }

        })

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