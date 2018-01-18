package com.example.demo

import android.app.ActionBar
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.graphics.Paint
import android.widget.Button
import android.widget.TextView

class DSL(private val act: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(act).apply { this.id = name }
    val bounds = ConstraintSet()
    val START = ConstraintSet.START
    val END = ConstraintSet.END
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM

    fun dp(x: Int) = (act.applicationContext.resources.displayMetrics.density * x).toInt()

    private val initEditText: EditText.(Int) -> EditText = { _name ->
        id = _name
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        this
    }

    private val initTextView: TextView.(Int) -> TextView = { _name ->
        id = _name
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        this
    }

    private val initButton: Button.(Int) -> Button = { _name ->
        id = _name
        layoutParams = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
        this
    }


    private fun <T : View> addView(view: T, init: T.() -> Unit): T {
        layout.addView(view)
        bounds.clone(layout)
        view.init()
        bounds.applyTo(layout)
        return view
    }

    fun editText(id: Int, init: EditText.() -> Unit) = addView(EditText(act).initEditText(id), init)
    fun textView(id: Int, init: TextView.() -> Unit) = addView(TextView(act).initTextView(id), init)
    fun button(id: Int, init: Button.() -> Unit) = addView(Button(act).initButton(id), init)


    fun <T : View> T.startConstaint(other: Int, side: Int, dist: Int) {
        bounds.connect(id, START, other, side, dist)
    }

    fun <T : View> T.endConstaint(other: Int, side: Int, dist: Int) {
        bounds.connect(id, END, other, side, dist)
    }

    fun <T : View> T.topConstaint(other: Int, side: Int, dist: Int) {
        bounds.connect(id, TOP, other, side, dist)
    }

    fun <T : View> T.bottomConstaint(other: Int, side: Int, dist: Int) {
        bounds.connect(id, BOTTOM, other, side, dist)
    }

}


fun Button.onCLick(init: () -> Unit) {
    setOnClickListener { init() }
}

fun AppCompatActivity.constraintLayout(name: Int, init: DSL.() -> Unit): ConstraintLayout {
    val layout = DSL(this, name)
    layout.init()
    return layout.layout
}


















