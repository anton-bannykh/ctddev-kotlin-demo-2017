package my.lib

fun main(args: Array<String>) {
    println("Hello world!")
}

fun MutableList<Int>.swap(index1: Int, index2: Int) {
    val x = this[index1]
    this[index1] = this[index2]
    this[index2] = x
}

fun nextPerm(args:MutableList<Int>):MutableList<Int> {
    var i = args.size - 2
    val n:Int = args.size
    var res:MutableList<Int> = mutableListOf()
    if (i > 0) {
        while ((i >= 0) and (args[i] > args[i + 1])) {
            i -= 1
            if (i < 0) {
                break
            }
        }
    }
    if (i < 0){
        for(j in 0 .. n - 1) {
            res.add(0)
        }
        return res
    } else {
        var j = args.size - 1
        while(args[j] <= args[i]){
            j -= 1
        }
        for(j in 0 .. n - 1) {
            res.add(args[j])
        }
        res.swap(i, j)
        for(j in 0 .. ((n - i - 1) / 2) - 1) {
            res.swap(i + 1 + j, n - 1 - j)
        }
    }
    return res
}