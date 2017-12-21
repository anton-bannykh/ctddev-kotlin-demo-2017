package My_DSl

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

@DslMarker
annotation class ActivityConstructor

@ActivityConstructor
class ActivityContext(private val activity: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(activity).apply { id = name }
    private val element = ConstraintSet()
    private val dp = activity.applicationContext.resources.displayMetrics.density
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM
    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END

    fun toDP(a: Int): Int = (dp * a).toInt()

    fun <T : View> T.leftMargin(other: Int, side: Int, dist: Int) {
        element.connect(id, LEFT, other, side, dist)
    }

    fun <T : View> T.rightMargin(other: Int, side: Int, dist: Int) {
        element.connect(id, RIGHT, other, side, dist)
    }

    fun <T : View> T.topMargin(other: Int, side: Int, dist: Int) {
        element.connect(id, TOP, other, side, dist)
    }

    fun <T : View> T.bottomMargin(other: Int, side: Int, dist: Int) {
        element.connect(id, BOTTOM, other, side, dist)
    }

    fun <T : View> T.center(layoutId: Int) {
        leftMargin(layoutId, LEFT, 8)
        rightMargin(layoutId, RIGHT, 8)
    }

    private fun <T : View> addElem(elem: T, init: T.() -> Unit): T {
        layout.addView(elem)
        element.clone(layout)
        elem.init()
        element.applyTo(layout)
        return elem
    }

    fun textView(name: Int, init: TextView.() -> Unit): TextView {
        val T = TextView(activity)
        T.id = name
        return addElem(T, init)
    }

    fun editText(name: Int, init: EditText.() -> Unit): EditText {
        val T = EditText(activity)
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

fun AppCompatActivity.makeLayout(name: Int, init: ActivityContext.() -> Unit) : ConstraintLayout {
    val element = ActivityContext(this, name)
    element.init()
    return element.layout
}