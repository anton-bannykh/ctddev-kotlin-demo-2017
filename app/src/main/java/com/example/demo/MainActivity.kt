/*MainActivity*/
package com.example.demo

import android.content.Intent

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun countM(view: View) {
        val countString = textViewM.text.toString()
        var count = countString.toInt()
        count++
        textViewM.text = count.toString()
    }

    fun countN(view: View) {
        val countString = textViewN.text.toString()
        var count = countString.toInt()
        count++
        textViewN.text = count.toString()
    }

    fun startRandomTest(view: View) {
        val randomIntent = Intent(this, SecondActivity::class.java)
        val countM = textViewM.text.toString().toInt()
        val countN = textViewN.text.toString().toInt()
        if (countM <= 0 || countN <= 0) {
            val toast = Toast.makeText(this, "N or M is Uncorrect", Toast.LENGTH_SHORT)
            toast.show()
            return
        }
        randomIntent.putExtra("countN", countN)
        randomIntent.putExtra("countM", countM)
        startActivity(randomIntent)
    }
}
