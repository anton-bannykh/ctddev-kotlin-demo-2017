package good_morning.libLIS

import java.util.PriorityQueue

fun LIS(args: IntArray): IntArray {
    val priorityQueue = PriorityQueue<Int>()
    val predecessors = HashMap<Int, Int?>()

    for (arg in args) {
        predecessors[arg] = priorityQueue.lastOrNull { elm -> elm < arg }
        priorityQueue.removeIf{elm -> elm > arg}
        priorityQueue.add(arg)
    }
    return restoreResult(priorityQueue.size, priorityQueue.last(), predecessors)
}

fun LIS(args: Array<Int>): IntArray {
    val input = IntArray(args.size, {ind -> args[ind]})
    return LIS(input)
}

private fun restoreResult(size: Int, first: Int, predecessors: HashMap<Int, Int?>): IntArray {
    val result = IntArray(size, {0})

    result[size - 1] = first
    var index = size - 1
    while (predecessors[result[index]] != null) {
        result[index - 1] = predecessors[result[index]] ?: break
        index -= 1
    }
    return result
}