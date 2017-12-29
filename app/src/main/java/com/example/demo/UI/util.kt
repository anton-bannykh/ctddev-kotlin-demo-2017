package com.example.demo.UI

import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import android.widget.RelativeLayout

/**
 * Created by Sergey on 27.12.2017.
 */

////////// Constant values

val LinearLayout.VERTICAL
    get() = LinearLayout.VERTICAL
val LinearLayout.HORIZONTAL
    get() = LinearLayout.HORIZONTAL

val ViewGroup.LayoutParams.MATCH_PARENT
    get() = ViewGroup.LayoutParams.MATCH_PARENT

val ViewGroup.LayoutParams.WRAP_CONTENT
    get() = ViewGroup.LayoutParams.WRAP_CONTENT

////////// init functions

fun View.initRelativeLayoutParams(init: RelativeLayout.LayoutParams.() -> Unit) {
    layoutParams = RelativeLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply(init)
}

fun View.initLinearLayoutParams(init: LinearLayout.LayoutParams.() -> Unit) {
    layoutParams = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT).apply(init)
}
