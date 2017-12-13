package com.example.demo

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.AhoCorasick

class MainActivity : AppCompatActivity() {
    val automat = AhoCorasick()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onAddStringClick(v: View){
        automat.addString(addStringEditText.text.toString())
        addStringEditText.setText("")
    }

    @SuppressLint("SetTextI18n") //студия предложила это сделать, зачем?
    fun onWriteTextClick(v: View){
        val res = automat.findPos(writeTextEditText.text.toString());
        output.text = getString(R.string.output) + writeTextEditText.text.toString() + "\n"
        for (i in res)
            output.append(i.toString() + " ")
        writeTextEditText.setText("")
    }
}
