package com.example.drden.androsort

import android.graphics.Color.BLACK
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet.*
import android.view.View
import android.view.View.generateViewId
import android.widget.TextView
import android.widget.Toast
import java.util.*

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
            startArray.split(" ").map {it -> it.toInt()}.joinToString(" ")
        } catch(e: Exception) {
            val myToast = Toast.makeText(this, "Incorrect input.\nArray must be represented as set of numbers separated by a space.\nTry again.", Toast.LENGTH_SHORT)
            myToast.show()
            "1 2 3 4 5"
        }
    }

    fun sort(view: View) {
        val holder = findViewById<View>(holderCurArr) as TextView
        val array = holder.text.split(" ").map {it -> it.toInt()}.toIntArray()
        mergeSort(array)
        holder.text = array.joinToString(" ")
    }

    fun shuffle(view: View) {
        val holder = findViewById<View>(holderCurArr) as TextView
        val array = holder.text.split(" ").map {it -> it.toInt()}.toMutableList()
        for (i in array.indices) {
            val newPos = Random().nextInt(array.size)
            val tmp = array[i]
            array[i] = array[newPos]
            array[newPos] = tmp
        }
        holder.text = array.joinToString(" ")
    }

    private fun mergeSort(a : IntArray, l : Int = 0, r : Int = a.size) {
        if (r - l < 2)
            return
        val m = (l + r) / 2
        mergeSort(a, l, m)
        mergeSort(a, m, r)
        merge(a, l, m, r)
    }

    private fun merge(a : IntArray, l : Int, m : Int, r : Int) {
        val tmp = IntArray(r - l)
        var it1 = l
        var it2 = m
        var dst = 0
        while (dst < tmp.size && it1 < m && it2 < r) {
            tmp[dst++] = if (a[it1] < a[it2]) a[it1++] else a[it2++]
        }
        if (dst == tmp.size) return
        val range = if (it1 == m) IntRange(it2, r - 1) else IntRange(it1, m - 1)
        for (i in range) {
            tmp[dst++] = a[i]
        }
        for (i in l until r) {
            a[i] = tmp[i - l]
        }
    }
}
