package com.example.demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun countMePlus(view: View) {
        val count: Int = Integer.parseInt(textView.text.toString()) + 1
        textView.text = count.toString()
    }

    fun countMeMinus(view: View) {
        var count: Int = Integer.parseInt(textView.text.toString())
        if (Integer.parseInt(textView.text.toString()) > 0) {
            count--
        }
        textView.text = count.toString()
    }

    fun fillMe(view: View) {
        if (Integer.parseInt(textView.text.toString()) > 0) {
            val randomIntent = Intent(this, SecondActivity::class.java)
            val count = Integer.parseInt(textView.text.toString())
            randomIntent.putExtra(SecondActivity.TOTAL_COUNT, count)
            startActivity(randomIntent)
        } else {
            val myText = Toast.makeText(this, "Count must be >0 to fill", Toast.LENGTH_SHORT)
            myText.show()
        }
    }
}
