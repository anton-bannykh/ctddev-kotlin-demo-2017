package com.example.demo

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.ViewGroup
import quickSort

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
                createDemoConstraintLayout(R.id.layoutId) {
                    val inputEditText = editText(R.id.inputEditText) {
                        width = ViewGroup.LayoutParams.WRAP_CONTENT
                        height = ViewGroup.LayoutParams.WRAP_CONTENT
                        setEms(10)
                        hint = resources.getText(R.string.input)
                        inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                        addConstraint(ConstraintSet.BOTTOM, R.id.layoutId, ConstraintSet.BOTTOM)
                        addConstraint(ConstraintSet.END, R.id.layoutId, ConstraintSet.END)
                        addConstraint(ConstraintSet.START, R.id.layoutId, ConstraintSet.START)
                        addConstraint(ConstraintSet.TOP, R.id.layoutId, ConstraintSet.TOP)
                        setVerticalBias(0.3f)
                    }

                    val outputEditText = editText(R.id.outputEditText) {
                        width = ViewGroup.LayoutParams.WRAP_CONTENT
                        height = ViewGroup.LayoutParams.WRAP_CONTENT
                        setEms(10)
                        hint = resources.getText(R.string.output)
                        inputType = InputType.TYPE_TEXT_VARIATION_PERSON_NAME
                        addConstraint(ConstraintSet.END, R.id.layoutId, ConstraintSet.END)
                        addConstraint(ConstraintSet.START, R.id.layoutId, ConstraintSet.START)
                        addConstraint(ConstraintSet.TOP, R.id.sortButton, ConstraintSet.BOTTOM, dp(16))
                    }

                    button(R.id.sortButton) {
                        width = ViewGroup.LayoutParams.WRAP_CONTENT
                        height = ViewGroup.LayoutParams.WRAP_CONTENT
                        text = resources.getText(R.string.sort)
                        addConstraint(ConstraintSet.END, R.id.layoutId, ConstraintSet.END)
                        addConstraint(ConstraintSet.START, R.id.layoutId, ConstraintSet.START)
                        addConstraint(ConstraintSet.TOP, R.id.inputEditText, ConstraintSet.BOTTOM, dp(16))
                        setOnClickListener {
                            val array = ArrayList(inputEditText.text.split(" ").map { it.toInt() })
                            quickSort(array)
                            outputEditText.setText(array.joinToString(" ") { it.toString() })
                        }
                    }

                    button(R.id.copyButton) {
                        width = ViewGroup.LayoutParams.WRAP_CONTENT
                        height = ViewGroup.LayoutParams.WRAP_CONTENT
                        text = resources.getText(R.string.copy)
                        addConstraint(ConstraintSet.END, R.id.layoutId, ConstraintSet.END)
                        addConstraint(ConstraintSet.START, R.id.layoutId, ConstraintSet.START)
                        addConstraint(ConstraintSet.TOP, R.id.outputEditText, ConstraintSet.BOTTOM, dp(16))
                        setOnClickListener {
                            val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
                            val clip = ClipData.newPlainText("sorted data", outputEditText.text)
                            clipboard.primaryClip = clip
                        }
                    }
                }
        )
    }
}
