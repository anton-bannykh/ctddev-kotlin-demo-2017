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
    private val set = ConstraintSet()
    private val density = activity.applicationContext.resources.displayMetrics.density
    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM

    fun dp(x: Int): Int = (density * x).toInt()

    var TextView.textColor
        get() = currentTextColor
        set(value) {
            setTextColor(value)
        }

    fun <T : View> T.margin(firstSide: Int, secondId: Int, secondSide: Int, dist: Int) {
        set.connect(id, firstSide, secondId, secondSide, dp(dist))
    }

    private fun <T : View> addElem(elem: T, init: T.() -> Unit): T {
        layout.addView(elem)
        set.clone(layout)
        elem.init()
        set.applyTo(layout)
        return elem
    }

    private fun <T : View> setDefault(elem: T, num: Int): T {
        elem.id = num
        elem.textAlignment = View.TEXT_ALIGNMENT_CENTER
        return elem
    }

    fun textView(id: Int, init: TextView.() -> Unit) = addElem(setDefault(TextView(activity), id), init)

    fun editText(id: Int, init: EditText.() -> Unit) = addElem(setDefault(EditText(activity), id), init)

    fun button(id: Int, clicker: () -> Unit, init: Button.() -> Unit) =
            addElem(setDefault(Button(activity), id), init).setOnClickListener { clicker() }
}

fun AppCompatActivity.constraintLayout(name: Int, init: ConstraintLayoutContext.() -> Unit): ConstraintLayout {
    val layout = ConstraintLayoutContext(this, name)
    layout.init()
    return layout.layout
}
