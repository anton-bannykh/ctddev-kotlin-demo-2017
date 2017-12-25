package com.example.dima.hw2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
//import dsl.constraintLayoutCreate
import dsl.constraintLayoutCreate
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main)
        setContentView(
                constraintLayoutCreate(R.id.layoutId) {
                    val view1 = textView(R.id.textView, conv_dp(300), conv_dp(100), "Number of Vertexes", 21) {
                        tMarg(R.id.layoutId, TOP, conv_dp(12))
                        lMarg(R.id.layoutId, LEFT, conv_dp(75))
                        rMarg(R.id.layoutId, RIGHT, conv_dp(75))
                        textAlignment = View.TEXT_ALIGNMENT_CENTER
                    }
                    val edT1 = editText(R.id.vert_num, conv_dp(100), conv_dp(100), "") {
                        tMarg(R.id.textView, TOP, conv_dp(100))
                        lMarg(R.id.layoutId, LEFT, conv_dp(75))
                        rMarg(R.id.layoutId, RIGHT, conv_dp(75))
                    }
                    val btn1 = button(R.id.init_btn, conv_dp(300), conv_dp(300), "create", 28) {
                        tMarg(R.id.vert_num, TOP, conv_dp(200))
                        lMarg(R.id.layoutId, LEFT, conv_dp(75))
                        rMarg(R.id.layoutId, RIGHT, conv_dp(75))
                        setOnClick { initialized() }
                    }
                }
        )
    }

    fun initialized() {
        if (vert_num.text.toString().isNotEmpty()) {
            val number = Integer.parseInt(vert_num.text.toString())
            vert_num.setText("")
            if (number > 1) {
                intent = Intent(this, Main2Activity::class.java)
                intent.putExtra("number", number)
                startActivity(intent)
            }
        }
    }
}