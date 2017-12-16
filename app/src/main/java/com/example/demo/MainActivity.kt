package com.example.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addTree(v: View) {
        val s = Elem.text.toString()
        val nextIntent = Intent(this, FuncActivity::class.java)
        nextIntent.putExtra(FuncActivity.ARRAY, s)
        Elem.setText("")
        startActivity(nextIntent)
    }

    fun Close(v: View) {
        finish()
    }
}
