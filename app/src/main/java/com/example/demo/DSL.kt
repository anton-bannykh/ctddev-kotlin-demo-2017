package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView

/**
 * Created by daminovn on 12/27/17.
 */

@DslMarker
annotation class LayoutBuilder

@LayoutBuilder
class RelativeLayoutData(private val activity: AppCompatActivity, layoutId: Int) {
    val layout = RelativeLayout(activity).apply {
        id = layoutId
    }

    fun <T : View> addItem(item: T, params: RelativeLayout.LayoutParams, init: T.() -> Unit): T {
        item.init()
        layout.addView(item, params)
        return item
    }

    fun layoutParams(w: Int, h: Int, init: RelativeLayout.LayoutParams.() -> Unit): RelativeLayout.LayoutParams {
        val el = RelativeLayout.LayoutParams(w, h)
        el.init()
        return el
    }

    fun textView(name: Int, params: RelativeLayout.LayoutParams, init: TextView.() -> Unit): TextView {
        val el = TextView(activity)
        el.id = name
        return addItem(el, params, init)
    }

    fun button(name: Int, params: RelativeLayout.LayoutParams, init: TextView.() -> Unit): TextView {
        val el = Button(activity)
        el.id = name
        return addItem(el, params, init)
    }

    fun editText(name: Int, params: RelativeLayout.LayoutParams, init: TextView.() -> Unit): TextView {
        val el = EditText(activity)
        el.id = name
        return addItem(el, params, init)
    }
}

fun AppCompatActivity.relativeLayout(name: Int, init: RelativeLayoutData.() -> Unit): RelativeLayout {
    val layout = RelativeLayoutData(this, name)
    layout.init()
    return layout.layout
}
