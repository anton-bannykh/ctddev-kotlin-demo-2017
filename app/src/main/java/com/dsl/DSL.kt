package dsl

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import com.example.sun.centroiddecomposition.R

@DslMarker
annotation class LayoutConstructor

@LayoutConstructor
class ConstraintLayoutContext(private val act: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(act).apply { id = name }
    private val border = ConstraintSet()
    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM

    fun dp(x: Int): Int = (act.applicationContext.resources.displayMetrics.density * x).toInt()

    private val createTextView: TextView.(Int) -> TextView = { textName ->
        id = textName
        width = dp(200)
        height = dp(40)
        textSize = 25f
        setTextAppearance(act, R.style.Base_TextAppearance_AppCompat_Large)
        this
    }

    private val createEditText: EditText.(Int) -> EditText = { mutName ->
        id = mutName
        layoutParams = ViewGroup.LayoutParams(dp(120), dp(40))
        textSize = 20f
        inputType = InputType.TYPE_TEXT_VARIATION_LONG_MESSAGE
        this
    }

    private val createButton: Button.(Int) -> Button = { buttonName ->
        id = buttonName
        width = dp(140)
        height = dp(45)
        this
    }

    fun <T : View> T.leftField(other: Int, side: Int, dist: Int) {
        border.connect(id, LEFT, other, side, dist)
    }

    fun <T : View> T.rightField(other: Int, side: Int, dist: Int) {
        border.connect(id, RIGHT, other, side, dist)
    }

    fun <T : View> T.topField(other: Int, side: Int, dist: Int) {
        border.connect(id, TOP, other, side, dist)
    }

    fun <T : View> T.middle(layoutId: Int) {
        leftField(layoutId, LEFT, 9)
        rightField(layoutId, RIGHT, 9)
    }

    private fun <T : View> addAny(a: T, init: T.() -> Unit): T {
        layout.addView(a)
        border.clone(layout)
        a.init()
        border.applyTo(layout)
        return a
    }

    fun button(id: Int, init: Button.() -> Unit) = addAny(Button(act).createButton(id), init)
    fun textView(id: Int, init: TextView.() -> Unit) = addAny(TextView(act).createTextView(id), init)
    fun numeric(id: Int, init: EditText.() -> Unit) = addAny(EditText(act).createEditText(id), init)
}

fun Button.onCLick(init: () -> Unit) {
    setOnClickListener { init() }
}

fun AppCompatActivity.constraintLayout(name: Int, init: ConstraintLayoutContext.() -> Unit): ConstraintLayout {
    val layout = ConstraintLayoutContext(this, name)
    layout.init()
    return layout.layout
}