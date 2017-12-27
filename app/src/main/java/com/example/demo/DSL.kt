package com.example.demo

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView


class MyConstraintLayout(private val activity: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(activity).apply { id = name }

    private val c = ConstraintSet()
    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM
    private val density = activity.applicationContext.resources.displayMetrics.density

    private fun <T : View> addElement(element: T, init: T.() -> Unit): T {
        layout.addView(element)
        c.clone(layout)
        element.init()
        c.applyTo(layout)
        return element
    }

    private fun <T : View> setDefault(element: T, num: Int): T {
        element.id = num
        element.textAlignment = View.TEXT_ALIGNMENT_CENTER
        return element
    }

    fun dp(x: Int): Int = (density * x).toInt()

    fun <T : View> T.leftMargin(other: Int, side: Int, dist: Int) {
        c.connect(id, LEFT, other, side, dist)
    }

    fun <T : View> T.rightMargin(other: Int, side: Int, dist: Int) {
        c.connect(id, RIGHT, other, side, dist)
    }

    fun <T : View> T.topMargin(other: Int, side: Int, dist: Int) {
        c.connect(id, TOP, other, side, dist)
    }

    fun <T : View> T.bottomMargin(other: Int, side: Int, dist: Int) {
        c.connect(id, BOTTOM, other, side, dist)
    }

    fun textView(id: Int, init: TextView.() -> Unit)
            = addElement(setDefault(TextView(activity), id), init)

    fun editText(id: Int, init: EditText.() -> Unit)
            = addElement(setDefault(EditText(activity), id), init)

    fun button(id: Int, button: () -> Unit, init: Button.() -> Unit) =
            addElement(setDefault(Button(activity), id), init).setOnClickListener { button() }
}

fun AppCompatActivity.constraintLayout(id: Int, init: MyConstraintLayout.() -> Unit): ConstraintLayout {
    val layout = MyConstraintLayout(this, id)
    layout.init()
    return layout.layout
}

