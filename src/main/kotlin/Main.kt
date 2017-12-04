var edges: Array<MutableSet<Int>> = emptyArray()
var sizeV: Array<Int> = emptyArray()
var prevCentroid: Array<Int> = emptyArray()

fun buildCentroidDec(edgesIn: Array<MutableSet<Int>>): Array<Int> {
    edges = edgesIn
    sizeV = Array(edges.size, { 0 })
    prevCentroid = Array(edges.size, { 0 })
    build(0, -1)
    return prevCentroid
}

fun dfs(p: Int, v: Int): Int {
    var res: Int = 1
    for (i in edges[v]) {
        if (i != p) {
            res += dfs(v, i)
        }
    }
    sizeV[v] = res
    return res
}

fun findCentroid(v: Int): Int {
    dfs(v, v)
    val size: Int = sizeV[v]
    var end: Boolean = false
    var prev: Int = v
    var centroid: Int = v
    while (!end)
    {
        end = true
        for (i in edges[v]) {
            if (i != prev && sizeV[i] > size / 2) {
                end = false
                prev = centroid
                centroid = i
                break
            }
        }
    }
    return centroid
}

fun build(v: Int, prev: Int) {
    val cur = findCentroid(v)
    prevCentroid[cur] = prev
    val nextStep: Set<Int> = edges[cur]
    edges[cur] = mutableSetOf()
    for (i in nextStep) {
        edges[i].remove(cur)
        build(i, cur)
    }
}
