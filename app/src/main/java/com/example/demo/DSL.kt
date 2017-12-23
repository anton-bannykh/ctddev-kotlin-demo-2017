package com.example.demo

import android.graphics.Color
import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

fun AppCompatActivity.constraintLayout(init: Constraint.() -> Unit): ConstraintLayout {
    val layout = Constraint(this)
    layout.layout.layoutParams = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.MATCH_PARENT, ConstraintLayout.LayoutParams.MATCH_PARENT)
    layout.init()
    return layout.layout
}

private var globalID: Int? = null

class Constraint(private val activity: AppCompatActivity) {
    val layout = ConstraintLayout(activity)
    private val constraintSet = ConstraintSet()

    var id: Int? = null
        set(value) {
            layout.id = value!!
            globalID = value
        }

    private fun <T : View> add(widget: T, margins: Array<Array<Int?>>) {
        layout.addView(widget)
        constraintSet.clone(layout)
        for (i in 0 until 4) {
            if (margins[i][0] != null) {
                constraintSet.connect(widget.id, margins[i][0]!!, margins[i][1]!!, margins[i][2]!!, margins[i][3]!!)
            }
        }
        constraintSet.applyTo(layout)
    }

    private fun <T : Element> initialize(element: T, init: T.() -> Unit) {
        element.init()
        add(element.takeData(), element.lst!!)
    }

    fun textView(init: AddText.() -> Unit) = initialize(AddText(activity), init)

    fun editText(init: AddEdit.() -> Unit) = initialize(AddEdit(activity), init)

    fun button(init: AddButton.() -> Unit) = initialize(AddButton(activity), init)
}

interface Element {
    var lst: Array<Array<Int?>>?
    fun takeData(): View
}

abstract class ViewElement(private val activity: AppCompatActivity) : Element {
    val params = ConstraintLayout.LayoutParams(ConstraintLayout.LayoutParams.WRAP_CONTENT, ConstraintLayout.LayoutParams.WRAP_CONTENT)
    var id: Int = 0
    var hint: String = "defaultStr"
    var text: String = "defaultStr"
    var textSize: Float = 18f
    var height: Any = "match"
    var width: Any = "match"
    var textColor: Int = Color.GRAY
    var gravity: Int? = null
    override var lst: Array<Array<Int?>>? = null

    fun margins(init: Margins.() -> Unit) {
        val data = Margins(activity)
        data.init()
        lst = data.lst
    }

    fun setBorders() {
        val h: Any = height
        if (h is String) {
            if (h == "wrap") {
                params.height = ViewGroup.LayoutParams.WRAP_CONTENT
            }
            if (h == "match") {
                params.height = 0
            }
        }
        if (h is Int) {
            params.height = dpConvert(activity, h)
        }

        val w = width
        if (w is String) {
            if (w == "wrap") {
                params.width = ViewGroup.LayoutParams.WRAP_CONTENT
            }
            if (w == "match") {
                params.width = 0
            }
        }
        if (w is Int) {
            params.width = dpConvert(activity, w)
        }
    }
}

class AddText(private val activity: AppCompatActivity) : ViewElement(activity) {
    val data = TextView(activity)
    override fun takeData(): TextView {
        setBorders()
        data.id = id
        data.text = text
        data.textSize = textSize
        data.gravity = gravity ?: data.gravity
        data.setTextColor(textColor)
        data.layoutParams = params
        return data
    }
}

class AddEdit(private val activity: AppCompatActivity) : ViewElement(activity) {
    val data = EditText(activity)
    override fun takeData(): EditText {
        setBorders()
        data.id = id
        data.hint = hint
        data.textSize = textSize
        data.gravity = gravity ?: data.gravity
        data.setTextColor(textColor)
        data.layoutParams = params
        return data
    }
}

class AddButton(private val activity: AppCompatActivity) : ViewElement(activity) {
    val data = Button(activity)
    override fun takeData(): Button {
        setBorders()
        data.id = id
        data.text = text
        data.textSize = textSize
        data.gravity = gravity ?: data.gravity
        data.setTextColor(textColor)
        data.layoutParams = params
        return data
    }

    fun onClick(init: () -> Unit) {
        data.setOnClickListener { init() }
    }
}

class Margins(private val activity: AppCompatActivity) {
    val lst = Array(4, { arrayOf(null, globalID, null, null) })
    var marginStart: Int? = null
        set(value) {
            lst[0][0] = ConstraintSet.START
            lst[0][2] = ConstraintSet.START
            lst[0][3] = dpConvert(activity, value!!)
        }
    var marginEnd: Int? = null
        set(value) {
            lst[1][0] = ConstraintSet.END
            lst[1][2] = ConstraintSet.END
            lst[1][3] = dpConvert(activity, value!!)
        }
    var marginTop: Int? = null
        set(value) {
            lst[2][0] = ConstraintSet.TOP
            lst[2][2] = ConstraintSet.TOP
            lst[2][3] = dpConvert(activity, value!!)
        }
    var marginBottom: Int? = null
        set(value) {
            lst[3][0] = ConstraintSet.BOTTOM
            lst[3][2] = ConstraintSet.BOTTOM
            lst[3][3] = dpConvert(activity, value!!)
        }

    var startTo: Int? = null
        set(value) {
            lst[0][1] = value
            lst[0][2] = ConstraintSet.END
        }
    var endTo: Int? = null
        set(value) {
            lst[1][1] = value
            lst[1][2] = ConstraintSet.START
        }
    var topTo: Int? = null
        set(value) {
            lst[2][1] = value
            lst[2][2] = ConstraintSet.BOTTOM
        }
    var bottomTo: Int? = null
        set(value) {
            lst[3][1] = value
            lst[3][2] = ConstraintSet.TOP
        }
}

fun dpConvert(activity: AppCompatActivity, dp: Int): Int {
    val a = activity.applicationContext.resources.displayMetrics.density
    return Math.round(dp.toFloat() * a)
}