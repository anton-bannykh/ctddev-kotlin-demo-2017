package com.example.demo.UI

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.RelativeLayout
import android.widget.ScrollView
import android.widget.TextView
import android.widget.EditText
import android.widget.Button

/**
 * Created by Sergey on 24.12.2017.
 */

fun Context.relativeLayout(id: Int? = null, init: RelativeLayout.() -> Unit) = RelativeLayout(this).apply {
    if (id != null) {
        this.id = id
    }
}.apply(init)

fun <T: View> ViewGroup.initCustomView(view: T, id: Int?, init : T.() -> Unit): T {
    view.init()
    if (id != null) {
        view.id = id
    }
    this.addView(view)
    return view
}

fun ViewGroup.linearLayout(id: Int? = null, init: LinearLayout.() -> Unit) = initCustomView(LinearLayout(context), id, init)

fun ViewGroup.scrollView(id: Int? = null, init: ScrollView.() -> Unit) = initCustomView(ScrollView(context), id, init)

fun ViewGroup.textView(id: Int, init: TextView.() -> Unit) = initCustomView(TextView(context), id, init)

fun ViewGroup.editText(id: Int, init: EditText.() -> Unit) = initCustomView(EditText(context), id, init)

fun ViewGroup.button(id: Int, init: Button.() -> Unit) = initCustomView(Button(context), id, init)
