package my.lib

public class Automation(private val numberOfVertexes: Int,
                        private val numberOfEdges: Int,
                        private val stringOfTerminals: String,
                        private val stringOfAutomate: String) {

    private val terminals = mutableListOf<Int>()
    private val G = Array(numberOfVertexes, { mutableListOf<Int>() })
    private val reverseG = Array(numberOfVertexes, { mutableListOf<Int>() })
    private val arr = Array(numberOfVertexes, { 0 })
    private val sumArr = Array(numberOfVertexes, { 0 })
    private val out = mutableListOf<Int>()
    private var key = 1

    private fun dfs(k: Int) {
        arr[k - 1] = 1
        for (i in 0 until G[k - 1].size) {
            if (arr[G[k - 1][i] - 1] == 0) {
                dfs(G[k - 1][i])
            }
        }
    }

    private fun secondDfs(k: Int) {
        arr[k - 1] = 2
        for (i in 0 until reverseG[k - 1].size) {
            if (arr[reverseG[k - 1][i] - 1] == 1) {
                secondDfs(reverseG[k - 1][i])
            }
        }
    }

    private fun cycle(k: Int) {
        if (key == 0) {
            return
        }
        arr[k - 1] = 3
        for (i in 0 until G[k - 1].size) {
            if (arr[G[k - 1][i] - 1] == 2) {
                cycle(G[k - 1][i])
            }
            if (arr[G[k - 1][i] - 1] == 3) {
                key = 0
                break
            }
        }
        arr[k - 1] = 4
        return
    }

    private fun anss(t: Int): Int {
        if (arr[t - 1] == 5) {
            return sumArr[t - 1]
        }
        var sum: Int = 0
        for (i in 0 until reverseG[t - 1].size) {
            if (arr[reverseG[t - 1][i] - 1] == 4 || arr[reverseG[t - 1][i] - 1] == 5) {
                sum += anss(reverseG[t - 1][i])
            }
        }
        arr[t - 1] = 5
        sumArr[t - 1] = sum
        return sum
    }

    fun numberOfWords(): Int {
        val termsS = stringOfTerminals.split(" ")
        for (i in termsS) {
            if (i == "") {
                continue
            }
            terminals.add(i.toInt())
        }

        val autoS = stringOfAutomate.split("\\s".toRegex())
        for (i in 0 until numberOfEdges) {
            val l = autoS[i * 3].toInt()
            val r = autoS[i * 3 + 1].toInt()
            G[l - 1].add(r)
            reverseG[r - 1].add(l)
        }

        dfs(1)

        for (i in 0 until terminals.size) {
            if (arr[terminals[i] - 1] == 1) {
                out.add(terminals[i])
            }
        }

        if (out.size == 0) {
            return 0
        }

        for (i in 0 until out.size) {
            secondDfs(out[i])
        }

        cycle(1)

        if (key == 0) {
            return -1
        }

        sumArr[0] = 1
        arr[0] = 5
        var answer: Int = 0

        for (i in 0 until out.size) {
            answer += anss(out[i])
        }

        return answer
    }
}

