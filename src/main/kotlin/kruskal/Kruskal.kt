package kruskal

class DisjointSet(n : Int) {
    var p = Array(n, { it })
    var r = Array(n, { 0 })
    fun get(a : Int) : Int {
        if (p[a] != a) p[a] = get(p[a])
        return p[a]
    }

    fun unite(a : Int, b : Int) {
        val u = get(a)
        val v = get(b)
        if (u == v) return
        if (r[u] == r[v]) r[u]++
        if (r[u] < r[v]) p[u] = v else p[v] = u
    }
}

class Edge(val u : Int, val v : Int, val w : Int)

fun getWeightOfMST(n : Int, edges : Array<Edge>) : Int {
    val ds = DisjointSet(n)
    var res = 0
    edges.sortBy { it.w }
    for (edge in edges) {
        if (ds.get(edge.u) != ds.get(edge.v)) {
            res += edge.w
            ds.unite(edge.u, edge.v)
        }
    }
    return res
}