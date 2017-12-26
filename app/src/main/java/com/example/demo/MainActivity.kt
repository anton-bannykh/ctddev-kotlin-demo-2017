package com.example.dmitriytrusienko.omgwtf

import android.annotation.SuppressLint
import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        letsGo.setOnClickListener() {
            val gS :Int? = graphSize.intText
            if(gS == null) {
                welcome.text = "Please, set a count"
            } else {
                when {
                    gS < 0 -> welcome.text = "Please, set an adequate count"
                    else -> {
                        val newIntent = Intent(this, GraphActivity::class.java)
                        newIntent.putExtra(GraphActivity.TOTAL_COUNT, gS)
                        startActivity(newIntent)
                    }
                }
            }
        }
    }

}

var TextView.intText: Int?
    get() = this.text.toString().toIntOrNull()
    set(value: Int?) {
        this.text = Int.toString()
    }

