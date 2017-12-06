private fun prefixFunction(s: String): Array<Int> {
    val pi = Array(s.length + 1, { 0 })
    for (i in 1 until s.length) {
        var j = pi[i - 1]
        while (j > 0 && s[j] != s[i])
            j = pi[j - 1]
        if (s[i] == s[j])
            pi[i] = j + 1
    }
    return pi
}

fun searchSubstring(s: String, k: String): Int {
    val pi = prefixFunction(k + 0.toChar() + s)
    for (ind in k.length until pi.size)
        if (pi[ind] == k.length) {
            return ind - 2 * k.length
        }
    return -1
}