package com.example.demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText


class MainActivity : AppCompatActivity() {
    lateinit var btn: Button
    lateinit var txt: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        btn = findViewById(R.id.button)
        txt = findViewById(R.id.editText2)


    }

    fun next(v: View) {
        val DSU = txt.text.toString().toInt()
        if (DSU > 0) {
            val intent = Intent(this, Main2Activity::class.java)
            intent.putExtra("NUMS", DSU)
            startActivity(intent)
        }
    }
    //fun test() = sumFun(1, 2, 3)
}
