package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import org.w3c.dom.Text

class Constraint(private val activity: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(activity)
    private val constraintSet = ConstraintSet()
    private val density = activity.applicationContext.resources.displayMetrics.density
    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM

    private fun <T : View> addElement(element: T, init: T.() -> Unit): T {
        layout.addView(element)
        constraintSet.clone(layout)
        element.init()
        constraintSet.applyTo(layout)

        return element
    }

    fun dp(x: Int) = (density * x).toInt()

    fun <T : View> T.leftMargin(other: Int, side: Int, dist: Int) {
        constraintSet.connect(id, LEFT, other, side, dist)
    }

    fun <T : View> T.rightMargin(other: Int, side: Int, dist: Int) {
        constraintSet.connect(id, RIGHT, other, side, dist)
    }

    fun <T : View> T.topMargin(other: Int, side: Int, dist: Int) {
        constraintSet.connect(id, TOP, other, side, dist)
    }

    fun <T : View> T.bottomMargin(other: Int, side: Int, dist: Int) {
        constraintSet.connect(id, BOTTOM, other, side, dist)
    }

    fun <T : View> initDefault(id: Int, element: T): T {
        element.id = id
        element.textAlignment = View.TEXT_ALIGNMENT_CENTER
        return element
    }

    fun button(id: Int, clicker: () -> Unit, init: Button.() -> Unit) =
            addElement(initDefault(id, Button(activity)), init).setOnClickListener { clicker() }

    fun editText(id: Int, init: EditText.() -> Unit) = addElement(initDefault(id, EditText(activity)), init)

    fun textView(id: Int, init: TextView.() -> Unit) = addElement(initDefault(id, TextView(activity)), init)
}

fun AppCompatActivity.constraintLayout(name: Int, init: Constraint.() -> Unit): ConstraintLayout {
    val layout = Constraint(this, name)
    layout.init()
    return layout.layout
}