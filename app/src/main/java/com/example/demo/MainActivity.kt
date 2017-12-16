package com.example.demo

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.Point
import my.lib.dist
import my.lib.smallestDist

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        tvAnsLen.text = getString(R.string.less_then_two_points_error)
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
