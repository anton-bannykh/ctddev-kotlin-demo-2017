package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*
import android.R.attr.start
import android.view.animation.LinearInterpolator
import android.animation.ObjectAnimator
import android.annotation.SuppressLint
import android.content.Context
import kotlinx.android.synthetic.main.activity_main.*
import android.content.Context.LAYOUT_INFLATER_SERVICE
import android.content.Intent
import android.support.v7.widget.SwitchCompat
import android.view.LayoutInflater
import android.view.View
import android.widget.*
import kotlinx.android.synthetic.main.activity_main.view.*
import android.widget.TextView
import com.example.demo.R

class Main2Activity : AppCompatActivity() {

    companion object {
        const val TOTAL_COUNT = "total_count"
    }
    var g: Array<ArrayList<Int>> = Array(0, { ArrayList<Int>()})
    var status50 = false

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 0, 50)
        progressAnimator.duration = 700
        progressAnimator.start()

        val numVertexex = intent.getIntExtra(TOTAL_COUNT, 0)
        g = Array(numVertexex, { ArrayList<Int>() })

        textView2.text = ("2. Выберите какие ребра есть в этом графе из " + numVertexex.toString() + " вершин")

        for (j in 1..numVertexex) {
            for (i in 1..(j-1) ) {
                if (i==j) continue

                val tv = Switch(this)
                tv.setPadding(0,0,70,0)
                tv.text = "  " + (i.toString() + "  --->  " + j.toString())

                tv.setOnClickListener {

                    if (status50) {
                        val progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 100, 50)
                        progressAnimator.duration = 700
                        progressAnimator.start()
                        status50 = false
                    }

                    if ( !((j-1) in g[i-1])) {
                        g[i-1].add(j-1)
                        g[j-1].add(i-1)

                    } else {
                        g[i-1].remove(j-1)
                        g[j-1].remove(i-1)

                    }
                }
                Lin.addView(tv)
            }
        }
    }

    fun findBridjes(view: View) {

        val progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 50, 100)
        progressAnimator.duration = 700
        progressAnimator.start()

        val numberOfVertexex = intent.getIntExtra(TOTAL_COUNT, 0)
        val used: BooleanArray = kotlin.BooleanArray(numberOfVertexex, { false })
        var timer: Int = 0
        val tin: IntArray = IntArray(numberOfVertexex)
        val fup: IntArray = IntArray(numberOfVertexex)
        val ans: ArrayList<Pair<Int, Int>> = ArrayList()


        fun dfs(vertex: Int, p: Int = -1) {
            used[vertex] = true
            fup[vertex] = timer++
            tin[vertex] = fup[vertex]
            for (i in g[vertex].indices) {
                val to = g[vertex][i]
                if (to == p) continue
                if (used[to]) {
                    fup[vertex] = minOf(fup[vertex], tin[to])
                } else {
                    dfs(to, vertex)
                    fup[vertex] = minOf(fup[vertex], fup[to])
                    if (fup[to] > tin[vertex]) {
                        ans.add(Pair(vertex, to))
                    }
                }
            }
        }

        for (i in used.indices) {
            if (!used[i]) {
                dfs(i)
            }
        }

        val numBridgeToast = Toast.makeText(this, "В данном графе " + ans.size.toString() + " мост (a, ов)", Toast.LENGTH_SHORT)
        numBridgeToast.show()

        status50 = true
    }

    fun backToFirstActivity(view: View) {

        val progressAnimator = ObjectAnimator.ofInt(progressBar, "progress", 50, 0)
        progressAnimator.duration = 700
        progressAnimator.start()

        val randomIntent = Intent(this, MainActivity::class.java)
        startActivity(randomIntent)
    }
}
