package my.lib

fun main(args: Array<String>) {
    println("Hello world!")
}


fun sumFun(vararg ints: Int) = ints.fold(0) { acc, i -> acc + i }

fun search(pat: String, txt: String): ArrayList<Int> {
    val P = 31
    val M = pat.length
    val N = txt.length
    val answer = ArrayList<Int>()

    if (M > N) {
        answer.add(-1)
    } else {
        val hashArray = LongArray(N + 1)
        val powAray = LongArray(N + 1)
        hashArray[0] = 0
        powAray[0] = 1
        var pow: Long = 1

        for (i in 1..N) { //хеши для всех префиксов txt
            hashArray[i] = hashArray[i - 1] + (txt[i - 1] - 'a' + 1) * pow
            pow *= P
            powAray[i] = pow
        }

        var hashT: Long = 0
        for (i in 0..M - 1) {
            hashT += (pat[i] - 'a' + 1) * powAray[i]
        }

        var i = 1
        var j = M
        while (j <= N) {
            val hT = hashT * powAray[i - 1]
            if (hashArray[j] - hashArray[i - 1] == hT) {
                answer.add(i)
            }
            i++
            j++
        }
    }
    return answer
}