package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(constraintLayout(R.id.globalLay) {

            textView(R.id.text) {

                text = "Вы дебилы"

            }

            button(R.id.but) {
                text = "Нажать!"
                onCLick {
                    text = "ЕЩЕ!!!"
                }
            }

        })
    }
}
