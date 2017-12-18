package com.example.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ProgressBar
import android.widget.Toast
import com.example.demo.R
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun enterNumVertexes(view: View) {

        //val myToast = Toast.makeText(this, editText2.text, Toast.LENGTH_SHORT)
        //myToast.show()
        val inputText: String = editText2.text.toString();
        if (inputText.isEmpty()) {

            val emptyFieldToast = Toast.makeText(this, "Поле не может быть пустым", Toast.LENGTH_SHORT)
            emptyFieldToast.show()

        } else if (!(Integer.parseInt(inputText) in 2..100)) {
            editText2.setText("")
            val emptyFieldToast = Toast.makeText(this, "Количество вершин должно быть в пределе от 2 до 100", Toast.LENGTH_SHORT)
            emptyFieldToast.show()

        } else {

            val randomIntent = Intent(this, Main2Activity::class.java)
            randomIntent.putExtra(Main2Activity.TOTAL_COUNT, Integer.parseInt(inputText))
            startActivity(randomIntent)

        }
    }
}
