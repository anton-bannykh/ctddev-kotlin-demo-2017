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
import com.example.demo.R

@DslMarker
annotation class LayoutConstructor

@LayoutConstructor
class ConstraintLayoutContext(private val act: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(act).apply { id = name }
    private val bounds = ConstraintSet()
    private val k = act.applicationContext.resources.displayMetrics.density
    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM

    private val initTextView: TextView.(Int) -> TextView = { textName ->
        id = textName
        width = dp(350)
        height = dp(100)
        textSize = 22f
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        setTextAppearance(act, R.style.Base_TextAppearance_AppCompat_Large)
        this
    }

    private val initEditText: EditText.(Int) -> EditText = { numberName ->
        id = numberName
        layoutParams = ViewGroup.LayoutParams(dp(350), dp(42))
        textSize = 20f
        inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        this
    }

    private val initButton: Button.(Int) -> Button = { buttonName ->
        id = buttonName
        width = dp(350)
        height = dp(42)
        this
    }

    fun dp(x: Int): Int = (k * x).toInt()

    fun <T : View> T.topMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, TOP, other, side, dist)
    }

    fun <T : View> T.center(layoutId: Int) {
        bounds.connect(id, RIGHT, layoutId, LEFT, 8)
        bounds.connect(id, LEFT, layoutId, RIGHT, 8)
    }

    private fun <T : View> addElem(elem: T, init: T.() -> Unit): T {
        layout.addView(elem)
        bounds.clone(layout)
        elem.init()
        bounds.applyTo(layout)
        return elem
    }

    fun textView(id: Int, init: TextView.() -> Unit) = addElem(TextView(act).initTextView(id), init)

    fun editText(id: Int, init: EditText.() -> Unit) = addElem(EditText(act).initEditText(id), init)

    fun button(id: Int, init: Button.() -> Unit) = addElem(Button(act).initButton(id), init)
}

fun Button.onCLick(init: () -> Unit) {
    setOnClickListener { init() }
}

fun AppCompatActivity.constraintLayout(name: Int, init: ConstraintLayoutContext.() -> Unit): ConstraintLayout {
    val layout = ConstraintLayoutContext(this, name)
    layout.init()
    return layout.layout
}
