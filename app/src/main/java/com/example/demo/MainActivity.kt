package com.example.demo

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.widget.TextView
import ru.walingar.kmp.searchSubstring
import dsl.layout.ConstraintLayout

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
                ConstraintLayout(R.id.layoutId) {
                    TextView(R.id.substringTextView) {
                        height = dp(24)
                        width = dp(73)
                        textSize = textSizeValue
                        text = getText(R.string.substring)
                        leftMargin(R.id.layoutId, ConstraintSet.START, dp(8))
                        topMargin(R.id.layoutId, ConstraintSet.TOP, dp(37))
                    }

                    val substringEditText = EditText(R.id.substringEditText) {
                        width = dp(368)
                        height = dp(67)
                        textSize = textSizeValue
                        hint = getText(R.string.input_substring)
                        leftMargin(R.id.layoutId, ConstraintSet.START, dp(8))
                        topMargin(R.id.substringTextView, ConstraintSet.BOTTOM, dp(2))
                    }

                    TextView(R.id.stringTextView) {
                        width = dp(86)
                        height = dp(24)
                        textSize = textSizeValue
                        text = getText(R.string.main_string)
                        leftMargin(R.id.layoutId, ConstraintSet.START, dp(8))
                        topMargin(R.id.substringEditText, ConstraintSet.BOTTOM, dp(10 + 24 + 48))
                    }

                    val stringEditText = EditText(R.id.stringEditText) {
                        width = dp(368)
                        height = dp(152)
                        textSize = textSizeValue
                        hint = getText(R.string.input_string)
                        leftMargin(R.id.layoutId, ConstraintSet.START, dp(8))
                        topMargin(R.id.stringTextView, ConstraintSet.BOTTOM, dp(2))
                    }

                    val answerTextView = TextView(R.id.answerTextView) {
                        width = dp(368)
                        height = dp(80)
                        textSize = textSizeValue
                        text = getText(R.string.first_occurrence)
                        leftMargin(R.id.layoutId, ConstraintSet.START, dp(8))
                        topMargin(R.id.stringEditText, ConstraintSet.BOTTOM, dp(15))
                    }

                    Button(R.id.findSubstringButton) {
                        width = dp(135)
                        height = dp(48)
                        textSize = textSizeValue
                        text = getText(R.string.find_substring)
                        setOnClickListener({
                            if (stringEditText.isEmpty || substringEditText.isEmpty) {
                                answerTextView.text = "One of the strings is empty"
                            } else {
                                val answer = searchSubstring(
                                        stringEditText.stringValue,
                                        substringEditText.stringValue
                                )
                                when (answer) {
                                    -1 -> answerTextView.text = "Substring is not contained in the string"
                                    else -> answerTextView.text = "First occurrence is " + answer.toString()
                                }
                            }
                        })
                        leftMargin(R.id.layoutId, ConstraintSet.START, dp(126))
                        topMargin(R.id.substringEditText, ConstraintSet.BOTTOM, dp(24))
                    }

                }
        )
    }

    private val textSizeValue = 14f

    private val TextView.stringValue
        get() = text.toString()

    private val TextView.isEmpty
        get() = this.stringValue == ""
}
