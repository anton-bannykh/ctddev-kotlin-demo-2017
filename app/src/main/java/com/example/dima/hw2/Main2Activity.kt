package com.example.dima.hw2

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main2.*

import dsl.constraintLayoutCreate
import kotlinx.android.synthetic.main.activity_main.*

class Main2Activity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //setContentView(R.layout.activity_main2)
        val number = intent.getIntExtra("number", 0)
        val graph = Cycle(number)
        setContentView(constraintLayoutCreate(R.id.layoutId) {
            val tview2 = textView(R.id.textView2, conv_dp(200), conv_dp(100), "Edge", 24) {
                tMarg(R.id.layoutId, TOP, conv_dp(12))
                lMarg(R.id.layoutId, LEFT, conv_dp(150))
                rMarg(R.id.layoutId, RIGHT, conv_dp(150))
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }
            val st = textView(R.id.textView5, conv_dp(100), conv_dp(70), "Start", 20) {
                tMarg(R.id.layoutId, TOP, conv_dp(100))
                lMarg(R.id.layoutId, LEFT, conv_dp(200))
                //rMarg(R.id.textView6, RIGHT, conv_dp(100))
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }
            val end = textView(R.id.textView6, conv_dp(100), conv_dp(70), "End", 20) {
                tMarg(R.id.layoutId, TOP, conv_dp(100))
                rMarg(R.id.layoutId, RIGHT, conv_dp(200))
                //lMarg(R.id.textView5, LEFT, conv_dp(100))
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }
            val st_ed = editText(R.id.st_txt, conv_dp(100), conv_dp(70), "") {
                tMarg(R.id.textView5, TOP, conv_dp(70))
                lMarg(R.id.layoutId, LEFT, conv_dp(4))
                //rMarg(R.id.end_txt, RIGHT, conv_dp(300))
            }
            val end_ed = editText(R.id.end_txt, conv_dp(100), conv_dp(70), "") {
                tMarg(R.id.textView6, TOP, conv_dp(70))
                rMarg(R.id.layoutId, RIGHT, conv_dp(4))
                //lMarg(R.id.st_txt, LEFT, conv_dp(150))
            }
            val btn_add = button(R.id.add_btn, conv_dp(150), conv_dp(100), "ADD", 21) {
                tMarg(R.id.textView2, TOP, conv_dp(300))
                lMarg(R.id.layoutId, LEFT, conv_dp(75))
                rMarg(R.id.layoutId, RIGHT, conv_dp(75))
                setOnClick {
                    if (end_txt.text.toString().isNotEmpty() && st_txt.text.toString().isNotEmpty()) {
                        val st = Integer.parseInt(st_txt.text.toString())
                        val end = Integer.parseInt(end_txt.text.toString())
                        if (number >= maxOf(st, end) && st != 0 && end != 0 && !graph.contain(st - 1, end - 1)) {
                            graph.addEdge(st - 1, end - 1)
                            graph_txt.setText(graph_txt.text.toString() + "($st; $end) ")
                        }
                    }
                    st_txt.setText("")
                    end_txt.setText("")
                }
            }
            val tview3 = textView(R.id.graph_txt, conv_dp(250), conv_dp(200), "", 14) {
                tMarg(R.id.add_btn, TOP, conv_dp(200))
                //lMarg(R.id.layoutId, LEFT, conv_dp(100))
                rMarg(R.id.layoutId, RIGHT, conv_dp(10))
                textAlignment = View.TEXT_ALIGNMENT_CENTER
            }
            val btn_res = button(R.id.res_btn, conv_dp(150), conv_dp(100), "RESULT", 21) {
                tMarg(R.id.add_btn, TOP, conv_dp(200))
                lMarg(R.id.layoutId, LEFT, conv_dp(50))
                //rMarg(R.id.res_txt, RIGHT, conv_dp(150))
                setOnClick {
                    val result = graph.answerRequest()
                    if (result.size != 0) {
                        res_txt.setText("YES\n")
                        res_txt.setText(res_txt.text.toString() + result.toString())
                    } else {
                        res_txt.setText("NO")
                    }
                }
            }
            val btn_clear = button(R.id.clr_btn, conv_dp(150), conv_dp(100), "CLEAR", 21) {
                tMarg(R.id.res_btn, TOP, conv_dp(100))
                lMarg(R.id.layoutId, LEFT, conv_dp(50))
                bMarg(R.id.layoutId, BOTTOM, conv_dp(40))
                //rMarg(R.id.res_txt, RIGHT, conv_dp(150))
                setOnClick {
                    graph.clearEd()
                    graph_txt.setText("")
                }
            }
            val tview4 = textView(R.id.res_txt, conv_dp(250), conv_dp(200), "", 20) {
                tMarg(R.id.graph_txt, TOP, conv_dp(120))
                rMarg(R.id.layoutId, RIGHT, conv_dp(10))
                //rMarg(R.id.layoutId, RIGHT, conv_dp(50))
            }

        })
    }

    /*fun add() {

    }*/
    /*
        clr_btn.setOnClickListener {

        }
        res_btn.setOnClickListener {

        }*/
}
