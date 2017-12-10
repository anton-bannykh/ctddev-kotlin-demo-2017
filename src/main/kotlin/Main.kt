import java.util.*
import kotlin.collections.ArrayList

class Bfs(n : Int, m : Int, inp : IntArray, var s : Int = -1) {
    var used : ArrayList<Boolean> = arrayListOf<Boolean>()
    var p : ArrayList<Int> = arrayListOf<Int>()
    var d : ArrayList<Int> = arrayListOf<Int>()
    var gr : ArrayList<ArrayList<Int>> = arrayListOf()
    var q : PriorityQueue<Int> = PriorityQueue<Int>()
    var ans : ArrayList<Int> = arrayListOf<Int>()

    init {
        for (i in 0..n - 1) {
            gr.add(ArrayList<Int>())
            used.add(false)
            d.add(0)
            p.add(1000000)
        }
        for (i in 0..m - 1) {
            val x = inp[i * 2]
            val y = inp[i * 2 + 1]
            gr[x - 1].add(y - 1)
            gr[y - 1].add(x - 1)
        }
        val random = Random()
        if (s == -1) {
            s = random.nextInt(n - 1)
        }
        val to: Int = random.nextInt(n - 1)
        bfs2(s)
    }

    fun bfs2(s: Int) {
        ans.add(s+1)
        used[s] = true
        q.add(s)
        p[s] = -1
        while (!(q.isEmpty())) {
            var v = q.element()
            q.remove()
            for (i in 0..gr[v].size-1) {
                var to = gr[v][i]
                if (!used[to]) {
                    ans.add(to+1)
                    used[to] = true
                    q.add(to)
                    d[to] = d[v] + 1
                    p[to] = v
                }
            }
        }
    }

    fun check() : ArrayList<Int> {
        return ans
    }
}
