import java.util.*

fun randomShuffle(array: Array<Int>) {
    val random = Random()
    for (ind in array.indices) {
        val ind0 = random.nextInt(array.size - ind) + ind
        array[ind] = array[ind0].also { array[ind0] = array[ind] }
    }
}

fun treeRandomPermutation(): VanEmdeBoas {
    val tree = VanEmdeBoas(5)
    val input = Array(32, { a->a })
    randomShuffle(input)
    for (i in input){
        print("$i  ")
    }
    println()
    input.forEach {elm -> tree.add(elm)}

    return tree
}