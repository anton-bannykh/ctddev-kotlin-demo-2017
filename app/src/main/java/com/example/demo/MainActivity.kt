package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import my.lib.suffixArray
import java.util.Arrays

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val lay = linearLayout {
            orientation = LinearLayout.VERTICAL
            setPadding(dp(10), dp(10), dp(10), dp(10))

            val edit = editText {
                hint = getString(R.string.enter_hint)
                layoutParams {
                    width = MATCH_PARENT
                    height = WRAP_CONTENT
                }
            }

            val tv1 = textView {
                layoutParams {
                    width = MATCH_PARENT
                    height = WRAP_CONTENT
                }
            }

            val tv2 = textView {
                maxLines = 12
                isVerticalScrollBarEnabled = true
                movementMethod = ScrollingMovementMethod()
                layoutParams {
                    width = MATCH_PARENT
                    height = WRAP_CONTENT
                }
            }

            button {
                text = getString(R.string.button_text)
                layoutParams {
                    width = MATCH_PARENT
                    height = WRAP_CONTENT
                }
                setOnClickListener {
                    try {
                        val str: String = edit.text.toString()
                        val temp = suffixArray(str.toLowerCase())
                        tv1.text = Arrays.toString(temp)
                        val s = StringBuilder()
                        for (i in temp) s.append(str.subSequence(i, str.length)).append("\n")
                        tv2.text = s
                    } catch (ex: Exception) {
                        tv1.text = getString(R.string.invalid_data)
                        tv2.text = ""
                    }
                }
            }
        }

        setContentView(lay)
    }

}
