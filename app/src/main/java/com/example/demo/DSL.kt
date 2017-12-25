package com.example.demo

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

@DslMarker
annotation class LayoutBuilder

@LayoutBuilder
class ConstraintLayoutContext(private val activity: AppCompatActivity, layoutId: Int) {
    val layout = ConstraintLayout(activity).apply {
        id = layoutId
    }
    private val bounds = ConstraintSet()

    fun <T : View> T.leftMargin(other: Int, side: Int, margin: Int) {
        bounds.connect(id, ConstraintSet.START, other, side, margin)
    }

    fun <T : View> T.rightMargin(other: Int, side: Int, margin: Int) {
        bounds.connect(id, ConstraintSet.END, other, side, margin)
    }

    fun <T : View> T.bottomMargin(other: Int, side: Int, margin: Int) {
        bounds.connect(id, ConstraintSet.BOTTOM, other, side, margin)
    }

    fun <T : View> T.topMargin(other: Int, side: Int, margin: Int) {
        bounds.connect(id, ConstraintSet.TOP, other, side, margin)
    }

    private fun <T : View> addItem(item: T, init: T.() -> Unit): T {
        layout.addView(item)
        bounds.clone(layout)
        item.init()
        bounds.applyTo(layout)
        return item
    }

    fun textView(name: Int, init: TextView.() -> Unit): TextView {
        val el = TextView(activity)
        el.id = name
        return addItem(el, init)
    }

    fun button(name: Int, init: Button.() -> Unit): Button {
        val el = Button(activity)
        el.id = name
        return addItem(el, init)
    }

    fun editText(name: Int, init: EditText.() -> Unit): EditText {
        val el = EditText(activity)
        el.id = name
        return addItem(el, init)
    }
}

fun AppCompatActivity.constraintLayout(name: Int, init: ConstraintLayoutContext.() -> Unit): ConstraintLayout {
    val layout = ConstraintLayoutContext(this, name)
    layout.init()
    return layout.layout
}