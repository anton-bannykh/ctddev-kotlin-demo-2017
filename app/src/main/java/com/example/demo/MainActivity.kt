package com.example.demo

import android.content.pm.ActivityInfo
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import my.lib.DSU
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.support.constraint.ConstraintSet
import android.support.v4.widget.TextViewCompat
import android.support.v7.app.AlertDialog
import android.text.InputType
import android.view.View
import android.widget.TextView
import android.widget.Button
import android.widget.ImageButton
import android.widget.Spinner
import android.widget.EditText
import android.widget.ArrayAdapter

class MainActivity : AppCompatActivity() {

    private fun dp(value: Int) = (this.applicationContext.resources.displayMetrics.density * value).toInt()
    private fun sp(value: Int) = (getResources().getConfiguration().fontScale * value)

    private var x = DSU(1)
    private var text1: TextView? = null
    private var text2: TextView? = null
    private var btnOk: Button? = null
    private var work: Button? = null
    private var reset: Button? = null
    private var info: ImageButton? = null
    private var mode: Spinner? = null
    private var edit1: EditText? = null
    private var edit2: EditText? = null
    private var edit3: EditText? = null
    private var amount: Int = 0

    val text1Id = View.generateViewId()
    val text2Id = View.generateViewId()
    val btnOkId = View.generateViewId()
    val workId = View.generateViewId()
    val resetId = View.generateViewId()
    val infoId = View.generateViewId()
    val modeId = View.generateViewId()
    val edit1Id = View.generateViewId()
    val edit2Id = View.generateViewId()
    val edit3Id = View.generateViewId()
    val viewMain = View.generateViewId()

