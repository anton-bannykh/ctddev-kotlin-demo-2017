package com.example.demo

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.sumFun

fun depthCounter(i: Int, batya: MutableList<Int>, depth: MutableList<Int>) {
    if (depth[i] == -1) {
        if (depth[batya[i]] == -1) {
            depthCounter(batya[i], batya, depth)
        }
        depth[i] = depth[batya[i]] + 1
    }
}

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    var n: Int = 0
    var iter: Int = 0
    var batya = mutableListOf<Int>()
    var depth = mutableListOf<Int>()
    val dp = mutableListOf<MutableList<Int>>()

    fun depthCounter(i: Int) {
        if (depth[i] == -1) {
            if (depth[batya[i]] == -1) {
                depthCounter(batya[i])
            }
            depth[i] = depth[batya[i]] + 1
        }
    }

    fun prep() {
        if (batya.size == 0) {
            return
        }

        depth.add(0)
        for (i in 1..(batya.size - 1)) {
            depth.add(-1)
        }

        for (i in 1..(batya.size - 1)) {
            depthCounter(i)
        }

        for (i in 0..(batya.size - 1)) {
            dp.add(mutableListOf())
            for (j in 0..18) {
                dp[i].add(0)
            }
        }

        for (i in 0..batya.size - 1) {
            dp[i].set(0, batya[i])
        }

        for (j in 1..18) {
            for (i in 1..(batya.size - 1)) {
                dp[i][j] = dp[dp[i][j - 1]][j - 1]
            }
        }
    }

    fun lca(uIn: Int, vIn: Int): Int {
        var u = uIn
        var v = vIn

        if (depth[u] > depth[v]) {
            u = v.also { v = u }
        }

        for (i in 18 downTo 0) {
            if (depth[dp[v][i]] >= depth[u]) {
                v = dp[v][i]
            }
        }

        if (u == v) {
            return u
        }

        for (i in 18 downTo 0) {
            if (dp[u][i] != dp[v][i]) {
                u = dp[u][i]
                v = dp[v][i]
            }
        }

        return batya[u]
    }

    @SuppressLint("SetTextI18n")
    fun startEnterFun(view: View) {
        n = editTextN.text.toString().toInt()
        textViewN.visibility = View.INVISIBLE
        editTextN.visibility = View.INVISIBLE
        buttonStartEnter.visibility = View.INVISIBLE
        textViewBatya.visibility = View.VISIBLE
        editTextBatya.visibility = View.VISIBLE
        buttonBatya.visibility = View.VISIBLE
        textViewBatya.setText("Insert parent of 1 element: ")
        batya.add(0, 0)
    }

    fun setVertex(view: View) {
        batya.add(editTextBatya.text.toString().toInt())
        iter++
        if (iter < n - 2) {
            textViewBatya.setText("Insert parent of ${iter + 1} element: ")
        } else if (iter == n - 2) {
            textViewBatya.setText("Insert parent of last element: ")
        } else if (iter == n - 1) {
            textViewBatya.visibility = View.INVISIBLE
            editTextBatya.visibility = View.INVISIBLE
            buttonBatya.visibility = View.INVISIBLE
            prep()
            textViewVert1.visibility = View.VISIBLE
            textViewVert2.visibility = View.VISIBLE
            editTextVert1.visibility = View.VISIBLE
            editTextVert2.visibility = View.VISIBLE
            buttonLCA.visibility = View.VISIBLE
        }
    }

    fun lca(view: View) {
        val v1 = editTextVert1.text.toString().toInt()
        val v2 = editTextVert2.text.toString().toInt()
        textViewLCA.setText("LCA is ${lca(v1, v2)}")
        textViewLCA.visibility = View.VISIBLE
    }

    fun test() = sumFun(1, 2, 3)
}
