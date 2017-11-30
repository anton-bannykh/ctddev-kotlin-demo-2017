package com.example.demo

import android.content.res.Configuration
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.InputType
import android.view.View
import android.widget.EditText
import hw1.splay.splaySetOf
import kotlinx.android.synthetic.main.activity_main.*
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton

class MainActivity : AppCompatActivity() {
    val splaySet = splaySetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onSetQueryClick(view: View) {
        when (view.id) {
            button_add.id -> {
                alert {
                    title = "Insert number"

                    val input = EditText(this@MainActivity)
                    input.inputType = InputType.TYPE_CLASS_NUMBER
                    input.setRawInputType(Configuration.KEYBOARD_12KEY)

                    customView = input

                    yesButton {
                        val number = input.text.toString().toInt()
                        splaySet.add(number)
                        toast("Number $number added to set!")
                    }

                    cancelButton {
                        toast("Input cancelled")
                    }
                }.show()
            }

            button_remove.id -> {
                alert {
                    title = "Remove number"

                    val input = EditText(this@MainActivity)
                    input.inputType = InputType.TYPE_CLASS_NUMBER
                    input.setRawInputType(Configuration.KEYBOARD_12KEY)

                    customView = input

                    yesButton {
                        val number = input.text.toString().toInt()
                        if (splaySet.remove(number)) {
                            toast("Number $number remove to set!")
                        } else {
                            toast("Number $number doesn't exist in set")
                        }
                    }

                    cancelButton {
                        toast("Input cancelled")
                    }
                }.show()
            }

            button_contains.id -> {
                alert {
                    title = "Contains number"

                    val input = EditText(this@MainActivity)
                    input.inputType = InputType.TYPE_CLASS_NUMBER
                    input.setRawInputType(Configuration.KEYBOARD_12KEY)

                    customView = input

                    yesButton {
                        val number = input.text.toString().toInt()
                        if (splaySet.remove(number)) {
                            toast("Number $number exists in set!")
                        } else {
                            toast("Number $number doesn't exist in set")
                        }
                    }

                    cancelButton {
                        toast("Input cancelled")
                    }
                }.show()
            }

            else -> throw IllegalArgumentException("Cannot process click on this button")
        }
    }
}
