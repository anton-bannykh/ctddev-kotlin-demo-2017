package dsl.layout

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

@DslMarker
annotation class LayoutBuilder

@LayoutBuilder
class ConstraintLayoutWithoutXML(private val activity: AppCompatActivity, LayoutId: Int) {
    val layout = ConstraintLayout(activity).apply { id = LayoutId }
    private val bounds = ConstraintSet()

    private fun <T : View> addElementToLayout(element: T, init: T.() -> Unit): T {
        layout.addView(element)
        bounds.clone(layout)
        element.init()
        bounds.applyTo(layout)
        return element
    }

    private fun <T : View> T.initialize(idView: Int): T {
        this.id = idView
        return this
    }

    fun <T : View> T.leftMargin(objectId: Int, side: Int, value: Int) {
        bounds.connect(id, ConstraintSet.START, objectId, side, value)
    }

    fun <T : View> T.topMargin(objectId: Int, side: Int, value: Int) {
        bounds.connect(id, ConstraintSet.TOP, objectId, side, value)
    }

    fun dp(x: Int): Int = (activity.applicationContext.resources.displayMetrics.density * x).toInt()

    fun TextView(id: Int, init: TextView.() -> Unit) = addElementToLayout(TextView(activity).initialize(id), init)
    fun EditText(id: Int, init: EditText.() -> Unit) = addElementToLayout(EditText(activity).initialize(id), init)
    fun Button(id: Int, init: Button.() -> Unit) = addElementToLayout(Button(activity).initialize(id), init)
}

fun AppCompatActivity.ConstraintLayout(id: Int, init: ConstraintLayoutWithoutXML.() -> Unit): ConstraintLayout {
    val layout = ConstraintLayoutWithoutXML(this, id)
    layout.init()
    return layout.layout
}