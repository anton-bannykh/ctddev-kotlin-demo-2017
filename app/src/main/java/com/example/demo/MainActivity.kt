package com.example.demo

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.LinearLayout
import android.widget.ProgressBar
import android.widget.Toast
import com.example.demo.R
import constraintLayout
import kotlinx.android.synthetic.main.activity_main.*
import onCLick

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val W = getWindowManager().getDefaultDisplay().getWidth();
        val H = getWindowManager().getDefaultDisplay().getHeight();
        setContentView(
                constraintLayout(R.layout.activity_main) {
                    textView(R.id.textView) {
                        text = "1. Введите количество вершин графа, в котором вы хотите найти количество мостов"
                        width = dp(250)
                        bottomMargin(R.layout.activity_main, BOTTOM, dp(H / 5))
                        leftMargin(R.layout.activity_main, LEFT, (W - dp(250)) / 2)
                    }

                    editText(R.id.editText2) {
                        width = dp(250)
                        height = dp(50)
                        inputType = 2
                        bottomMargin(R.layout.activity_main, BOTTOM, dp(60))
                        leftMargin(R.layout.activity_main, LEFT, (W - dp(250)) / 2)
                    }

                    button(R.id.button) {
                        text = "ДАЛЕЕ"
                        width = dp(250)
                        height = dp(50)
                        bottomMargin(R.layout.activity_main, BOTTOM, dp(10))
                        leftMargin(R.layout.activity_main, LEFT, (W - dp(250)) / 2)

                        onCLick {
                            enterNumVertexes(this)
                        }
                    }
                }.layout
        )
    }

    fun enterNumVertexes(view: View) {
        val inputText: String = editText2.text.toString();
        if (inputText.isEmpty()) {

            val emptyFieldToast = Toast.makeText(this, "Поле не может быть пустым", Toast.LENGTH_SHORT)
            emptyFieldToast.show()

        } else if (!(Integer.parseInt(inputText) in 2..6)) {
            editText2.setText("")
            val emptyFieldToast = Toast.makeText(this, "Количество вершин должно быть в пределе от 2 до 6", Toast.LENGTH_SHORT)
            emptyFieldToast.show()

        } else {

            val randomIntent = Intent(this, Main2Activity::class.java)
            randomIntent.putExtra(Main2Activity.TOTAL_COUNT, Integer.parseInt(inputText))
            startActivity(randomIntent)

        }
    }
}
