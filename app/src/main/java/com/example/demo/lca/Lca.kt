package com.example.demo.lca

/**
 * Created by Facmad on 12.12.2017.
 */


import java.util.*


class Tree(n : Int, graph : Array<ArrayList<Int>>){
    val g = graph
    val tin = IntArray(n,{0})
    val tout = IntArray(n,{0})
    val log = kotlin.math.log(n.toDouble(),2.0).toInt() + 1
    val jumps = Array(n,{ IntArray(log + 1,{0})})
    var time : Int = 0
    init {
        dfs(0)
    }

    private fun dfs(v : Int, p : Int = 0){
        tin[v] = ++time
        jumps[v][0] = p
        for (i in 1..log){
            jumps[v][i] = jumps[jumps[v][i - 1]][i - 1]
        }
        g[v].forEach{it -> if (it != p) dfs(it,v)}
        tout[v] = ++time
    }

    private fun upper(x : Int, y : Int) = tin[x] <= tin[y] && tout[x] >= tout[y]

    fun getLca(x : Int, y : Int) : Int{
        if (upper(x,y))
            return x
        if (upper(y,x))
            return y
        var i = log
        var cx = x
        while (i >= 0){
            if (!upper(jumps[x][i],y))
                cx = jumps[x][i]
            i--
        }
        return jumps[cx][0]
    }

}
