package com.example.timofey.myfirstapp

import GiveMeOtvet
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlin.collections.ArrayList
import android.widget.TextView
import android.widget.Toast
import build
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Integer.parseInt
import java.util.*

var n = 0
var count = 0
var graph = ArrayList<ArrayList<Int>>()
var used = BooleanArray(0)
var papa = IntArray(0)
var size = IntArray(0)

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun init()  {
        used = BooleanArray(n + 1)
        papa = IntArray(n + 1)
        size = IntArray(n + 1)
        for (i in 0..n) {
            graph.add(ArrayList())
        }
    }

    fun readEdges(view: View) {
        val showCountOfEdges = findViewById<View>(R.id.Edges) as TextView
        val EdgesString = showCountOfEdges.text.toString()
        n = Integer.parseInt(EdgesString)
        init()
        readVerges()
        build(graph, 1, size, used, papa, 0)
        val Ans = GiveMeOtvet(n, papa)
        Answer.text = Ans.toString()
    }

    fun read1Verge() {
        val show1Verge = findViewById<View>(R.id.Verge1) as TextView
        val Verge1String = show1Verge.text.toString()
        Verge1String.split(" ")
        val numArr = ArrayList<Int>()
        for (i in 0..Verge1String.length)
            numArr.add(Integer.parseInt(Verge1String[i].toString()))
        graph[count] = numArr
        count++
    }

    fun read2Verge() {
        val show2Verge = findViewById<View>(R.id.Verge2) as TextView
        val Verge2String = show2Verge.text.toString()
        Verge2String.split(" ")
        val numArr = ArrayList<Int>()
        for (i in 0..Verge2String.length)
            numArr.add(Integer.parseInt(Verge2String[i].toString()))
        graph[count] = numArr
        count++
    }

    fun readVerges() {
        read1Verge()
        read2Verge()
    }







/*    fun readVerges(view: View) {
        val
    }*/

}
