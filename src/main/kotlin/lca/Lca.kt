package lca

import java.util.*

class Tree(n : Int, log : Int =  kotlin.math.log(n.toDouble(), 2.0).toInt() + 1, graph : Array<ArrayList<Int>>){
    var g = graph
    var tin = Array(n,{0})
    var tout = Array(n,{0})
    var log = kotlin.math.log(n.toDouble(), 2.0).toInt() + 1
    var jumps = Array(n,{ Array(log + 1,{0})})
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