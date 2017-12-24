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
class MyContraintLayout(private val activity: AppCompatActivity, private val layoutId: Int) {

    private val bounds = ConstraintSet()

    val layout = ConstraintLayout(activity).apply {
        id = layoutId
    }

    fun <T : View> T.leftMargin(side: Int, value: Int) {
        leftMargin(side, layoutId, value)
    }

    fun <T : View> T.leftMargin(side: Int, objectId: Int, value: Int) {
        bounds.connect(id, ConstraintSet.LEFT, objectId, side, value)
    }

    fun <T : View> T.rightMargin(side: Int, value: Int) {
        rightMargin(side, layoutId, value)
    }

    fun <T : View> T.rightMargin(side: Int, objectId: Int, value: Int) {
        bounds.connect(id, ConstraintSet.RIGHT, objectId, side, value)
    }

    fun <T : View> T.topMargin(side: Int, value: Int) {
        topMargin(side, layoutId, value)
    }

    fun <T : View> T.topMargin(side: Int, objectId: Int, value: Int) {
        bounds.connect(id, ConstraintSet.TOP, objectId, side, value)
    }

    fun <T : View> T.bottomMargin(side: Int, value: Int) {
        bottomMargin(side, layoutId, value)
    }

    fun <T : View> T.bottomMargin(side: Int, objectId: Int, value: Int) {
        bounds.connect(id, ConstraintSet.BOTTOM, objectId, side, value)
    }

    private fun <T : View> addSubview(view: T, init: T.() -> Unit): T {
        layout.addView(view)
        bounds.clone(layout)
        view.init()
        bounds.applyTo(layout)
        return view
    }

    // I wanted to add view.setTextAppearance(resId: Int) but, unfortunately, this option it available only from API 23 :(
    fun textView(id: Int, init: TextView.() -> Unit): TextView {
        val view = TextView(activity)
        view.id = id
        return addSubview(view, init)
    }

    fun editText(id: Int, init: EditText.() -> Unit): EditText {
        val view = EditText(activity)
        view.id = id
        return addSubview(view, init)
    }

    fun button(id: Int, init: Button.() -> Unit): Button {
        val button = Button(activity)
        button.id = id
        return addSubview(button, init)
    }

}