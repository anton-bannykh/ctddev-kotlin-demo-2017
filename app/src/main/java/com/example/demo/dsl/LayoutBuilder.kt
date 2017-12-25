package com.example.demo.dsl

/**
 * Created by Facmad on 19.12.2017.
 */

import android.content.Context
import android.widget.LinearLayout


fun Context.linearLayout (init: LinearLayout.() -> Unit) : LinearLayout {
    val linearLayout = LinearLayout(this)
    linearLayout.init()
    return linearLayout
}
