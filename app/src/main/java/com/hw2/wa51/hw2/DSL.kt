package com.hw2.wa51.hw2

import android.support.v7.app.AppCompatActivity
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.TextView
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.widget.Button

@DslMarker
annotation class LayoutConstructor

@LayoutConstructor
class ConstraintLayoutContext(private val act: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(act)
    private val bounds = ConstraintSet()
    private val k = act.applicationContext.resources.displayMetrics.density
    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM

    private val initTextView: TextView.(Int) -> TextView = { textName ->
        id = textName
        textSize = 28f
        maxLines = 100
        this
    }

    private val initButton: Button.(Int) -> Button = { buttonName ->
        id = buttonName
        this
    }

    init {
        layout.id = name
    }

    fun dp(x: Int): Int = (k * x).toInt()

    fun <T : View> T.bottomMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, BOTTOM, other, side, dist)
    }

    fun <T : View> T.leftMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, LEFT, other, side, dist)
    }

    private fun <T : View> addElem(elem: T, init: T.() -> Unit): T {
        layout.addView(elem)
        bounds.clone(layout)
        elem.init()
        bounds.applyTo(layout)
        return elem
    }

    fun textView(id: Int, init: TextView.() -> Unit) = addElem(TextView(act).initTextView(id), init)

    fun button(id: Int, init: Button.() -> Unit) = addElem(Button(act).initButton(id), init)
}

fun Button.onCLick(init: () -> Unit) {
    setOnClickListener { init() }
}

fun AppCompatActivity.constraintLayout(name: Int, init: ConstraintLayoutContext.() -> Unit): ConstraintLayoutContext {
    val layout = ConstraintLayoutContext(this, name)
    layout.init()
    return layout
}
