package dsl

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

@DslMarker
annotation class MyLayout

@MyLayout
class CreaeteActivity(private val activity: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(activity).apply { id = name }
    private val element = ConstraintSet()
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM
    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END
    val dp = activity.applicationContext.resources.displayMetrics.density

    fun toDP(pixels: Int): Int = (dp * pixels).toInt()

    fun <T : View> T.leftMargin(another: Int, side: Int, dist: Int) {
        element.connect(id, LEFT, another, side, dist)
    }

    fun <T : View> T.rightMargin(another: Int, side: Int, dist: Int) {
        element.connect(id, RIGHT, another, side, dist)
    }

    fun <T : View> T.topMargin(another: Int, side: Int, dist: Int) {
        element.connect(id, TOP, another, side, dist)
    }

    fun <T : View> T.bottomMargin(another: Int, side: Int, dist: Int) {
        element.connect(id, BOTTOM, another, side, dist)
    }

    private fun <T : View> addElem(elem: T, init: T.() -> Unit): T {
        layout.addView(elem)
        element.clone(layout)
        elem.init()
        element.applyTo(layout)
        return elem
    }

    fun editText(name: Int, init: EditText.() -> Unit): EditText {
        val T = EditText(activity)
        T.id = name
        return addElem(T, init)
    }

    fun viewText(name: Int, init: TextView.() -> Unit): TextView {
        val T = TextView(activity)
        T.id = name
        return addElem(T, init)
    }

    fun button(name: Int, init: Button.() -> Unit): Button {
        val T = Button(activity)
        T.id = name
        return addElem(T, init)
    }

    fun Button.onClick(init: () -> Unit) {
        setOnClickListener { init() }
    }
}

fun AppCompatActivity.makeLayout(name: Int, init: CreaeteActivity.() -> Unit): ConstraintLayout {
    val element = CreaeteActivity(this, name)
    element.init()
    return element.layout
}