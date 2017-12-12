package com.example.demo

import android.app.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import math.stringToIntArray
import my.lib.segmenttree.RSQ

/**
 * Created by ivan on 12/11/17.
 */
class StartTestingActivity: Activity() {
    var rsq = RSQ(Array<Int>(1, {i -> 0}))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_start_testing)
    }


    fun buildTree(view: View) {
        val et = findViewById<EditText>(R.id.array);
        val a: Array<Int> = stringToIntArray( et.getText().toString() )
        for (x in a) {
            println(x)
        }
        rsq = RSQ(a)
        //rsq.look()
        val button = findViewById<Button>(R.id.buildtree);
        button.setBackgroundColor(Color.GREEN)
    }

    fun add(view: View) {
        val et = findViewById<EditText>(R.id.add);
        val q: Array<Int> = stringToIntArray( et.getText().toString() )
        for (x in q) {
            println(x)
        }
        rsq.add(q[0], q[1], q[2])
        //rsq.look()
    }

    fun get(view: View) {
        val et = findViewById<EditText>(R.id.get)
        val q:  Array<Int> = stringToIntArray( et.getText().toString() )
        val tv = findViewById<TextView>(R.id.answer)
        tv.text = "The answer is ${rsq.findAnswer(q[0], q[1]).toString()}"
    }
}