package com.example.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle

val VALUE = "demo.example.com.value"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(constraintLayout(R.id.globalLay) {

            textView(R.id.text) {
                marginLeft(R.id.globalLay, LEFT, 1)
                marginRight(R.id.globalLay, RIGHT, 1)
                marginTop(R.id.globalLay, TOP, 1)
                text = "Число ферзей"
            }

            val value = textView(R.id.number) {
                marginLeft(R.id.globalLay, LEFT, 1)
                marginRight(R.id.globalLay, RIGHT, 1)
                marginTop(R.id.text, BOTTOM, 50)
                text = "5"
            }

            button(R.id.minus) {
                text = "-"
                marginRight(R.id.globalLay, RIGHT, 1)
                marginLeft(R.id.plus, RIGHT, 1)
                marginTop(R.id.number, BOTTOM, 50)
                onCLick {
                    var a = value.text.toString().toInt()
                    a--
                    if (a < 5)
                        a += 2
                    value.text = a.toString()
                }
            }

            button(R.id.plus) {
                text = "+"
                marginLeft(R.id.globalLay, LEFT, 1)
                marginRight(R.id.minus, LEFT, 1)
                marginTop(R.id.number, BOTTOM, 50)
                onCLick {
                    var a = value.text.toString().toInt()
                    a++
                    value.text = a.toString()
                }
            }

            button(R.id.but) {
                marginLeft(R.id.globalLay, LEFT, 1)
                marginRight(R.id.globalLay, RIGHT, 1)
                marginTop(R.id.plus, BOTTOM, 1)
                text = "Старт"
                onCLick {
                    intent = Intent(this@MainActivity, ResultView::class.java)
                    intent.putExtra(VALUE, value.text.toString().toInt())
                    startActivity(intent)
                }
            }
        })
    }

}
