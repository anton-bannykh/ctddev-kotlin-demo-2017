package my.lib

class MyDSU(sizeX: Int) {

    private val parent = Array(sizeX + 1, { it })
    private val size = Array(sizeX + 1, { 1 })
    private var countSets = sizeX

    fun size(x: Int): Int {
        return size[find(x)]
    }

    fun countSets(): Int {
        return countSets
    }

    fun union(x: Int, y: Int) {
        var x = find(x)
        var y = find(y)
        if (x == y) return
        if (size[x] > size[y]) x = y.also { y = x }
        parent[x] = y
        size[y] += size[x]
        countSets -= 1
    }

    fun find(x: Int): Int {
        if (parent[x] == x)
            parent[x] = x
        else
            parent[x] = find(parent[x])
        return parent[x]
    }
}

