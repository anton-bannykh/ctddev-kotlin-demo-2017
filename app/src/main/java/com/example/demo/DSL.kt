package com.example.demo

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

@DslMarker
annotation class MyLayout

@MyLayout
class CreateActivity(private val activity: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(activity).apply { id = name }
    private val elementConstraintSet = ConstraintSet()
    private val sizeOfOnePixel = activity.applicationContext.resources.displayMetrics.density

    fun toDP(pixels: Int): Int = (sizeOfOnePixel * pixels).toInt()

    fun <T : View> T.leftMargin(another: Int, side: Int, dist: Int) {
        elementConstraintSet.connect(id, ConstraintSet.START, another, side, dist)
    }

    fun <T : View> T.rightMargin(another: Int, side: Int, dist: Int) {
        elementConstraintSet.connect(id, ConstraintSet.END, another, side, dist)
    }

    fun <T : View> T.topMargin(another: Int, side: Int, dist: Int) {
        elementConstraintSet.connect(id, ConstraintSet.TOP, another, side, dist)
    }

    fun <T : View> T.bottomMargin(another: Int, side: Int, dist: Int) {
        elementConstraintSet.connect(id, ConstraintSet.BOTTOM, another, side, dist)
    }

    private fun <T : View> addElem(elem: T, init: T.() -> Unit): T {
        layout.addView(elem)
        elementConstraintSet.clone(layout)
        elem.init()
        elementConstraintSet.applyTo(layout)
        return elem
    }

    private val newTextView: TextView.(Int) -> TextView = { textName ->
        id = textName
        textSize = 69f
        this
    }

    private val newButton: Button.(Int) -> Button = { buttonName ->
        id = buttonName
        text = "Ну давай, нажми меня"
        this
    }

    fun editText(name: Int, init: EditText.() -> Unit): EditText {
        val T = EditText(activity)
        T.id = name
        return addElem(T, init)
    }

    fun viewText(name: Int, init: TextView.() -> Unit): TextView {
        val T = TextView(activity)
        T.id = name
        return addElem(T, init)
    }

    fun button(name: Int, init: Button.() -> Unit): Button {
        val T = Button(activity)
        T.id = name
        return addElem(T, init)
    }

    fun Button.onClick(init: () -> Unit) {
        setOnClickListener { init() }
    }
}

fun AppCompatActivity.makeLayout(name: Int, init: CreateActivity.() -> Unit): ConstraintLayout {
    val cell = CreateActivity(this, name)
    cell.init()
    return cell.layout
}