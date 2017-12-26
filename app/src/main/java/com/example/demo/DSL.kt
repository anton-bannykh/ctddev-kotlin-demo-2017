package com.example.test.myapplication

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

@DslMarker
annotation class LayoutConstructor

@LayoutConstructor
class ConstraintLayoutContext(private val activity: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(activity).apply { id = name }
    private val bounds = ConstraintSet()
    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM

    fun dp(x: Int): Int = (activity.applicationContext.resources.displayMetrics.density * x).toInt()

    fun <T : View> T.topMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, TOP, other, side, dist)
    }
    fun <T : View> T.bottomMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, BOTTOM, other, side, dist)
    }
    fun <T : View> T.rightMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, RIGHT, other, side, dist)
    }
    fun <T : View> T.leftMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, LEFT, other, side, dist)
    }
    fun <T : View> T.horizontalCenter(layoutId: Int) {
        bounds.connect(id, RIGHT, layoutId, LEFT)
        bounds.connect(id, LEFT, layoutId, RIGHT)
    }
    fun <T : View> T.verticalCenter(layoutId: Int) {
        bounds.connect(id, TOP, layoutId, BOTTOM)
        bounds.connect(id, BOTTOM, layoutId, TOP)
    }

    private val initTextView: TextView.(Int) -> TextView = { textName ->
        id = textName
        textSize = 22f
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        this
    }

    private val initEditText: EditText.(Int) -> EditText = { numberName ->
        id = numberName
        minEms = 10
        textSize = 18f
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        this
    }

    private val initButton: Button.(Int) -> Button = { buttonName ->
        textSize = 18f
        id = buttonName
        this
    }

    private fun <T : View> addElem(element: T, init: T.() -> Unit): T {
        layout.addView(element)
        bounds.clone(layout)
        element.init()
        bounds.applyTo(layout)
        return element
    }

    fun textView(id: Int, init: TextView.() -> Unit) = addElem(TextView(activity).initTextView(id), init)

    fun textEdit(id: Int, init: EditText.() -> Unit) = addElem(EditText(activity).initEditText(id), init)

    fun button(id: Int, init: Button.() -> Unit) = addElem(Button(activity).initButton(id), init)
}

fun AppCompatActivity.constraintLayout(name: Int, init: ConstraintLayoutContext.() -> Unit): ConstraintLayout {
    val layout = ConstraintLayoutContext(this, name)
    layout.init()
    return layout.layout
}