package com.example.demo

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class ConstraintBuilder(private val activity: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(activity).apply { id = name }
    private val constraint = ConstraintSet()

    fun inDp(x: Int) = (activity.applicationContext.resources.displayMetrics.density * x).toInt()

    fun connection(startId: Int, startFace: Int, endId: Int, endFace: Int) {
        constraint.connect(startId, startFace, endId, endFace)
    }

    private fun <T : View> addElement(element: T, init: T.() -> Unit): T {
        layout.addView(element)
        constraint.clone(layout)
        element.init()
        constraint.applyTo(layout)
        return element
    }

    fun addButton(name: Int, init: Button.() -> Unit): Button {
        val button = Button(activity)
        button.id = name
        return addElement(button, init)
    }

    fun addTextView(name: Int, init: TextView.() -> Unit): TextView {
        val view = TextView(activity)
        view.id = name
        return addElement(view, init)
    }

    fun addEditText(name: Int, init: EditText.() -> Unit): EditText {
        val edit = EditText(activity)
        edit.id = name
        return addElement(edit, init)
    }
}

fun constraintBuilder(name: Int, activity: AppCompatActivity, call: ConstraintBuilder.() -> Unit): ConstraintLayout {
    val layout = ConstraintBuilder(activity, name)
    layout.call()
    return layout.layout
}