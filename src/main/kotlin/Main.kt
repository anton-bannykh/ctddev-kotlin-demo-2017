/**
 * Created by гыук on 03.12.2017.
 */
fun main(args: Array<String>) {
    println("Hi!")
}

fun nextPerm(vararg ints: Int):MutableList<Int> {
    var args:MutableList<Int> = mutableListOf()
    for (i in ints) {
        args.add(i)
    }
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
        var x = res[i]
        res[i] = res[j]
        res[j] = x
        for(j in 0 .. ((n - i - 1) / 2) - 1) {
            x = res[i + 1 + j]
            res[i + 1 + j] = res[n - 1 - j]
            res[n - 1 - j] = x
        }
    }
    return res
}