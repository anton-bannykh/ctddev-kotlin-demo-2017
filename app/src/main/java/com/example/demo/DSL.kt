package com.example.demo

import android.content.Context
import android.text.Editable
import android.util.TypedValue
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.LinearLayout.LayoutParams
import android.widget.TextView

interface MyView<out T : View> {
    fun build(context: Context): T
}

abstract class AbstractView<T : View> : MyView<T> {
    var id = 0
    var visible = View.VISIBLE
    private var layout = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)

    fun init(View: T) {
        View.id = id
        View.visibility = visible
        View.layoutParams = layout
    }

    fun setWidth(width: Int) {
        layout.width = width
    }

    fun setHeight(height: Int) {
        layout.height = height
    }
}

class MyTextView : AbstractView<TextView>() {
    var text = ""
    override fun build(context: Context): TextView {
        val textView = TextView(context)
        init(textView)
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.toFloat())
        return textView
    }
}

class MyEditText : AbstractView<EditText>() {
    var hint = ""
    var text: Editable? = null
    override fun build(context: Context): EditText {
        val editText = EditText(context)
        init(editText)
        editText.hint = hint
        editText.text = text
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.toFloat())
        return editText
    }
}

class MyButton : AbstractView<Button>() {
    var text = ""
    var onClick: View.OnClickListener? = null
    override fun build(context: Context): Button {
        val button = Button(context)
        init(button)
        button.text = text
        button.setOnClickListener(onClick)
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 25.toFloat())
        return button
    }
}

class MyLinearLayout : AbstractView<LinearLayout>() {
    private val children = arrayListOf<MyView<View>>()
    var orientation = LinearLayout.HORIZONTAL

    override fun build(context: Context): LinearLayout {
        val layout = LinearLayout(context)
        init(layout)
        layout.orientation = orientation
        for (child in children) {
            layout.addView(child.build(context))
        }
        return layout
    }

    fun textView(function: MyTextView.() -> Unit): MyTextView {
        val text = MyTextView()
        text.function()
        children.add(text)
        return text
    }

    fun button(function: MyButton.() -> Unit): MyButton {
        val button = MyButton()
        button.function()
        children.add(button)
        return button
    }

    fun editText(function: MyEditText.() -> Unit): MyEditText {
        val editText = MyEditText()
        editText.function()
        children.add(editText)
        return editText
    }

}

fun linearLayout(function: MyLinearLayout.() -> Unit): MyLinearLayout {
    val linearLayout = MyLinearLayout()
    linearLayout.function()
    return linearLayout
}
