package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.RecyclerView
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView

/**
 * Created by drxu on 26.12.2017.
 */
class LinearLayoutContext(private val context: AppCompatActivity) {
    val layout = LinearLayout(context)
    var orientation
        get() = layout.orientation
        set(value) {
            layout.orientation = value
        }

    fun dp(px: Int) = (context.resources.displayMetrics.density * px).toInt()

    fun sp(px: Int) = context.resources.configuration.fontScale * px

    fun linearLayout(width: Int, height: Int, init: LinearLayoutContext.() -> Unit): LinearLayoutContext {
        val layoutContext = LinearLayoutContext(context)
        val layout = layoutContext.layout
        layout.layoutParams = ViewGroup.LayoutParams(width, height)
        layoutContext.init()
        this.layout.addView(layout)
        return layoutContext
    }

    private fun <T : View> viewItem(width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
                                    height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
                                    init: T.() -> Unit,
                                    constructT: () -> T): T {
        val view = constructT()
        view.layoutParams = ViewGroup.LayoutParams(width, height)
        view.init()
        layout.addView(view)
        return view
    }

    fun editText(width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
                 height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
                 init: EditText.() -> Unit) = viewItem(width, height, init) {
        EditText(context)
    }

    fun textView(width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
                 height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
                 init: TextView.() -> Unit) = viewItem(width, height, init) {
        TextView(context)
    }

    fun button(width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
               height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
               init: Button.() -> Unit) = viewItem(width, height, init) {
        Button(context)
    }

    fun recyclerView(width: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
                     height: Int = ViewGroup.LayoutParams.WRAP_CONTENT,
                     init: RecyclerView.() -> Unit) = viewItem(width, height, init) {
        RecyclerView(context)
    }
}

fun AppCompatActivity.linearLayout(width: Int, height: Int, init: LinearLayoutContext.() -> Unit): LinearLayoutContext {
    val layoutContext = LinearLayoutContext(this)
    layoutContext.init()
    layoutContext.layout.layoutParams = ViewGroup.LayoutParams(width, height)
    return layoutContext
}

var EditText.weight: Float
    get() = (layoutParams as LinearLayout.LayoutParams).weight
    set(value) {
        layoutParams = LinearLayout.LayoutParams(layoutParams.width, layoutParams.height, value)
    }