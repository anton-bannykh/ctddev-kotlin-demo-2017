fun suffixArray(input: String): IntArray {
    val delimiter = 'a' - 1
    val alphabetSize = 27

    val t = input + delimiter
    val n = t.length
    val s = IntArray(n)
    val cnt = IntArray(alphabetSize)
    val p = IntArray(n)
    var c = IntArray(n)

    for (i in 0 until n) s[i] = t[i].toInt() - delimiter.toInt()
    for (i in 0 until n) cnt[s[i]]++
    for (i in 1 until alphabetSize) cnt[i] += cnt[i - 1]
    for (i in 0 until n) p[--cnt[s[i]]] = i

    c[p[0]] = 0
    var cl = 0
    for (i in 1 until n) {
        if (s[p[i]] != s[p[i - 1]]) cl++
        c[p[i]] = cl
    }

    val pn = IntArray(n)
    var cn: IntArray
    var k = 1

    while (k < n) {
        cn = IntArray(n)
        cnt.fill(0)
        for (i in 0 until n) pn[i] = (p[i] - (k) + n) % n

        for (i in 0 until n) ++cnt[c[pn[i]]]
        for (i in 1 until c[p[n - 1]] + 1) cnt[i] += cnt[i - 1]
        for (i in n - 1 downTo 0) p[--cnt[c[pn[i]]]] = pn[i]

        cn[p[0]] = 0
        for (i in 1 until n) {
            if (c[p[i]] == c[p[i - 1]] && c[(p[i] + (k)) % n] == c[(p[i - 1] + (k)) % n]) {
                cn[p[i]] = cn[p[i - 1]]
            } else {
                cn[p[i]] = cn[p[i - 1]] + 1
            }
        }

        c = cn
        k*=2
    }

    val answer = IntArray(n - 1)
    System.arraycopy(p, 1, answer, 0, n - 1)

    return answer
}
