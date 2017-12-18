package com.example.drden.androsort

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun start(view: View) {
        val startArray = (findViewById<View>(R.id.startArray) as TextView).text.toString()
        val holder = findViewById<View>(R.id.currentArray) as TextView
        holder.text = try {
            startArray.split(" ").map {it -> it.toInt()}.joinToString(" ")
        } catch(e: Exception) {
            val myToast = Toast.makeText(this, "Incorrect input.\nArray must be represented as set of numbers separated by a space.\nTry again.", Toast.LENGTH_SHORT)
            myToast.show()
            "1 2 3 4 5"
        }
    }

    fun sort(view: View) {
        val holder = findViewById<View>(R.id.currentArray) as TextView
        val array = holder.text.split(" ").map {it -> it.toInt()}.toIntArray()
        mergeSort(array)
        holder.text = array.joinToString(" ")
    }

    fun shuffle(view: View) {
        val holder = findViewById<View>(R.id.currentArray) as TextView
        val array = holder.text.split(" ").map {it -> it.toInt()}.toMutableList()
        for (i in array.indices) {
            val newPos = Random().nextInt(array.size)
            val tmp = array[i]
            array[i] = array[newPos]
            array[newPos] = tmp
        }
        holder.text = array.joinToString(" ")
    }

    private fun mergeSort(a : IntArray, l : Int = 0, r : Int = a.size) {
        if (r - l < 2)
            return
        val m = (l + r) / 2
        mergeSort(a, l, m)
        mergeSort(a, m, r)
        merge(a, l, m, r)
    }

    private fun merge(a : IntArray, l : Int, m : Int, r : Int) {
        val tmp = IntArray(r - l)
        var it1 = l
        var it2 = m
        var dst = 0
        while (dst < tmp.size && it1 < m && it2 < r) {
            tmp[dst++] = if (a[it1] < a[it2]) a[it1++] else a[it2++]
        }
        if (dst == tmp.size) return
        val range = if (it1 == m) IntRange(it2, r - 1) else IntRange(it1, m - 1)
        for (i in range) {
            tmp[dst++] = a[i]
        }
        for (i in l until r) {
            a[i] = tmp[i - l]
        }
    }
}
