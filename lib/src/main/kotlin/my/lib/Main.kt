fun get_size(v: Int, p: Int, size: Array<Int>, graph: Array<ArrayList<Int>>, used: Array<Boolean>): Int {
    size[v] = 1;
    for (to in graph[v]) {
        if (!used[to] && to != p) {
            size[v] += get_size(to, v, size, graph, used);
        }
    }
    return size[v];
}

fun get_center(v: Int, p: Int, sz: Int, size: Array<Int>, graph: Array<ArrayList<Int>>, used: Array<Boolean>): Int {
    for (to in graph[v]) {
        if (!used[to] && to != p && 2 * size[to] > sz) {
            return get_center(to, v, sz, size, graph, used);
        }
    }
    return v;
}

fun build(v: Int, last: Int, size: Array<Int>, graph: Array<ArrayList<Int>>, used: Array<Boolean>, parent: Array<Int>) {
    val sz_comp = get_size(v, -1, size, graph, used);
    val c = get_center(v, -1, sz_comp, size, graph, used);
    parent[c] = last;
    used[c] = true;
    for (to in graph[c]) {
        if (!used[to]) {
            build(to, c, size, graph, used, parent);
        }
    }
}

fun output_parent(n: Int, parent: Array<Int>) {
    for (c in 1..n) {
        print("${parent[c]} ");
    }
}

fun ans(n: Int, parent: Array<Int>): Array<Int> { //for Tests
    val result: Array<Int> = Array(n, { 0 })
    for (i in 0..n - 1) {
        result[i] = parent[i + 1]
    }
    return result
}

fun main(args: Array<String>) {
    val n = readLine()!!.toInt()
    println(n)
    val graph = Array(n + 1) { arrayListOf<Int>() }
    val used = Array(n + 1, { false })
    val parent = Array(n + 1) { 0 }
    val size = Array(n + 1, { 0 })

    for (i in 0..n - 2) {
        val (from, to) = readLine()!!.split(' ').map(String::toInt)
        graph[from].add(to)
        graph[to].add(from)
    }
    build(1, 0, size, graph, used, parent)
    output_parent(n, parent)
}
