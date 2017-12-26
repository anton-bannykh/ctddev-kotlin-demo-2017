package com.example.demo

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class ActivityCreate(private val activity: AppCompatActivity, id: Int) {
    val layout = ConstraintLayout(activity).apply { this.id = id }
    private val worker = ConstraintSet()
    val _top = ConstraintSet.TOP
    val _bottom = ConstraintSet.BOTTOM
    val _left = ConstraintSet.START
    val _right = ConstraintSet.END

    fun <T : View> T.linker(from: Int, fromSide: Int, to: Int, toSide: Int, length: Int) {
        worker.connect(from, fromSide, to, toSide, length)
    }

    fun TextV(id: Int, block: TextView.() -> Unit): TextView {
        val et = TextView(activity)
        et.id = id
        layout.addView(et)
        worker.clone(layout)
        et.block()
        worker.applyTo(layout)
        return et
    }

    fun button(id: Int, block: Button.() -> Unit): Button {
        val button = Button(activity)
        button.id = id
        layout.addView(button)
        worker.clone(layout)
        button.block()
        worker.applyTo(layout)
        return button
    }
}

fun AppCompatActivity.layout(id: Int, block: ActivityCreate.() -> Unit): ConstraintLayout {
    val act = ActivityCreate(this, id)
    act.block()
    return act.layout
}