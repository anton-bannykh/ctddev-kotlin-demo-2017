package com.example.demo

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import com.example.demo.builder.*
import hw1.splay.splaySetOf

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val splaySet = splaySetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                linearLayout {
                    layoutParams = linearLayoutParams {
                        width = MATCH_PARENT
                        height = MATCH_PARENT
                    }

                    orientation = VERTICAL

                    textView {
                        text = extractString(R.string.greeting)
                        textColor = extractColor(R.color.colorPrimaryDark)
                        textSize = 36F
                    }

                    button {
                        id = R.id.button_add
                        text = extractString(R.string.add_number)
                        onClick= this@MainActivity
                    }

                    button {
                        id = R.id.button_contains
                        text = extractString(R.string.contains_number)
                        onClick = this@MainActivity
                    }

                    button {
                        id = R.id.button_remove
                        text = extractString(R.string.remove_number)
                        onClick = this@MainActivity
                    }

                    childrenLayout = linearLayoutParams {
                        width = MATCH_PARENT
                        height = WRAP_CONTENT
                        weight = 1F

                        margin = 16
                    }
                }
        )
    }

    override fun onClick(view: View) {
        val input = editText {
            inputType = InputType.TYPE_CLASS_NUMBER
            setRawInputType(Configuration.KEYBOARD_12KEY)
        }

        when (view.id) {
            R.id.button_add -> {
                alert {
                    title = "Insert number"
                    customView = input

                    applyButton {
                        val number = input.text.toString().toInt()
                        splaySet.add(number)
                        toast("Number $number added to set!")
                    }

                    cancelButton {
                        toast("Input cancelled")
                    }
                }.show()
            }

            R.id.button_remove -> {
                alert {
                    title = "Remove number"
                    customView = input

                    applyButton {
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

            R.id.button_contains -> {
                alert {
                    title = "Contains number"
                    customView = input

                    applyButton {
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
