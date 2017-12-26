package com.example.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.EditText
import android.widget.RelativeLayout
import android.widget.TextView
import com.example.demo.UI.*
import my.lib.huffman.HuffmanEncode

class MainActivity : AppCompatActivity() {

    private lateinit var mEditText: EditText
    private lateinit var mCodeText: TextView

    private val encoder = HuffmanEncode()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                relativeLayout {
                    initRelativeLayoutParams {
                        width = MATCH_PARENT
                        height = MATCH_PARENT

                        setMargins(15, 15, 15, 15)
                    }

                    scrollView {
                        initRelativeLayoutParams {
                            width = MATCH_PARENT
                            height = WRAP_CONTENT

                            gravity = Gravity.BOTTOM or Gravity.CENTER

                            addRule(RelativeLayout.ALIGN_PARENT_TOP)
                            addRule(RelativeLayout.ABOVE, R.id.encode_string_layout)
                        }

                        mCodeText = textView(R.id.tv_code) {
                            initRelativeLayoutParams {
                                width = MATCH_PARENT
                                height = WRAP_CONTENT
                            }

                            textSize = 25F
                        }
                    }

                    linearLayout(R.id.encode_string_layout) {
                        initRelativeLayoutParams {
                            width = MATCH_PARENT
                            height = WRAP_CONTENT
                            addRule(RelativeLayout.ALIGN_PARENT_BOTTOM)
                        }

                        mEditText = editText(R.id.et_encode_text) {
                            initLinearLayoutParams {
                                width = WRAP_CONTENT
                                height = MATCH_PARENT
                                weight = 9F
                            }

                            hint = getString(R.string.et_hint)
                            textSize = 15F
                        }

                        button(R.id.button_encode) {
                            initLinearLayoutParams {
                                width = WRAP_CONTENT
                                height = MATCH_PARENT
                                weight = 1F
                            }

                            text = getString(R.string.encode_command)
                            textSize = 12F

                            this.setOnClickListener({
                                val stringToEncode = mEditText.text.toString()

                                if (stringToEncode != "") {
                                    mCodeText.text = buildString {
                                        append(getString(R.string.input_string_text))
                                        append(" '$stringToEncode'\n")

                                        append(getString(R.string.code_text))
                                        append(" '${encoder.huffmanEncode(stringToEncode)}'")
                                        append("\n\n\n")

                                        append(getString(R.string.digits_info))
                                        append(encoder.digitsCode)
                                    }
                                } else {
                                    mCodeText.text = getString(R.string.input_string_text)
                                }
                            })
                        }
                    }
                }
        )
    }
}