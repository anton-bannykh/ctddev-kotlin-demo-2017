package com.example.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import my.lib.sumFun
import android.widget.LinearLayout
import com.example.demo.DslBuilder.*
import com.example.demo.DslBuilder.MATCH_PARENT


class MainActivity : AppCompatActivity() {

    private var numOfVertices = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                linearLayout {
                    orientation = LinearLayout.VERTICAL
                    layoutParams = linearLayoutParams {
                        width = MATCH_PARENT
                        height = MATCH_PARENT

                    }
                   val inputVertices = editText {
                        id = R.id.plain_text_input
                        hint = extractString(R.string.vertices_hint)
                        textSize = 14F
                    }
                    button {
                        id = R.id.button
                        text = extractString(R.string.submit_num_of_vertices)
                        textSize = 14F
                        setOnClickListener {
                            readNumOfVertices(inputVertices)
                        }
                    }
                    textView {
                        text = extractString(R.string.sequence_input_description)
                        textSize = 14F
                    }
                    val inputSequence = editText {}
                    button {
                        text = extractString(R.string.submit_sequence_of_vertices)
                        setOnClickListener {
                            readSequenceOfVertices(inputSequence)
                        }
                    }

                    childrenLayout = linearLayoutParams {
                        width = MATCH_PARENT
                        height = WRAP_CONTENT
                        setMargins(9,9,9,9)
                    }


                }

        )

    }

    fun readNumOfVertices(view: View) {
        val showInpEditText = view as EditText
        val numOfVerticesString = showInpEditText.text.toString()
        if (numOfVerticesString == "") {
            showInpEditText.hint = "Please enter num of vertices"
        } else {
            numOfVertices = Integer.parseInt(numOfVerticesString)
            if (numOfVertices == 0) {
                showInpEditText.hint = "Number must be greater than zero."
                showInpEditText.text = null
                numOfVertices = 1
            }
            showInpEditText.hint = "Accepted."
            showInpEditText.text = null
            findViewById<View>(R.id.button).isClickable = false
        }

    }

    fun readSequenceOfVertices(view: View) {
        val showInpEditText = view as EditText
        val verticesSequenceString = showInpEditText.text.toString()
        verticesSequenceString.trim()
        val intent = Intent(this@MainActivity, SecondActivity::class.java)
        intent.putExtra("seq", verticesSequenceString)
        intent.putExtra("num", numOfVertices)
        startActivity(intent)

    }

    fun test() = sumFun(1, 2, 3)


}
