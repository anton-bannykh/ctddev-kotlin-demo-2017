package com.example.dmitriytrusienko.omgwtf

/**
 * Created by Dmitriy Trusienko on 13.12.2017.
 */
class Worker(private val countGraphBad:Int) {
    private val countGraph = countGraphBad + 1
    public var bridges = 0
    private val parent = IntArray(countGraph, { -1 })
    private val edgeDSU = IntArray(countGraph , { i -> i })
    private val compDSU = IntArray(countGraph, { i -> i })
    private val size = IntArray(countGraph, {1})
    public fun addEdge(a:Int, b:Int) {
        var a = get(a)
        var b = get(b)
        if (a == b) return
        val ca = getComp(a)
        var cb = getComp(b)
        if (ca != cb) {
            ++bridges
            if (size[ca] > size[cb]) {
                val c = a
                a = b
                b = c
                val cc = ca
                cb = cc
            }
            makeRoot(a)
            compDSU[a] = b
            parent[a] = b
            size[cb] += size[a]
        } else {
            mergePath(a, b)
        }
    }

    private fun mergePath(a:Int, b:Int) {
        val used = BooleanArray(countGraph, { false })
        val va = mutableListOf<Int>()
        val vb = mutableListOf<Int>()
        var lca = -1
        var a = a
        var b = b
        loop@ while (true) {
            if (a != -1) {
                a = get(a)
                va.add(a)
                if (used[a]) {
                    lca = a
                    break@loop
                }
                used[a] = true
                a = parent[a]
            }
            if (b != -1) {
                b = get(b)
                vb.add(b)
                if (used[b]) {
                    lca = b
                    break@loop
                }
                used[b] = true
                b = parent[b]
            }
        }

        for (element in va) {
            edgeDSU[element] = lca
            if (element == lca) break
            --bridges
        }

        for (element in vb) {
            edgeDSU[element] = lca
            if (element == lca) break
            --bridges
        }
    }

    private fun makeRoot(v:Int) {
        var v = get(v)
        var root = v
        var child = -1
        while (v != -1) {
            val p = get(parent[v])
            parent[v] = child
            compDSU[v] = root
            child = v
            v = p
        }
        size[root] = size[child]
    }

    private fun getComp(v:Int):Int {
        val u = get(v)
        return if (compDSU[u] == u) u else {
            compDSU[u] = getComp(compDSU[u])
            compDSU[u]
        }
    }

    private fun get(v:Int):Int = if (v == -1) -1 else if (edgeDSU[v] == v) v else {
        edgeDSU[v] = get(edgeDSU[v])
        edgeDSU[v]
    }
}