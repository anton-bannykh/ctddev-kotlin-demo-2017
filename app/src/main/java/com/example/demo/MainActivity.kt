package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.lcs

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
                val linear = linearLayout {
            orientation = LinearLayout.VERTICAL
            setWidth(MATCH_PARENT)
            setHeight(WRAP_CONTENT)

            editText {
                id = R.id.first
                hint = getString(R.string.enter_the_first_sequence)
            }

            editText {
                id = R.id.second
                hint = getString(R.string.enter_the_second_sequence)
            }

            button {
                id = R.id.button
                text = getString(R.string.go)
                onClick = View.OnClickListener {
                    answer.text = lcs(first.text.toString().toList(), second.text.toString().toList()).toString()

                }
            }

            textView {
                id = R.id.answer
            }
        }
        setContentView(linear.build(this))

    }


}
