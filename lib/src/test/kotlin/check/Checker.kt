package check

class Checker {

    private fun checkFun(args: ArrayList<Boolean>, function: ArrayList<Pair<Int, Int>>): Boolean {
        val n = args.size
        for (cur in function) {
            val first = cur.first
            val second = cur.second
            if (!(if (first >= n) !args[first - n] else args[first]) &&
                    !(if (second >= n) !args[second - n] else args[second])) {
                return false
            }
        }
        return true
    }

    private fun nextBitSet(args: ArrayList<Boolean>): ArrayList<Boolean> {
        for (i in 0 until args.size) {
            if (!args[i]) {
                args[i] = true
                break
            }
            args[i] = false
        }
        return args
    }

    private fun checkNoSolution(n: Int, function: ArrayList<Pair<Int, Int>>): Boolean {
        var ans = ArrayList<Boolean>()
        for (i in 0 until n) {
            ans.add(false)
        }
        for (i in 0 until (1 shl n)) {
            if (checkFun(ans, function)) {
                System.out.println(ans)
                return false
            }
            ans = nextBitSet(ans)
        }
        return true
    }

    private fun getAns(args: String): ArrayList<Boolean> {
        val ans = ArrayList<Boolean>()
        args.replace(" ", "")
                .split("\n")
                .filter { it != "" }
                .mapTo(ans) { it.split("=")[1] == "1" }

        return ans
    }

    fun checkAns(n: Int, args: String, function: ArrayList<Pair<Int, Int>>): Boolean
            = if (args == "NO SOLUTION") checkNoSolution(n, function) else checkFun(getAns(args), function)

}