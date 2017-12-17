package my.lib

class MyDSU(sizeX: Int) {

<<<<<<< HEAD
    private var parent: Array<Int>? = Array(sizeX + 1, { it })
    private var size: Array<Int>? = Array(sizeX + 1, { 1 })
    private var countSets: Int = sizeX

=======
    private var parent: Array<Int>? = null
    private var size: Array<Int>? = null
    private var countSets: Int = 0

    parent = Array(sizeX + 1, { it })
    size = Array(sizeX + 1, { 1 })
    countSets = sizeX
    
>>>>>>> f477fd8b2a99a2af1c42b313f2f4716e4fb413a7
    fun size(x: Int): Int {
        return size!![find(x)]
    }

    fun countSets(): Int {
        return countSets
    }

    fun union(x: Int, y: Int) {
        var x = find(x)
        var y = find(y)
        if (x == y) return
        if (size!![x] > size!![y]) x = y.also { y = x }
        parent!![x] = y
        size!![y] += size!![x]
        countSets -= 1
    }

    fun find(x: Int): Int {
        if (parent!![x] == x)
            parent!![x] = x
        else
            parent!![x] = find(parent!![x])
        return parent!![x]
    }
}

