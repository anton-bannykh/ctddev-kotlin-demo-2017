class Stack<T> {
    val elements: MutableList<T> = mutableListOf()
    fun isEmpty() = elements.isEmpty()
    fun count() = elements.size
    fun push(item: T) = elements.add(item)
    fun pop(): T? {
        val item = elements.lastOrNull()
        if (!isEmpty()) {
            elements.removeAt(elements.size - 1)
        }
        return item
    }

    fun peek(): T? = elements.lastOrNull()

}

fun cbs(s: String): Boolean {
    val st = Stack<Char>()
    var flag = false
    for (i in s) {
        if (i == '(')
            st.push(i)
        else {
            if (st.isEmpty()) {
                flag = true
                break
            } else
                st.pop()

        }
    }
    return (st.isEmpty() && !flag)

}

/*fun Main(args: Array<String>) {
    val input: String
    val sc = Scanner(System.`in`)
    input = sc.next()
    cbs(input)

}*/