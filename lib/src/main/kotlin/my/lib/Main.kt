package my.lib

fun main(args: Array<String>) {
    println("Hello world!")
}

fun foo() = 10

fun sum(vararg ints: Int): Int {
    var result = 0
    for (v in ints) {
        result += v
    }
    return result
}

fun sumFun(vararg ints: Int) = ints.fold(0) { acc, i -> acc + i }