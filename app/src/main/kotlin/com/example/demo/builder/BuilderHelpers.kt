package com.example.demo.builder

import android.content.Context
import android.content.res.Resources
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by greg on 17/12/2017.
 */

internal val nop: Any.() -> Unit = {}

fun Context.extractString(id: Int): String = resources.getText(id).toString()

fun Context.extractColor(id: Int): Int = resources.getColor(id)

val <T: Number> T.dp: Int
    get() {
        return TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), Resources.getSystem().displayMetrics).toInt()
    }

val <T: Number> T.sp: Float
    get() = TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_SP, this.toFloat(), Resources.getSystem().displayMetrics)

var View.onClick: View.OnClickListener
    get() = throw RuntimeException() // TODO Have to something with this
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

private fun equals(vararg os: Any?): Boolean {
    val o1 = os.first()
    for (o2 in os) {
        if (o1?.equals(o2) == false) {
            return false
        }
    }
    return true
}

val ViewGroup.LayoutParams.MATCH_PARENT get() = ViewGroup.LayoutParams.MATCH_PARENT
val ViewGroup.LayoutParams.WRAP_CONTENT get() = ViewGroup.LayoutParams.WRAP_CONTENT
var ViewGroup.MarginLayoutParams.margin: Int
    get() = if (equals(leftMargin, topMargin, rightMargin, bottomMargin))
        leftMargin
    else
        throw IllegalStateException("Margins are not equal")
    set(value) = setMargins(value, value, value, value)

val LinearLayout.VERTICAL get() = LinearLayout.VERTICAL
val LinearLayout.HORIZONTAL get() = LinearLayout.HORIZONTAL

fun <T: ViewGroup, R: ViewGroup.LayoutParams>
        T.customLayoutParams(layoutParams: R, init: R.() -> Unit = nop): R {
    layoutParams.init()
    return layoutParams
}

fun LinearLayout.linearLayoutParams(init: LinearLayout.LayoutParams.() -> Unit = nop) =
        customLayoutParams(LinearLayout.LayoutParams(
                    LinearLayout.LayoutParams.MATCH_PARENT,
                    LinearLayout.LayoutParams.MATCH_PARENT
                    ), init)
