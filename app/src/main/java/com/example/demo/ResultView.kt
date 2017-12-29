package com.example.demo

import my.lib.algo
import android.os.Bundle
import android.support.v7.app.AppCompatActivity

class ResultView : AppCompatActivity() {

    override fun onCreate(savedInstanceState : Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(constraintLayout(R.id.resLay) {

            textView(R.id.res) {
                val n = intent.getIntExtra(VALUE, 8)
                val result = getViewRes(n)

                text = result

                marginLeft(R.id.resLay, LEFT, 1)
                marginRight(R.id.resLay, RIGHT, 1)
                marginTop(R.id.resLay, TOP, 1)
                marginBottom(R.id.resLay, BOTTOM, 1)
            }

        })
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