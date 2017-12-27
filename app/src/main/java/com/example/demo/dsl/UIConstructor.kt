package com.example.demo.dsl

import android.content.Context
import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import android.widget.EditText
import android.widget.Button

@DslMarker
annotation class UIConstructorMarker

@UIConstructorMarker
class LayoutConstructor(val layout: ViewGroup) {

    private fun <T: ViewGroup> createLayout(factory: (Context) -> T, init: LayoutConstructor.() -> Unit): T {
        val layout = factory(layout.context)
        LayoutConstructor(layout).init()
        this.layout.addView(layout)
        return layout
    }

    private fun <T: View> createView(factory: (Context) -> T, init: T.() -> Unit): T
            = customView(factory(layout.context), init)

    fun constraintLayout(init: LayoutConstructor.() -> Unit): ConstraintLayout {
        return createLayout(::ConstraintLayout, init)
    }

    fun constrain(init: ConstraintSet.() -> Unit) {
        val set = ConstraintSet()
        set.clone(layout as ConstraintLayout?)
        set.init()
        set.applyTo(layout as ConstraintLayout?)
    }

    fun linearLayout(init: LayoutConstructor.() -> Unit): LinearLayout = createLayout(::LinearLayout, init)

    fun textView(init: TextView.() -> Unit): TextView {
        val view = createView(::TextView, init)
        view.setTextColor(Color.DKGRAY)
        view.textSize = 16f
        return view
    }

    fun editText(init: EditText.() -> Unit): EditText {
        val view = createView(::EditText, init)
        view.setTextColor(Color.DKGRAY)
        view.setBackgroundResource(android.R.drawable.editbox_background_normal)
        return view
    }

    fun button(init: Button.() -> Unit): Button = createView(::Button, init)

    fun <T: View> customView(view: T, init: T.() -> Unit): T {
        view.init()
        layout.addView(view)
        return view
    }
}

fun ui(view: ViewGroup, init: LayoutConstructor.() -> Unit): LayoutConstructor {
    val root = LayoutConstructor(view)
    root.init()
    return root
}
