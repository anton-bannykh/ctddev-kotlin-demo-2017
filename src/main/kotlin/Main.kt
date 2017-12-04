
var graph: MutableList<MutableList<Int>> = mutableListOf()
var cycle: MutableList<Int> = mutableListOf()
var parent: MutableList<Int> = mutableListOf()
var c_st : Int = -1
var c_end: Int = 0
fun dfs(v: Int) : Boolean {
    cycle[v] = 1
    for (i in graph[v]) {
        if (cycle[i] == 0) {
            parent[i] = v
            if (dfs(i)) {
                return true
            }
        } else if (cycle[i] == 1) {
            c_end = v
            c_st = i
            return true
        }
    }
    cycle[v] = 2
    return false
}
fun answerRequest(ar: MutableList<Pair<Int, Int>> , n: Int) : MutableList<Int> {
    graph.clear()
    cycle.clear()
    parent.clear()
    c_st = -1
    c_end = 0

    for (i in 0..n - 1) {
        parent.add(-1)
        cycle.add(0)
        graph.add(mutableListOf())
    }
    for (i in 0..ar.size - 1) {
        graph[ar[i].first].add(ar[i].second)

    }

    for (i in 0..n - 1) {
        if (dfs(i)) break
    }
    var temp: MutableList<Int> = mutableListOf()
    if (c_st == -1) {
        return temp
    } else {
        temp.add(c_st)
        var i = c_end
        while (i != c_st) {
            temp.add(i)
            i = parent[i]
        }
        temp.add(c_st)
        temp.reverse()
        return temp
    }
}

fun main(args: Array<String>) {
}