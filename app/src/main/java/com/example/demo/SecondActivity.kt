package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_second.*
import my.lib.RMQ

class SecondActivity : AppCompatActivity() {

    var rmq1 = RMQ()

    companion object {
        const val TOTAL_COUNT = "total_count"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        fillMeImpl()
    }

    fun fillMeImpl() {
        val count = intent.getIntExtra(TOTAL_COUNT, 0)
        val a = Array<Int>(count, { (21 * Math.random()).toInt() - 10 })
        rmq1.create_segment_tree(count, a)
        showMe()
    }

    fun setMe(view: View) {
        val count = intent.getIntExtra(TOTAL_COUNT, 0)
        val pos = (count * Math.random()).toInt() + 1
        val num = (21 * Math.random()).toInt() - 10
        rmq1.set(pos, num)
        showMe()
        val myText = Toast.makeText(this, "Successfully put num " + num.toString() + " to " + pos.toString() + " position", Toast.LENGTH_SHORT)
        myText.show()
    }

    fun minMe(view: View) {
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

    fun showMe() {
        var s = ""
        for (i in rmq1.y..rmq1.y + rmq1.n - 1) {
            s += rmq1.a[i].toString() + ' '
        }
        textView.text = getString(R.string.array, s)
    }
}
