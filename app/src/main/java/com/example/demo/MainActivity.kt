package com.example.demo

import android.annotation.SuppressLint
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import my.lib.substringSearch

class MainActivity : AppCompatActivity() {

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(R.id.layout) {

                    val string = EditText(R.id.string) {
                        width = dp(256)
                        height = dp(64)
                        textSize = 16f
                        hint = "СЃС‚СЂРѕРєР°"
                        leftMargin(R.id.layout, ConstraintSet.START, dp(8))
                        topMargin(R.id.layout, ConstraintSet.TOP, dp(8))
                    }

                    val substring = EditText(R.id.substring) {
                        width = dp(256)
                        height = dp(64)
                        textSize = 16f
                        hint = "РїРѕРґСЃС‚СЂРѕРєР°"
                        leftMargin(R.id.layout, ConstraintSet.START, dp(8))
                        topMargin(R.id.string, ConstraintSet.BOTTOM, dp(24))
                    }

                    TextView(R.id.answer_text) {
                        width = dp(256)
                        height = dp(64)
                        textSize = 16f
                        text = "РРЅРґРµРєСЃС‹ РІС…РѕР¶РґРµРЅРёСЏ РїРѕРґСЃС‚СЂРѕРєРё РІ СЃС‚СЂРѕРєСѓ:"
                        leftMargin(R.id.layout, ConstraintSet.START, dp(8))
                        topMargin(R.id.substring, ConstraintSet.BOTTOM, dp(24))
                    }

                    val answer = TextView(R.id.answer) {
                        width = dp(256)
                        height = dp(64)
                        textSize = 16f
                        text = "[]"
                        leftMargin(R.id.layout, ConstraintSet.START, dp(8))
                        topMargin(R.id.answer_text, ConstraintSet.BOTTOM, dp(24))
                    }

                    Button(R.id.button) {
                        width = dp(128)
                        height = dp(64)
                        textSize = 16f
                        text = "РџРѕРёСЃРє"
                        leftMargin(R.id.layout, ConstraintSet.START, dp(126))
                        topMargin(R.id.answer, ConstraintSet.BOTTOM, dp(24))
                        setOnClickListener({
                            answer.text = substringSearch(substring.text.toString(), string.text.toString()).toString()
                        })
                    }
                }
        )
    }
}
