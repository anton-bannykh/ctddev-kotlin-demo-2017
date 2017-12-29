package com.example.demo

import android.graphics.Paint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import com.example.demo.dsl.constraintLayout
import com.example.demo.dsl.onCLick
import my.lib.getLIS

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(R.id.layoutId) {
                    textView(R.id.description) {
                        text = "Введите массив: "
                        topConstaint(R.id.layoutId, TOP, dp(4))
                        bottomConstaint(R.id.array, TOP, dp(100))
                        center(R.id.layoutId)
                    }

                    val array = editView(R.id.array) {
                        topConstaint(R.id.description, BOTTOM, dp(100))
                        bottomConstaint(R.id.array, TOP, dp(100))
                        center(R.id.layoutId)
                    }

                    val ans = textView(R.id.answer) {
                        topConstaint(R.id.calculate, BOTTOM, dp(100))
                        bottomConstaint(R.id.layoutId, BOTTOM, dp(8))
                        center(R.id.layoutId)
                        gravity = 1
                    }

                    button(R.id.calculate) {
                        topConstaint(R.id.array, BOTTOM, dp(100))
                        bottomConstaint(R.id.answer, TOP, dp(100))
                        center(R.id.layoutId)

                        text = "CALCULATE"

                        onCLick {
                            val b : List<String> = array.text.toString().split(" ").filter { !it.isEmpty() }
                            val a = IntArray(b.size)
                            for(i in 0 until b.size) {
                                a[i] = b[i].toInt()
                            }

                            val answer = "Ответ: " + getLIS(a).toString()
                            ans.text = answer
                        }
                    }


                }
        )
    }
}
