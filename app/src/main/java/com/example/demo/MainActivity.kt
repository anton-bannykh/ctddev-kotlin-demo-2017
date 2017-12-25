package com.example.demo

import android.graphics.Color.BLACK
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet.TOP
import android.support.constraint.ConstraintSet.BOTTOM
import android.support.constraint.ConstraintSet.START
import android.support.constraint.ConstraintSet.END
import android.support.constraint.ConstraintSet.WRAP_CONTENT
import android.support.constraint.ConstraintSet.PARENT_ID
import android.view.View
import android.view.View.generateViewId
import android.widget.TextView
import android.widget.Toast
import java.util.Random
import my.lib.mergeSort

class MainActivity : AppCompatActivity() {

    private val descriptionStart = generateViewId()
    private val descriptionOther = generateViewId()
    private val holderStartArr = generateViewId()
    private val holderCurArr = generateViewId()
    private val buttonStart = generateViewId()
    private val buttonSort = generateViewId()
    private val buttonShuffle = generateViewId()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val mainLayout =
                constraintLayout {
                    textView(descriptionStart) {
                        width = convertDpToPx(269f)
                        height = convertDpToPx(33f)
                        text = "Enter the array and press START:"
                        setTextColor(BLACK)
                        textSize = 18f
                        setConstraint(START, PARENT_ID, START, convertDpToPx(8f))
                        setConstraint(TOP, PARENT_ID, TOP, convertDpToPx(8f))
                    }
                    editText(holderStartArr) {
                        width = 1234
                        height = convertDpToPx(47f)
                        setEms(20)
                        inputType = 1
                        setConstraint(START, PARENT_ID, START, convertDpToPx(8f))
                        setConstraint(END, PARENT_ID, END, convertDpToPx(8f))
                        setConstraint(TOP, descriptionStart, BOTTOM, convertDpToPx(8f))
                    }
                    textView(descriptionOther) {
                        width = convertDpToPx(113f)
                        height = convertDpToPx(25f)
                        text = "Current array:"
                        setTextColor(BLACK)
                        textSize = 18f
                        setConstraint(START, PARENT_ID, START, convertDpToPx(8f))
                        setConstraint(END, PARENT_ID, END, convertDpToPx(8f))
                        setConstraint(TOP, holderStartArr, BOTTOM, convertDpToPx(40f))
                    }
                    button(buttonStart) {
                        text = "START"
                        width = WRAP_CONTENT
                        height = WRAP_CONTENT
                        setOnClickListener { start(this) }
                        setConstraint(START, PARENT_ID, START, convertDpToPx(8f))
                        setConstraint(TOP, holderStartArr, BOTTOM, convertDpToPx(8f))
                        setConstraint(BOTTOM, descriptionOther, TOP, convertDpToPx(0f))
                    }
                    textView(holderCurArr) {
                        width = 1234
                        height = convertDpToPx(47f)
                        text = "1 2 3 4 5"
                        setTextColor(BLACK)
                        textSize = 18f
                        setConstraint(START, PARENT_ID, START, 0)
                        setConstraint(END, PARENT_ID, END, 0)
                        setConstraint(TOP, descriptionOther, BOTTOM, convertDpToPx(8f))
                    }
                    button(buttonSort) {
                        text = "SORT"
                        width = WRAP_CONTENT
                        height = WRAP_CONTENT
                        setOnClickListener { sort(this) }
                        setConstraint(START, PARENT_ID, START, convertDpToPx(8f))
                        setConstraint(BOTTOM, PARENT_ID, BOTTOM, convertDpToPx(8f))
                        setConstraint(TOP, holderCurArr, BOTTOM, convertDpToPx(8f))
                    }
                    button(buttonShuffle) {
                        text = "SHUFFLE"
                        width = WRAP_CONTENT
                        height = WRAP_CONTENT
                        setOnClickListener { shuffle(this) }
                        setConstraint(END, PARENT_ID, END, convertDpToPx(8f))
                        setConstraint(BOTTOM, PARENT_ID, BOTTOM, convertDpToPx(8f))
                        setConstraint(TOP, holderCurArr, BOTTOM, convertDpToPx(8f))
                    }
                }
        setContentView(mainLayout)
    }

    fun start(view: View) {
        val startArray = (findViewById<View>(holderStartArr) as TextView).text.toString()
        val holder = findViewById<View>(holderCurArr) as TextView
        holder.text = try {
            startArray.split(" ").map { it -> it.toInt() }.joinToString(" ")
        } catch (e: Exception) {
            val myToast = Toast.makeText(this, "Incorrect input.\nArray must be represented as set of numbers separated by a space.\nTry again.", Toast.LENGTH_SHORT)
            myToast.show()
            "1 2 3 4 5"
        }
    }

    fun sort(view: View) {
        val holder = findViewById<View>(holderCurArr) as TextView
        val array = holder.text.split(" ").map { it -> it.toInt() }.toIntArray()
        mergeSort(array)
        holder.text = array.joinToString(" ")
    }

    fun shuffle(view: View) {
        val holder = findViewById<View>(holderCurArr) as TextView
        val array = holder.text.split(" ").map { it -> it.toInt() }.toMutableList()
        for (i in array.indices) {
            val newPos = Random().nextInt(array.size)
            val tmp = array[i]
            array[i] = array[newPos]
            array[newPos] = tmp
        }
        holder.text = array.joinToString(" ")
    }
}
