package com.example.demo.builder

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.Toast
import kotlin.DeprecationLevel.ERROR

/**
* Created by greg on 17/12/2017.
*/
const val NO_GETTER = "This property haven't got getter"

fun Context.extractString(id: Int): String = resources.getText(id).toString()

fun Context.extractColor(id: Int): Int = resources.getColor(id)

val <T: Number> T.dp: Int
    get() {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()
    }

val <T: Number> T.sp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), Resources.getSystem().displayMetrics)

var View.onClick: View.OnClickListener
    @Deprecated(NO_GETTER, level = ERROR) get() = throw IllegalStateException("Don't even try through reflection!")
    set(value) = setOnClickListener(value)

fun View.onClick(onClick: (View) -> Unit) =
        setOnClickListener(onClick)

var TextView.textColor
    get() = currentTextColor
    set(value) {
        setTextColor(value)
    }

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

val ViewGroup.LayoutParams.MATCH_PARENT get() = ViewGroup.LayoutParams.MATCH_PARENT
val ViewGroup.LayoutParams.WRAP_CONTENT get() = ViewGroup.LayoutParams.WRAP_CONTENT
var ViewGroup.MarginLayoutParams.margin: Int
    @Deprecated(NO_GETTER, level = ERROR) get() = throw IllegalStateException("Don't even try through reflection!")
    set(value) = setMargins(value, value, value, value)

val LinearLayout.VERTICAL get() = LinearLayout.VERTICAL
val LinearLayout.HORIZONTAL get() = LinearLayout.HORIZONTAL

fun <T: ViewGroup, R: ViewGroup.LayoutParams>
        T.customLayoutParams(layoutParams: R, init: R.() -> Unit): R {
    layoutParams.init()
    return layoutParams
}

fun LinearLayout.linearLayoutParams(init: LinearLayout.LayoutParams.() -> Unit) =
        customLayoutParams(LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                    ), init)

fun Context.toast(text: CharSequence) {
    Toast.makeText(this, text, Toast.LENGTH_SHORT).show()
}
