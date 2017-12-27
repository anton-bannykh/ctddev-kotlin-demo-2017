package dsl

import android.content.Context
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class Layout(context: Context, id: Int, init: Layout.() -> Unit) {
    private val layout: ConstraintLayout
    private val context_: Context
    private val constraints: ConstraintSet = ConstraintSet()

    private fun <T: View> addView (view: T, init: T.() -> Unit) : T {
        layout.addView(view)
        constraints.clone(layout)
        view.init()
        constraints.applyTo(layout)
        return view
    }

    private val defaultButton: Button.(Int) -> Button = { buttonName->
        this.id = buttonName
        margin(START, 8)
        margin(END, 8)
        margin(BOTTOM, 8)
        margin(TOP, 8)
        this
    }

    private val defaultTextView: TextView.(Int) -> TextView = { textViewName->
        this.id = textViewName
        margin(START, 8)
        margin(END, 8)
        margin(BOTTOM, 8)
        margin(TOP, 8)
        textSize = 24f
        this
    }

    private val defaultEditText: EditText.(Int) -> EditText = { editTextName->
        this.id = editTextName
        margin(START, 8)
        margin(END, 8)
        margin(BOTTOM, 8)
        margin(TOP, 8)
        width = pxFromDp(124)
        textSize = 24f
        inputType = InputType.TYPE_CLASS_NUMBER
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        this
    }

    val START = ConstraintSet.START
    val END = ConstraintSet.END
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM

    fun View.constraint(startSide: Int, endId: Int, endSide: Int) {
        constraints.connect(this.id, startSide, endId, endSide)
    }

    fun View.constraint(startSide: Int, endId: Int, endSide: Int, margin: Int) {
        constraints.connect(this.id, startSide, endId, endSide, margin)
    }

    fun View.margin(anchor: Int, value: Int) {
        constraints.setMargin(this.id, anchor, value)
    }

    fun View.horizontalBias(bias: Float) {
        constraints.setHorizontalBias(this.id, bias)
    }

    fun pxFromDp(dp: Int): Int = (dp * context_.applicationContext.resources.displayMetrics.density).toInt()

    fun button(id: Int, init: Button.() -> Unit) { addView(Button(context_).defaultButton(id), init) }
    fun textView(id: Int, init: TextView.() -> Unit) { addView(TextView(context_).defaultTextView(id), init) }
    fun editText(id: Int, init: EditText.() -> Unit) { addView(EditText(context_).defaultEditText(id), init) }

    fun getLayout(): ConstraintLayout = layout

    init {
        layout = ConstraintLayout(context)
        layout.id = id
        context_ = context
        init()
    }
}

fun Button.onCLick(init: () -> Unit) {
    setOnClickListener { init() }
}