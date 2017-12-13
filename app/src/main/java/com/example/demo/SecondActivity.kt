/*SecondActivity*/
package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_second.*
import my.lib.LCA

//import java.util.*

class SecondActivity : AppCompatActivity() {

    fun rand(from: Int, to: Int): Int {
        if (from == to) {
            return from
        } else {
            //return Random().nextInt(to - from) + from
            return 1
        }
    }

    fun makeRandomTest(n: Int, m: Int): Pair<Array<Int>, Array<Pair<Int, Int>>> {
        var arr = Array<Int>(n, { 0 })
        var req = Array<Pair<Int, Int>>(m, { Pair(0, 0) })
        for (i in 1..(n - 1)) {
            if (i == 1) {
                arr[i] = 1
                continue
            }
            arr[i] = rand(1, i)
        }
        for (i in 0..(m - 1)) {
            req[i] = Pair(rand(1, n), rand(1, n))
        }
        return Pair(arr, req)
    }

    fun startAndShowRandomTest() {
        val countN = intent.getIntExtra("countN", 0)
        val countM = intent.getIntExtra("countM", 0)
        val data = makeRandomTest(countN, countM)
        var lca = LCA(countN, data.first)
        var ans = Array<Int>(countM, { 0 })
        for (i in 0..(countM - 1)) {
            ans[i] = lca.lca(data.second[i].first, data.second[i].second)
        }
        var dataFirstString: String = "\n"
        for (i in 0..(data.first.size - 1)) {
            dataFirstString += data.first[i].toString() + " "
        }
        var dataSecondString: String = "\n"
        for (i in 0..(data.second.size - 1)) {
            dataSecondString += "(" + data.second[i].first.toString() + "," + data.second[i].second.toString() + ")\n"
        }
        var ansString: String = ""
        for (i in 0..(ans.size - 1)) {
            ansString += ans[i].toString() + " "
        }
        textViewTest.text = getString(R.string.test, dataFirstString, dataSecondString)
        textViewAnswer.text = getString(R.string.answer, ansString)
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
        startAndShowRandomTest()
    }

}
