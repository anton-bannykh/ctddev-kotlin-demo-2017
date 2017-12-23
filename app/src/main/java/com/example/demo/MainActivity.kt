package com.example.demo

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.widget.EditText
import my.lib.Automation

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = constraintLayout {
            id = R.id.constLay
            textView {
                id = R.id.textV
                text = "Number of vertexes:"
                textSize = 24f
                height = "wrap"
                width = "wrap"
                textColor = Color.BLACK
                margins {
                    marginTop = 32
                    marginStart = 16
                }
            }

            editText {
                id = R.id.editV
                textSize = 24f
                height = "wrap"
                width = "match"
                hint = "42"
                textColor = Color.BLACK
                gravity = Gravity.END
                margins {
                    marginEnd = 8
                    marginTop = 20
                    marginStart = 8
                    startTo = R.id.textV
                }
            }

            textView {
                id = R.id.textE
                text = "Number of edges:"
                textSize = 24f
                height = "wrap"
                width = "wrap"
                textColor = Color.BLACK
                margins {
                    marginTop = 28
                    topTo = R.id.textV
                    marginStart = 16
                }
            }

            editText {
                id = R.id.editE
                textSize = 24f
                height = "wrap"
                width = "match"
                hint = "111"
                textColor = Color.BLACK
                gravity = Gravity.END
                margins {
                    marginEnd = 8
                    marginTop = 8
                    topTo = R.id.editV
                    marginStart = 8
                    startTo = R.id.textE
                }
            }

            textView {
                id = R.id.textT
                text = "Number of terminals:"
                textSize = 24f
                height = "wrap"
                width = "wrap"
                textColor = Color.BLACK
                margins {
                    marginTop = 32
                    topTo = R.id.textE
                    marginStart = 8
                    marginEnd = 8
                }
            }

            editText {
                id = R.id.editT
                textSize = 24f
                height = "wrap"
                width = "match"
                hint = "1 2 3 4 5"
                textColor = Color.BLACK
                gravity = Gravity.END
                margins {
                    marginEnd = 8
                    marginTop = 8
                    topTo = R.id.textT
                    marginStart = 8
                }
            }

            textView {
                id = R.id.textVU
                text = "Input edges:"
                textSize = 24f
                height = "wrap"
                width = "wrap"
                textColor = Color.BLACK
                margins {
                    marginTop = 16
                    topTo = R.id.editT
                    marginStart = 16
                }
            }

            editText {
                id = R.id.editVU
                textSize = 24f
                height = 350
                width = 220
                hint = "Example: 1 2 a"
                textColor = Color.BLACK
                gravity = Gravity.END
                margins {
                    marginStart = 8
                    marginTop = 8
                    topTo = R.id.textVU
                }
            }

            button {
                id = R.id.buttonH
                text = "Help"
                textSize = 18f
                height = 75
                width = 126
                textColor = Color.BLACK
                onClick { helpCmnd() }
                margins {
                    marginEnd = 32
                    marginBottom = 100
                }
            }

            button {
                id = R.id.buttonA
                text = "Answer"
                textSize = 18f
                height = 75
                width = 126
                textColor = Color.BLACK
                onClick { ans() }
                margins {
                    marginEnd = 32
                    marginBottom = 32
                    bottomTo = R.id.buttonH
                }
            }
        }
        setContentView(view)
    }

    private fun ans() {
        val v = findViewById<EditText>(R.id.editV).text.toString()
        val e = findViewById<EditText>(R.id.editE).text.toString()
        val t = findViewById<EditText>(R.id.editT).text.toString()
        val str = findViewById<EditText>(R.id.editVU).text.toString()

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
