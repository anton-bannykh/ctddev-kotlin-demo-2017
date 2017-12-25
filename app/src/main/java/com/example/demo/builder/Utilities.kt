package com.example.demo.builder
import android.content.Context
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout

/**
 * Created by Facmad on 25.12.2017.
 */


val MATCH_PARENT get() = ViewGroup.LayoutParams.MATCH_PARENT
val WRAP_CONTENT get() = ViewGroup.LayoutParams.WRAP_CONTENT

fun Context.extractString(id : Int) : String = resources.getText(id).toString()

fun ViewGroup.forEachChild(action: View.() -> Unit) {
    for (i in 0 until childCount) {
        getChildAt(i).action()
    }
}

var ViewGroup.childrenLayout: ViewGroup.LayoutParams?
    get() = layoutParams
    set(value) {
        forEachChild {
            layoutParams = value
        }
    }

fun LinearLayout.linearLayoutParams(init : LinearLayout.LayoutParams.() -> Unit) : LinearLayout.LayoutParams{
    val layoutParams = LinearLayout.LayoutParams(
            LinearLayout.LayoutParams.MATCH_PARENT,
            LinearLayout.LayoutParams.MATCH_PARENT
    )
    layoutParams.init()
    return  layoutParams
}
