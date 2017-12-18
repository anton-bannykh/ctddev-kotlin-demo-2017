package com.example.demo

import android.content.res.Configuration
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import hw1.splay.splaySetOf
import org.jetbrains.anko.alert
import org.jetbrains.anko.cancelButton
import org.jetbrains.anko.toast
import org.jetbrains.anko.yesButton


class MainActivity : AppCompatActivity(), View.OnClickListener {
    private val splaySet = splaySetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                linearLayout {
                    layoutParams = LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.MATCH_PARENT
                    )

                    orientation = LinearLayout.VERTICAL


                    val layoutParams =
                        LinearLayout.LayoutParams(
                            LinearLayout.LayoutParams.MATCH_PARENT,
                            LinearLayout.LayoutParams.WRAP_CONTENT,
                            1F
                        ).apply {
                            setMargins(16.toDp(), 16.toDp(), 16.toDp(), 16.toDp())
                        }

                    textView(text = resources.getText(R.string.greeting)) {
                        setTextColor(resources.getColor(R.color.colorPrimaryDark))
                        textSize = 36F
                    }

                    button(
                            id = R.id.button_add,
                            text = resources.getText(R.string.add_number),
                            onClickListener = this@MainActivity
                    )

                    button(
                            id = R.id.button_contains,
                            text = resources.getText(R.string.contains_number),
                            onClickListener = this@MainActivity
                    )

                    button(
                            id = R.id.button_remove,
                            text = resources.getText(R.string.remove_number),
                            onClickListener = this@MainActivity
                    )

                    forEachView {
                        this.layoutParams = layoutParams
                    }
                }
        )
    }

    override fun onClick(view: View) {
        when (view.id) {
            R.id.button_add -> {
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

            R.id.button_remove -> {
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

            R.id.button_contains -> {
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
