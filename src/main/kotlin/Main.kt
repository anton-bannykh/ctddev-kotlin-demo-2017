import java.util.*
import kotlin.collections.ArrayList

class Bfs(n: Int, m: Int, inp: IntArray, var s: Int = -1) {
    var used: ArrayList<Boolean> = arrayListOf<Boolean>()
    var p: ArrayList<Int> = arrayListOf<Int>()
    var d: ArrayList<Int> = arrayListOf<Int>()
    var gr: ArrayList<ArrayList<Int>> = arrayListOf()
    var q: PriorityQueue<Int> = PriorityQueue<Int>()
    var ans: ArrayList<Int> = arrayListOf<Int>()

    init {
        for (i in 0..n - 1) {
            gr.add(ArrayList<Int>())
            used.add(false)
            d.add(0)
            p.add(1000000)
        }
        println("Input:\n" + "n : $n; m : $m")
        for (i in 0..m - 1) {
            val x = inp[i * 2]
            val y = inp[i * 2 + 1]
            println("x : $x; y : $y")
            gr[x - 1].add(y - 1)
            gr[y - 1].add(x - 1)
        }
        val random = Random()
        if (s == -1) {
            s = random.nextInt(n - 1)
        }
        val to: Int = random.nextInt(n - 1)
        println("Output:")
        println("s : ${s + 1}; to : ${to + 1}")
        bfs2(s)
        shortestWay(s, to)
    }

    fun bfs2(s: Int) {
        println("Порядок обхода графа из вершины {${s + 1}}:")
        print("" + (s + 1) + " ")
        ans.add(s + 1)
        used[s] = true
        q.add(s)
        p[s] = -1
        while (!(q.isEmpty())) {
            var v = q.element()
            q.remove()
            for (i in 0..gr[v].size - 1) {
                var to = gr[v][i]
                if (!used[to]) {
                    print("" + (to + 1) + " ")
                    ans.add(to + 1)
                    used[to] = true
                    q.add(to)
                    d[to] = d[v] + 1
                    p[to] = v
                }
            }
        }
        println()
        println("Кратчайшие расстояние до всех вершин от вершины {${s + 1}}:")
        for (i in 0..d.size - 1) {
            print("${i + 1} : " + d[i] + "; ")
        }
        println()
    }

    fun shortestWay(s: Int, to: Int) {
        if (!used[to]) {
            println("Нет пути из вершины {${s + 1}} в вершину {${to + 1}}!\n")
        } else {
            var path = arrayListOf<Int>()
            var v: Int = to
            path.add(v)
            while (v != -1) {
                v = p[v]
                path.add(v)
            }
            println("Кратчайший путь от вершины {${s + 1}} до вершины {${to + 1}}:")
            for (i in path.size - 2 downTo 0) {
                print("" + (path[i] + 1) + " ")
            }
            println("\n")
        }
    }

    fun check(): ArrayList<Int> {
        return ans
    }
}
