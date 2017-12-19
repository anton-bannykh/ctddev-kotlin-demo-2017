package volkov.alexandr.kotlinandroid

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.text.InputType
import android.text.TextWatcher
import android.text.method.MovementMethod
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.ListView
import android.widget.ArrayAdapter

interface Element<T : View> {
    fun build(context: Context): T

    fun init(view: T)
}

abstract class BaseElement<T : View> : Element<T> {
    private var layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

    override fun build(context: Context): T {
        val view = createView(context)
        init(view)
        return view
    }

    override fun init(view: T) {
        view.layoutParams = layoutParams
    }

    fun setHeight(height: Int) {
        layoutParams.height = height
    }

    fun setWidth(width: Int) {
        layoutParams.width = width
    }

    fun setWeight(weight: Float) {
        layoutParams.weight = weight
    }

    fun setGravity(gravity: Int) {
        layoutParams.gravity = gravity
    }

    abstract fun createView(context: Context): T
}

abstract class GroupView<T : ViewGroup> : BaseElement<T>() {
    private val children = arrayListOf<Element<out View>>()

    protected fun <T : Element<out View>> initElement(tag: T, init: T.() -> Unit): T {
        tag.init()
        children.add(tag)
        return tag
    }

    override fun build(context: Context): T {
        val view = createView(context)
        init(view)
        for (c in children) {
            view.addView(c.build(context))
        }
        return view
    }

    abstract override fun createView(context: Context): T
}

class Linear : GroupView<LinearLayout>() {
    var orientation: Int = LinearLayout.VERTICAL

    override fun createView(context: Context): LinearLayout = LinearLayout(context)

    override fun init(view: LinearLayout) {
        view.orientation = orientation
    }

    fun text(init: Text.() -> Unit) = initElement(Text(), init)

    fun button(init: Btn.() -> Unit) = initElement(Btn(), init)

    fun edit(init: Edit.() -> Unit) = initElement(Edit(), init)

    fun linearLayout(init: Linear.() -> Unit) = initElement(Linear(), init)

    fun list(init: List.() -> Unit) = initElement(List(), init)
}

class Text : BaseElement<TextView>() {
    var text: CharSequence? = null
        set(s) {
            field = s
            textView?.text = s
        }

    var textSize = 14f
    var lines = 1
    var maxLines = 1
    var minLines = 1
    var textColor = Color.BLACK
    var movementMethod: MovementMethod? = null
    private var textView: TextView? = null

    override fun createView(context: Context): TextView {
        textView = TextView(context)
        return textView!!
    }

    override fun init(view: TextView) {
        super.init(view)
        view.text = text
        view.setTextColor(textColor)
        view.textSize = textSize
        view.maxLines = maxLines
        view.minLines = minLines
        view.setLines(lines)
        view.isVerticalScrollBarEnabled = true
        view.movementMethod = movementMethod
    }
}

class Btn : BaseElement<Button>() {
    var text: String = "Button"
    var onClick: View.OnClickListener? = null

    override fun createView(context: Context): Button = Button(context)

    override fun init(view: Button) {
        super.init(view)
        view.text = text
        view.setOnClickListener(onClick)
    }
}

class Edit : BaseElement<EditText>() {
    private var edit: EditText? = null

    var hint: String? = null
    var textSize = 14f
    var inputType = InputType.TYPE_CLASS_TEXT
    var textWatcher: TextWatcher? = null

    override fun createView(context: Context): EditText {
        edit = EditText(context)
        return edit!!
    }

    override fun init(view: EditText) {
        super.init(view)
        view.hint = hint
        view.textSize = textSize
        view.setSelection(0)
        view.setEms(10)
        view.inputType = inputType
        if (textWatcher != null) {
            view.addTextChangedListener(textWatcher)
        }
    }

    fun getText() = edit!!.text!!
}

class List : BaseElement<ListView>() {
    var adapter: ArrayAdapter<String>? = null

    override fun createView(context: Context): ListView = ListView(context)

    override fun init(view: ListView) {
        super.init(view)
        view.adapter = adapter
    }
}

fun linearLayout(init: Linear.() -> Unit): Element<LinearLayout> {
    val layout = Linear()
    layout.init()
    return layout
}

fun simpleTextWatcher(afterTextChanged: (p0: Editable?) -> Unit): TextWatcher {
    return object : TextWatcher {
        override fun afterTextChanged(p0: Editable?) {
            afterTextChanged(p0)
        }

        override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}

        override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
    }
}