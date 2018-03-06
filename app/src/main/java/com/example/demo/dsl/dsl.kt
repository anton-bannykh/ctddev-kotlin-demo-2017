package com.example.demo.dsl

/**
 * Created by Ильдар on 06.03.2018.
 */
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

@DslMarker
annotation class LayoutConstructor

@LayoutConstructor
class ConstraintLayoutContext(private val act: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(act).apply { id = name }
    private val bounds = ConstraintSet()

    fun dp(x: Int): Int = (act.applicationContext.resources.displayMetrics.density * x).toInt()

    val START = ConstraintSet.START
    val END = ConstraintSet.END
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM

    private val initTextView: TextView.(Int) -> TextView = { _name ->
        id = _name
        width = dp(358)
        height = dp(66)
        this
    }

    private val initEditText: EditText.(Int) -> EditText = { _name ->
        id = _name
        layoutParams = ViewGroup.LayoutParams(dp(350), dp(42))
        inputType = InputType.TYPE_CLASS_TEXT
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        this
    }

    private val initButton: Button.(Int) -> Button = { _name ->
        id = _name
        width = dp(150)
        height = dp(42)
        this
    }

    fun <T : View> T.startConstaint(other: Int, side: Int, dist: Int) {
        bounds.connect(id, START, other, side, dist)
    }

    fun <T : View> T.endConstaint(other: Int, side: Int, dist: Int) {
        bounds.connect(id, END, other, side, dist)
    }

    fun <T : View> T.topConstaint(other: Int, side: Int, dist: Int) {
        bounds.connect(id, TOP, other, side, dist)
    }

    fun <T : View> T.bottomConstaint(other: Int, side: Int, dist: Int) {
        bounds.connect(id, BOTTOM, other, side, dist)
    }

    fun <T : View> T.center(layoutId: Int) {
        startConstaint(layoutId, START, 8)
        endConstaint(layoutId, END, 8)
    }

    private fun <T : View> addView(view: T, init: T.() -> Unit): T {
        layout.addView(view)
        bounds.clone(layout)
        view.init()
        bounds.applyTo(layout)
        return view
    }

    fun textView(id: Int, init: TextView.() -> Unit) = addView(TextView(act).initTextView(id), init)

    fun editView(id: Int, init: EditText.() -> Unit) = addView(EditText(act).initEditText(id), init)

    fun button(id: Int, init: Button.() -> Unit) = addView(Button(act).initButton(id), init)

}

fun Button.onCLick(init: () -> Unit) {
    setOnClickListener { init() }
}

fun AppCompatActivity.constraintLayout(name: Int, init: ConstraintLayoutContext.() -> Unit): ConstraintLayout {
    val layout = ConstraintLayoutContext(this, name)
    layout.init()
    return layout.layout
}