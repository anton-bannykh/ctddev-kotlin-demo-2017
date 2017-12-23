package com.example.demo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import kotlin.math.min
import dsl.constraintLayout
import dsl.onCLick
import my.lib.nextPerm

class MainActivity : AppCompatActivity() {
    var TextView.value: MutableList<Int>
        get() {
            var a = mutableListOf<Int>()
            val parts = (text.toString()).split("\u0020")
            for (j in parts.indices) {
                if (parts[j].compareTo("") > 0) {
                    a.add(Integer.parseInt(parts[j]))
                }
            }
            return a
        }
        set(x) {
            text = x.toString()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(R.id.layoutId) {
                    textView(R.id.title) {
                        text = "Введите целые числа через пробел"
                        topMargin(R.id.layoutId, TOP, dp(52))
                    }

                    val perm = editText(R.id.inputPerm) {
                        topMargin(R.id.title, TOP, dp(52))
                    }

                    val ans = editText(R.id.outputPerm) {
                        topMargin(R.id.startButton, BOTTOM, dp(52))
                    }

                    button(R.id.startButton) {
                        topMargin(R.id.inputPerm, BOTTOM, dp(52))
                        text = "Старт"

                        onCLick {
                            val a = perm.value
                            ans.value = nextPerm(a)
                        }
                    }
                }
        )
    }
}