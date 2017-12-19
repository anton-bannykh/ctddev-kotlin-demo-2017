package com.example.demo.builder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

/**
* Created by greg on 19/12/2017.
*/

fun <T: View> Context.addCustomView(view: T, init: T.() -> Unit): T {
    view.init()
    return view
}

fun Context.textView(init: TextView.() -> Unit) =
        addCustomView(TextView(this), init)

fun Context.button(init: Button.() -> Unit) =
        addCustomView(Button(this), init)

fun Context.editText(init: EditText.() -> Unit) =
        addCustomView(EditText(this), init)

fun <T: View> ViewGroup.addCustomView(view: T, init: T.() -> Unit): T {
    view.init()
    addView(view)
    return view
}

fun ViewGroup.textView(init: TextView.() -> Unit) =
        addCustomView(TextView(context), init)

fun ViewGroup.button(init: Button.() -> Unit) =
        addCustomView(Button(context), init)

fun ViewGroup.editText(init: EditText.() -> Unit) =
        addCustomView(EditText(context), init)