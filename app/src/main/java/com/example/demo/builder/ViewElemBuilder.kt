package com.example.demo.builder

import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

/**
 * Created by Facmad on 19.12.2017.
 */


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