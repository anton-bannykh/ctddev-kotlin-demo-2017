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
annotation class layoutConstructor

@layoutConstructor
class MyConstraintLayout(private val act: AppCompatActivity, name: Int) : ConstraintLayout(act) {
    private val k = act.applicationContext.resources.displayMetrics.density
    private val bounds = ConstraintSet()

    init {
        id = name
    }

    private fun <T : View> addElem(elem: T, init: T.() -> Unit) {
        addView(elem)
        bounds.clone(this)
        elem.init()
        bounds.applyTo(this)
    }

    fun textView(id: Int, init: MyTextView.() -> Unit) = addElem(MyTextView(act, id, bounds, k), init)

    fun number(id: Int, init: MyNumber.() -> Unit) = addElem(MyNumber(act, id, bounds, k), init)

    fun button(id: Int, init: MyButton.() -> Unit) = addElem(MyButton(act, id, bounds, k), init)
}

@layoutConstructor
class MyTextView(act: AppCompatActivity, name: Int, private val bounds: ConstraintSet, private val k: Float) : TextView(act) {
    init {
        id = name
        width = dp(195)
        height = dp(42)
        textSize = 22f
        textAlignment = View.TEXT_ALIGNMENT_VIEW_END
        setTextAppearance(act, R.style.Base_TextAppearance_AppCompat_Large)
    }

    fun dp(x: Int): Int = (k * x).toInt()

    fun leftMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, ConstraintSet.START, other, side, dist)
    }

    fun rightMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, ConstraintSet.END, other, side, dist)
    }

    fun topMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, ConstraintSet.TOP, other, side, dist)
    }

    fun bottomMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, ConstraintSet.BOTTOM, other, side, dist)
    }

    fun center(layoutId: Int) {
        leftMargin(layoutId, ConstraintSet.START, 8)
        rightMargin(layoutId, ConstraintSet.END, 8)
    }
}

@layoutConstructor
class MyNumber(act: AppCompatActivity, name: Int, private val bounds: ConstraintSet, private val k: Float) : EditText(act) {
    init {
        id = name
        layoutParams = ViewGroup.LayoutParams(dp(137), dp(42))
        textSize = 20f
        inputType = InputType.TYPE_CLASS_NUMBER
        textAlignment = View.TEXT_ALIGNMENT_CENTER
    }

    fun dp(x: Int): Int = (k * x).toInt()

    fun leftMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, ConstraintSet.START, other, side, dist)
    }

    fun rightMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, ConstraintSet.END, other, side, dist)
    }

    fun topMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, ConstraintSet.TOP, other, side, dist)
    }

    fun bottomMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, ConstraintSet.BOTTOM, other, side, dist)
    }

    fun center(layoutId: Int) {
        leftMargin(layoutId, ConstraintSet.START, 8)
        rightMargin(layoutId, ConstraintSet.END, 8)
    }
}

@layoutConstructor
class MyButton(act: AppCompatActivity, name: Int, val bounds: ConstraintSet, private val k: Float) : Button(act) {
    init {
        id = name
        width = dp(115)
        height = dp(42)

    }

    fun dp(x: Int): Int = (k * x).toInt()

    fun leftMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, ConstraintSet.START, other, side, dist)
    }

    fun rightMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, ConstraintSet.END, other, side, dist)
    }

    fun topMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, ConstraintSet.TOP, other, side, dist)
    }

    fun bottomMargin(other: Int, side: Int, dist: Int) {
        bounds.connect(id, ConstraintSet.BOTTOM, other, side, dist)
    }

    fun onCLick(init: () -> Unit) {
        setOnClickListener { init() }
    }
}

fun AppCompatActivity.constraintLayout(name: Int, init: MyConstraintLayout.() -> Unit): MyConstraintLayout {
    val layout = MyConstraintLayout(this, name)
    layout.init()
    return layout
}
