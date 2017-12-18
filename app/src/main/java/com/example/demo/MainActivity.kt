package com.example.demo

import my.lib.DSU
import android.content.pm.ActivityInfo
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.v7.app.AlertDialog
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner

class MainActivity : AppCompatActivity() {

    private var x = DSU(1)
    private var btnOk: Button? = null
    private var work: Button? = null
    private var reset: Button? = null
    private var info: Button? = null
    private var mode: Spinner? = null
    private var edit1: EditText? = null
    private var edit2: EditText? = null
    private var edit3: EditText? = null
    private var amount: Int = 0

    private var oclBtnOk: View.OnClickListener = View.OnClickListener {
        if (edit1!!.text.toString().length > 6) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Ошибка!")
                    .setMessage("Число слишком большое")
                    .setIcon(R.drawable.error)
                    .setNegativeButton("Принять",
                            { dialog, _ -> dialog.cancel() })
            builder.create().show()
            return@OnClickListener
        }
        if (edit1!!.text.toString().isEmpty()) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Ошибка!")
                    .setMessage("Некоректные входные данные")
                    .setIcon(R.drawable.error)
                    .setNegativeButton("Принять",
                            { dialog, _ -> dialog.cancel() })
            builder.create().show()
            return@OnClickListener
        }

        btnOk!!.isEnabled = false
        edit1!!.isEnabled = false
        work!!.isEnabled = true
        mode!!.isEnabled = true
        edit2!!.isEnabled = true
        edit3!!.isEnabled = true
        amount = edit1!!.text.toString().toInt()
        x = DSU(amount)
        edit1!!.setText(edit1!!.text.toString().toInt().toString())
    }

    private var oclBtnCalc: View.OnClickListener = View.OnClickListener {
        fun isInBounds(x: String): Boolean {
            if (x.toInt() < amount)
                return true
            return false
        }
        if (edit2!!.text.toString().length > 6 || edit3!!.text.toString().length > 6) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Ошибка!")
                    .setMessage("Одно или оба из числа слишком большие")
                    .setIcon(R.drawable.error)
                    .setNegativeButton("Принять",
                            { dialog, _ -> dialog.cancel() })
            builder.create().show()
            return@OnClickListener
        }
        if (edit2!!.text.toString().isEmpty() || edit3!!.text.toString().isEmpty() || !isInBounds(edit2!!.text.toString()) || !isInBounds(edit3!!.text.toString())) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle("Ошибка!")
                    .setMessage("Некоректные входные данные")
                    .setIcon(R.drawable.error)
                    .setNegativeButton("Принять",
                            { dialog, _ -> dialog.cancel() })
            builder.create().show()
            return@OnClickListener
        }
        edit2!!.setText(edit2!!.text.toString().toInt().toString())
        edit3!!.setText(edit3!!.text.toString().toInt().toString())
        val l = edit2!!.text.toString().toInt()
        val r = edit3!!.text.toString().toInt()
        if (mode!!.selectedItemPosition == 0) {
            if (x.getX(l) == x.getX(r)) {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Результат")
                        .setMessage("Элементы принадлежат одному множеству")
                        .setIcon(R.drawable.error)
                        .setNegativeButton("Принять",
                                { dialog, _ -> dialog.cancel() })
                builder.create().show()
                return@OnClickListener
            } else {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Результат")
                        .setMessage("Элементы лежат в разных множествах")
                        .setIcon(R.drawable.error)
                        .setNegativeButton("Принять",
                                { dialog, _ -> dialog.cancel() })
                builder.create().show()
                return@OnClickListener
            }

        }
        if (mode!!.selectedItemPosition == 1) {
            if (x.getX(l) == x.getX(r)) {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Результат")
                        .setMessage("Элементы принадлежат одному множеству")
                        .setIcon(R.drawable.error)
                        .setNegativeButton("Принять",
                                { dialog, _ -> dialog.cancel() })
                builder.create().show()
            } else {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle("Результат")
                        .setMessage("Множества успешно объединены")
                        .setIcon(R.drawable.error)
                        .setNegativeButton("Принять",
                                { dialog, _ -> dialog.cancel() })
                builder.create().show()
            }
            x.Union(l, r)
        }
    }

    private var oclBtnReset: View.OnClickListener = View.OnClickListener {
        btnOk!!.isEnabled = true
        edit1!!.isEnabled = true

        work!!.isEnabled = false
        mode!!.isEnabled = false
        edit2!!.isEnabled = false
        edit3!!.isEnabled = false

        edit1!!.setText("")
        edit2!!.setText("")
        edit3!!.setText("")

        mode!!.setSelection(0)
    }

    private var oclBtnInfo: View.OnClickListener = View.OnClickListener {
        val builder = AlertDialog.Builder(this@MainActivity)
        builder.setTitle("INFO")
                .setMessage("DSU - структура позволяющая быстро выполнять запросы двух видов:\n" +
                        "\t1) Узнать принадлежат ли два элемента одному множеству\n" +
                        "\t2) Связать два множества к которому принадлежат элементы\n\n" +
                        "Сначала задайте количество элементов в Вашем множестве, элементы нумеруются от 0 до n - 1, где n количество. Далее Вы можете выполнять запросы.")
                .setIcon(R.drawable.error)
                .setNegativeButton("Ок",
                        { dialog, _ -> dialog.cancel() })
        builder.create().show()

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT

        val spinner = findViewById<Spinner>(R.id.spinner)
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.arrayOfChoises, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.adapter = adapter

        btnOk = findViewById(R.id.button)
        work = findViewById(R.id.button2)
        reset = findViewById(R.id.button3)
        info = findViewById(R.id.button4)

        edit1 = findViewById(R.id.editText5)
        edit2 = findViewById(R.id.editText4)
        edit3 = findViewById(R.id.editText3)

        mode = findViewById(R.id.spinner)

        btnOk!!.setOnClickListener(oclBtnOk)
        work!!.setOnClickListener(oclBtnCalc)
        reset!!.setOnClickListener(oclBtnReset)
        info!!.setOnClickListener(oclBtnInfo)

        work!!.isEnabled = false
        mode!!.isEnabled = false
        edit2!!.isEnabled = false
        edit3!!.isEnabled = false
    }
}
