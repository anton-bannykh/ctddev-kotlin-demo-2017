package com.example.demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import dsl.makeLayout

class MainActivity : AppCompatActivity() {

    lateinit var btn: Button
    lateinit var txt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                makeLayout(R.id.Layout) {

                    editText(R.id.editText2) {
                        topMargin(R.id.Layout, TOP, 8)
                        bottomMargin(R.id.Layout, BOTTOM, 8)
                        leftMargin(R.id.Layout, LEFT, 8)
                        rightMargin(R.id.Layout, RIGHT, 8)
                        width = toDP(387)
                        height = toDP(107)
                        hint = "Input numbers of elements"
                        textSize = 28f
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }

                    button(R.id.button) {
                        topMargin(R.id.editText2, BOTTOM, 192)
                        bottomMargin(R.id.Layout, BOTTOM, 119)
                        leftMargin(R.id.Layout, LEFT, 10)
                        rightMargin(R.id.Layout, RIGHT, 10)
                        width = toDP(143)
                        height = toDP(84)
                        text = "Next"
                        textSize = 30f
                        onClick { next() }
                    }
                }
        )
        btn = findViewById(R.id.button)
        txt = findViewById(R.id.editText2)

    }

    fun next() {
        val DSU = txt.text.toString().toInt()
        if (DSU > 0) {
            val intent = Intent(this, Main2Activity::class.java)
            intent.putExtra("NUMS", DSU)
            startActivity(intent)
        }
    }
    //fun test() = sumFun(1, 2, 3)
}
