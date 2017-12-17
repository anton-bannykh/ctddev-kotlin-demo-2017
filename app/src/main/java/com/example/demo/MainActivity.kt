package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.widget.Button
import android.widget.EditText
import android.widget.SearchView
import com.example.demo.R.color.colorAccent
import com.example.demo.R.color.colorAdditional
import my.lib.search
import my.lib.sumFun

class MainActivity: AppCompatActivity(){


    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val addButton = findViewById<Button>(R.id.button2)

        addButton.setOnClickListener() {

            val textQuery = findViewById<SearchView>(R.id.search_query)
            val textFor = findViewById<TextInputEditText>(R.id.text)
            val outputText = findViewById<EditText>(R.id.editText)

            fun onClick(){
                val stringTextQuery = textQuery.query.toString()
                val stringText = textFor.text.toString()
                val positionsList = search(stringTextQuery, stringText)

                if (positionsList[0] == -1){
                    outputText.setText(getString(R.string.string_no_matches_found))
                 //   textFor.setTextColor(resources.getColor(colorAdditional))
                } else {
                    val stringOfPositionList = positionsList.toString()
                    outputText.setText(stringOfPositionList)
                }

            }
            onClick()
        }

    }

fun test() = sumFun(1, 2, 3)

}
