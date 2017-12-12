package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import kotlin.math.min
import my.lib.random
import my.lib.genTest
import my.lib.maxFlow

class MainActivity : AppCompatActivity() {
    var TextView.value : Int
        get() = text.toString().toInt()
        set(x) {
            text = x.toString()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        makeRandomInput()
    }

    private fun makeRandomInput() {
        val n = (2..10).random()
        numberOfVertices.value = n
        numberOfEdges.value = (1..min(50, n * (n - 1))).random()
        maxCapacity.value = (1..100).random()
    }

    fun genRandomInput(view: View) {
        makeRandomInput()
    }

    fun start(view: View) {
        val n = numberOfVertices.value
        val m = numberOfEdges.value
        val data = genTest(n, m, maxCapacity.value)
        val flows = maxFlow(n, m, data.v, data.u, data.c, 0, n - 1)
        var res = 0
        data.v.forEachIndexed { ind, ver -> if (ver == 1) res += flows[ind] }
        ans.value = res
    }
}
