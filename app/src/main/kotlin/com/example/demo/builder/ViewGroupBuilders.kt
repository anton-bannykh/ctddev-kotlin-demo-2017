package com.example.demo.builder

import android.content.Context
import android.widget.LinearLayout

/**
* Created by greg on 19/12/2017.
*/

fun Context.linearLayout(init: LinearLayout.() -> Unit): LinearLayout {
    val linearLayout = LinearLayout(this)
    linearLayout.init()
    return linearLayout
}