package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import my.lib.sumFun

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun test() = sumFun(1, 2, 3)
}
