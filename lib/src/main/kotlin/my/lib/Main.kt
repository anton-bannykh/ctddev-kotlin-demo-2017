package my.lib

val INF: Int = Int.MAX_VALUE

class Graph(sizeVertex: Int) {
    val n = sizeVertex
    private val edges = arrayListOf<Edge>()
    private val paths = arrayListOf<ArrayList<Int>>()

    class Edge(val from: Int, val to: Int, val weight: Int)

    fun addEdge(from: Int, to: Int, weight: Int) {
        edges.add(Edge(from, to, weight))
    }

    fun fordBellman(start: Int): Pair<IntArray?, ArrayList<ArrayList<Int>>> {
        val dist = IntArray(n, { INF })
        val parent = IntArray(n, { -1 })
        dist[start] = 0
        val m = edges.size
        var x: Int = -1
        for (i in 0 until n) {
            var check = false
            x = -1
            for (j in 0 until m)
                if (dist[edges[j].from] < INF) {
                    if (dist[edges[j].to] > dist[edges[j].from] + edges[j].weight) {
                        dist[edges[j].to] = dist[edges[j].from] + edges[j].weight
                        parent[edges[j].to] = edges[j].from
                        x = edges[j].to
                        check = true
                    }
                }
            if (!check) break
        }
        return if (x == -1) {
            getPaths(start, dist, parent)
        } else {
            getNegativeCycle(x, parent)
        }
    }

    private fun getNegativeCycle(x: Int, parent: IntArray): Pair<IntArray?, ArrayList<ArrayList<Int>>> {
        var y = x
        for (i in 0 until n)
            y = parent[y]
        val path = arrayListOf<Int>()
        var cur = y
        while (cur != y || path.size <= 1) {
            path.add(cur)
            cur = parent[cur]
        }
        path.reverse()
        paths.add(path)
        return Pair(null, paths)
    }

    private fun getPaths(start: Int, dist: IntArray, parent: IntArray): Pair<IntArray?, ArrayList<ArrayList<Int>>> {
        for (i in 0 until n)
            paths.add(ArrayList())
        for (i in 0 until n) {
            if (i == start) {
                continue
            }
            if (dist[i] == INF) {
                paths[i].add(INF)
                continue
            }
            val path = arrayListOf<Int>()
            var cur = i
            while (cur != -1) {
                path.add(cur)
                cur = parent[cur]
            }
            path.reverse()
            for (item in path)
                paths[i].add(item)
        }
        return Pair(dist, paths)
    }
}

