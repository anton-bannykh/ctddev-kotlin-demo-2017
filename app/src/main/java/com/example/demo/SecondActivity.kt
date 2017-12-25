package com.example.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
import com.example.demo.dsl.*
import my.lib.Tree

class SecondActivity : AppCompatActivity() {

    private var num = 0
    private var sequence = ""
    private var errorsFlag = false

    private lateinit var tree : Tree

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    layoutParams = linearLayoutParams {
                        width = MATCH_PARENT
                        height = MATCH_PARENT
                    }
                    val inputLca = editText {
                        hint = extractString(R.string.lca_hint)
                    }
                    button {
                        text = extractString(R.string.run)
                        setOnClickListener {
                            getLca(inputLca)
                        }
                    }
                    textView {
                        id = R.id.textView3
                        text = "0"
                        gravity = Gravity.CENTER
                        textSize = 30F
                    }

                    button {
                        text = extractString(R.string.back)
                        setOnClickListener {
                            backToMainActivity()
                        }
                    }

                    textView {
                        id = R.id.textView4
                    }

                    childrenLayout = linearLayoutParams {
                        width = MATCH_PARENT
                        height = WRAP_CONTENT
                        setMargins(9,9,9,9)
                    }
                }

        )

        val intent = getIntent()
        sequence = intent.getStringExtra("seq")
        num = intent.getIntExtra("num", 1)
        buildLca()
    }

    private fun buildLca() {
        val showErrors = findViewById<View>(R.id.textView4) as TextView
        val tmp = sequence.split(" ")
        if (tmp.size != num - 1) {
            errorsFlag = true
            showErrors.text = "Incorrect sequence length.Return to previous screen and enter correct data."
            return

        }
        val g = Array(num ) { ArrayList<Int>(0) }
        var index = 1
        for (it in tmp) {
            if (Integer.parseInt(it) > tmp.size + 1) {
                errorsFlag = true
                showErrors.text = "Incorrect vertices value. Return to previous screen and enter correct data."
                return
            }
            g[Integer.parseInt(it) - 1].add(index)
            g[index].add(Integer.parseInt(it)-1)
            index++
        }
        tree = Tree(num + 1, g)
    }

    fun getLca(view: View) {
        val showLcaView = view as EditText
        val showOutput = findViewById<View>(R.id.textView3) as TextView
        if (!errorsFlag) {
            val lcaVerticesString = showLcaView.text.toString()
            lcaVerticesString.trim()
            val tmp = lcaVerticesString.split(" ")
            val res = tree.getLca(Integer.parseInt(tmp[0]) - 1, Integer.parseInt(tmp[1]) - 1) + 1
            println(res.toString())
            showOutput.text = res.toString()
            showLcaView.text = null
        } else {
            showOutput.text = "0"
            showLcaView.hint = "Error"
            showLcaView.text = null
        }

    }

    fun backToMainActivity() {
        val intent = Intent(this@SecondActivity, MainActivity::class.java)
        startActivity(intent)
    }
}