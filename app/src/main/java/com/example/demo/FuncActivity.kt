package com.example.demo

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.TextView
import my.lib.SegmentTree

import kotlinx.android.synthetic.main.activity_func.*
import kotlinx.android.synthetic.main.content_func.*

class FuncActivity : AppCompatActivity() {

    companion object {
        const val ARRAY = "array_tree"
    }

    var s = ""
    var SIZE = 0
    lateinit var TREE:SegmentTree

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_func)
        setSupportActionBar(toolbar)
        s = intent.getStringExtra(ARRAY)
        val a = s.trim().split("\\s+".toRegex())
        val arr = Array(a.size, {i -> (a[i].toInt())})
        SIZE = arr.size
        TREE = SegmentTree(arr)
    }

    fun AddElem (v: View) {
        val index = editAddInd.text.toString().toInt()
        val value = editAddVal.text.toString().toInt()
        TREE.add(index, value)
        editAddInd.setText("")
        editAddVal.setText("")
    }

    fun SetElem (v: View) {
        val index = editSetInd.text.toString().toInt()
        val value = editSetVal.text.toString().toInt()
        TREE.set(index, value)
        editSetInd.setText("")
        editSetVal.setText("")
    }

    fun GetRes (v: View) {
        val left = editGetSt.text.toString().toInt()
        val right = editGetFn.text.toString().toInt()
        ViewAns.text = TREE.get(left, right).toString()
        editGetFn.setText("")
        editGetSt.setText("")
    }

    fun Close (v: View) {
        finish()
    }
}
