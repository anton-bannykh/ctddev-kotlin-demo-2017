import java.util.*

fun main(args: Array<String>) {
    println("Hello world!")
}

var jmp = arrayOf<Array<Int>>()
var sum_jmp = arrayOf<Array<Int>>()
var graph = arrayOf<Array<Int>>()
var weight = arrayOf<Array<Int>>()
var dist = arrayOf<Int>()
var was = arrayOf<Boolean>()

fun dfs(v: Int, h: Int, parent: Int)
{
    was[v] = true
    dist[v] = h
    jmp[v][0] = parent
    sum_jmp[v][0] = weight[v][parent]
    //var i : Int = 1
    for (i in 1 .. jmp[v].size - 1)
    {
        jmp[v][i] = jmp[jmp[v][i - 1]][i - 1]
    }
    var flag: Boolean = false
    for (i in 1 .. jmp[v].size - 1)
    {
        if (!flag)
        {
            sum_jmp[v][i] += sum_jmp[jmp[v][i - 1]][i - 1] + sum_jmp[v][i - 1]
            if (dist[jmp[v][i]] == 0)
                flag = true
        } else
            sum_jmp[v][i] = sum_jmp[v][i - 1]

    }

    for (i in 0..graph[v].size - 1)
    {
        if (!was[graph[v][i]])
            dfs(graph[v][i], h + 1, v)
    }
}

fun LCA(a : Int, b : Int, l : Int) : Int
{
    var A = a
    var B = b
    if (dist[a] > dist[b])
    {
        var tmp : Int = A
        A = B
        B = tmp
    }
    var ans = 0
    var i = l
    while (i >= 0 )
    {
        var v = jmp[B][i]
        if (dist[v] >= dist[A])
        {
            ans += sum_jmp[B][i]
            B = v
        }
        i--
    }
    if (A == B)
        return ans

    i = l
    while (i >= 0)
    {
        var u = jmp[A][i]
        var v = jmp[B][i]
        if (u != v)
        {
            ans += sum_jmp[A][i] + sum_jmp[B][i]
            A = u
            B = v
        }
        i--
    }
    ans += sum_jmp[A][0] + sum_jmp[B][0]
    return ans
}

fun Build_graph(args: Array<Int>) : Boolean
{
    var n: Int = args[0]
    //var s = args.split(" ")

    var l = Math.log(n.toDouble()).toInt()
    l++

    //n = args[0].toInt()
    jmp = Array(n, { Array(l, { i -> 0 }) })
    sum_jmp = Array(n, { Array(l, { i -> 0 }) })
    graph = Array(n, { Array(0, { i -> 0 }) })
    was = Array(n, { i -> false })
    weight = Array(n, { Array(n, { i -> 0 }) })
    dist = Array(n, { i -> 0 })

    var i: Int = 1
    while (i < 3 * (n - 1))
    {
        var a : Int = args[i]
        i++
        var b : Int = args[i]
        i++
        var w : Int = args[i]
        i++
        graph[a] = graph[a].plus(b)
        graph[b] = graph[b].plus(a)
        weight[a][b] = w
        weight[b][a] = w
    }
    dfs(0, 0, 0)
    return true
}