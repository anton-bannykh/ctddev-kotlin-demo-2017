package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import my.lib.sortH

class MainActivity : AppCompatActivity() {

    lateinit var txt: TextView
    fun makeText(s: String) {
        txt.text = s;
    }

    val ms = intArrayOf(1, 4, 2, 5, 6, 4, -4, -43, 313, 242, 32, 42, 2, 4, 42)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                createConstraintLayout(R.id.main_layout) {
                    textView(R.id.tv) {
                        text = (ms.contentToString())
                        margin(TOP, R.id.main_layout, TOP, 80)
                        margin(LEFT, R.id.main_layout, LEFT, 140)
                    }
                    button(R.id.btn_sort) {
                        onMyClick {
                            sortH(ms)
                            makeText(ms.contentToString())
                        }
                        text = "SORT BY HEAPSORT"
                        margin(RIGHT, R.id.main_layout, RIGHT, 8)
                        margin(TOP, R.id.main_layout, TOP, 8)
                        margin(LEFT, R.id.main_layout, RIGHT, 8)
                    }
                }
        )
        txt = findViewById<TextView>(R.id.tv)
    }
}

