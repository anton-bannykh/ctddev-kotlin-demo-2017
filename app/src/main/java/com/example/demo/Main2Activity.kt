package com.example.demo

import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import my.lib.MyDSU

class Main2Activity : AppCompatActivity() {

    lateinit var DSU: MyDSU

    lateinit var btn: Button
    lateinit var txtUnion1: EditText
    lateinit var txtUnion2: EditText
    lateinit var txtFind3: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)
        txtUnion1 = findViewById(R.id.editText4)
        txtUnion2 = findViewById(R.id.editText3)
        txtFind3 = findViewById(R.id.editText)
        val d = intent.getIntExtra("NUMS", 0)
        if (d == 0) {
            throw RuntimeException("Unexpected behavior")
        }
        DSU = MyDSU(d)
    }

    fun unionBtn(v: View) {
        val x = txtUnion1.text.toString().toInt()
        val y = txtUnion2.text.toString().toInt()
        DSU.union(x, y)
        Toast.makeText(this,"OK", Toast.LENGTH_SHORT ).show()
    }

    fun findBtn(v: View) {
        val x = txtFind3.text.toString().toInt()
        var ans = DSU.find(x)
        Toast.makeText(this, ans.toString(), Toast.LENGTH_LONG).show()
    }

}
