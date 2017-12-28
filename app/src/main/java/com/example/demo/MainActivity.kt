package com.example.demo

import android.annotation.SuppressLint
import android.os.Bundle
import android.support.design.widget.TextInputEditText
import android.support.v7.app.AppCompatActivity
import android.widget.EditText
import android.widget.*
import my.lib.search


class MainActivity: AppCompatActivity() {
    private lateinit var answerOutput: EditText
    private lateinit var searchQuery: SearchView
    private lateinit var textIn: TextInputEditText


    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
                constraintLayout(R.id.ConstrLayoutID) {

                    searchQuery = searchView(R.id.SearchQueryID) {
                        leftMargin(R.id.ConstrLayoutID, LEFT, dp(10))
                        rightMargin(R.id.ConstrLayoutID, RIGHT, dp(10))
                        topMargin(R.id.ConstrLayoutID, TOP, dp(16))
                    }
                    textIn = textInputEditText(R.id.textInID) {
                        width = dp(400)

                        topMargin(R.id.ConstrLayoutID, TOP, dp(50))
                        bottomMargin(R.id.answerOutputID, TOP, dp(10))
                    }
                    answerOutput = editText(R.id.answerOutputID) {
                        width = dp(400)

                        bottomMargin(R.id.SearchButtonID, TOP, dp(8))
                    }

                    button(R.id.SearchButtonID, { onClick() }) {
                        text = "Search"
                        textSize = 18f
                        bottomMargin(R.id.ConstrLayoutID, BOTTOM, dp(8))
                        leftMargin(R.id.ConstrLayoutID, RIGHT, dp(150))
                    }
                }
        )
    }

     private fun onClick() {

         val stringTextQuery = searchQuery.query.toString()
         val stringText = textIn.text.toString()
         val positionsList = search(stringTextQuery, stringText)

         if (positionsList.isEmpty()) {
             answerOutput.setText(getString(R.string.string_no_matches_found))
         } else {
             val stringOfPositionList = positionsList.toString()
             answerOutput.setText(stringOfPositionList)
         }
    }
}
