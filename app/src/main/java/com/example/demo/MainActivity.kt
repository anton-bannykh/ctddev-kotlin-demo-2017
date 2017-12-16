package com.example.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

val VALUE = "demo.example.com.value"

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        up.setOnClickListener(View.OnClickListener {
            var a = value.text.toString().toInt()
            a++
            value.text = a.toString()
        })

        down.setOnClickListener(View.OnClickListener {
            var a = value.text.toString().toInt()
            a--
            if (a < 5)
                a += 2 // не баг, а фича))))))
            value.text = a.toString()
        })

        start.setOnClickListener(View.OnClickListener {
            intent = Intent(this@MainActivity, ResultView::class.java)
            intent.putExtra(VALUE, value.text.toString().toInt())
            startActivity(intent)
        })
    }
}
