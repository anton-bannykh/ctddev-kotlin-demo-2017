package com.example.demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText

class MainActivity : AppCompatActivity() {

    lateinit var text: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        text = findViewById<EditText>(R.id.input)
    }

    fun Go(v: View) {
        val intent = Intent(this, Main2Activity::class.java)
        val numberArr = text.getText().toString().split(" ")
        val num: ArrayList<Int> = ArrayList()
        for (i in numberArr) {
            if (!i.isEmpty() ) {
                num.add(i.trim().toInt())
            }
        }
        intent.putExtra("NUM_ARR", num)
        startActivity(intent)
    }

}
