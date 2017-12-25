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
        textSize = 50f
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
}

fun AppCompatActivity.constraintLayout(name: Int, init: MyConstraintLayout.() -> Unit): ConstraintLayout {
    val layout = MyConstraintLayout(this, name)
    layout.init()
    return layout.layout
}