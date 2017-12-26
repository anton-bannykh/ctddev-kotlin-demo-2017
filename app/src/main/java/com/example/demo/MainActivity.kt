package com.example.demo

import android.graphics.Typeface
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import android.view.View.generateViewId
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

    val layoutId = generateViewId()
    val textView3 = generateViewId()
    val editText3 = generateViewId()
    val button4 = generateViewId()
    val textView2 = generateViewId()
    val editText2 = generateViewId()
    val button = generateViewId()
    val textView = generateViewId()
    val editText = generateViewId()
    val button3 = generateViewId()
    val textView4 = generateViewId()

    private var tree = SplayTree<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

//        setContentView(R.layout.activity_main)
        setContentView(constraintLayout(layoutId) {
            textView(textView3) {
                width = dp(100)
                height = dp(40)
                leftMargin(layoutId, ConstraintSet.START, dp(28))
                topMargin(layoutId, ConstraintSet.TOP, dp(48))
                text = resources.getText(R.string.enter_value)
            }
            editText(editText3) {
                width = dp(150)
                height = dp(40)
                setEms(10)
                inputType = InputType.TYPE_CLASS_NUMBER
                labelFor = editText3
                rightMargin(layoutId, ConstraintSet.END, dp(24))
                topMargin(layoutId, ConstraintSet.TOP, dp(48))
            }
            button(button4) {
                width = dp(100)
                height = dp(40)
                text = resources.getText(R.string.add_value)
                topMargin(layoutId, ConstraintSet.TOP, dp(88))
                leftMargin(layoutId, ConstraintSet.START, dp(8))
                rightMargin(layoutId, ConstraintSet.END, dp(8))
            }

            textView(textView2) {
                width = dp(100)
                height = dp(40)
                leftMargin(layoutId, ConstraintSet.START, dp(28))
                topMargin(textView3, ConstraintSet.BOTTOM, dp(60))
                text = resources.getText(R.string.enter_value)
            }
            editText(editText2) {
                width = dp(150)
                height = dp(40)
                setEms(10)
                inputType = InputType.TYPE_CLASS_NUMBER
                labelFor = editText2
                rightMargin(layoutId, ConstraintSet.END, dp(24))
                topMargin(editText3, ConstraintSet.BOTTOM, dp(60))
            }
            button(button) {
                width = dp(100)
                height = dp(40)
                text = resources.getText(R.string.erase)
                topMargin(button4, ConstraintSet.BOTTOM, dp(60))
                leftMargin(layoutId, ConstraintSet.START, dp(8))
                rightMargin(layoutId, ConstraintSet.END, dp(8))
            }

            textView(textView) {
                width = dp(100)
                height = dp(40)
                leftMargin(layoutId, ConstraintSet.START, dp(28))
                topMargin(textView2, ConstraintSet.BOTTOM, dp(60))
                text = resources.getText(R.string.enter_value)
            }
            editText(editText) {
                width = dp(150)
                height = dp(40)
                setEms(10)
                inputType = InputType.TYPE_CLASS_NUMBER
                labelFor = editText
                rightMargin(layoutId, ConstraintSet.END, dp(24))
                topMargin(editText2, ConstraintSet.BOTTOM, dp(60))
            }
            button(button3) {
                width = dp(100)
                height = dp(40)
                text = resources.getText(R.string.check)
                topMargin(button, ConstraintSet.BOTTOM, dp(60))
                leftMargin(layoutId, ConstraintSet.START, dp(8))
                rightMargin(layoutId, ConstraintSet.END, dp(8))
            }

            textView(textView4) {
                width = dp(170)
                height = dp(55)
                textSize = sp(14)
                setTypeface(null, Typeface.BOLD)
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                leftMargin(layoutId, ConstraintSet.START, dp(0))
                rightMargin(layoutId, ConstraintSet.END, dp(0))
                bottomMargin(layoutId, ConstraintSet.BOTTOM, dp(8))
                topMargin(button3, ConstraintSet.BOTTOM, dp(8))
                text = resources.getText(R.string.enter_value)
            }

        })

        addValueField = findViewById(editText3)
        checkValueField = findViewById(editText)
        delValueField = findViewById(editText2)
        answerField = findViewById(textView4)

        val btnAdd = findViewById<Button>(button4)
        val btnDel = findViewById<Button>(button)
        val btnCheck = findViewById<Button>(button3)

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