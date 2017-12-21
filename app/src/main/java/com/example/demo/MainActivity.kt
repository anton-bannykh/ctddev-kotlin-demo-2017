package com.example.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import My_DSl.makeLayout
import android.view.ViewGroup

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                makeLayout(R.id.Layout) {
                    textView(R.id.WelcomeHeader) {
                        text = getString(R.string.Preview)
                        textSize = 24f
                        setTextColor(resources.getColor(R.color.abc_tint_spinner))
                        topMargin(R.id.Layout, TOP, toDP(16))
                        center(R.id.Layout)
                        width = toDP(297)
                        height = toDP(105)
                    }

                    editText(R.id.Elem) {
                        hint = getString(R.string.Addd)
                        topMargin(R.id.WelcomeHeader, BOTTOM, toDP(60))
                        center(R.id.Layout)
                        width = toDP(362)
                        height = toDP(67)
                    }

                    button(R.id.AddTree) {
                        text = getString(R.string.AddTr)
                        topMargin(R.id.Elem, BOTTOM, toDP(60))
                        center(R.id.Layout)
                        width = ViewGroup.LayoutParams.WRAP_CONTENT
                        height = ViewGroup.LayoutParams.WRAP_CONTENT
                        onClick {
                            addTree()
                        }
                    }

                    button(R.id.button) {
                        text = "Close"
                        center(R.id.Layout)
                        topMargin(R.id.AddTree, BOTTOM, 42)
                        bottomMargin(R.id.Layout, BOTTOM, 8)
                        width = ViewGroup.LayoutParams.WRAP_CONTENT
                        height = ViewGroup.LayoutParams.WRAP_CONTENT
                        onClick {
                            Close()
                        }
                    }
                }
        )
    }

    fun addTree() {
        val s = Elem.text.toString()
        val nextIntent = Intent(this, FuncActivity::class.java)
        nextIntent.putExtra(FuncActivity.ARRAY, s)
        Elem.setText("")
        startActivity(nextIntent)
    }

    fun Close() {
        finish()
    }
}
