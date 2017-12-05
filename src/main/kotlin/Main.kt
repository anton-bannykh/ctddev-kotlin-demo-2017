class vertex {
    var alph = arrayOfNulls<edge>(28)
    var suf_link = vertex()
}

class edge {
    var left = 0
    var right = 0
    var end = vertex()
    var begin = vertex()
}

fun ukkonen(sa : String) : Int {
    val a = sa + "$"
    val l = a.length
    var imag = vertex()
    var root = vertex()
    var count = 0
    for (i in 0..27) {
        imag.alph[i] = edge()
        (imag.alph[i])!!.left = -1
        (imag.alph[i])!!.right = 0
        (imag.alph[i])!!.end = root
        (imag.alph[i])!!.begin = imag
    }
    root.suf_link = imag
    val int_a = 'a'.toInt()
    var index_on_edge = -1
    var u = root
    var e = edge()
    for (i in 0..l - 1) {
        if (index_on_edge != -1) {
            if (a[i] == a[e.left + index_on_edge +1]) {
                if (index_on_edge + 1 + e.left == e.right - 1) {
                    u = e.end
                    index_on_edge = -1
                } else {
                    index_on_edge++
                }
            } else {
                count += 2
                var e1 = edge()
                var u1 = vertex()
                e1.left = e.left + index_on_edge + 1
                e1.right = e.right
                e1.begin = u1
                e1.end = e.end
                u1.alph[a[e1.left].toInt() - int_a] = e1
                e.right = e.left + index_on_edge + 1
                e.end = u1
                var e2 = edge()
                e2.left = i
                e2.right = l
                var u2 = vertex()
                e2.end = u2
                e2.begin = u1
                u1.alph[a[i].toInt() - int_a] = e2
                var uf = e.begin.suf_link
                var ef = uf.alph[a[e.left].toInt() - int_a]!!
                while (uf != root) {
                    count += 2
                    var ef1 = edge()
                    var uf1 = vertex()
                    ef1.left = ef.left + index_on_edge + 1
                    ef1.right = ef.right
                    ef1.begin = uf1
                    ef1.end = ef.end
                    uf1.alph[a[ef1.left].toInt() - int_a] = ef1
                    ef.right = ef.left + index_on_edge + 1
                    ef.end = uf1
                    var ef2 = edge()
                    ef2.left = i
                    ef2.right = l
                    var uf2 = vertex()
                    ef2.end = uf2
                    ef2.begin = uf1
                    uf1.alph[a[i].toInt() - int_a] = ef2
                    uf = ef.begin.suf_link
                    ef = uf.alph[a[e.left].toInt() - int_a]!!
                }
                u = u1
                if (e1.right - e1.left > 1) {
                    index_on_edge = 0
                } else {
                    index_on_edge = -1
                    u = e1.end
                }
            }
        } else {
            var e1 = u.alph[a[i].toInt() - int_a]
            if (e1 == null) {
                count++
                e1 = edge()
                e1.left = i
                e1.right = l
                e1.begin = u
                e1.end = vertex()
                u.alph[a[i].toInt() - int_a] = e1
                var uf = u.suf_link
                while (uf != imag) {
                    count++
                    var ef1 = edge()
                    ef1.left = i
                    ef1.right = l
                    ef1.begin = uf
                    ef1.end = vertex()
                    uf.alph[a[i].toInt() - int_a] = ef1
                }
            } else {
                if (e1.right == e1.left + 1) {
                    u = e1.end
                } else {
                    index_on_edge = 0
                }
            }
        }
    }
    return count
}