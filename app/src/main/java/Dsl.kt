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
class ConstraintLayoutContext(private val activity: AppCompatActivity, layoutId: Int) {
    val myLayout = ConstraintLayout(activity).apply { id = layoutId }
    private val myConstraintSet = ConstraintSet()

    private fun <SomeView : View> addItem(item : SomeView, init: SomeView.() -> Unit) {
        myLayout.addView(item)
        myConstraintSet.clone(myLayout)
        item.init()
        myConstraintSet.applyTo(myLayout)
    }

    fun <T: View> T.topMargin(other: Int, side: Int, margin: Int) {
        myConstraintSet.connect(id, ConstraintSet.TOP, other, side, margin)
    }

    fun <T : View> T.bottomMargin(other: Int, side: Int, margin: Int) {
        myConstraintSet.connect(id, ConstraintSet.BOTTOM, other, side, margin)
    }

    fun <T : View> T.leftMargin(other: Int, side: Int, margin: Int) {
        myConstraintSet.connect(id, ConstraintSet.START, other, side, margin)
    }

    fun <T : View> T.rightMargin(other: Int, side: Int, margin: Int) {
        myConstraintSet.connect(id, ConstraintSet.END, other, side, margin)
    }

    fun textView(idForView: Int, init: TextView.() -> Unit) {
        val myTextView = TextView(activity)
        myTextView.id = idForView
        addItem(myTextView, init)
    }

    fun button(idForView: Int, init: Button.() -> Unit) {
        val myButton = Button(activity)
        myButton.id = idForView
        addItem(myButton, init)
    }

    fun editText(idForView: Int, init: EditText.() -> Unit) {
        val myEditText = EditText(activity)
        myEditText.id = idForView
        addItem(myEditText, init)
    }
}

fun AppCompatActivity.constraintLayout(idForLayout: Int, init: ConstraintLayoutContext.() -> Unit):
        ConstraintLayout {
    val layout = ConstraintLayoutContext(this, idForLayout)
    layout.init()
    return layout.myLayout
}