package com.example.demo

import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity

fun AppCompatActivity.myContraintLayout(id: Int, init: MyContraintLayout.() -> Unit): ConstraintLayout {
    val result = MyContraintLayout(this, id)
    result.init()
    return result.layout
}