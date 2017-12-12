package com.example.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import my.lib.sumFun

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun startTesting(view: View):Unit {
        val intent = Intent(this, StartTestingActivity::class.java)
        startActivity(intent)
    }
}