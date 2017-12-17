import java.util.ArrayList

class Node(val l: Int, val r: Int, private var value: Int) {
    val nodeL: Node?
    val nodeR: Node?

    init {
        val m = (l + r) / 2
        nodeL = if (l + 1 == r) null else Node(l, m, value)
        nodeR = if (l + 1 == r) null else Node(m, r, value)
    }

    fun setValue(value: Int) {
        this.value = value
    }

    fun getValue() = value
}

class SegmentTree(size: Int, value: Int = 0) {
    private var root: Node = Node(0, size, value)

    operator fun set(pos: Int, value: Int) = _set(root, pos, value)
    private fun _set(cur: Node, pos: Int, value: Int) {
        val m = (cur.l + cur.r) / 2
        if (cur.l + 1 == cur.r)
            cur.setValue(value)
        else {
            if (pos < m)
                _set(cur.nodeL!!, pos, value)
            else
                _set(cur.nodeR!!, pos, value)
            cur.setValue(cur.nodeL!!.getValue() + cur.nodeR!!.getValue())
        }
    }

    fun ask(l: Int, r: Int) = _ask(root, l, r)

    private fun _ask(cur: Node, l: Int, r: Int): Int {
        val m = (cur.l + cur.r) / 2
        return when {
            cur.l == l && r == cur.r -> cur.getValue()
            r <= m -> _ask(cur.nodeL!!, l, r)
            l >= m -> _ask(cur.nodeR!!, l, r)
            else -> _ask(cur.nodeL!!, l, m) + _ask(cur.nodeR!!, m, r)
        }
    }

    fun toList(): List<Int> {
        val one: MutableList<Int> = ArrayList()
        _toList(root, one)
        return one
    }

    private fun _toList(cur: Node, one: MutableList<Int>) {
        if (cur.l + 1 != cur.r) {
            _toList(cur.nodeL!!, one)
            _toList(cur.nodeR!!, one)
        } else {
            one.add(cur.getValue())
        }
    }

    constructor(one: List<Int>) : this(one.size, 0) {
        _build(root, one)
    }

    private fun _build(cur: Node, one: List<Int>) {
        val m = (cur.l + cur.r) / 2
        if (cur.r - cur.l == 1) {
            cur.setValue(one[cur.l])
        } else {
            _build(cur.nodeL!!, one)
            _build(cur.nodeR!!, one)
            cur.setValue(cur.nodeL.getValue() + cur.nodeR.getValue())
        }
    }

    fun size(): Int {
        return root.r - root.l
    }
}