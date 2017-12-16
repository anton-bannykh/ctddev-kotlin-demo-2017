package com.example.demo

import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.kth

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        calcButton.setOnClickListener {
            val arrayText = arrayField.text.toString()
            val kText = kField.text.toString()

            val array = arrayText.split(" ").map { s -> s.toInt() }.toIntArray()
            val k = kText.toInt()

            try {
                answerField.text = kth(array, k).toString()
            } catch (ex: IllegalArgumentException) {
                val alert = AlertDialog.Builder(this@MainActivity).create()
                alert.setTitle("Error")
                alert.setMessage(ex.message)

                alert.setButton(AlertDialog.BUTTON_NEUTRAL, "Try again", {
                    dialogInterface, _ -> dialogInterface.cancel()
                })

                alert.show()
            }
        }
    }
}
