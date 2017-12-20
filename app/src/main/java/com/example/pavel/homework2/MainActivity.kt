package com.example.pavel.homework2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import DsuBase
import android.view.View
import android.widget.TextView
import android.widget.Toast
import java.util.*

val mainDsu = DsuBase()

class MainActivity : AppCompatActivity() {

    var valueA: Int = 0
    var valueB: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onClickAddSet(view: View) {
        mainDsu.addNode()
        val textViewSize = findViewById<View>(R.id.textViewSize) as TextView
        textViewSize.text = mainDsu.size().toString()
    }

    fun readAB(): Boolean {
        val editA = (findViewById<View>(R.id.editParamA) as TextView)
        val editB = (findViewById<View>(R.id.editParamB) as TextView)
        try {
            valueA = editA.text.toString().toInt()
            valueB = editB.text.toString().toInt()
            --valueA;
            --valueB;
            if (valueA >= mainDsu.size() || valueB >= mainDsu.size()) {
                val myToast = Toast.makeText(this, "Can you add more node?", Toast.LENGTH_SHORT)
                myToast.show()
                return false;
            }
            return true;
        } catch (e: Exception) {
            val myToast = Toast.makeText(this, "Error input!", Toast.LENGTH_SHORT)
            myToast.show()
            editA.text = ""
            editB.text = ""
            return false;
        }
    }

    fun onClickUnion(view: View) {
        if (readAB()) {
            val strMessage = if (mainDsu.unionSet(valueA, valueB))
                "Element is connected!"
                else "Element was connected yet."
            val myToast = Toast.makeText(this, strMessage, Toast.LENGTH_SHORT)
            myToast.show()
        }
    }

    fun onClickIsCommonSet(view: View) {
        if (readAB()) {
            val myMessage = if (mainDsu.commonSet(valueA, valueB)) "It's True." else "It's False"
            val myToast = Toast.makeText(this,  myMessage, Toast.LENGTH_SHORT)
            myToast.show()
        }
    }

    fun onClickCleanUp(view: View) {
        mainDsu.clear()
        val textViewSize = findViewById<View>(R.id.textViewSize) as TextView
        textViewSize.text = mainDsu.size().toString()
    }
}