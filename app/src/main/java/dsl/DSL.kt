package dsl

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
class Layout(private val act: AppCompatActivity, name: Int) {
    val layout = ConstraintLayout(act).apply { id = name }
    private val elem = ConstraintSet()
    val _TOP = ConstraintSet.TOP
    val _BOTTOM = ConstraintSet.BOTTOM
    val _LEFT = ConstraintSet.START
    val _RIGHT = ConstraintSet.END

    fun <T: View> T.leftMargin(another: Int, side: Int, dist: Int) {
        elem.connect(id, _LEFT, another, side, dist)
    }

    fun <T: View> T.rightMargin(another: Int, side: Int, dist: Int) {
        elem.connect(id, _RIGHT, another, side, dist)
    }

    fun <T: View> T.topMargin(another: Int, side: Int, dist: Int) {
        elem.connect(id, _TOP, another, side, dist)
    }

    fun <T: View> T.bottomMargin(another: Int, side: Int, dist: Int) {
        elem.connect(id, _BOTTOM, another, side, dist)
    }
    /*fun<T: View> T.linkAB(a: Int, b: Int, firstId: Int, secondId: Int, intent: Int){
        elem.connect(firstId, a, secondId, b, intent)
    }*/

    private fun <T: View> addElement(element: T, init: T.() -> Unit): T{
        layout.addView(element)
        elem.clone(layout)
        element.init()
        elem.applyTo(layout)
        return element
    }

    fun editText(name: Int, init: EditText.() -> Unit): EditText{
        val T = EditText(act)
        T.id = name
        return addElement(T, init)
    }

    fun textView(name: Int, init: TextView.() -> Unit): TextView{
        val T = TextView(act)
        T.id = name
        return addElement(T, init)
    }

    fun button(name: Int, init: Button.() -> Unit): Button{
        val T = Button(act)
        T.id = name
        return addElement(T, init)
    }

    fun Button.onClick(init: () -> Unit){
        setOnClickListener({init()})
    }
    fun toDP(pixels: Int): Int = (act.applicationContext.resources.displayMetrics.density * pixels).toInt()

}

fun AppCompatActivity.makeLayout(name: Int, init: Layout.() -> Unit): ConstraintLayout {
    val elem = Layout(this, name)
    elem.init()
    return elem.layout
}