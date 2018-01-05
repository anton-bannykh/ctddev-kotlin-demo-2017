package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

fun AppCompatActivity.linearLayout(init: LinearLayout.() -> Unit): LinearLayout {
    val layout = LinearLayout(this)
    layout.init()
    layout.id = View.generateViewId()
    layout.visibility = View.VISIBLE
    return layout
}

fun <T : View> ViewGroup.initView(view: T, init: T.() -> Unit): T {
    view.init()
    view.id = View.generateViewId()
    view.visibility = View.VISIBLE
    this.addView(view)
    return view
}

fun ViewGroup.editText(init: EditText.() -> Unit): EditText = initView(EditText(this.context), init)

fun ViewGroup.button(init: Button.() -> Unit): Button = initView(Button(this.context), init)

fun ViewGroup.textView(init: TextView.() -> Unit): TextView = initView(TextView(this.context), init)

fun View.layoutParams(init: ViewGroup.LayoutParams.() -> Unit) {
    val lp = ViewGroup.LayoutParams(ViewGroup.LayoutParams.WRAP_CONTENT, ViewGroup.LayoutParams.WRAP_CONTENT)
    lp.init()
    this.layoutParams = lp
}
