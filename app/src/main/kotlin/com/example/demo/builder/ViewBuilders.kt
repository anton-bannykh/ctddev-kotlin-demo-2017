package com.example.demo.builder

import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

/**
 * Created by greg on 19/12/2017.
 */

fun <T: View> ViewGroup.addCustomView(view: T, init: T.() -> Unit = nop): T {
    view.init()
    addView(view)
    return view
}

fun ViewGroup.textView(init: TextView.() -> Unit = nop) =
        addCustomView(TextView(context), init)

fun ViewGroup.button(init: Button.() -> Unit = nop) =
        addCustomView(Button(context), init)

fun ViewGroup.editText(init: EditText.() -> Unit) =
        addCustomView(EditText(context), init)