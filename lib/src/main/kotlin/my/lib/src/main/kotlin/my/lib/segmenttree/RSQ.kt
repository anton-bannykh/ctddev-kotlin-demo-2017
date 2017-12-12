package my.lib.segmenttree

class RSQ(a: Array<Int>) : SegmentTree(a) {
    override val undifined: Int = 0

    override fun f(a: Int, b: Int) = a + b

    override fun push(ind: Int) {
        tree[ind].value += tree[ind].modified * tree[ind].getRange()
        super.push(ind)
    }
}
