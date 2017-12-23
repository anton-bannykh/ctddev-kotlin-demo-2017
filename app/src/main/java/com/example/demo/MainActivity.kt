package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import Dsl.constraintLayout
import Dsl.onCLick
import my.lib.SegmentTree


class MainActivity : AppCompatActivity() {
    var TextView.str: String?
        get() {
            var a = text.toString()
            return a
        }
        set(x) {
            text = x.toString()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(R.id.layoutId) {
                    textView(R.id.STId) {
                        text = "Segment Tree"
                        top_(R.id.layoutId, TOP, dp(1))
                        left_(R.id.layoutId, LEFT, dp(151))
                        right_(R.id.layoutId, RIGHT, dp(151))
                    }

                    val array = mutNumber(R.id.arrayId) {
                        top_(R.id.STId, BOTTOM, dp(44))
                        left_(R.id.layoutId, LEFT, dp(16))
                    }

                    val posVal = mutNumber(R.id.forModifyId) {
                        top_(R.id.arrayId, BOTTOM, dp(14))
                        left_(R.id.layoutId, LEFT, dp(16))
                    }

                    val q = mutNumber(R.id.forQueryId) {
                        top_(R.id.forModifyId, BOTTOM, dp(14))
                        left_(R.id.layoutId, LEFT, dp(16))
                    }

                    textView(R.id.answerTextId) {
                        text = "Answer"
                        top_(R.id.forQueryId, BOTTOM, dp(25))
                        left_(R.id.layoutId, LEFT, dp(151))
                        right_(R.id.layoutId, RIGHT, dp(151))
                    }

                    val ans = mutNumber(R.id.answerId) {
                        top_(R.id.answerTextId, BOTTOM, dp(10))
                        left_(R.id.layoutId, LEFT, dp(151))
                        right_(R.id.layoutId, RIGHT, dp(151))
                    }

                    button(R.id.createSTId) {
                        top_(R.id.STId, BOTTOM, dp(44))
                        right_(R.id.layoutId, RIGHT, dp(32))
                        text = "CREATE ST"

                        onCLick {
                            val textArray = array.str
                            if (textArray != null) {
                                if (textArray.toString()[0] != 'A') {
                                    val listArray = textArray.toString().split("\u0020")

                                    for (it in listArray) {
                                        mutListArray.add(it.toInt())
                                    }
                                    n = mutListArray.size

                                    segmentTree = SegmentTree(mutListArray)
                                    array.str = "Array is ${textArray}"
                                }
                            }
                        }
                    }

                    button(R.id.modifyId) {
                        top_(R.id.createSTId, BOTTOM, dp(14))
                        right_(R.id.layoutId, RIGHT, dp(32))
                        text = "MODIFY"

                        onCLick {
                            val textModify = posVal.str
                            if (textModify != null) {
                                val listModify = textModify.toString().split("\u0020")
                                posVal.str = ""
                                val index = listModify[0].toInt()
                                val value = listModify[1].toInt()

                                segmentTree.modify(index, value)
                                mutListArray[index] = value

                                array.str = ""
                                array.str = "Array is ${mutListArray.joinToString(" ").substring(0, 2 * n - 1)}"
                            }
                        }
                    }

                    button(R.id.queryId) {
                        top_(R.id.modifyId, BOTTOM, dp(14))
                        right_(R.id.layoutId, RIGHT, dp(32))
                        text = "FIND MAX"

                        onCLick {
                            ans.str = ""

                            val textQuery = q.str
                            if (textQuery != null) {
                                val listQuery = textQuery.toString().split("\u0020")
                                q.str = ""

                                val a = listQuery[0].toInt()
                                val b = listQuery[1].toInt()
                                ans.str = "${segmentTree.query(a, b).toString()}"
                            }
                        }
                    }
                }
        )
    }

    val mutListArray = mutableListOf<Int>()
    var segmentTree = SegmentTree()
    var n = 0
}
