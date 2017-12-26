package dsl

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import android.widget.*

/**
 * Created by Gleb on 26.12.2017.
 */

@DslMarker
annotation class LayoutBuilder

@LayoutBuilder
class MyConstraintLayout(private val activity: AppCompatActivity, private val layoutId: Int) {
    private val constraintSet = ConstraintSet()
    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM
    val layout = ConstraintLayout(activity).apply {
        id = layoutId
    }

    private fun <T : View> addElement(elem: T, init: T.() -> Unit): T {
        layout.addView(elem)
        constraintSet.clone(layout)
        elem.init()
        constraintSet.applyTo(layout)
        return elem
    }

    fun <T : View> T.margin(startSide: Int, endID: Int, endSide: Int, margin: Int) {
        constraintSet.connect(id, startSide, endID, endSide, dp(margin))
    }

    fun button(id: Int, init: Button.() -> Unit): Button {
        val button = Button(activity)
        button.id = id
        return addElement(button, init)
    }

    fun editText(id: Int, init: EditText.() -> Unit): EditText {
        val editText = EditText(activity)
        editText.id = id
        editText.inputType = InputType.TYPE_CLASS_NUMBER
        return addElement(editText, init)
    }

    fun textView(id: Int, init: TextView.() -> Unit): TextView {
        val textView = TextView(activity)
        textView.id = id
        textView.textSize = 20f
        return addElement(textView, init)
    }

    fun Button.onMyClick(init: () -> Unit) {
        setOnClickListener { init() }
    }

    fun dp(value: Int): Int = (activity.applicationContext.resources.displayMetrics.density * value).toInt()

}

fun AppCompatActivity.createConstraintLayout(id: Int, init: MyConstraintLayout.() -> Unit): ConstraintLayout {
    val element = MyConstraintLayout(this, id)
    element.init()
    return element.layout
}