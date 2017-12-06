package segmenttree

open abstract class SegmentTree(arr: Array<Int>) {
    val h: Int = math.log2(arr.size) + 1
    val firstLeaf: Int = math.pow2(h - 1)
    val size: Int = math.pow2(h)
    val tree: Array<Cell>

    abstract val undifined: Int
    abstract fun f(a: Int, b: Int): Int

    init {
        tree = Array<Cell>(size, { i -> Cell() })
        for (i in arr.indices) {
            tree[firstLeaf + i] = Cell(arr[i], i, i)
        }
        for (i in arr.size..(firstLeaf - 1)) {
            tree[firstLeaf + i] = Cell(undifined, i, i)
        }
        for (i in (firstLeaf - 1) downTo 1) {
            tree[i] = Cell(update(i), tree[left(i)].l, tree[right(i)].r)
        }
    }

    fun left(ind: Int): Int = ind * 2
    fun right(ind: Int): Int = ind * 2 + 1

    fun update(ind: Int): Int {
        push(left(ind))
        push(right(ind))
        tree[ind].value = f(tree[left(ind)].value, tree[right(ind)].value)
        return tree[ind].value
    }

    open fun push(ind: Int) {
        if (ind < firstLeaf) {
            tree[left(ind)].modified += tree[ind].modified
            tree[right(ind)].modified += tree[ind].modified
        }
        tree[ind].modified = 0
    }

    fun findAnswer(l: Int, r: Int, toEx: (cellInd: Int) -> Unit = {}, ind: Int = 1): Int {
        push(ind)
        if (l == tree[ind].l && r == tree[ind].r) {
            //println("found: $l -> $r ... ${tree[ind].value}")
            toEx(ind)
            return tree[ind].value
        }
        val res = when {
            (l == tree[ind].l && r == tree[ind].r) -> { toEx(ind); return tree[ind].value }
            (r <= tree[left(ind)].r) -> findAnswer(l, r, toEx, left(ind))
            (l >= tree[right(ind)].l) -> findAnswer(l, r, toEx, right(ind))
            else -> f(findAnswer(l, tree[left(ind)].r, toEx, left(ind)), findAnswer(tree[right(ind)].l, r, toEx, right(ind)))
        }
        update(ind)
        return res
    }

    fun add(l: Int, r: Int, value: Int) {
        findAnswer(l, r, { cellInd -> tree[cellInd].modified += value; push(cellInd) })
    }

    fun look() {
        for (i in tree.indices) {
            println("$i. ${tree[i].value} ${tree[i].modified}")
        }
    }
}
