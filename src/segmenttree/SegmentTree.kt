package segmenttree

open abstract class SegmentTree(arr: Array<Int>) {
    val h: Int = math.log2(arr.size) + 1
    val firstLeaf: Int = math.pow2(h - 1)
    val size: Int = math.pow2(h)
    val tree: Array<Cell>

    abstract val undifined: Int
    abstract fun f(a: Int, b: Int): Int
    abstract fun addPushing(ind: Int)
    abstract fun assigmentPushing(ind: Int)

    init {
        tree = Array<Cell>(size, {i -> Cell()})
        for (i in arr.indices) {
            tree[firstLeaf + i] = Cell(arr[i], i, i)
        }
        for (i in arr.size..(firstLeaf-1)) {
            tree[firstLeaf + i] = Cell(undifined, i, i)
        }
        for (i in (firstLeaf - 1) downTo 1) {
            tree[i] = Cell(update(i), tree[left(i)].l, tree[right(i)].r)
        }
    }

    fun left(ind: Int): Int = ind * 2
    fun right(ind: Int): Int = ind * 2 + 1

    fun update(ind: Int): Int {
        tree[ind].value = f(tree[left(ind)].value, tree[right(ind)].value)
        return tree[ind].value
    }

    fun tryToPush(ind: Int, here: Int) {
        if (ind < firstLeaf) {
            tree[here].setModify(tree[ind].modified, tree[ind].modValue)
        }
    }

    fun push(ind: Int) {
        if (tree[ind].isModify()) {
            addPushing(ind)
            assigmentPushing(ind)
            tryToPush(ind, left(ind))
            tryToPush(ind, right(ind))
            tree[ind].resetModify()
        }
    }

    fun findAnswer(l: Int, r: Int, toEx: (cell: Cell) -> Unit = {}, ind: Int = 1, w: Int = 0): Int {
        push(ind)
        val res = when {
            (l == tree[ind].l && r == tree[ind].r) -> { toEx(tree[ind]); return tree[ind].value }
            (r <= tree[left(ind)].r) -> findAnswer(l, r, toEx, left(ind), 1)
            (l >= tree[right(ind)].l) -> findAnswer(l, r, toEx, right(ind), 2)
            else -> f(findAnswer(l, tree[left(ind)].r, toEx, left(ind), 3), findAnswer(tree[right(ind)].l, r, toEx, right(ind), 4))
        }
        if (ind < firstLeaf) {
            update(ind)
        }
        return res
    }

    fun modificationFun(com: Char, value: Int): (cell: Cell) -> Unit {
        return {cell -> cell.modified = com; cell.modValue = value}
    }

    fun add(l: Int, r: Int, value: Int) {
        findAnswer(l, r, modificationFun('s', value))
    }

    fun assignment(l: Int, r: Int, value: Int) {
        findAnswer(l, r, modificationFun('a', value))
    }
}
