fun main(args: Array<String>) {
    println("Hello world!")
}

val d = 256
fun search(pat: String, txt: String, q: Int): Int {
    var result = 0
    val M = pat.length
    val N = txt.length
    var p = 0 // hash value for pattern
    var t = 0 // hash value for txt
    var h = 1
    var i: Int
    var j: Int
    i = 0
    while (i < M - 1) {
        h = h * d % q
        i++
    }
    i = 0
    while (i < M) {
        p = (d * p + pat[i].toInt()) % q
        t = (d * t + txt[i].toInt()) % q
        i++
    }
    i = 0
    while (i <= N - M) {
        if (p == t) {
            j = 0
            while (j < M) {
                if (txt[i + j] != pat[j])
                    break
                j++
            }
            if (j == M)
                result = i + 1
        }
        if (i < N - M) {
            t = (d * (t - txt[i].toInt() * h) + txt[i + M].toInt()) % q
            if (t < 0)
                t += q
        }
        i++
    }
    return result
}