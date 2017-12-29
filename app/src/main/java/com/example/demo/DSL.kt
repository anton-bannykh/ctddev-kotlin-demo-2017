package com.example.demo

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

@DslMarker
annotation class LayoutConstructor

@LayoutConstructor
class ConstraintLayoutContext(private val activity: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(activity).apply { id = name }
    private val bounds = ConstraintSet()
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM
    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END
    val defaultMargin = 8

    fun toDp(x: Int): Int =
            (activity.applicationContext.resources.displayMetrics.density * x).toInt()

    fun <T : View> T.leftMargin(relationElement: Int, side: Int, value: Int) {
        bounds.connect(id, LEFT, relationElement, side, value)
    }

    fun <T : View> T.rightMargin(relationElement: Int, side: Int, value: Int) {
        bounds.connect(id, RIGHT, relationElement, side, value)
    }

    fun <T : View> T.topMargin(relationElement: Int, side: Int, value: Int) {
        bounds.connect(id, TOP, relationElement, side, value)
    }

    fun <T : View> T.bottomMargin(relationElement: Int, side: Int, value: Int) {
        bounds.connect(id, BOTTOM, relationElement, side, value)
    }

    fun <T : View> T.center(layout: Int) {
        leftMargin(layout, LEFT, defaultMargin)
        rightMargin(layout, RIGHT, defaultMargin)

    }

    private fun <T : View> addElement(element: T, init: T.() -> Unit): T {
        layout.addView(element)
        bounds.clone(layout)
        element.init()
        bounds.applyTo(layout)
        return element
    }

    fun textView(name: Int, init: TextView.() -> Unit): TextView {
        val el = TextView(activity)
        el.id = name
        return addElement(el, init)
    }

    fun editText(name: Int, init: EditText.() -> Unit): EditText {
        val el = EditText(activity)
        el.id = name
        return addElement(el, init)
    }

    fun button(name: Int, init: Button.() -> Unit): Button {
        val el = Button(activity)
        el.id = name
        return addElement(el, init)
    }
}

fun AppCompatActivity.constraintLayout(name: Int, init: ConstraintLayoutContext.() -> Unit): ConstraintLayout {
    val layout = ConstraintLayoutContext(this, name)
    layout.init()
    return layout.layout
}