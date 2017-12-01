import java.util.*
import kotlin.collections.ArrayList

val INF = Int.MAX_VALUE

//Scans one number from the Scanner. If fails, throws Invalid input exception
fun getNextNumber(scanner: Scanner): Int {
    try {
        return scanner.nextInt()
    }
    catch (e: Exception) {
        throw RuntimeException("Not a valid number, please, check input")
    }
}

typealias Graph = Array<ArrayList<Pair<Int, Int>>>

//Reads graph from console input
//element number 0 is a fake vertex, used in algorithm
//graph[v] = [{u, w}], where vu in E and weight(vu) = w
fun getGraph() : Graph {
    val scanner = Scanner(System.`in`)
    //Number of vertex and edges, |V| and |E|
    val n = getNextNumber(scanner)
    val m = getNextNumber(scanner)
    val graph = Array<ArrayList<Pair<Int, Int>>>(n + 1, {i -> ArrayList() })
    for (i in 0..m - 1) {
        val u = getNextNumber(scanner)
        val v = getNextNumber(scanner)
        val w = getNextNumber(scanner)
        graph[u].add(Pair(v, w))
        //adds edge uv, weight(uw) = w
    }
    for (i in 1..n) {
        //adds edges from fake vertex to other vertex with weight 0
        graph[0].add(Pair(i, 0))
    }
    scanner.close()
    return graph
}

//returns distances from start vertex to other vertex, if there is no negative cycle
//if there is negative cycle, returns null
fun bellmanFord(graph: Graph) : Array<Int>? {
    val dist = Array(graph.size, {i ->
        if (i == 0)
            0
        else
            INF
    }) //d <- INF, d[s] = 0

    //Body of Bellman-Ford's algorithm, finds distance from s to other vertex
    //Works with negative weights
    for (i in 0..graph.size - 1) {
        for (v in 0..graph.size - 1) {
            if (dist[v] < INF) {
                for (t in graph[v]) { //relaxation (v, u)
                    val u = t.first
                    val w = t.second
                    dist[u] = minOf(dist[u], dist[v] + w)
                }
            }
        }
    }

    //if we can do relaxation after the algorithm body, there is a negative cycle
    for (v in 0..graph.size - 1) {
        if (dist[v] < INF) {
            for (t in graph[v]) {
                val u = t.first
                val w = t.second
                if (dist[u] > dist[v] + w) {
                    //if relaxation succeed, we've found a vertex on a negative cycle
                    return null
                }
            }
        }
    }
    return dist
}

//Dijkstra's algorithm. Finds paths with min weight from start vertex to other vertex
//if al weights are not-negative
//returns dist, where d[v] is distance from s to v, INF when there are no paths P: s~>v
fun dijkstra(graph: Graph, q: Array<Int>, s: Int) : Array<Int> {
    val dist = Array(graph.size, {i ->
        if (i == s)
            0
        else
            INF
    }) //d <- INF, d[s] = 0

    val used = Array(graph.size, {i -> false}) //used <- false

    while (true) {
        var z = -1
        for (v in 1..graph.size - 1) {
            if (!used[v] && dist[v] < INF) {
                z = v
                break
            }
        } //find first unused vertex
        if (z == -1) { //if all vertex have been used, algorithm has found answer
            return dist
        }
        for (v in z + 1..graph.size - 1) {
            if (!used[v] && dist[v] < dist[z]) {
                z = v
            }
        } //finds unused vertex with min distance from s to v
        used[z] = true
        for (t in graph[z]) {
            val u = t.first
            val w = t.second
            dist[u] = minOf(dist[u], dist[z] + w + q[z] - q[u])
            //relaxes (z, u), using weight' = weight(zu) + q[z] - q[u]
            //where q - distances from s to other vertex
        }
    }
}

//Gets graph with fake vertex
//Finds distances between all vertices, returns null if there is negative cycle
// and distances if there are no negative cycles, d[v][u] is distance from v to u, INF if there are no paths
// P: v~>u
fun johnson(graph: Graph): Array<Array<Int>>? {
    val n = graph.size - 1
    val q = bellmanFord(graph) //returns null when there is negative cycle
    if (q == null) {
        //Negative cycle
        return null
    }
    val ans = Array(n, { i -> Array(n, { i -> 0 }) })
    for (v in 1..n) {
        ans[v - 1] = dijkstra(graph, q, v).sliceArray(1..n)
        //don't get fake vertex(has number 0)
        for (u in 1..n) {
            if (ans[v - 1][u - 1] == INF) {
                //there is no path P: v~>u
                //print("INF ")
                continue
            }
            ans[v - 1][u - 1] += (q[u] - q[v])
            //reestablish distances. If w' = w + q[v] - q[u] => w = w' - q[v] + q[u]
        }
    }
    return ans
}

fun main(args: Array<String>) {
    try {
        val graph = getGraph()
        val answer = johnson(graph)
        if (answer == null) {
            //if Johnson's algorithm has found negative cycle and returned null
            println("Negative cycle");
            return
        }
        for (x in answer) {
            //distanced between vertex
            for (d in x) {
                if (d == INF) {
                    //No path v~>u
                    print("INF ")
                    continue
                }
                print("$d ")
            }
            println()
        }
    } catch (e: Exception) {
        //If input is incorrect, let user know about ir
        println(e.message)
        return
    }
    return
}