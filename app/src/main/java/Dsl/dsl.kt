package Dsl

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
    private val faces = ConstraintSet()
    private val for_dp = act.applicationContext.resources.displayMetrics.density
    val TOP = ConstraintSet.TOP
    val BOTTOM = ConstraintSet.BOTTOM
    val LEFT = ConstraintSet.START
    val RIGHT = ConstraintSet.END

    private val TextView_: TextView.(Int) -> TextView = { textName ->
        id = textName
        textSize = 36f
        width = dp(200)
        height = dp(45)
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        setTextAppearance(act, R.style.Base_TextAppearance_AppCompat_Large)
        this
    }

    private val EditText_: EditText.(Int) -> EditText = { mutNumberName ->
        id = mutNumberName
        textSize = 15f
        layoutParams = ViewGroup.LayoutParams(dp(180), dp(45))
        inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
        textAlignment = View.TEXT_ALIGNMENT_CENTER
        this
    }

    private val Button_: Button.(Int) -> Button = { buttonName ->
        id = buttonName
        textSize = 14f
        width = dp(115)
        height = dp(42)
        this
    }

    fun dp(x: Int): Int = (for_dp * x).toInt()

    fun <T : View> T.top_(secondId: Int, endSide: Int, dist: Int) {
        faces.connect(id, TOP, secondId, endSide, dist)
    }

    fun <T : View> T.left_(secondId: Int, endSide: Int, dist: Int) {
        faces.connect(id, LEFT, secondId, endSide, dist)
    }

    fun <T : View> T.right_(secondId: Int, endSide: Int, dist: Int) {
        faces.connect(id, RIGHT, secondId, endSide, dist)
    }

    private fun <T : View> add_(elem: T, init: T.() -> Unit): T {
        layout.addView(elem)
        faces.clone(layout)
        elem.init()
        faces.applyTo(layout)
        return elem
    }

    fun textView(id: Int, init: TextView.() -> Unit) = add_(TextView(act).TextView_(id), init)
    fun mutNumber(id: Int, init: EditText.() -> Unit) = add_(EditText(act).EditText_(id), init)
    fun button(id: Int, init: Button.() -> Unit) = add_(Button(act).Button_(id), init)
}

fun Button.onCLick(init: () -> Unit) {
    setOnClickListener { init() }
}

fun AppCompatActivity.constraintLayout(name: Int, init: ConstraintLayoutContext.() -> Unit): ConstraintLayout {
    val layout = ConstraintLayoutContext(this, name)
    layout.init()
    return layout.layout
}