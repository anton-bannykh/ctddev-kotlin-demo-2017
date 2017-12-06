package main

/**
 * Created by Maxim on 05.12.2017.
 */

fun compress(a: IntArray) : IntArray{
    val b : IntArray = a.copyOf()
    b.sort()
    for(i in 0..a.size - 1) {
        a[i] = b.binarySearch(a[i])
    }
    return a
}

class FenwickTree {

    private val SIZE : Int
    private var f : IntArray

    constructor(size: Int) {
        SIZE = size
        f = IntArray(size)
    }

    fun add(start: Int, value: Int) {
        var pos = start
        while(pos < SIZE) {
            f[pos] = maxOf(f[pos], value)
            pos = pos or (pos + 1)
        }
    }

    fun get(start: Int) : Int {
        var pos = start
        var result = 0
        while(pos >= 0) {
            result = maxOf(result, f[pos])
            pos = (pos and (pos +1)) - 1
        }
        return result
    }
}

fun getLIS(numbers : IntArray) : Int {
    val a = compress(numbers)
    val tree = FenwickTree(a.size)
    var result : Int = 0
    for(x in a) {
        val curr : Int = tree.get(x - 1) + 1
        result = maxOf(result, curr)
        tree.add(x, curr)
    }
    return result
}



