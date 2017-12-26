package com.example.demo

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.TextView

class MyLayoutActivityCreator(act: AppCompatActivity, id: Int) {
    private val activity = act
    val layout = ConstraintLayout(activity).apply { this.id = id }
    private val e = ConstraintSet()

    fun textView(id: Int, block: TextView.() -> Unit): TextView {
        val textView = TextView(activity)
        textView.id = id
        layout.addView(textView)
        e.clone(layout)
        textView.block()
        e.applyTo(layout)
        return textView
    }

    fun button(id: Int, block: Button.() -> Unit): TextView {
        val button = Button(activity)
        button.id = id
        layout.addView(button)
        e.clone(layout)
        button.block()
        e.applyTo(layout)
        return button
    }

    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM
    val START = ConstraintSet.START
    val END = ConstraintSet.END

    fun <T : View> T.connectFromAToB(a: Int, b: Int, firstId: Int, secondId: Int, intent: Int = 0) {
        e.connect(firstId, a, secondId, b, intent)
    }

}

fun layout(act: AppCompatActivity, id: Int, block: MyLayoutActivityCreator.() -> Unit): ConstraintLayout {
    val layout = MyLayoutActivityCreator(act, id)
    layout.apply(block)
    return layout.layout
}
