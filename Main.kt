fun <T> lcs(first_seq: List<T>, second_seq: List<T>): Int {
    val n = first_seq.size
    val m = second_seq.size
    val ans = Array(n + 1) { IntArray( m + 1) }
    for (i in 1..n)
        for (j in 1..m)
            ans[i][j] = if (first_seq[i - 1] == second_seq[j - 1]) {
                ans[i - 1][j - 1] + 1
            } else {
                maxOf(ans[i - 1][j], ans[i][j - 1])
            }
    return ans[n][m]
}
