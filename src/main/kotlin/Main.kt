import java.util.PriorityQueue

fun bfs(n : Int, m : Int, inp : MutableList<Int>, s : Int) : MutableList<Int> {
    var used = mutableListOf<Boolean>()
    var p = mutableListOf<Int>()
    var d = mutableListOf<Int>()
    var gr : MutableList<MutableList<Int>> = mutableListOf()
    var q : PriorityQueue<Int> = PriorityQueue<Int>()
    var ans = mutableListOf<Int>()

    for (i in 0..n - 1) {
        gr.add(mutableListOf<Int>())
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
                ans.add(to + 1)
                used[to] = true
                q.add(to)
                d[to] = d[v] + 1
                p[to] = v
            }
        }
    }
    return ans
}
