package com.example.demo

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class MyConstraintLayout(private val curActiv: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(curActiv).apply { id = name }
    val pixelOne = curActiv.applicationContext.resources.displayMetrics.density
    val constraintSet = ConstraintSet()

    fun dp(x: Int): Int = (x * pixelOne).toInt()

    fun textView(id: Int, init: TextView.() -> Unit) = addElem(TextView(curActiv).newTextView(id), init)

    fun button(id: Int, init: Button.() -> Unit) = addElem(Button(curActiv).newButton(id), init)

    private val newTextView: TextView.(Int) -> TextView = { textName ->
        id = textName
        textSize = 30f
        this
    }

    private val newButton: Button.(Int) -> Button = { buttonName ->
        id = buttonName
        text = "Нажать"
        this
    }

    private fun <T : View> addElem(elem: T, init: T.() -> Unit): T {
        layout.addView(elem)
        constraintSet.clone(layout)
        elem.init()
        constraintSet.applyTo(layout)
        return elem
    }

    fun Button.onCLick(init: () -> Unit) {
        setOnClickListener { init() }
    }

    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM

    fun <T : View> T.marginTop(other: Int, side: Int, dist: Int) {
        constraintSet.connect(id, TOP, other, side, dist)
    }

    fun <T : View> T.marginLeft(other: Int, side: Int, dist: Int) {
        constraintSet.connect(id, LEFT, other, side, dist)
    }

    fun <T : View> T.marginRight(other: Int, side: Int, dist: Int) {
        constraintSet.connect(id, RIGHT, other, side, dist)
    }

    fun <T : View> T.marginBottom(other: Int, side: Int, dist: Int) {
        constraintSet.connect(id, BOTTOM, other, side, dist)
    }

}

fun AppCompatActivity.constraintLayout(name: Int, init: MyConstraintLayout.() -> Unit): ConstraintLayout {
    val layout = MyConstraintLayout(this, name)
    layout.init()
    return layout.layout
}