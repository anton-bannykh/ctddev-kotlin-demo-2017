package com.example.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import com.example.demo.dsl.constraintLayout
import com.example.demo.dsl.onCLick
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(R.id.layoutId) {
                    val textView = textView(R.id.description) {
                        text = ""
                        topConstaint(R.id.layoutId, TOP, dp(4))
                        bottomConstaint(R.id.number, TOP, dp(100))
                        center(R.id.layoutId)
                    }
                    val number = editView(R.id.number) {
                        topConstaint(R.id.description, BOTTOM, dp(100))
                        bottomConstaint(R.id.number, TOP, dp(100))
                        center(R.id.layoutId)
                    }


                    val ans = textView(R.id.answer) {
                        topConstaint(R.id.calculate, TOP, dp(100))
                        bottomConstaint(R.id.layoutId, BOTTOM, dp(50))
                        center(R.id.layoutId)

                        gravity = 1
                    }

                    button(R.id.calculate) {
                        topConstaint(R.id.number, BOTTOM, dp(100))
                        bottomConstaint(R.id.answer, TOP, dp(100))
                        center(R.id.layoutId)

                        text = "CHECK"

                        onCLick {

                            val a = number.text.toString().toInt()


                            ans.text = a.toString() + " is " + simpleCheck(a)

                        }
                        gravity = 1
                    }


                }
        )
    }

    fun isOdd(n: Int): Boolean {
        return (n % 2 == 0)
    }

    fun pow(a: Long, t: Long, mod: Int): Long {
        var b = a
        var c = 1L
        var _t = t
        while (_t > 0) {
            if (_t % 2 == 1L) {
                c *= b
                c %= mod
            }
            b = b * b % mod
            _t /= 2L
        }
        return c
    }

    fun simpleCheck(n: Int): String {
        when {
            n == 2 || n == 3 -> return "simple"

            isOdd(n) -> return "not simple"
            else -> {
                loop@ for (i in 1..20) {
                    var x: Long = n - 1L;
                    var s = 0

                    while (x % 2 == 0L) {
                        x /= 2
                        s++
                    }
                    val random = Random()
                    var a = (random.nextInt(n - 4) + 2).toLong()
                    a = pow(a, x, n)
                    if (a == 1L || a == n - 1L)
                        continue@loop

                    for (j in 1..s) {
                        a = a * a % n
                        if (a == 1L) {
                            return "not simple"
                        }
                        if (a == n - 1L) {
                            continue@loop
                        }
                    }
                    return "not simple"
                }
                return "simple"
            }
        }
    }


}