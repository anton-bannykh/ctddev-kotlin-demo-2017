package com.example.demo

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_result.*
import my.lib.algo

class ResultView : Activity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_result)

        val n = intent.getIntExtra(VALUE, 8)
        val result = getViewRes(n)

        res.text = result
    }

    private fun getViewRes(n : Int) : String {

        val a = algo(n)

        var s = ""

        for (i in 0..n - 1) {
            for (j in 0..n - 1) {
                    s += if (a.solution[i] == j) "f " else ". "
            }
            s += "\n"
        }

        return s
    }

}
