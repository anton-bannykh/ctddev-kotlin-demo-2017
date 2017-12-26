package com.example.demo

import android.content.Intent
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.InputType
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout

class MainActivity : AppCompatActivity() {

    lateinit var text: EditText
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        // setContentView(R.layout.activity_main)
        //text = findViewById<EditText>(R.id.input)
        val linearL: LinearLayout = LinearLayout(this)
        linearL.orientation = LinearLayout.VERTICAL
        val lPar: LinearLayout.LayoutParams = LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT)
        setContentView(linearL, lPar)



        text = EditText(this)
        text.setInputType(InputType.TYPE_CLASS_PHONE)
        linearL.addView(text)
        val btn = Button(this)
        btn.setText("GO")
        btn.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            val numberArr = text.getText().toString().split(" ")
            val num: ArrayList<Int> = ArrayList()
            for (i in numberArr) {
                if (!i.isEmpty()) {
                    num.add(i.trim().toInt())
                }
            }
            intent.putExtra("NUM_ARR", num)
            startActivity(intent)

        }
        linearL.addView(btn)


    }

    fun go(v: View) {
        val intent = Intent(this, Main2Activity::class.java)
        val numberArr = text.getText().toString().split(" ")
        val num: ArrayList<Int> = ArrayList()
        for (i in numberArr) {
            if (!i.isEmpty()) {
                num.add(i.trim().toInt())
            }
        }
        intent.putExtra("NUM_ARR", num)
        startActivity(intent)
    }

}
