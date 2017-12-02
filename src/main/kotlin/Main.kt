import java.util.Queue
import java.util.PriorityQueue
import java.util.Random

internal class Comp : Comparator<Pair<Int, Long>> {
    override fun compare(a: Pair<Int, Long>, b: Pair<Int, Long>): Int {
        return if (a.second < b.second) -1 else if (a.second == b.second) 0 else 1
    }
}

fun dijkstra(from: Int, edges: Array<ArrayList<Pair<Int, Long>>>): LongArray {
    val dist = LongArray(edges.size, { 1_000_000_000_000 })
    dist[from] = 0

    val queue: Queue<Pair<Int, Long>> = PriorityQueue<Pair<Int, Long>>(Comp())
    queue.offer(Pair(from, 0))

    while (!queue.isEmpty()) {
        val (v, distance) = queue.poll()
        if (dist[v] < distance) continue
        for ((u, len) in edges[v]) {
            if (dist[v] + len < dist[u]) {
                dist[u] = dist[v] + len
                queue.offer(Pair(u, dist[u]))
            }
        }
    }

    return dist
}

fun ClosedRange<Int>.random() = Random().nextInt(endInclusive - start) + start