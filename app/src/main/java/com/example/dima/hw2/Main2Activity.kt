package com.example.dima.hw2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*


class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        val number = intent.getIntExtra("number", 0)
        val graph = Cycle(number)

        add_btn.setOnClickListener {
            if(end_txt.text.toString().isNotEmpty() && st_txt.text.toString().isNotEmpty()) {
                val st = Integer.parseInt(st_txt.text.toString())
                val end = Integer.parseInt(end_txt.text.toString())
                if(number >= maxOf(st,end) && st != 0 && end != 0 && !graph.contain(st - 1, end - 1)) {
                    graph.addEdge(st - 1, end - 1)
                    graph_txt.setText(graph_txt.text.toString() + "($st; $end) ")
                }
            }
            st_txt.setText("")
            end_txt.setText("")
        }
        clr_btn.setOnClickListener {
            graph.clearEd()
            graph_txt.setText("")
        }
        res_btn.setOnClickListener {
            val result = graph.answerRequest()
            if(result.size != 0) {
                res_txt.setText("YES\n")
                res_txt.setText(res_txt.text.toString() + result.toString())
            } else {
                res_txt.setText("NO")
            }
        }
    }
}
