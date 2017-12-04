import java.util.*

var n: Int = 0
var p : MutableList<Int> = mutableListOf()
var ww : MutableList<Int> = mutableListOf()
var d : MutableList<Int> = mutableListOf()
var dp : MutableList<MutableList<Int> > = mutableListOf()
var summ : MutableList<MutableList<Int> > = mutableListOf()
var graf : MutableList<MutableList<Pair <Int,Int> > > = mutableListOf()
var used: MutableList<Boolean> = mutableListOf()


fun dfs (v: Int,  time: Int, prev: Int,  w: Int)  {

    d[v] = time
    used[v] = true
    p[v] = prev
    ww[v] = w
    for (j in 0..(graf[v].size - 1)) {
        if (!used[graf[v][j].first] ) {
            dfs(graf[v][j].first, time + 1, v, graf[v][j].second)
        }
    }
}
fun prep() {
    for (i in 0.. (n - 1)) {
        dp.add(mutableListOf())
        summ.add(mutableListOf())

        for (j in 0..18) {
            dp[i].add(0)
            summ[i].add(0)
        }
    }

    for (i in 0..(n - 1)) {
      summ[i][0] = ww[i]
      dp[i][0] = p[i]
    }

    for ( j in 1 .. 18)
      for ( i in 0..(n - 1) ) {
          dp[i][j] = dp[dp[i][j - 1 ]][j - 1 ]
          summ[i][j] = summ[dp[i][j - 1]][j - 1] + summ[i][j - 1]
    }
}

fun lca( vIn: Int, uIn: Int): Int  {
    var u = uIn
    var v = vIn
    var r: Int = 0
    if ( d[v] > d[u]) {
        var temp: Int  = u
        u = v
        v = temp
    }

    for ( i in 18 downTo 0)
    if (d[dp[u][i]] >= d[v]){
        r += summ[u][i];
        u = dp[u][i];
    }

    if (v == u) {
        return r;
    }

    for ( i in 18 downTo 0)
     if (dp[v][i] != dp[u][i]) {
            r += summ[u][i];
            u = dp[u][i];
            r += summ[v][i];
            v = dp[v][i];

     }

    r += ww[u];
    r += ww[v];
    return r;
}


fun solve(n1: Int, edges: MutableList<MutableList<Int> >, question: Pair<Int, Int> ) : Int {
      n = n1
      val cc: Boolean = false

      for (i in 0..(n - 1)) {
          graf.add(mutableListOf())
          d.add(0)
          p.add(0)
          ww.add(0)
          used.add(cc)
      }

    for (i in 0..(n - 2)) {
        var a: Int  = edges[i][0]
        var b: Int  = edges[i][1]
        var w: Int  = edges[i][2]
        graf[a].add(Pair(b, w))
        graf[b].add(Pair(a, w))
    }

    dfs(0, 0, 0, 0)
    prep()

    var ans : Int = lca(question.first, question.second)

    p.clear()
    ww.clear()
    d.clear()
    dp.clear()
    summ.clear()
    graf.clear()
    used.clear()

    return ans
}

