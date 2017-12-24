package com.example.demo

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.InputType
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


class MyButton : MyView<Button> {
    var text = " "
    var id = 0
    var onClick: View.OnClickListener? = null
    var vis = View.INVISIBLE
    val layout_lin = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    override fun build(context: Context): Button {
        var button = Button(context, null, R.style.Padding)
        button.layoutParams = layout_lin
        button.visibility = vis
        button.id = id
        button.setOnClickListener(onClick)
        button.text = text
        button.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.toFloat())
        return button
    }
}

class MyEditText : MyView<EditText> {
    var hint = ""
    var text: Editable? = null
    var id = 0
    val input = InputType.TYPE_NUMBER_FLAG_DECIMAL
    val layout_lin = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    var vis = View.INVISIBLE
    override fun build(context: Context): EditText {
        var editText = EditText(context, null, R.style.Padding)
        editText.layoutParams = layout_lin
        editText.visibility = vis
        editText.hint = hint
        editText.text = text
        editText.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.toFloat())
        editText.id = id
        return editText
    }
}

class MyTextView : MyView<TextView> {
    var text = " "
    var id = 0
    val layout_lin = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    var vis = View.INVISIBLE
    override fun build(context: Context): TextView {
        val textView = TextView(context, null, R.style.Padding)
        textView.layoutParams = layout_lin
        textView.text = text
        textView.id = id
        textView.setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.toFloat())
        textView.setTextColor(Color.BLACK)
        textView.visibility = vis
        return textView
    }
}

class MyLinearLayout : MyView<LinearLayout> {
    private val children = arrayListOf<MyView<out View>>()
    var orient = LinearLayout.HORIZONTAL
    var id = 0
    val layout_lin = LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT)
    fun width(wid: Int) {
        layout_lin.width = wid
    }

    fun height(hei: Int) {
        layout_lin.height = hei
    }

    fun gravity(gravity: Int) {
        layout_lin.gravity = gravity
    }

    override fun build(context: Context): LinearLayout {
        val layout = LinearLayout(context)
        layout.layoutParams = layout_lin
        layout.orientation = orient
        layout.id = id
        for (i in children)
            layout.addView(i.build(context))
        return layout
    }

    fun text(init: MyTextView.() -> Unit): MyTextView {
        val text = MyTextView()
        text.init()
        children.add(text)
        return text
    }

    fun button(init: MyButton.() -> Unit): MyButton {
        val button = MyButton()
        button.init()
        children.add(button)
        return button
    }

    fun editText(init: MyEditText.() -> Unit): MyEditText {
        val editText = MyEditText()
        editText.init()
        children.add(editText)
        return editText
    }

    fun linearLayout(init: MyLinearLayout.() -> Unit): MyLinearLayout {
        val linearLayout = MyLinearLayout()
        linearLayout.init()
        children.add(linearLayout)
        return linearLayout
    }
}

fun linearLayout(init: MyLinearLayout.() -> Unit): MyLinearLayout {
    val linearLayout = MyLinearLayout()
    linearLayout.init()
    return linearLayout
}