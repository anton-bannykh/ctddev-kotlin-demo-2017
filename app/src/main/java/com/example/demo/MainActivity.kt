package com.example.demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.widget.Button
import android.widget.TextView
import android.widget.Toast

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layout(R.layout.activity_main) {
            layout.setBackgroundColor(resources.getColor(R.color.screenBackground))
            TextV(R.id.textView) {
                textSize = 72f
                text = getString(R.string.hello_world_text)
                setTextColor(resources.getColor(R.color.text))
                linker(R.id.textView, _top, R.layout.activity_main, _top, 0)
                linker(R.id.textView, _bottom, R.layout.activity_main, _bottom, 0)
                linker(R.id.textView, _left, R.layout.activity_main, _left, 0)
                linker(R.id.textView, _right, R.layout.activity_main, _right, 0)
            }

            button(R.id.countplus_button) {
                text = getString(R.string.countPlus)
                setBackgroundColor(resources.getColor(R.color.buttonBackground))
                linker(R.id.countplus_button, _bottom, R.layout.activity_main, _bottom, 8)
                linker(R.id.countplus_button, _left, R.layout.activity_main, _left, 32)
                linker(R.id.countplus_button, _top, R.id.textView, _bottom, 8)
            }

            button(R.id.countminus_button) {
                text = getString(R.string.countMinus)
                setBackgroundColor(resources.getColor(R.color.buttonBackground))
                linker(R.id.countminus_button, _bottom, R.layout.activity_main, _bottom, 8)
                linker(R.id.countminus_button, _right, R.id.fill_button, _left, 8)
                linker(R.id.countminus_button, _left, R.id.countplus_button, _right, 8)
                linker(R.id.countminus_button, _top, R.id.textView, _bottom, 8)
            }

            button(R.id.fill_button) {
                text = getString(R.string.fill)
                setBackgroundColor(resources.getColor(R.color.buttonBackground))
                linker(R.id.fill_button, _bottom, R.layout.activity_main, _bottom, 8)
                linker(R.id.fill_button, _right, R.layout.activity_main, _right, 32)
                linker(R.id.fill_button, _top, R.id.textView, _bottom, 8)
            }
        })

        var textid = findViewById<TextView>(R.id.textView)
        var plusbuttonid = findViewById<Button>(R.id.countplus_button)
        var minusbuttonid = findViewById<Button>(R.id.countminus_button)
        var fillbuttonid = findViewById<Button>(R.id.fill_button)

        fun countMePlus() {
            val count: Int = Integer.parseInt(textid.text.toString()) + 1
            textid.text = count.toString()
        }

        fun countMeMinus() {
            var count: Int = Integer.parseInt(textid.text.toString())
            if (Integer.parseInt(textid.text.toString()) > 0) {
                count--
            }
            textid.text = count.toString()
        }

        fun fillMe() {
            if (Integer.parseInt(textid.text.toString()) > 0) {
                val randomIntent = Intent(this, SecondActivity::class.java)
                val count = Integer.parseInt(textid.text.toString())
                randomIntent.putExtra(SecondActivity.TOTAL_COUNT, count)
                startActivity(randomIntent)
            } else {
                val myText = Toast.makeText(this, "Count must be >0 to fill", Toast.LENGTH_SHORT)
                myText.show()
            }
        }

        plusbuttonid.setOnClickListener { countMePlus() }
        minusbuttonid.setOnClickListener { countMeMinus() }
        fillbuttonid.setOnClickListener { fillMe() }
    }
}
