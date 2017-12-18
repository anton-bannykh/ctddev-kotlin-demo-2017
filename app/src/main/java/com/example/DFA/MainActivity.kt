package com.example.DFA

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*
import my.lib.Automation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val buttonAns = findViewById<View>(R.id.Answer) as Button
        val buttonHelp = findViewById<View>(R.id.Help) as Button
        buttonAns.setOnClickListener {
            ans()
        }
        buttonHelp.setOnClickListener {
            helpCmnd()
        }
    }

    var v: String = ""
    var e: String = ""
    var t: String = ""

    private fun ans() {
        v = editVNumber.text.toString()
        e = editENumber.text.toString()
        t = EditTerminal.text.toString()
        val str = etText.text.toString()

        val automation = Automation(Integer.parseInt(v), Integer.parseInt(e), t, str)
        val answer = automation.numberOfWords()
        val intent = Intent(this, Main2Activity::class.java)
        intent.putExtra(Main2Activity.save, Integer.toString(answer))
        startActivity(intent)
    }

    private fun helpCmnd() {
        val intent = Intent(this, Main3Activity::class.java)
        startActivity(intent)
    }

}
