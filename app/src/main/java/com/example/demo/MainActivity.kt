package com.example.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import android.widget.TextView
import my.lib.huffman.HuffmanEncode
import my.lib.sumFun

class MainActivity : AppCompatActivity() {

    private lateinit var mEditText: EditText
    private lateinit var mCodeText: TextView

    private val encoder = HuffmanEncode()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        mEditText = findViewById(R.id.et_encode_text)

        mCodeText = findViewById(R.id.tv_code)
    }

    fun onClick(v : View) {
        val stringToEncode = mEditText.text.toString()

        if (stringToEncode != "") {
            mCodeText.text = buildString {
                append(getString(R.string.input_string_text))
                append(" '$stringToEncode'\n")

                append(getString(R.string.code_text))
                append(" '${encoder.huffmanEncode(stringToEncode)}'")
                append("\n\n\n")

                append(getString(R.string.digits_info))
                append(encoder.digitsCode)
            }
        } else {
            mCodeText.text = getString(R.string.input_string_text)
        }
    }

    fun test() = sumFun(1, 2, 3)
}
