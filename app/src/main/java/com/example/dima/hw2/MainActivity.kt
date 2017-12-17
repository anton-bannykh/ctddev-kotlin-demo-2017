package com.example.dima.hw2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        init_btn.setOnClickListener {
            if(vert_num.text.toString().isNotEmpty()) {
                val number = Integer.parseInt(vert_num.text.toString())
                vert_num.setText("")
                if(number > 1) {
                    intent = Intent(this, Main2Activity::class.java)
                    intent.putExtra("number", number)
                    startActivity(intent)
                }
            }
        }
    }
}
