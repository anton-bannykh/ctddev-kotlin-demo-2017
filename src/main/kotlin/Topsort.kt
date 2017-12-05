/**
 * Created by User on 05.12.2017.
 */
import java.lang.Integer.parseInt
import java.util.*

val st: Stack<Int> = Stack()

fun dfs(color: Array<Int>, graph: Array<ArrayList<Int>>, v: Int): Boolean {
    if (color[v] == 1) return true
    if (color[v] == 2) return false
    color[v] = 1
    for (i in graph[v]) {
        if (dfs(color, graph, i)) return true
    }
    st.push(v)
    color[v] = 2
    return false
}

fun topsort(color: Array<Int>, graph: Array<ArrayList<Int>>, topol: Array<Int>): Boolean {
    var cyclic: Boolean
    for (i in 0..graph.size - 1) {
        cyclic = dfs(color, graph, i)
        if (cyclic) return false
    }
    for (i in 1..graph.size) {
        topol[st.pop()] = i
    }
    return true
}