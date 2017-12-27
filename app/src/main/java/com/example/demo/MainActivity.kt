package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import java.util.Random
import kotlinx.android.synthetic.main.activity_main.*
class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
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
            n == 2 || n == 3 -> return "Simple"

            isOdd(n) -> return "NotSimple"
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
                            return "NotSimple"
                        }
                        if (a == n - 1L) {
                            continue@loop
                        }
                    }
                    return "NotSimple"
                }
                return "Simple"
            }
        }
    }

    fun onClick(v: View) {
        val x = editT.getText().toString().toInt(10)
        answer.setText(simpleCheck(x))

    }
}