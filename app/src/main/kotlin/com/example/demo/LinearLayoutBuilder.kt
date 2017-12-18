package com.example.demo

import android.content.Context
import android.util.DisplayMetrics
import android.util.TypedValue
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by greg on 17/12/2017.
 */

private val nop: Any.() -> Unit = {}
private val displayMetrics = DisplayMetrics()

fun <T: Number> T.toDp() =
    TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, this.toFloat(), displayMetrics).toInt()

fun Context.linearLayout(init: LinearLayout.() -> Unit = nop): LinearLayout {
    val linearLayout = LinearLayout(this)
    linearLayout.init()
    return linearLayout
}

private fun <T: View> ViewGroup.addCustomView(value: T, init: T.() -> Unit = nop): T {
    value.init()
    addView(value)
    return value
}

fun ViewGroup.textView(id: Int = -1, text: CharSequence? = null, init: TextView.() -> Unit = nop): TextView {
    val view = TextView(context)
            .apply {
                this.id = id
                this.text = text
            }

    return addCustomView(view, init)
}

fun ViewGroup.forEachView(action: View.() -> Unit) {
    for (i in 0 until childCount) {
        getChildAt(i).action()
    }
}

fun ViewGroup.button(
        id: Int = -1,
        text: CharSequence? = null,
        onClickListener: View.OnClickListener? = null,
        init: Button.() -> Unit = nop
): Button {
    val view = Button(context).apply {
        this.id = id
        this.text = text
        setOnClickListener(onClickListener)
    }
    return addCustomView(view, init)
}
