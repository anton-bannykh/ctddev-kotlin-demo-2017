package DSL

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

/**
 * Created by Илья Кокорин on 19.12.2017.
 */
class ConstraintLayoutConstructor(_activity: AppCompatActivity) {
    private val activity = _activity
    private val layout = ConstraintLayout(activity)

    val constraintLayout
            get() = layout

    private val dotPerInch = activity.applicationContext.resources.displayMetrics.density.toInt()
    private val constraints = ConstraintSet()
    private val boundsSet = setOf(LEFTBOUND, RIGHTBOUND, UPPERBOUND, LOWERBOUND)

    private fun scale(dist: Int): Int = dist * dotPerInch

    private fun <T : View> addElement(element: T, init: T.() -> Unit): T {
        layout.addView(element)
        constraints.clone(layout)
        element.init()
        element.textAlignment = View.TEXT_ALIGNMENT_CENTER
        constraints.applyTo(layout)
        return element
    }

    private fun EditText.initEditText(_id: Int, width: Int, height: Int,
                                      fontSize: Float, _inputType: Int, _hint: String): EditText {
        id = _id
        layoutParams = ViewGroup.LayoutParams(scale(width), scale(height))
        textSize = fontSize
        inputType = _inputType
        hint = _hint
        return this
    }

    fun editText(id: Int, width: Int, height: Int, fontSize: Float,
                 inputType: Int, hint: String, init: EditText.() -> Unit) =
            addElement(EditText(activity).initEditText(id, width, height, fontSize, inputType, hint), init)

    private fun Button.initButton(_id: Int, _width: Int, _height: Int,
                                  _text: String): Button {
        id = _id
        width = scale(_width)
        height = scale(_height)
        text = _text
        return this
    }

    fun button(id: Int, width: Int, height: Int, text: String,
               init: Button.() -> Unit) =
            addElement(Button(activity).initButton(id, width, height, text), init)

    fun Button.setListener(OnClickListener: () -> Unit) {
        setOnClickListener { OnClickListener() }
    }

    private fun TextView.initTextView(_id: Int, _width: Int, _height: Int,
                                      _textSize: Float, _text: String): TextView {
        id = _id
        width = scale(_width)
        height = scale(_height)
        textSize = _textSize
        text = _text
        return this
    }

    fun textView(id: Int, width: Int, height: Int, textSize: Float,
                 text: String, init: TextView.() -> Unit) =
            addElement(TextView(activity).initTextView(id, width, height, textSize, text), init)

    private fun throwIllegalArgumentException(argument: Int) {
        throw IllegalArgumentException("Argument of makeConstraint function must be " +
                "${LEFTBOUND.toString()}(LEFTBOUND), " +
                "${RIGHTBOUND.toString()}(RIGHTBOUND), " +
                "${UPPERBOUND.toString()}(UPPERBOUND) or " +
                "${LOWERBOUND.toString()}(LOWERBOUND), not ${argument.toString()}")
    }

    fun <T: View> T.makeConstraint(myBound: Int, otherId: Int, otherBound: Int, distance: Int) {
        if (myBound !in boundsSet) {
            throwIllegalArgumentException(myBound)
        }
        if (otherBound !in boundsSet) {
            throwIllegalArgumentException(otherBound)
        }
        constraints.connect(id, myBound, otherId, otherBound, scale(distance))
    }
}