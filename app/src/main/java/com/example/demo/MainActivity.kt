package com.example.demo

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import calculateTSP
import java.util.Random

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(R.id.layout) {
                    val input = editText(R.id.input) {
                        hint = "Введите координаты вершин"
                        topConstaint(R.id.layout, TOP, dp(8))
                        bottomConstaint(R.id.answer, TOP, dp(8))
                        startConstaint(R.id.layout, START, dp(8))
                        endConstaint(R.id.layout, END, dp(8))
                    }
                    button(R.id.calculate) {
                        text = "РЕШЕНИЕ"
                        bottomConstaint(R.id.layout, BOTTOM, dp(8))
                        endConstaint(R.id.layout, END, dp(8))
                        onCLick {
                            clickTest(this)
                        }

                    }

                    val ans = textView(R.id.answer) {
                        text = "Введите координаты вершин и нажмите \"решение\""
                        bottomConstaint(R.id.calculate, TOP, dp(8))
                        startConstaint(R.id.layout, START, dp(8))
                        endConstaint(R.id.layout, END, dp(8))
                    }




                    button(R.id.random) {
                        text = "RANDOM"
                        bottomConstaint(R.id.calculate, BOTTOM, dp(0))
                        startConstaint(R.id.layout, START, dp(8))
                        topConstaint(R.id.calculate, TOP, dp(0))
                        onCLick {
                            clickRandom(this)
                        }
                    }
                }
        )
    }

    @SuppressLint("SetTextI18n", "ResourceType")
    fun clickTest(view: View) {
        val answer = findViewById<TextView>(R.id.answer)
        val input = findViewById<EditText>(R.id.input)
        answer.text = "Загрузка"

        val x = parse(input.text.toString())

        if (x == null) {
            answer.text = "Вердикт: Координаты заданы некоректно"
            return
        }
        if (x.isEmpty() || x.size % 2 != 0) {
            answer.text = "Вердикт: Не хватает чисел"
            return
        }
        val e = Array(x.size / 2, {i -> Pair(x[i * 2], x[i * 2 + 1])})

        val ans = calculateTSP(e)

        answer.text = "Результат: ${ans.first}"
    }

    private fun parse(s: String) : DoubleArray? {
        val t = s.replace("\n", " ")
        val a = t.split(" ")
        val k = a.count { it.toDoubleOrNull() != null }
        val x = DoubleArray(k, {0.0})
        var j = 0

        for (i in a.indices) {
            if (a[i].toDoubleOrNull() != null) {
                x[j] = a[i].toDouble()
                j++
            }
            else if (!a[i].isEmpty()) {
                return null
            }
        }

        return x
    }

    fun clickRandom(view: View) {
        val random = Random()
        val n = random.nextInt(20)
        val s = StringBuilder("")
        for (i in 1..n) {
            s.append(random.nextInt(30))
            s.append(" ")
            s.append(random.nextInt(30))
            s.append("\n")
        }
        val input = findViewById<EditText>(R.id.input)
        input.text.clear()
        input.text.insert(0, s)
    }


}
