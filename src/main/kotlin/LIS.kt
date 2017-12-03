import java.util.PriorityQueue

fun LIS(args: Array<Int>): Array<Int> {
    val priorityQueue = PriorityQueue<Int>()
    val predecessors = HashMap<Int, Int?>()

    for (arg in args) {
        predecessors[arg] = priorityQueue.lastOrNull { elm -> elm < arg }
        priorityQueue.removeIf{elm -> elm > arg}
        priorityQueue.add(arg)
    }
    return restoreResult(priorityQueue.size, priorityQueue.last(), predecessors)
}

fun restoreResult(size: Int, first: Int, predecessors: HashMap<Int, Int?>): Array<Int> {
    val result = Array(size, {_->0})

    result[size - 1] = first
    var index = size - 1
    while (predecessors[result[index]] != null) {
        result[index - 1] = predecessors[result[index]] as Int
        index -= 1
    }
    return result
}
