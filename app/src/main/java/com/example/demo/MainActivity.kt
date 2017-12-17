package com.example.demo

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.EditText
import quickSort

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun sortButtonOnClick(v: View) {
        val inputEditText = findViewById<EditText>(R.id.inputEditText)
        val outputEditText = findViewById<EditText>(R.id.outputEditText)
        val array = ArrayList(inputEditText.text.split(" ").map { it.toInt() })
        quickSort(array)
        outputEditText.setText(array.joinToString(" ") { it.toString() })
    }

    fun copyButtonOnClick(v: View) {
        val outputEditText = findViewById<EditText>(R.id.outputEditText)
        val clipboard = getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        val clip = ClipData.newPlainText("sorted data", outputEditText.text)
        clipboard.primaryClip = clip
    }
}
