package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.appcompat.R.id.wrap_content
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import my.lib.binSearch
import dsl.constraintLayout
import dsl.onCLick

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(R.id.layoutId) {
                    editText(R.id.editTextAddNumber) {
                        width = dp(300)
                        hint = "Введите число"
                        topMargin(R.id.layoutId, TOP, dp(8))
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                        rightMargin(R.id.layoutId, LEFT, dp(8))
                    }

                    button(R.id.button_add) {
                        topMargin(R.id.editTextAddNumber, BOTTOM, dp(8))
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                        rightMargin(R.id.layoutId, LEFT, dp(8))
                        text = "Добавить"
                        onCLick {
                            onAddNumber()
                        }
                    }

                    textView(R.id.textView_showArray) {
                        topMargin(R.id.button_add, BOTTOM, dp(16))
                        width = wrap_content
                        height = wrap_content
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                        rightMargin(R.id.layoutId, LEFT, dp(8))
                    }

                    editText(R.id.editTextFind) {
                        hint = "Введите число"
                        width = dp(300)
                        topMargin(R.id.textView_showArray, TOP, dp(270))
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                        rightMargin(R.id.layoutId, LEFT, dp(8))
                    }

                    button(R.id.button_find) {
                     //   width = wrap_content
                     //   height = dp(80)
                        topMargin(R.id.editTextFind, BOTTOM, dp(0))
                        leftMargin(R.id.layoutId, RIGHT , dp(8))
                        rightMargin(R.id.layoutId, LEFT, dp(8))
                        text = "Найти позицию"
                        onCLick {
                            onFind()
                        }
                    }

                    textView(R.id.PosView) {
                        width = wrap_content
                        topMargin(R.id.button_find, BOTTOM, dp(24))
                        leftMargin(R.id.layoutId, LEFT, dp(8))
                        rightMargin(R.id.layoutId, LEFT, dp(8))
                    }
                }
        )
    }

    private var a = ArrayList<Int> ()

    fun onAddNumber(){
        val inputText:String = editTextAddNumber.text.toString()
        if(inputText==""){
            Toast.makeText(applicationContext,"Введите число",Toast.LENGTH_SHORT).show()
            editTextAddNumber.text.clear()
            return
        }
        if (!a.isEmpty()) {
            if (inputText.toInt() <= a[a.lastIndex]) {
                Toast.makeText(applicationContext, "Введите большее число", Toast.LENGTH_SHORT).show()
                editTextAddNumber.text.clear()
                return
            }
        }
        val number:Int = editTextAddNumber.text.toString().toInt()
        a.add(number)
        textView_showArray.text = a.toString()
        editTextAddNumber.text.clear()
    }

    fun onFind() {
        val input = editTextFind.text.toString()
        if(input==""){
            Toast.makeText(applicationContext,"Введите число",Toast.LENGTH_SHORT).show()
            editTextFind.text.clear()
            return
        }
        val bin = binSearch(a, 0, a.size - 1, input.toInt())
        if (bin == a.size + 1) {
            Toast.makeText(applicationContext, "Числа не существует", Toast.LENGTH_SHORT).show()
            editTextFind.text.clear()
            return
        }
        PosView.text = bin.toString()
        editTextFind.text.clear()

    }
}
