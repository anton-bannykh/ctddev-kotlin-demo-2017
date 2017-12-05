fun get(edgeDsu:IntArray, v:Int):Int = if (v == -1) -1 else if (edgeDsu[v] == v) v else {edgeDsu[v] = get(edgeDsu, edgeDsu[v]); edgeDsu[v]}

fun getComp(compDsu:IntArray, edgeDsu:IntArray, v:Int):Int {
    val u = get(edgeDsu, v)
    return if (compDsu[u] == u) u else {
        compDsu[u] = getComp(compDsu, edgeDsu, compDsu[u])
        compDsu[u]
    }
}

fun makeRoot(compDsu:IntArray, edgeDsu:IntArray, size:IntArray, parent:IntArray, v:Int) {
    var v = get(edgeDsu, v)
    var root = v
    var child = -1
    while(v != -1) {
        val p = get(edgeDsu, parent[v])
        parent[v] = child
        compDsu[v] = root
        child = v
        v = p
    }
    size[root] = size[child]
}

fun mergePath(a:Int, b:Int, n:Int, parent: IntArray, edgeDsu: IntArray, bridges:Int):Int {
    val used = BooleanArray(n, {false})
    val va = mutableListOf<Int>()
    val vb = mutableListOf<Int>()
    var lca = -1
    var a = a
    var b = b
    var bridges = bridges
    loop@while(true) {
        if (a != -1) {
            a = get(edgeDsu, a)
            va.add(a)
            if(used[a]) {
                lca = a
                break@loop
            }
            used[a] = true
            a = parent[a]
        }
        if (b != -1) {
            b = get(edgeDsu, b)
            vb.add(b)
            if(used[b]) {
                lca = b
                break@loop
            }
            used[b] = true
            b = parent[b]
        }
    }

    for(element in va) {
        edgeDsu[element] = lca
        if(element == lca) break
        --bridges
    }

    for(element in vb) {
        edgeDsu[element] = lca
        if(element == lca) break
        --bridges
    }
    return bridges
}

fun addEdge(a:Int, b:Int, n:Int, size:IntArray, parent: IntArray, compDsu: IntArray, edgeDsu: IntArray, bridges: Int):Int {
    var a = get(edgeDsu, a)
    var b = get(edgeDsu, b)
    if(a == b) return bridges
    var ca = getComp(compDsu, edgeDsu, a)
    var cb = getComp(compDsu, edgeDsu, b)
    var bridges = bridges
    if(ca != cb) {
        ++bridges
        if(size[ca] > size[cb]) {
            val c = a
            a = b
            b = c
            val cc = ca
            ca = cb
            cb = cc
        }
        makeRoot(compDsu, edgeDsu, size, parent, a)
        compDsu[a] = b
        parent[a] = b
        size[cb] += size[a]
        return bridges
    } else {
        return mergePath(a, b, n, parent, edgeDsu, bridges)
    }
}

fun runTest(n:Int, queries:Array<Array<Int>>):MutableList<Int> {
    var bridges = 0
    val parent = IntArray(n + 1, {-1})
    val edgeDSU = IntArray(n + 1, {i -> i})
    val compDSU = IntArray(n + 1, {i -> i})
    val size = IntArray(n + 1, {1})
    val ans = mutableListOf<Int>()
    for(query in queries) {
        when(query[0]) {
            -1 -> {bridges = addEdge(query[1], query[2], n + 1, size, parent, compDSU, edgeDSU, bridges)}
            -2 -> {ans.add(bridges)}
        }
    }
    return ans
}