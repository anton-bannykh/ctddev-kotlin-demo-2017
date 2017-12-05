public class Graph(n: Int) {

    val n: Int = n
    val edges: MutableList<MutableList<Int>> = mutableListOf()
    val revEdges: MutableList<MutableList<Int>> = mutableListOf()

    fun addEdge(from: Int, to: Int) {
        val correctVertices = (from in 0..(n - 1) && to in 0..(n - 1))
        if (!correctVertices) {
            throw GraphException("Impossible to add the edge between vertices with indices $from and $to")
        }
        // lazy init
        if (edges.size == 0 || revEdges.size == 0) {
            fun initLists() {
                for (i in 0 until n) {
                    edges.add(mutableListOf())
                    revEdges.add(mutableListOf())
                }
            }
            initLists()
        }
        edges[from].add(to)
        revEdges[to].add(from)
    }


    private fun topologicalSort(ver: Int, used: Array<Boolean>, result: MutableList<Int>) {
        used[ver] = true
        /*
            This will not work, as it firstly gets all not used vertices :(

            ----------------------------------------------------------------------
            edges[ver].filterNot { used[it] }
                  .forEach { topologicalSort(it, used, result) }
            ----------------------------------------------------------------------

        */
        for (nxt in edges[ver]) {
            if (!used[nxt]) {
                topologicalSort(nxt, used, result)
            }
        }
        result.add(ver)
    }

    private fun sccDfs(ver: Int, used: Array<Boolean>, result: MutableList<Int>) {
        used[ver] = true
        result.add(ver)
        for (nxt in revEdges[ver]) {
            if (!used[nxt]) {
                sccDfs(nxt, used, result)
            }
        }
    }

    fun getScc(): Map<Int, Int> {
        val used = Array(n, {false})
        var order = mutableListOf<Int>()
        val component = mutableListOf<Int>()
        val result = hashMapOf<Int, Int>()
        if (edges.size == 0) { // requires this 'if' statement because of lazy init
            for (i in 0 until n) {
                result[i] = i + 1
            }
            return result
        }
        for (ver in 0 until n) {
            if (!used[ver]) {
                topologicalSort(ver, used, order)
            }
        }
        used.fill(false)
        var lastComponentId: Int = 0
        for (i in 0 until n) {
            val vertex = order[n - 1 - i]
            if (!used[vertex]) {
                component.clear()
                sccDfs(vertex, used, component)
                lastComponentId += 1
                for (v in component) {
                    result[v] = lastComponentId
                }
            }
        }
        return result
    }

}