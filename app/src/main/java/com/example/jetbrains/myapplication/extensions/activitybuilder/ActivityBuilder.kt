package com.example.jetbrains.myapplication.extensions.activitybuilder

import android.app.Activity
import android.content.Context
import android.support.constraint.ConstraintLayout
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by jetbrains on 17/12/2017.
 */

inline fun <reified T: ViewGroup> Activity.layout(init: (Context) -> T, modifier: T.() -> Unit) =
        init(this).also {
            modifier(it)
            this.addContentView(it, it.layoutParams)
        }

inline fun Activity.constraintLayout(modifier: ConstraintLayout.() -> Unit) =
        this.layout(::ConstraintLayout, modifier)

inline fun Activity.linearLayout(modifier: LinearLayout.() -> Unit) =
        this.layout(::LinearLayout, modifier)

inline fun <reified T: View> ViewGroup.view(init: (Context) -> T, modifier: T.() -> Unit) =
        this.addView(init(this.context).also { modifier(it) })

inline fun ViewGroup.layout(modifier: ConstraintLayout.() -> Unit) =
        this.view(::ConstraintLayout, modifier)

inline fun ViewGroup.linearLayout(modifier: LinearLayout.() -> Unit) =
        this.view(::LinearLayout, modifier)

inline fun ViewGroup.button(modifier: Button.() -> Unit) = this.view(::Button, modifier)

inline fun ViewGroup.label(modifier: TextView.() -> Unit) = this.view(::TextView, modifier)

inline fun ViewGroup.textEdit(modifier: EditText.() -> Unit) = this.view(::EditText, modifier)


fun View.fillParent() {
    this.layoutParams = ViewGroup.LayoutParams(
            LinearLayout.LayoutParams.FILL_PARENT,
            LinearLayout.LayoutParams.FILL_PARENT
    )
}

fun View.wrapContent() {
    this.layoutParams = ViewGroup.LayoutParams(
            LinearLayout.LayoutParams.WRAP_CONTENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
    )
}

fun View.fillParentXwrapContentY() {
    this.layoutParams = ViewGroup.LayoutParams(
            LinearLayout.LayoutParams.FILL_PARENT,
            LinearLayout.LayoutParams.WRAP_CONTENT
    )
}