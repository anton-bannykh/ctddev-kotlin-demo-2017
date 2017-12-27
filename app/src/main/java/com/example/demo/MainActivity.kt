package com.example.demo

import DSL.constraintLayout
import DSL.onCLick
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import my.lib.BinarySearch

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        setContentView(

                constraintLayout(R.id.layoutId) {

                    textView(R.id.textView5) {

                        topMargin(R.id.layoutId, TOP, dp(15))

                        hint = "Добавить элемент массива"

                        leftMargin(R.id.layoutId, LEFT, dp(8))

                        rightMargin(R.id.layoutId, LEFT, dp(8))

                    }

                    editText(R.id.editText) {

                        width = dp(150)

                        topMargin(R.id.textView5, TOP, dp(43))

                        leftMargin(R.id.layoutId, LEFT, dp(8))

//                        rightMargin(R.id.layoutId, LEFT, dp(8))

                    }

                    button(R.id.button) {

                        width = dp(150)

                        topMargin(R.id.textView5, BOTTOM, dp(0))

                        rightMargin(R.id.layoutId, RIGHT, dp(8))

                        // rightMargin(R.id.layoutId, LEFT, dp(8))

                        text = "ДОБАВИТЬ!"

                        onCLick {

                            push()

                        }

                    }

                    textView(R.id.textView) {

                        topMargin(R.id.editText, BOTTOM, dp(25))

                        width = dp(190)

                        leftMargin(R.id.layoutId, LEFT, dp(8))

//                        rightMargin(R.id.layoutId, LEFT, dp(8))

                        text = "Массив:"

                    }

                    textView(R.id.textView2) {

                        //                        width = dp(200)
                        height = dp(170)

                        topMargin(R.id.textView, BOTTOM, dp(8))

                        leftMargin(R.id.layoutId, LEFT, dp(8))

                        rightMargin(R.id.layoutId, LEFT, dp(8))

                    }

                    textView(R.id.textView3) {

                        //                        width = dp(280)
                        height = dp(50)

                        topMargin(R.id.textView2, BOTTOM, dp(8))

                        hint = "Индекс какого числа надо найти?"

                        leftMargin(R.id.layoutId, LEFT, dp(8))

                        //rightMargin(R.id.layoutId, LEFT, dp(8))

                    }

                    editText(R.id.editText2) {

                        width = dp(150)

                        topMargin(R.id.textView3, TOP, dp(70))

                        leftMargin(R.id.layoutId, LEFT, dp(8))

                    }

                    button(R.id.button2) {

                        width = dp(150)

//                        height = dp(80)

                        topMargin(R.id.textView3, BOTTOM, dp(18))

                        rightMargin(R.id.layoutId, RIGHT, dp(8))

                        text = "НАЙТИ!"

                        onCLick {

                            push2()

                        }

                    }

                    button(R.id.button3) {

                        width = dp(150)

//                        height = dp(80)

                        topMargin(R.id.button2, BOTTOM, dp(8))

                        rightMargin(R.id.layoutId, RIGHT, dp(8))

                        text = "ОЧИСТИТЬ"

                        onCLick {

                            push3()

                        }

                    }

                    textView(R.id.textView4) {

                        //width = dp(280)

                        topMargin(R.id.editText2, BOTTOM, dp(24))

                        leftMargin(R.id.layoutId, LEFT, dp(8))

                        rightMargin(R.id.button3, LEFT, dp(8))

                    }
                }
        )
    }

    var clean = true
    val arr = ArrayList<Int>()
    fun push() {
        if (clean) {
            val x = Integer.parseInt(editText.text.toString())
            if (!arr.isEmpty() && arr.last() > x) {
                Toast.makeText(this, "Введите x побольше", Toast.LENGTH_SHORT).show()
                editText.setText("")
            } else {
                editText.setText("")
                arr.add(x)
                textView2.text = textView2.text.toString().plus(" $x")
            }
        }
    }

    fun push2() {
        if (arr.isEmpty()) {
            Toast.makeText(this, "Сначала введите массив", Toast.LENGTH_SHORT).show()
        } else {
            val x = Integer.parseInt(editText2.text.toString())
            val ans = BinarySearch(arr.toIntArray(), x)
            textView4.text = "$ans"
        }
        clean = false
    }

    fun push3() {
        clean = true
        textView4.text = ""
        textView2.text = ""
        editText2.setText("")
        arr.clear()
    }
}

