package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.util.TypedValue
import android.view.Gravity
import android.widget.EditText
import android.widget.Button
import android.widget.Toast
import my.lib.createFenwickTree
import my.lib.getElement
import my.lib.updateAtSegment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(
                constraintLayout(R.id.layoutId) {
                    textView(R.id.typeArray) {
                        text = "Введите массив:"
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 20.0f)
                        topMargin(R.id.layoutId, TOP, toDp(35))
                        center(R.id.layoutId)
                    }

                    editText(R.id.arrayField) {
                        setText("1 2 3 4 -5")
                        width = toDp(150)
                        topMargin(R.id.typeArray, BOTTOM, toDp(5))
                        center(R.id.layoutId)
                    }

                    textView(R.id.addOnSegment) {
                        text = "Прибавить на отрезке:"
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 15.0f)
                        topMargin(R.id.arrayField, BOTTOM, toDp(45))
                        leftMargin(R.id.layoutId, LEFT, toDp(20))
                    }

                    textView(R.id.from) {
                        text = "От:"
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
                        topMargin(R.id.addOnSegment, BOTTOM, toDp(20))
                        leftMargin(R.id.layoutId, LEFT, toDp(20))
                    }

                    editText(R.id.fromField) {
                        setText("0")
                        gravity = Gravity.CENTER
                        width = toDp(40)
                        topMargin(R.id.addOnSegment, BOTTOM, toDp(15))
                        leftMargin(R.id.from, RIGHT, toDp(0))
                    }

                    textView(R.id.to) {
                        text = "До:"
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
                        topMargin(R.id.addOnSegment, BOTTOM, toDp(20))
                        leftMargin(R.id.fromField, RIGHT, toDp(14))
                    }

                    editText(R.id.toField) {
                        setText("4")
                        gravity = Gravity.CENTER
                        width = toDp(40)
                        topMargin(R.id.addOnSegment, BOTTOM, toDp(15))
                        leftMargin(R.id.to, RIGHT, toDp(0))
                    }

                    textView(R.id.value) {
                        text = "Значение:"
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
                        topMargin(R.id.addOnSegment, BOTTOM, toDp(75))
                        leftMargin(R.id.layoutId, LEFT, toDp(25))
                    }

                    editText(R.id.addValueField) {
                        setText("1")
                        gravity = Gravity.CENTER
                        width = toDp(40)
                        topMargin(R.id.addOnSegment, BOTTOM, toDp(70))
                        leftMargin(R.id.value, RIGHT, toDp(0))
                    }

                    button(R.id.addRequest) {
                        text = "Выполнить"
                        topMargin(R.id.addOnSegment, BOTTOM, toDp(120))
                        leftMargin(R.id.layoutId, LEFT, toDp(34))
                    }

                    textView(R.id.getValue) {
                        text = "Получить значение:"
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 15.0f)
                        topMargin(R.id.arrayField, BOTTOM, toDp(45))
                        rightMargin(R.id.layoutId, RIGHT, toDp(25))
                    }

                    textView(R.id.index) {
                        text = "Индекс:"
                        setTextSize(TypedValue.COMPLEX_UNIT_SP, 18.0f)
                        topMargin(R.id.getValue, BOTTOM, toDp(45))
                        rightMargin(R.id.layoutId, RIGHT, toDp(88))
                    }

                    editText(R.id.indexField) {
                        setText("0")
                        gravity = Gravity.CENTER
                        width = toDp(40)
                        topMargin(R.id.getValue, BOTTOM, toDp(40))
                        leftMargin(R.id.index, RIGHT, toDp(0))
                    }

                    button(R.id.getRequest) {
                        text = "Выполнить"
                        topMargin(R.id.getValue, BOTTOM, toDp(120))
                        rightMargin(R.id.layoutId, RIGHT, toDp(34))
                    }
                }
        )

        val arrayField = findViewById<EditText>(R.id.arrayField)
        val fromField = findViewById<EditText>(R.id.fromField)
        val toField = findViewById<EditText>(R.id.toField)
        val addValueField = findViewById<EditText>(R.id.addValueField)
        val indexField = findViewById<EditText>(R.id.indexField)

        fun initFenwickWithArray(array: List<String>, fenwick: IntArray) {
            for (i in 0 until array.size) {
                updateAtSegment(i, i, Integer.parseInt(array[i]), fenwick)
            }
        }

        val array = arrayField.text.split(" ")

        val fenwickTree = createFenwickTree(array.size)
        initFenwickWithArray(array, fenwickTree)

        val addRequestButton = findViewById<Button>(R.id.addRequest)
        addRequestButton.setOnClickListener {
            updateAtSegment(Integer.parseInt(fromField.text.toString()),
                    Integer.parseInt(toField.text.toString()),
                    Integer.parseInt(addValueField.text.toString()), fenwickTree)
        }

        val getButton = findViewById<Button>(R.id.getRequest)
        getButton.setOnClickListener {
            val index = Integer.parseInt(indexField.text.toString())
            val valueAtIndex = getElement(index, fenwickTree)
            Toast.makeText(this, "Значение в индексе $index = $valueAtIndex", Toast.LENGTH_SHORT).show()
        }
    }
}
