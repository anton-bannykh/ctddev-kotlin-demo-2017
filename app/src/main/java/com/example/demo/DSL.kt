package com.example.demo

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText

@DslMarker
annotation class LayoutBuilder

@LayoutBuilder
class DemoConstraintLayout(private val activity: AppCompatActivity, layoutId: Int) {
    val layout = ConstraintLayout(activity).apply { id = layoutId }
    private val bounds = ConstraintSet()

    private fun <T : View> addView(view: T, init: T.() -> Unit): T {
        layout.addView(view)
        bounds.clone(layout)
        view.init()
        bounds.applyTo(layout)
        return view
    }

    fun button(id: Int, init: Button.() -> Unit): Button {
        val button = Button(activity)
        button.id = id
        return addView(button, init)
    }

    fun editText(id: Int, init: EditText.() -> Unit): EditText {
        val editText = EditText(activity)
        editText.id = id
        return addView(editText, init)
    }

    fun <T : View> T.addConstraint(startSide: Int, endId: Int, endSide: Int) {
        addConstraint(startSide, endId, endSide, 0)
    }

    fun <T : View> T.addConstraint(startSide: Int, endId: Int, endSide: Int, margin: Int) {
        bounds.connect(id, startSide, endId, endSide, margin)
    }

    fun <T : View> T.setVerticalBias(bias: Float) {
        bounds.setVerticalBias(id, bias)
    }

    fun dp(x: Int): Int = (activity.applicationContext.resources.displayMetrics.density * x).toInt()
}

fun AppCompatActivity.createDemoConstraintLayout(id: Int, init: DemoConstraintLayout.() -> Unit): ConstraintLayout {
    val layout = DemoConstraintLayout(this, id)
    layout.init()
    return layout.layout
}
