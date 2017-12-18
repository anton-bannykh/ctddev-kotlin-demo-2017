package com.example.DFA

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main2.*

class Main2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        getAnswer()
    }

    companion object {
        const val save = "0"
    }

    fun getAnswer() {
        Ans.text = intent.getStringExtra(save)
    }

}
