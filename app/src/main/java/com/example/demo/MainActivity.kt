package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import android.view.View.OnClickListener
import android.widget.EditText
import my.lib.solve


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val editArray = findViewById<EditText>(R.id.array)
        val left = findViewById<EditText>(R.id.left)
        val right = findViewById<EditText>(R.id.right)
        val outAns = findViewById<TextView>(R.id.out)
        val button = findViewById<Button>(R.id.button)

        val onClickBut = OnClickListener {
            val str = editArray.text.toString() + ','

            val arr = ArrayList<Int>()
            var flag = false
            var i = 0
            while (i != str.length) {
                if (str[i] == ',') {
                    i++
                    continue
                }
                if (str[i] == '-') {
                    flag = true
                    i++
                    continue
                }
                var j = i
                while (str[j].isDigit()) {
                    j++
                }
                val num : Int = str.substring(i, j).toInt()
                i = j
                if (flag) {
                    arr.add(-num)
                    flag = false
                } else {
                    arr.add(num)
                }
            }
            val ans = solve(arr.size, arr.toIntArray(), left.text.toString()[0].toInt() - 48, right.text.toString()[0].toInt() - 48)
            outAns.text = "Ответ = " + ans.toString()
        }
        button.setOnClickListener(onClickBut)

    }


}
