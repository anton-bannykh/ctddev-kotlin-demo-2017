package dsl

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
    private val bounds = ConstraintSet()
    private val k = activity.applicationContext.resources.displayMetrics.density
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM
    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END
    val defaultMargin = 8

    fun scale(x: Int): Int = (k * x).toInt()

    fun <T : View> T.leftMargin(other: Int, side: Int, margin: Int) {
        bounds.connect(id, LEFT, other, side, margin)
    }

    fun <T : View> T.rightMargin(other: Int, side: Int, margin: Int) {
        bounds.connect(id, RIGHT, other, side, margin)
    }

    fun <T : View> T.topMargin(other: Int, side: Int, margin: Int) {
        bounds.connect(id, TOP, other, side, margin)
    }

    fun <T : View> T.bottomMargin(other: Int, side: Int, margin: Int) {
        bounds.connect(id, BOTTOM, other, side, margin)
    }

    fun <T : View> T.center(layoutId: Int) {
        leftMargin(layoutId, LEFT, defaultMargin)
        rightMargin(layoutId, RIGHT, defaultMargin)
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

fun Button.onCLick(init: () -> Unit) {
    setOnClickListener { init() }
}

fun AppCompatActivity.constraintLayout(name: Int, init: ConstraintLayoutContext.() -> Unit): ConstraintLayout {
    val layout = ConstraintLayoutContext(this, name)
    layout.init()
    return layout.layout
}