package com.example.demo

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.text.InputType
import android.view.Gravity
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import my.lib.Point
import my.lib.dist
import my.lib.smallestDist

class MainActivity : AppCompatActivity() {
    private lateinit var tvAnsLen: TextView
    private lateinit var recyclerView: RecyclerView
    private lateinit var etX: EditText
    private lateinit var etY: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val layout =
            linearLayout(width = ViewGroup.LayoutParams.MATCH_PARENT, height = ViewGroup.LayoutParams.MATCH_PARENT) {
                orientation = LinearLayout.VERTICAL
                linearLayout(width = ViewGroup.LayoutParams.MATCH_PARENT, height = ViewGroup.LayoutParams.WRAP_CONTENT) {
                    etX = editText(width = 0) {
                        hint = "x"
                        weight = 1f
                        setRawInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                    }
                    etY = editText(width = 0) {
                        hint = "y"
                        weight = 1f
                        setRawInputType(InputType.TYPE_CLASS_NUMBER or InputType.TYPE_NUMBER_FLAG_DECIMAL or InputType.TYPE_NUMBER_FLAG_SIGNED)
                    }
                    button {
                        text = resources.getString(R.string.button_text)
                        setOnClickListener({ addPoint(this) })
                    }
                }
                tvAnsLen = textView {
                    text = resources.getString(R.string.less_then_two_points_error)
                    setTextColor(resources.getColor(R.color.distance_message_color))
                    setPadding(dp(10), dp(5), dp(10), dp(5))
                    gravity = Gravity.CENTER
                    textSize = sp(16)
                }
                recyclerView = recyclerView(width = LinearLayout.LayoutParams.MATCH_PARENT,
                                            height = LinearLayout.LayoutParams.MATCH_PARENT) {

                }
            }
        setContentView(layout.layout)
        //setContentView(R.layout.activity_main)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = PointAdapter()
    }

    fun addPoint(view : View) {
        val x = etX.text.toString().toDoubleOrNull()
        val y = etY.text.toString().toDoubleOrNull()
        if (x != null && y != null) {
            val adapter = recyclerView.adapter as PointAdapter
            val dist = adapter.addPoint(x, y)
            if (dist == null) {
                tvAnsLen.text = getString(R.string.less_then_two_points_error)
            } else {
                tvAnsLen.text = getString(R.string.ans_len_text, dist)
            }
        }
    }

    class PointAdapter : RecyclerView.Adapter<RecyclerView.ViewHolder>() {
        val data = ArrayList<Point>()
        private lateinit var curAns : Pair<Point, Point>

        class ItemHolder(itemView: View?) : RecyclerView.ViewHolder(itemView) {
            private lateinit var p : Point
            fun bindPoint(p : Point, isAns : Boolean) {
                this.p = p
                val view = (itemView as TextView)
                view.text = p.toString()
                view.setBackgroundColor(
                        if (isAns) itemView.context.resources.getColor(R.color.ansPointsBackground)
                        else Color.WHITE)
            }
        }

        override fun onBindViewHolder(holder: RecyclerView.ViewHolder?, position: Int) {
            val isAns = (data.size >= 2 && (data[position] == curAns.first || data[position] == curAns.second))
            (holder as ItemHolder).bindPoint(data[position], isAns)
        }

        override fun getItemCount() = data.size

        override fun onCreateViewHolder(parent: ViewGroup?, viewType: Int): RecyclerView.ViewHolder {
            return ItemHolder(LayoutInflater.from(parent?.context).inflate(R.layout.list_item, parent, false))
        }

        fun addPoint(x : Double, y : Double): Double? {
            data.add(Point(x, y, data.size))
            val res = if (data.size >= 2) {
                        curAns = smallestDist(data)
                        dist(curAns.first, curAns.second)
                    } else null
            notifyDataSetChanged()
            return res
        }
    }
}
