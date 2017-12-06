package segmenttree

class RSQ(a: Array<Int>): SegmentTree(a) {
    override val undifined: Int = 0
    override fun f(a: Int, b: Int) = a + b
    override fun addPushing(ind: Int) {
        if (tree[ind].modified == 's') {
            tree[ind].value += tree[ind].getRange() * tree[ind].modValue
        }
    }

    override fun assigmentPushing(ind: Int) {
        if (tree[ind].modified == 'a') {
            tree[ind].value = tree[ind].getRange() * tree[ind].modValue
        }
    }
}