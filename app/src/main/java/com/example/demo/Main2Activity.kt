package com.example.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import dsl.makeLayout
import my.lib.MyDSU

class Main2Activity : AppCompatActivity() {

    lateinit var DSU: MyDSU
    lateinit var btn: Button
    lateinit var txtUnion1: EditText
    lateinit var txtUnion2: EditText
    lateinit var txtFind3: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                makeLayout(R.id.Layout) {

                    editText(R.id.editText4) {
                        topMargin(R.id.Layout, TOP, 120)
                        leftMargin(R.id.Layout, LEFT, 140)
                        width = toDP(77)
                        height = toDP(67)
                        hint = "X"
                        textSize = 18f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }

                    editText(R.id.editText3) {
                        topMargin(R.id.Layout, TOP, 120)
                        rightMargin(R.id.Layout, RIGHT, 140)
                        width = toDP(77)
                        height = toDP(67)
                        hint = "Y"
                        textSize = 18f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }

                    button(R.id.button2) {
                        topMargin(R.id.Layout, TOP, 240)
                        leftMargin(R.id.Layout, LEFT, 8)
                        rightMargin(R.id.Layout, RIGHT, 8)
                        width = toDP(64)
                        height = toDP(64)
                        background = resources.getDrawable(R.drawable.abc_ic_arrow_drop_right_black_24dp)
                        textSize = 30f
                        onClick { unionBtn() }
                    }

                    viewText(R.id.textView2) {
                        text = "Find"
                        textSize = 30f
                        setTextColor(resources.getColor(R.color.abc_tint_default))
                        topMargin(R.id.button2, BOTTOM, toDP(16))
                        leftMargin(R.id.Layout, LEFT, 8)
                        rightMargin(R.id.Layout, RIGHT, 8)
                        width = toDP(146)
                        height = toDP(60)
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }

                    viewText(R.id.textView) {
                        text = "Union"
                        textSize = 30f
                        setTextColor(resources.getColor(R.color.abc_tint_default))
                        topMargin(R.id.Layout, TOP, toDP(16))
                        leftMargin(R.id.Layout, LEFT, 8)
                        rightMargin(R.id.Layout, RIGHT, 8)
                        width = toDP(146)
                        height = toDP(60)
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }

                    editText(R.id.editText) {
                        topMargin(R.id.textView2, TOP, 30)
                        leftMargin(R.id.Layout, LEFT, 8)
                        rightMargin(R.id.Layout, RIGHT, 8)
                        width = toDP(77)
                        height = toDP(67)
                        hint = "X"
                        textSize = 18f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }

                    button(R.id.button4) {
                        topMargin(R.id.editText, BOTTOM, 8)
                        leftMargin(R.id.Layout, LEFT, 8)
                        rightMargin(R.id.Layout, RIGHT, 8)
                        width = toDP(94)
                        height = toDP(94)
                        background = resources.getDrawable(R.drawable.abc_ic_arrow_drop_right_black_24dp)
                        textSize = 30f
                        onClick { findBtn() }
                    }
                }
        )
        txtUnion1 = findViewById(R.id.editText4)
        txtUnion2 = findViewById(R.id.editText3)
        txtFind3 = findViewById(R.id.editText)
        val d = intent.getIntExtra("NUMS", 0)
        if (d == 0) {
            throw RuntimeException("Unexpected behavior")
        }
        DSU = MyDSU(d)
    }

    fun unionBtn() {
        val x = txtUnion1.text.toString().toInt()
        val y = txtUnion2.text.toString().toInt()
        DSU.union(x, y)
        Toast.makeText(this, "OK", Toast.LENGTH_SHORT).show()
    }

    fun findBtn() {
        val x = txtFind3.text.toString().toInt()
        var ans = DSU.find(x)
        Toast.makeText(this, ans.toString(), Toast.LENGTH_LONG).show()
    }

}
