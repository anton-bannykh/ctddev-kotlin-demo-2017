package vladsaif.kotlin

import java.util.Random
import kotlin.collections.ArrayList

class Treap(var value: Int) {
    var parent: Treap? = null
    var left: Treap? = null
    var right: Treap? = null
    var size: Int = 1
    var priority: Int = Random().nextInt()
}

fun update(t: Treap) {
    t.size = (t.left?.size ?: 0) + (t.right?.size ?: 0) + 1
}

fun split(t: Treap?, key: Int): Pair<Treap?, Treap?> {
    if (t == null) return Pair(null, null)
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

fun merge(_t1: Treap?, _t2: Treap?, b: Boolean = true): Treap? {
    var t1 = _t1
    var t2 = _t2
    if (b) {
        if (t1 != null) t1 = getRoot(t1)
        if (t2 != null) t2 = getRoot(t2)
    }
    t1 ?: return t2
    t2 ?: return t1
    if (t1.priority > t2.priority) {
        t1.right = merge(t1.right, t2, false)
        update(t1)
        t1.right?.parent = t1
        return t1
    } else {
        t2.left = merge(t1, t2.left, false)
        update(t2)
        t2.left?.parent = t2
        return t2
    }
}

fun getKey(t: Treap?, accum: Int = 0, comeFrom: Treap? = null): Int {
    t ?: return accum
    comeFrom ?: return getKey(t.parent, accum + (t.left?.size ?: 0) + 1, t)
    if (comeFrom == t.right) return getKey(t.parent, accum + (t.left?.size ?: 0) + 1, t)
    else return getKey(t.parent, accum, t)
}

fun getRoot(t: Treap): Treap {
    var cur: Treap? = t
    while (cur!!.parent != null) {
        cur = cur.parent
    }
    return cur
}

fun getArray(x: ArrayList<Treap>): ArrayList<Treap> {
    val ans = Array(x.size) { Treap(0) }
    for (i in 0 until x.size) {
        ans[getKey(x[i]) - 1] = x[i]
    }
    val k = ArrayList<Treap>()
    k.addAll(ans)
    return k
}

fun slice(left: Treap, right: Treap, to: Treap) {
    if (getKey(left) <= getKey(to) && getKey(to) <= getKey(right) + 1) return
    val after = getKey(to) > getKey(right)
    val (a, _) = split(getRoot(left), getKey(left) - 1)
    val (b2, c) = split(getRoot(right), getKey(right))
    val (d, e) = split(getRoot(to), getKey(to) - 1)
    if (after) {
        merge(merge(merge(a, d), b2), e)
    } else {
        merge(merge(merge(d, b2), e), c)
    }
}