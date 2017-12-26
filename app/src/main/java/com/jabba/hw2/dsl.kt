package com.jabba.hw2

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.*

class ConstraintBuilder(private val activity: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(activity).apply { id = name }
    private val cs = ConstraintSet()
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM
    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END
    val WRAP = ViewGroup.LayoutParams.WRAP_CONTENT

    fun toDp(x: Int) = (activity.applicationContext.resources.displayMetrics.density * x).toInt()

    fun connect(startId: Int, startSide: Int, endId: Int, endSide: Int, dp: Int) {
        cs.connect(startId, startSide, endId, endSide, toDp(dp))
    }

    private fun <T : View> addElement(element: T, init: T.() -> Unit): T {
        layout.addView(element)
        cs.clone(layout)
        element.init()
        cs.applyTo(layout)
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

fun constraintBuilder(name: Int, activity: AppCompatActivity, init: ConstraintBuilder.() -> Unit): ConstraintLayout {
    val layout = ConstraintBuilder(activity, name)
    layout.init()
    return layout.layout
}