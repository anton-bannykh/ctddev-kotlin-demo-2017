fun main(args: Array<String>) {
    println("Hello world!")
}

fun foo() = 10

fun sum(vararg ints: Int) = ints.sum()

fun sumFun(vararg ints: Int) = ints.fold(0) {acc, i -> acc + i }
