import java.util.*

class Treap {
    var parent: Treap? = null
    var left: Treap? = null
    var right: Treap? = null
    var size: Int = 1
    var value: Int = -1
    var priority: Int = Random().nextInt()
}

class PairTreap {
    var first: Treap? = null
    var second: Treap? = null
}

fun update(t: Treap): Unit {
    t.size = (t.left?.size ?: 0) + (t.right?.size ?: 0) + 1
}

fun split(t: Treap?, key: Int): PairTreap {
    val ans = PairTreap()
    if(t == null) return ans
    val leftKey = t.left?.size ?: 0
    if (key > leftKey) {
        val r = split(t.right, key - leftKey - 1)
        t.right = r.first
        r.second?.parent = null
        r.first?.parent = t
        ans.first = t
        ans.second = r.second
    } else {
        val r = split(t.left, key)
        t.left = r.second
        r.first?.parent = null
        r.second?.parent = t
        ans.first = r.first
        ans.second = t
    }
    update(t)
    return ans
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

