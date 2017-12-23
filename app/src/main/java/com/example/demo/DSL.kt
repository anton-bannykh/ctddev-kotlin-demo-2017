package com.example.demo

import android.content.Context
import android.graphics.Color
import android.text.Editable
import android.text.InputType
import android.view.View
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.ListView
import android.widget.EditText
import android.widget.Spinner
import android.widget.Button
import android.widget.TextView
import android.widget.LinearLayout
import android.widget.ArrayAdapter

/**
 * Created by tujh on 22.12.2017.
 */

interface Element<T : View> {
    fun build(context: Context): T

}

abstract class PreElement<T : View> : Element<T> {
    protected val params = LinearLayout.LayoutParams(WRAP_CONTENT, WRAP_CONTENT)

    fun setWeight(w: Float) {
        params.weight = w
    }

    fun setGravity(g: Int) {
        params.gravity = g
    }

    fun setHeight(h: Int) {
        params.height = h
    }

    fun setWidth(w: Int) {
        params.width = w
    }

}

class MyLinearLayout : PreElement<LinearLayout>() {
    private val children = arrayListOf<Element<out View>>()
    var orientation: Int = LinearLayout.VERTICAL
    override fun build(context: Context): LinearLayout {
        val view = LinearLayout(context)
        view.layoutParams = params
        view.orientation = orientation
        for (i in children)
            view.addView(i.build(context))
        return view
    }

    private fun <T : Element<out View>> initImpl(tag: T, init: T.() -> Unit): T {
        tag.init()
        children.add(tag)
        return tag
    }

    fun text(init: MyText.() -> Unit) = initImpl(MyText(), init)

    fun numberEdit(init: MyNumberEdit.() -> Unit) = initImpl(MyNumberEdit(), init)

    fun list(init: MyList.() -> Unit) = initImpl(MyList(), init)

    fun spinner(init: MySpinner.() -> Unit) = initImpl(MySpinner(), init)

    fun button(init: MyButton.() -> Unit) = initImpl(MyButton(), init)

    fun linearLayout(init: MyLinearLayout.() -> Unit) = initImpl(MyLinearLayout(), init)
}

class MyText : PreElement<TextView>() {
    var text = ""

    override fun build(context: Context): TextView {
        val view = TextView(context)
        view.layoutParams = params
        view.text = text
        view.setTextColor(Color.BLACK)
        view.text = text
        return view
    }
}

class MyNumberEdit : PreElement<EditText>() {
    var view: EditText? = null
    var text: Editable? = null
        get() {
            return view!!.text
        }

    override fun build(context: Context): EditText {
        val view = EditText(context)
        view.layoutParams = params
        view.inputType = InputType.TYPE_CLASS_NUMBER
        this.view = view
        return view
    }
}

class MyList : PreElement<ListView>() {
    private var view: ListView? = null
    var adapter: ArrayAdapter<String>? = null
        set(a: ArrayAdapter<String>?) {
            field = a
            view?.adapter = a
        }

    override fun build(context: Context): ListView {
        val view = ListView(context)
        view.layoutParams = params
        view.adapter = adapter
        this.view = view
        return view
    }
}

class MySpinner : PreElement<Spinner>() {
    private var view: Spinner? = null
    var adapter: ArrayAdapter<String>? = null
        set(a: ArrayAdapter<String>?) {
            field = a
            view?.adapter = a
        }

    fun selectedItem() = view!!.selectedItem

    override fun build(context: Context): Spinner {
        val view = Spinner(context)
        view.layoutParams = params
        view.adapter = adapter
        this.view = view
        return view
    }
}

class MyButton : PreElement<Button>() {
    var text = ""
    var onClickEvent: View.OnClickListener? = null
    override fun build(context: Context): Button {
        val view = Button(context)
        view.layoutParams = params
        view.text = text
        view.setOnClickListener(onClickEvent)
        return view
    }
}

fun linearLayout(init: MyLinearLayout.() -> Unit): MyLinearLayout {
    val view = MyLinearLayout()
    view.init()
    return view
}