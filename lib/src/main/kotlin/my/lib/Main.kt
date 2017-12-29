package my.lib

private fun prefixFunction(s: String): Array<Int> {
    val p = Array(s.length, { 0 })

    for (i in 1 until s.length) {
        var k = p[i - 1]
        while (k > 0 && s[i] != s[k])
            k = p[k - 1]
        if (s[i] == s[k])
            k++
        p[i] = k
    }

    return p;
}

fun substringSearch(s1: String, s2: String): ArrayList<Int> {
    val answer = ArrayList<Int>()
    if(s1.isEmpty()||s2.isEmpty()) return answer

    val sLength = s1.length

    val s = s1 + "#" + s2
    val p = prefixFunction(s)

    for (i in sLength until p.size) {
        if (p[i] == sLength) {
            answer.add(i - 2 * sLength)
        }
    }

    return answer
}
