fun main(args: Array<String>) {
    println("Hello world!")
}

fun foo() = 10

fun sum(vararg ints: Int): Int {
    var result = 0
    for (v in ints) {
        result += v
    }
    return result
}

fun sumFun(vararg ints: Int) = ints.fold(0) { acc, i -> acc + i }

fun min(a : Int, b : Int) : Int = if (a > b) b else a

class Vertex
{
    var to : ArrayList<Int> = ArrayList()
    var id : ArrayList<Int> = ArrayList()
    fun insert (a : Int, id_e : Int) {
        to.add(a)
        id.add(id_e)
    }
    fun size () = to.size
}

class Graph (max_size : Int)
{
    var listInc : ArrayList<Vertex> = ArrayList()
    var tmp_id = 0

    init {
        for (i in 0..(max_size - 1)) {
            var tmp : Vertex = Vertex()
            listInc.add(tmp)
        }
    }

    fun insert (a : Int, b : Int) {
        listInc[a].insert(b, tmp_id)
        listInc[b].insert(a, tmp_id)
        tmp_id++
    }

    fun to(i : Int, j : Int) = listInc[i].to[j]

    fun size() = listInc.size

    fun id(i : Int, j : Int) = listInc[i].id[j]

    fun size_id(i : Int) = (listInc[i].id).size
}

fun points (g : Graph) : ArrayList<Boolean> {
    var vis : ArrayList<Boolean> = ArrayList()
    var d : ArrayList<Int> = ArrayList()
    var up : ArrayList<Int> = ArrayList()
    var is_point : ArrayList<Boolean> = ArrayList()
    for (i in 0..(g.size() - 1)) {
        vis.add(false)
        d.add(0)
        up.add(0)
        is_point.add(false)
    }
    var cnt = 0
    fun dfs(v : Int, d_v : Int, e_id : Int, root : Int) {
        vis[v] = true
        d[v] = d_v
        up[v] = d_v
        for (i in 0..(g.size_id(v) - 1)) {
            var u = g.to(v, i)
            if (vis[u] == false) {
                if (v == root) {
                    cnt++
                }
                dfs(u, d_v + 1, g.id(v, i), root)
                up[v] = min(up[v], up[u])
                if ((up[u] >= d[v]) && (v != root)) {
                    is_point[v] = true
                }
            } else {
                if (g.id(v, i) != e_id) {
                    up[v] = min(up[v], d[u])
                }
            }
        }
        if (cnt > 1) {
            is_point[root] = true
        }
    }
    for (i in 0..(g.size() - 1)) {
        if (vis[i] == false) {
            cnt = 0
            dfs(i, 0, -1, i)
        }
    }
    return is_point
}
