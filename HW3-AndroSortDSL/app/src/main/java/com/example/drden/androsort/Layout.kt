package com.example.drden.androsort

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.View.generateViewId
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.util.TypedValue

class Layout(context: Context) : ConstraintLayout(context) {
    private val set = ConstraintSet()

    private fun<T: View> initView(_id: Int, init: T.() -> Unit, factory: (Context) -> T) {
        val newView = factory(context)
        newView.id = _id
        addView(newView)
        set.clone(this)
        newView.init()
        set.applyTo(this)
    }

    fun textView(_id: Int, factory: (Context) -> TextView = {TextView(context)}, init: TextView.() -> Unit) = initView(_id, init, factory)
    fun editText(_id: Int, factory: (Context) -> EditText = {EditText(context)}, init: EditText.() -> Unit) = initView(_id, init, factory)
    fun button(_id: Int, factory: (Context) -> Button = {Button(context)}, init: Button.() -> Unit) = initView(_id, init, factory)

    fun convertDpToPx(dp: Float): Int {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, dp, context.resources.displayMetrics).toInt()
    }

    fun<T: View> T.setConstraint(fromSide: Int, to: Int, toSide: Int, margin: Int) {
        set.connect(id, fromSide, to, toSide, margin)
    }
}

fun AppCompatActivity.constraintLayout(init: Layout.() -> Unit): Layout {
    val layout = Layout(this)
    layout.id = generateViewId()
    layout.init()
    return layout
}