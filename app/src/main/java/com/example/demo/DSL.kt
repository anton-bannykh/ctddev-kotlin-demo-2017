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
class DemoConstraintLayout(private val activity: AppCompatActivity, layoutID: Int) {
    val layout = ConstraintLayout(activity).apply { id = layoutID }
    val bounds = ConstraintSet()

    private fun <T : View> addToLayout(view: T, init: T.() -> Unit): T {
        layout.addView(view)
        bounds.clone(layout)
        view.init()
        bounds.applyTo(layout)
        return view
    }

    private fun <T:View> T.initialize(id:Int):T{
        this.id = id
        return this
    }

    fun <T : View> T.leftMargin(objectId: Int, side: Int, value: Int) {
        bounds.connect(id, ConstraintSet.START, objectId, side, value)
    }

    fun <T : View> T.topMargin(objectId: Int, side: Int, value: Int) {
        bounds.connect(id, ConstraintSet.TOP, objectId, side, value)
    }

    fun TextView(id: Int, init: TextView.() -> Unit) = addToLayout(TextView(activity).initialize(id), init)

    fun EditText(id: Int, init: EditText.() -> Unit) = addToLayout(EditText(activity).initialize(id), init)

    fun Button(id: Int, init: Button.() -> Unit) = addToLayout(Button(activity).initialize(id), init)

    fun dp(x: Int): Int = (activity.applicationContext.resources.displayMetrics.density * x).toInt()
}

fun AppCompatActivity.constraintLayout(id: Int, init: DemoConstraintLayout.() -> Unit): ConstraintLayout {
    val layout = DemoConstraintLayout(this, id)
    layout.init()
    return layout.layout
}
