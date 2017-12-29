package DSL

/**
 * Created by Rhbcnbyf on 27.12.2017.
 */
import android.support.constraint.ConstraintLayout

import android.support.constraint.ConstraintSet

import android.support.v7.app.AppCompatActivity

import android.text.InputType

import android.view.View

import android.widget.Button

import android.widget.EditText

import android.widget.TextView

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

    private val init_TextView: TextView.(Int) -> TextView = { textName ->

        id = textName

        textSize = 20f

        width = dp(300)

        height = dp(42)

        textAlignment = View.TEXT_ALIGNMENT_CENTER

        this

    }

    private val init_EditText: EditText.(Int) -> EditText = { numberName ->

        id = numberName

        textSize = 20f

        width = dp(220)

        height = dp(42)

        inputType = InputType.TYPE_CLASS_TEXT

        textAlignment = View.TEXT_ALIGNMENT_CENTER

        this

    }

    private val init_Button: Button.(Int) -> Button = { buttonName ->

        id = buttonName

        textSize = 20f

        width = dp(220)

        height = dp(42)

        textAlignment = View.TEXT_ALIGNMENT_CENTER

        this

    }

    fun dp(x: Int): Int = (k * x).toInt()

    fun <T : View> T.leftMargin(other: Int, side: Int, dist: Int) {

        bounds.connect(id, LEFT, other, side, dist)

    }

    fun <T : View> T.rightMargin(other: Int, side: Int, dist: Int) {

        bounds.connect(id, RIGHT, other, side, dist)

    }

    fun <T : View> T.topMargin(other: Int, side: Int, dist: Int) {

        bounds.connect(id, TOP, other, side, dist)

    }

    fun <T : View> T.bottomMargin(other: Int, side: Int, dist: Int) {

        bounds.connect(id, BOTTOM, other, side, dist)

    }

    private fun <T : View> addsmth(elem: T, init: T.() -> Unit): T {

        layout.addView(elem)

        bounds.clone(layout)

        elem.init()

        bounds.applyTo(layout)

        return elem

    }

    fun textView(id: Int, init: TextView.() -> Unit) = addsmth(TextView(act).init_TextView(id), init)

    fun editText(id: Int, init: EditText.() -> Unit) = addsmth(EditText(act).init_EditText(id), init)

    fun button(id: Int, init: Button.() -> Unit) = addsmth(Button(act).init_Button(id), init)

}

fun Button.onCLick(init: () -> Unit) {

    setOnClickListener { init() }

}

fun AppCompatActivity.constraintLayout(name: Int, init: ConstraintLayoutContext.() -> Unit): ConstraintLayout {

    val layout = ConstraintLayoutContext(this, name)

    layout.init()

    return layout.layout

}