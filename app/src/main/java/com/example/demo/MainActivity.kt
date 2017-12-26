package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.view.ViewGroup.LayoutParams.WRAP_CONTENT
import android.widget.LinearLayout
import com.example.demo.R.id.text
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.lis

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val linear = linearLayout {
            orientation = LinearLayout.VERTICAL
            setWidth(MATCH_PARENT)
            setHeight(WRAP_CONTENT)

            editText {
                id = R.id.str
                hint = getString(R.string.enter_the_sequence)
            }

            button {
                id = R.id.button
                text = getString(R.string.go)
                onClick = View.OnClickListener {
                    val arrayString = str.text.split(" ")
                    val seq = Array(arrayString.size, {i -> arrayString[i].toInt()})
                    answer.text = lis(seq.size, seq).toString()
                }
            }

            textView {
                id = R.id.answer
            }
        }
        setContentView(linear.build(this))
    }
}
