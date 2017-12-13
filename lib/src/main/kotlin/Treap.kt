import java.util.*

class Treap(var value: Int) {
    var parent: Treap? = null
    var left: Treap? = null
    var right: Treap? = null
    var size: Int = 1
    var priority: Int = Random().nextInt()
}

fun update(t: Treap): Unit {
    t.size = (t.left?.size ?: 0) + (t.right?.size ?: 0) + 1
}

fun split(t: Treap?, key: Int): Pair<Treap?, Treap?> {
    if(t == null) return Pair(null, null)
    val leftKey = t.left?.size ?: 0
    if (key > leftKey) {
        val r = split(t.right, key - leftKey - 1)
        t.right = r.first
        r.second?.parent = null
        r.first?.parent = t
        update(t)
        return Pair(t, r.second)
    } else {
        val r = split(t.left, key)
        t.left = r.second
        r.first?.parent = null
        r.second?.parent = t
        update(t)
        return Pair(r.first, t)
    }
}

fun merge(t1: Treap?, t2: Treap?): Treap? {
    t1 ?: return t2
    t2 ?: return t1
    if (t1.priority > t2.priority) {
        t1.right = merge(t1.right, t2)
        update(t1)
        t1.right?.parent = t1
        return t1
    } else {
        t2.left = merge(t1, t2.left)
        update(t2)
        t2.left?.parent = t2
        return t2
    }
}

fun getKey(t: Treap?, accum: Int = 0, comeFrom: Treap? = null) : Int {
    t ?: return accum
    comeFrom ?: return getKey(t.parent, accum + (t.left?.size ?: 0) + 1, t)
    if (comeFrom == t.right) return getKey(t.parent, accum + (t.left?.size ?: 0) + 1, t)
    else return getKey(t.parent, accum, t)
}

fun getRoot(t: Treap) : Treap {
    var cur: Treap? = t
    while(cur!!.parent != null) {
        cur = cur.parent
    }
    return cur
}

fun getArray(x: ArrayList<Treap>): ArrayList<Treap> {
    val ans = ArrayList<Treap>(x.size)
    for (i in 0 until x.size) {
        ans[getKey(x[i])-1] = x[i]
    }
    return ans
}

fun slice(left: Treap, right: Treap, to: Treap) {
    if(getKey(left) <= getKey(to) && getKey(to) <= getKey(right) + 1) return
    val after = getKey(to) > getKey(right)
    val (a, _) = split(getRoot(left), getKey(left)-1)
    val (b2, c) = split(getRoot(right), getKey(right))
    val (d, e) = split(getRoot(to), getKey(to))
    if(after) {
        merge(merge(merge(a, b2), d), e)
    } else {
        merge(merge(merge(d, e), b2), c)
    }

}




