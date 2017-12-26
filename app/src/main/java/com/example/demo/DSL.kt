package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.*

@DslMarker
annotation class LayoutConstructor

@LayoutConstructor
class LayoutBuilder(private val activity: AppCompatActivity, layoutId: Int) {
//    val linearLayout = LinearLayout(activity).apply {
//        id = layoutId
//    }
//
//    val relativeLayout = RelativeLayout(activity).apply {
//        id = layoutId
//    }

    val layout = LinearLayout(activity).apply {
        id = layoutId
    }

    private fun <T : View> addView(view: T, init: T.() -> Unit): T {
        layout.addView(view)
        view.init()
        return view
    }

    fun addTextView(id: Int, init: TextView.() -> Unit): TextView {
        val view = TextView(activity)
        view.id = id
        return addView(view, init)
    }

    fun addEditText(id: Int, init: EditText.() -> Unit): EditText {
        val view = EditText(activity)
        view.id = id
        return addView(view, init)
    }

    fun addButton(id: Int, init: Button.() -> Unit): Button {
        val button = Button(activity)
        button.id = id
        return addView(button, init)
    }
}

fun AppCompatActivity.layout(name: Int, init: LayoutBuilder.() -> Unit): LinearLayout {
    val layout = LayoutBuilder(this, name)
    layout.init()
    layout.layout.orientation = LinearLayout.VERTICAL
    return layout.layout
}

//fun AppCompatActivity.linearLayout(name: Int, init: LayoutBuilder.() -> Unit): LinearLayout {
//    val layout = LayoutBuilder(this, name)
//    layout.init()
//    return layout.linearLayout
//}
//
//fun AppCompatActivity.relativeLayout(name: Int, init: LayoutBuilder.() -> Unit): RelativeLayout {
//    val layout = LayoutBuilder(this, name)
//    layout.init()
//    return layout.relativeLayout
//}