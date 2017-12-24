package com.example.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import my.lib.SegmentTree

import kotlinx.android.synthetic.main.activity_func.*
import kotlinx.android.synthetic.main.content_func.*

class FuncActivity : AppCompatActivity() {

    companion object {
        const val ARRAY = "array_tree"
    }

    var s = ""
    var SIZE = 0
    lateinit var TREE: SegmentTree

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_func)
        setSupportActionBar(toolbar)
        s = intent.getStringExtra(ARRAY)
        val a = s.trim().split("\\s+".toRegex())
        val arr = Array(a.size, { i -> (a[i].toInt()) })
        SIZE = arr.size
        TREE = SegmentTree(arr)
    }

    fun Check(index: String, value: String): Boolean {
        var f = true
        var _index = -1
        try {
            _index = index.toInt()
        } catch (e: Exception) {
            Toast.makeText(this, "!Wrong Index!", Toast.LENGTH_LONG).show()
            f = false
        }
        if (_index < 0 || _index >= SIZE) {
            Toast.makeText(this, "!Wrong Index!", Toast.LENGTH_LONG).show()
            f = false
        }
        try {
            value.toInt()
        } catch (e: Exception) {
            Toast.makeText(this, "!Wrong value!", Toast.LENGTH_LONG).show()
            f = false
        }
        return f
    }

    fun AddElem(v: View) {
        val _index = editAddInd.text.toString()
        val _value = editAddVal.text.toString()
        if (Check(_index, _value)) {
            val index = _index.toInt()
            val value = _value.toInt()
            TREE.add(index, value)
            editAddInd.setText("")
            editAddVal.setText("")
        }
    }

    fun SetElem(v: View) {
        val _index = editSetInd.text.toString()
        val _value = editSetVal.text.toString()
        if (Check(_index, _value)) {
            val index = _index.toInt()
            val value = _value.toInt()
            TREE.set(index, value)
            editAddInd.setText("")
            editAddVal.setText("")
        }
    }

    fun GetRes(v: View) {
        try {
            val left = editGetSt.text.toString().toInt()
            val right = editGetFn.text.toString().toInt()
            ViewAns.text = TREE.get(left, right).toString()
            editGetFn.setText("")
            editGetSt.setText("")
        } catch (e: Exception) {
            Toast.makeText(this, "Try to use index in: [0...$SIZE]" , Toast.LENGTH_LONG).show()
        }
    }

    fun Close(v: View) {
        finish()
    }
}
