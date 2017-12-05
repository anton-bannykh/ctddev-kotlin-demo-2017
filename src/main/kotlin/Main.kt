import java.util.ArrayList

/*
* Implementation disjoint set union
* */

// global data for DSU
var parent = ArrayList<Int>()
var rank = ArrayList<Int>()
var countElement = 0

fun makeSet(newElement: Int) {
    rank.add(0)
    parent.add(parent.size)
    countElement += 1
}

fun findLead(v: Int): Int {
    if (parent[v] == v) {
        return v
    }
    parent[v] = findLead(parent[v])
    return parent[v]
}

fun unionSet(x: Int, y: Int) {
    var leadX = findLead(x)
    var leadY = findLead(y)
    if (leadX != leadY) {
        if (rank[leadX] < rank[leadY]) {
            leadX = leadY.also { leadY = leadX }
        }
        if (rank[leadX] == rank[leadY])
            ++rank[leadX]
        parent[leadY] = leadX
    }
}

fun printCondition() {
    for (it in 0..countElement - 1) {
        print(parent[it])
        print(" ")
    }
    println()
}

fun clear() {
    parent.clear()
    rank.clear()
    countElement = 0
}

fun main(args: Array<String>) {

}
