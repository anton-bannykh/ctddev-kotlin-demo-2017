
class Edge(val a: Int, val b: Int, val weight: Int)

class DSU(n: Int) {
    var head = Array(n, { it })
    var height = Array(n, { 0 })

    fun get(a: Int): Int {
        if (head[a] != a)
            head[a] = get(head[a])
        return head[a]
    }

    fun unite(x: Int, y: Int) {
        val a = get(x)
        val b = get(y)
        if (a == b) return
        if (height[a] < height[b]) {
            head[a] = b
        } else {
            if (height[a] == height[b]) height[a]++
            head[b] = a
        }
    }
}

fun minSpanningTreeWeight(n: Int, graph: Array<Edge>): Int {
    graph.sortBy { it.weight }
    val dsu = DSU(n)
    var answer = 0

    for (i in graph)
        if (dsu.get(i.a) != dsu.get(i.b)) {
            dsu.unite(i.a, i.b)
            answer += i.weight
        }

    return answer
}

