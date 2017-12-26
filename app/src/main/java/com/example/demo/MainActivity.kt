package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import DsuBase
import android.support.constraint.ConstraintSet
import android.view.View
import android.view.View.generateViewId
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import dsl.constraintLayout

class MainActivity : AppCompatActivity() {

    var valueA: Int = 0
    var valueB: Int = 0
    val mainDsu = DsuBase()

    private fun dp(value: Int) = (this.applicationContext.resources.displayMetrics.density * value).toInt()
    private fun sp(value: Int) = (getResources().getConfiguration().fontScale * value)

    val layoutId = generateViewId()
    val idButUnion = generateViewId()
    val idButCleanUp = generateViewId()
    val idButAddSet = generateViewId()
    val idButIsCommon = generateViewId()
    val idEditParamA = generateViewId()
    val idEditParamB = generateViewId()
    val idStaticTextA = generateViewId()
    val idStaticTextB = generateViewId()
    val idTextViewSize = generateViewId()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
//        setContentView(R.layout.activity_main)
        setContentView(constraintLayout(layoutId) {

            button(idButIsCommon) {
                text = "Is Common Set(A, B)"
                width = dp(200)
                height = ConstraintSet.WRAP_CONTENT
                bottomMargin(layoutId, ConstraintSet.BOTTOM, dp(28))
                leftMargin(layoutId, ConstraintSet.START, dp(8))
                rightMargin(layoutId, ConstraintSet.END, dp(8))
            }

            button(idButUnion) {
                text = "Union(A, B)"
                width = dp(150)
                height = ConstraintSet.WRAP_CONTENT
                bottomMargin(idButIsCommon, ConstraintSet.TOP, dp(8))
                leftMargin(layoutId, ConstraintSet.START, dp(8))
                rightMargin(layoutId, ConstraintSet.END, dp(8))
            }

            textView(idTextViewSize) {
                text = "0"
                textSize = sp(30)
                width = dp(50)
                height = dp(50)
                topMargin(layoutId, ConstraintSet.TOP, dp(8))
                leftMargin(layoutId, ConstraintSet.START, dp(8))
                rightMargin(layoutId, ConstraintSet.END, dp(8))
                bottomMargin(idButUnion, ConstraintSet.TOP, dp(8))
            }

            button(idButCleanUp) {
                text = "Clean up"
                width = dp(130)
                height = dp(50)
                bottomMargin(idButUnion, ConstraintSet.BOTTOM, dp(8))
                leftMargin(layoutId, ConstraintSet.START, dp(8))
                topMargin(idTextViewSize, ConstraintSet.BOTTOM, dp(8))
            }

            button(idButAddSet) {
                text = "Add set"
                width = ConstraintSet.WRAP_CONTENT
                height = ConstraintSet.WRAP_CONTENT
                bottomMargin(idButUnion, ConstraintSet.BOTTOM, dp(8))
                rightMargin(layoutId, ConstraintSet.END, dp(8))
                topMargin(idTextViewSize, ConstraintSet.BOTTOM, dp(8))
            }

            editText(idEditParamA) {
                width = dp(67)
                height = dp(57)
                leftMargin(layoutId, ConstraintSet.START, dp(15))
                topMargin(layoutId, ConstraintSet.TOP, dp(25))
            }

            editText(idEditParamB) {
                width = dp(67)
                height = dp(57)
                rightMargin(layoutId, ConstraintSet.END, dp(8))
                topMargin(layoutId, ConstraintSet.TOP, dp(25))
            }

            textView(idStaticTextA) {
                text = "A:"
                textSize = sp(18)
                topMargin(idEditParamA, ConstraintSet.TOP, dp(4))
                bottomMargin(idEditParamA, ConstraintSet.BOTTOM, dp(4))
                rightMargin(idEditParamA, ConstraintSet.START, dp(8))
                leftMargin(layoutId, ConstraintSet.START, dp(16))
            }

            textView(idStaticTextB) {
                text = "B:"
                textSize = sp(18)
                topMargin(idEditParamB, ConstraintSet.TOP, dp(4))
                bottomMargin(idEditParamB, ConstraintSet.BOTTOM, dp(4))
                rightMargin(idEditParamB, ConstraintSet.START, dp(4))
            }

        })

        findViewById<Button>(idButUnion).setOnClickListener { onClickUnion() }
        findViewById<Button>(idButCleanUp).setOnClickListener { onClickCleanUp() }
        findViewById<Button>(idButAddSet).setOnClickListener { onClickAddSet() }
        findViewById<Button>(idButIsCommon).setOnClickListener { onClickIsCommonSet() }
    }

    fun onClickAddSet() {
        mainDsu.addNode()
        val textViewSize = findViewById<View>(idTextViewSize) as TextView
        textViewSize.text = mainDsu.size().toString()
    }

    fun readAB(): Boolean {
        val editA = (findViewById<View>(idEditParamA) as TextView)
        val editB = (findViewById<View>(idEditParamB) as TextView)
        try {
            valueA = editA.text.toString().toInt()
            valueB = editB.text.toString().toInt()
            --valueA
            --valueB
            if (valueA >= mainDsu.size() || valueB >= mainDsu.size()) {
                val myToast = Toast.makeText(this, "Can you add more node?", Toast.LENGTH_SHORT)
                myToast.show()
                return false
            }
            return true
        } catch (e: Exception) {
            val myToast = Toast.makeText(this, "Error input!", Toast.LENGTH_SHORT)
            myToast.show()
            editA.text = ""
            editB.text = ""
            return false
        }
    }

    fun onClickUnion() {
        if (readAB()) {
            val strMessage = if (mainDsu.unionSet(valueA, valueB))
                "Element is connected!"
            else "Element was connected yet."
            val myToast = Toast.makeText(this, strMessage, Toast.LENGTH_SHORT)
            myToast.show()
        }
    }

    fun onClickIsCommonSet() {
        if (readAB()) {
            val myMessage = if (mainDsu.commonSet(valueA, valueB)) "It's True." else "It's False"
            val myToast = Toast.makeText(this, myMessage, Toast.LENGTH_SHORT)
            myToast.show()
        }
    }

    fun onClickCleanUp() {
        mainDsu.clear()
        val textViewSize = findViewById<View>(idTextViewSize) as TextView
        textViewSize.text = mainDsu.size().toString()
    }
}