    private var oclBtnOk: View.OnClickListener = View.OnClickListener {
        if (edit1!!.text.toString().length > 6) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle(getString(R.string.errorMessage))
                    .setMessage(getString(R.string.numberIsTooBig))
                    .setIcon(R.drawable.error)
                    .setNegativeButton(getString(R.string.accept),
                            { dialog, _ -> dialog.cancel() })
            builder.create().show()
            return@OnClickListener
        }
        if (edit1!!.text.toString().isEmpty()) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle(getString(R.string.errorMessage))
                    .setMessage(getString(R.string.incorrectInput))
                    .setIcon(R.drawable.error)
                    .setNegativeButton(getString(R.string.accept),
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
            builder.setTitle(getString(R.string.errorMessage))
                    .setMessage(getString(R.string.oneOrTwoNumbers))
                    .setIcon(R.drawable.error)
                    .setNegativeButton(getString(R.string.accept),
                            { dialog, _ -> dialog.cancel() })
            builder.create().show()
            return@OnClickListener
        }
        if (edit2!!.text.toString().isEmpty() || edit3!!.text.toString().isEmpty() || !isInBounds(edit2!!.text.toString()) || !isInBounds(edit3!!.text.toString())) {
            val builder = AlertDialog.Builder(this@MainActivity)
            builder.setTitle(getString(R.string.errorMessage))
                    .setMessage(getString(R.string.incorrectInput))
                    .setIcon(R.drawable.error)
                    .setNegativeButton(getString(R.string.accept),
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
                builder.setTitle(getString(R.string.result))
                        .setMessage(getString(R.string.oneU))
                        .setIcon(R.drawable.error)
                        .setNegativeButton(getString(R.string.accept),
                                { dialog, _ -> dialog.cancel() })
                builder.create().show()
                return@OnClickListener
            } else {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle(getString(R.string.result))
                        .setMessage(getString(R.string.diffU))
                        .setIcon(R.drawable.error)
                        .setNegativeButton(getString(R.string.accept),
                                { dialog, _ -> dialog.cancel() })
                builder.create().show()
                return@OnClickListener
            }

        }
        if (mode!!.selectedItemPosition == 1) {
            if (x.getX(l) == x.getX(r)) {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle(getString(R.string.result))
                        .setMessage(getString(R.string.oneU))
                        .setIcon(R.drawable.error)
                        .setNegativeButton(getString(R.string.accept),
                                { dialog, _ -> dialog.cancel() })
                builder.create().show()
            } else {
                val builder = AlertDialog.Builder(this@MainActivity)
                builder.setTitle(getString(R.string.result))
                        .setMessage(getString(R.string.succesInUnite))
                        .setIcon(R.drawable.error)
                        .setNegativeButton(getString(R.string.accept),
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
        builder.setTitle(getString(R.string.info))
                .setMessage(getString(R.string.infoText))
                .setIcon(R.drawable.error)
                .setNegativeButton(getString(R.string.oK),
                        { dialog, _ -> dialog.cancel() })
        builder.create().show()
    }
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        var bmp: Bitmap = BitmapFactory.decodeResource(getResources(), R.mipmap.info_ico_foreground)
        bmp = Bitmap.createScaledBitmap(bmp, dp(35), dp(35), true)
        setContentView(constraintLayout(viewMain, {
            text1 = textView(text1Id, {
                setSingleLine(true)
                leftMargin(viewMain, ConstraintSet.START, dp(28))
                topMargin(viewMain, ConstraintSet.TOP, dp(55))
                textSize = sp(20)
                text = "Количество элементов(n):"
            })
            edit1 = editText(edit1Id, {
                width = TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
                minWidth = dp(30)
                maxWidth = dp(70)
                topMargin(text1Id, ConstraintSet.TOP, dp(0))
                bottomMargin(text1Id, ConstraintSet.BOTTOM, dp(0))
                leftMargin(text1Id, ConstraintSet.END, dp(10))
                textSize = 22F
                inputType = InputType.TYPE_CLASS_NUMBER
            })
            btnOk = button(btnOkId, {
                setSingleLine(true)
                topMargin(text1Id, ConstraintSet.BOTTOM, dp(30))
                leftMargin(viewMain, ConstraintSet.START, dp(0))
                rightMargin(viewMain, ConstraintSet.END, dp(0))
                textSize = sp(20)
                text = "Подтвердить"
            })
            mode = spinner(modeId, {
                topMargin(btnOkId, ConstraintSet.TOP, dp(100))
                leftMargin(viewMain, ConstraintSet.START, dp(0))
                rightMargin(viewMain, ConstraintSet.END, dp(0))
            })
            text2 = textView(text2Id, {
                setSingleLine(true)
                leftMargin(viewMain, ConstraintSet.START, dp(0))
                rightMargin(viewMain, ConstraintSet.END, dp(0))
                topMargin(modeId, ConstraintSet.BOTTOM, dp(30))
                textSize = sp(20)
                text = "Номер элемента (0..n-1):"
            })
            edit2 = editText(edit2Id, {
                width = TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
                minWidth = dp(30)
                maxWidth = dp(70)
                topMargin(text2Id, ConstraintSet.BOTTOM, dp(10))
                leftMargin(text2Id, ConstraintSet.START, dp(0))
                textSize = 22F
                inputType = InputType.TYPE_CLASS_NUMBER
            })
            edit3 = editText(edit3Id, {
                width = TextViewCompat.AUTO_SIZE_TEXT_TYPE_UNIFORM
                minWidth = dp(30)
                maxWidth = dp(70)
                topMargin(text2Id, ConstraintSet.BOTTOM, dp(10))
                rightMargin(text2Id, ConstraintSet.END, 0)
                textSize = 22F
                inputType = InputType.TYPE_CLASS_NUMBER
            })
            work = button(workId, {
                setSingleLine(true)
                topMargin(text2Id, ConstraintSet.BOTTOM, dp(80))
                leftMargin(viewMain, ConstraintSet.START, dp(0))
                rightMargin(viewMain, ConstraintSet.END, dp(0))
                textSize = sp(20)
                text = "Провести расчет"
            })
            reset = button(resetId, {
                setSingleLine(true)
                topMargin(workId, ConstraintSet.BOTTOM, dp(30))
                leftMargin(viewMain, ConstraintSet.START, dp(0))
                rightMargin(viewMain, ConstraintSet.END, dp(0))
                textSize = sp(20)
                text = "Сброс"
            })
            info = imageButton(infoId, {
                topMargin(viewMain, ConstraintSet.TOP, dp(0))
                rightMargin(viewMain, ConstraintSet.END, dp(0))
                setBackgroundColor(android.graphics.Color.TRANSPARENT)
                setImageBitmap(bmp)
            })
        }) )
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        val adapter = ArrayAdapter.createFromResource(this,
                R.array.arrayOfChoises, android.R.layout.simple_spinner_item)
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        mode!!.adapter = adapter

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