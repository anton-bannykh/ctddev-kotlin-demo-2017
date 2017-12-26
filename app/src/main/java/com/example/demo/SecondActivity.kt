package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import my.lib.RMQ

class SecondActivity : AppCompatActivity() {

    var rmq1 = RMQ()

    companion object {
        const val TOTAL_COUNT = "total_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(layout(R.layout.activity_second) {
            layout.setBackgroundColor(resources.getColor(R.color.screenBackground2))
            TextV(R.id.textView) {
                textSize = 36f
                text = getString(R.string.array)
                setTextColor(resources.getColor(R.color.text2))
                linker(R.id.textView, _top, R.layout.activity_second, _top, 0)
                linker(R.id.textView, _bottom, R.layout.activity_second, _bottom, 0)
                linker(R.id.textView, _left, R.layout.activity_second, _left, 0)
                linker(R.id.textView, _right, R.layout.activity_second, _right, 0)
            }

            button(R.id.min_button) {
                text = getString(R.string.min)
                setBackgroundColor(resources.getColor(R.color.buttonBackground2))
                linker(R.id.min_button, _bottom, R.layout.activity_second, _bottom, 8)
                linker(R.id.min_button, _left, R.layout.activity_second, _left, 96)
                linker(R.id.min_button, _top, R.id.textView, _bottom, 8)
            }

            button(R.id.set_button) {
                text = getString(R.string.set)
                setBackgroundColor(resources.getColor(R.color.buttonBackground2))
                linker(R.id.set_button, _bottom, R.layout.activity_second, _bottom, 8)
                linker(R.id.set_button, _right, R.layout.activity_second, _right, 96)
                linker(R.id.set_button, _top, R.id.textView, _bottom, 8)
            }
        })

        var textid2 = findViewById<TextView>(R.id.textView)
        var minbuttonid = findViewById<Button>(R.id.min_button)
        var setbuttonid = findViewById<Button>(R.id.set_button)

        fun showMe() {
            var s = ""
            for (i in rmq1.y..rmq1.y + rmq1.n - 1) {
                s += rmq1.a[i].toString() + ' '
            }
            textid2.text = getString(R.string.array, s)
        }

        fun fillMeImpl() {
            val count = intent.getIntExtra(TOTAL_COUNT, 0)
            val a = Array<Int>(count, { (21 * Math.random()).toInt() - 10 })
            rmq1.create_segment_tree(count, a)
            showMe()
        }

        fun setMe() {
            val count = intent.getIntExtra(TOTAL_COUNT, 0)
            val pos = (count * Math.random()).toInt() + 1
            val num = (21 * Math.random()).toInt() - 10
            rmq1.set(pos, num)
            showMe()
            val myText = Toast.makeText(this, "Successfully put num " + num.toString() + " to " + pos.toString() + " position", Toast.LENGTH_SHORT)
            myText.show()
        }

        fun minMe() {
            val count = intent.getIntExtra(TOTAL_COUNT, 0)
            var pos1 = (count * Math.random()).toInt() + 1
            var pos2 = (count * Math.random()).toInt() + 1
            if (pos1 > pos2) {
                val x = pos1
                pos1 = pos2
                pos2 = x
            }
            val ans = rmq1.minstart(pos1, pos2)
            val myText = Toast.makeText(this, "Min on segment [" + pos1.toString() + "," + pos2.toString() + "] is " + ans.toString(), Toast.LENGTH_SHORT)
            myText.show()
        }

        minbuttonid.setOnClickListener { minMe() }
        setbuttonid.setOnClickListener { setMe() }

        fillMeImpl()
    }
}