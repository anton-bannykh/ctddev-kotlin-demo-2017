package dsl

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

/**
 * Created by Dima on 25.12.2017.
 */
class ConstraintLayoutCreator(activity_t: AppCompatActivity, name: Int) {
    private val activity = activity_t
    val layout = ConstraintLayout(activity).apply {id = name}
    private val constraints = ConstraintSet()

    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM
    val LEFT = ConstraintSet.LEFT
    val RIGHT = ConstraintSet.RIGHT

    private val in_dp = activity.applicationContext.resources.displayMetrics.density.toInt()
    fun conv_dp(pixels: Int): Int {
        return in_dp * pixels
    }

    private fun <T : View> newElem(el: T, initialize: T.() -> Unit): T {
        layout.addView(el)
        constraints.clone(layout)
        el.initialize()
        constraints.applyTo(layout)
        return el
    }

    fun editText(id: Int, w: Int, h: Int, txt: String, initialize: EditText.() -> Unit) = newElem(EditText(activity).new_editText(id, w, h), initialize)

    private fun EditText.new_editText(id_in: Int, w_in: Int, h_in: Int): EditText {
        id = id_in
        width = w_in
        height = h_in
        return this
    }

    fun textView(id: Int, w: Int, h: Int, txt: String, size : Int, initialize: TextView.() -> Unit) = newElem(TextView(activity).new_textView(id, w, h, txt, size), initialize)

    private fun TextView.new_textView(id_in: Int, w_in: Int, h_in: Int, txt_in: String, size : Int): TextView {
        id = id_in
        width = w_in
        height = h_in
        text = txt_in
        textSize = size.toFloat()
        return this
    }

    fun button(id: Int, w: Int, h: Int, txt: String,size : Int, initialize: Button.() -> Unit) = newElem(Button(activity).new_button(id, w, h, txt, size), initialize)

    private fun Button.new_button(id_in: Int, w_in: Int, h_in: Int, txt_in: String,size : Int): Button {
        id = id_in
        width = w_in
        height = h_in
        text = txt_in
        textSize = size.toFloat()
        return this
    }

    fun Button.setOnClick(onClick: () -> Unit) {
        setOnClickListener { onClick() }
    }
    //connect two elem on default
    fun setConnection(st_id: Int, st: Int, end_id: Int, end: Int) {
        constraints.connect(st_id, st, end_id, end)
    }
    //connect two elem on distance
    fun <T : View> T.tMarg(x: Int, side: Int, d: Int) {
        constraints.connect(id, TOP, x, side, d)
    }
    fun <T : View> T.bMarg(x: Int, side: Int, d: Int) {
        constraints.connect(id, BOTTOM, x, side, d)
    }
    fun <T : View> T.lMarg(x : Int, side: Int, d: Int) {
        constraints.connect(id, LEFT, x, side, d)
    }
    fun <T : View> T.rMarg(x: Int, side: Int, d: Int) {
        constraints.connect(id, RIGHT, x, side, d)
    }

}

fun AppCompatActivity.constraintLayoutCreate(name: Int, call: ConstraintLayoutCreator.() -> Unit): ConstraintLayout {
    val lt = ConstraintLayoutCreator(this, name)
    lt.call()
    return lt.layout
}