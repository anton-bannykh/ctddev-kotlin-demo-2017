package com.example.demo

import android.support.constraint.ConstraintLayout
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class LazyConnect(private val id: Int, private val sideA: Int, private val parent: Int, private val sideB: Int, private val dist: Int) {
    fun release(set: ConstraintSet) {
        set.connect(id, sideA, parent, sideB, dist)
    }
}

class MyConstraintLayout(private val context: AppCompatActivity, id: Int) {
    var layout : ConstraintLayout = ConstraintLayout(context)
    private val lazyList: MutableList<LazyConnect> = mutableListOf()

    init {
        layout.id = id
    }

    private fun <T : View> addView(one: T, build: T.() -> Unit) {
        one.build()
        layout.addView(one)
    }

    fun releaseLazy() {
        val set = ConstraintSet()
        set.clone(layout)
        for (one in lazyList) {
            one.release(set)
        }
        set.applyTo(layout)
        lazyList.clear()
    }

    fun View.connect(parent: Int, sideA: Int, sideB: Int, dist: Int = 0) {
        lazyList.add(LazyConnect(id, sideA, parent, sideB, dist))
    }

    fun myTextView(build: TextView.() -> Unit) = addView(TextView(context), build)

    fun myButton(build: Button.() -> Unit) = addView(Button(context), build)

    fun myEditText(build: EditText.() -> Unit) = addView(EditText(context), build)

    fun toConstraintLayout(): ConstraintLayout {
        return layout
    }
}

fun myConstraintLayout(context: AppCompatActivity, id: Int, build: MyConstraintLayout.() -> Unit): MyConstraintLayout {
    val myLayout = MyConstraintLayout(context, id)
    myLayout.build()
    myLayout.releaseLazy()
    return myLayout
}