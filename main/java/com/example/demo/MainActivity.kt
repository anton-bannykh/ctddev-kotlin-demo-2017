package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import rx.Observable
import java.util.*
import android.util.Log
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    val inputData = ArrayList<Command>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(

                constraintBuilder(R.id.layoutId, this) {
                    val addButton = addButton(R.id.addButton) {
                        text = getString(R.string.add_button)
                        width = inDp(88)
                        height = inDp(50)
                        connection(R.id.addButton, LEFT, R.id.layoutId, RIGHT)

                    }

                    val resultButton = addButton(R.id.resultButton) {
                        text = getString(R.string.calculate_button)
                        width = inDp(105)
                        height = inDp(50)
                        connection(R.id.resultButton, LEFT, R.id.addButton, RIGHT)
                    }
                    val clearButton = addButton(R.id.clearButton) {
                        text = "Clear"
                        width = inDp(100)
                        height = inDp(50)
                        connection(R.id.clearButton, LEFT, R.id.resultButton, RIGHT)
                    }

                    val inputText = addEditText(R.id.inputText) {
                        this.setText("0 0 0")
                        width = inDp(179)
                        height = inDp(44)
                        connection(R.id.inputText, TOP, R.id.addButton, BOTTOM)
                    }

                    val vertexSize = addEditText(R.id.vertexSize) {
                        this.setText("0")
                        width = inDp(210)
                        height = inDp(49)
                        connection(R.id.vertexSize, TOP, R.id.addButton, BOTTOM)
                        connection(R.id.vertexSize, LEFT, R.id.inputText, RIGHT)
                    }

                    val resultView = addTextView(R.id.resultView) {
                        text = getString(R.string.result_view_success)
                        width = inDp(133)
                        height = inDp(46)
                        connection(R.id.resultView, TOP, R.id.inputText, BOTTOM)
                    }

                    clearButton.setOnClickListener {
                        Observable.just(inputData).map { it -> it.clear() }.subscribeOn(Schedulers.newThread()).
                                observeOn(AndroidSchedulers.mainThread()).subscribe({ it ->
                            resultView.text = getResources().getString(R.string.result_view_clear)
                        })
                    }

                    resultButton.setOnClickListener {

                        Observable.just(vertexSize.text.toString()).map { it
                            ->
                            DynamicConnectivity(it.toInt(), inputData.size, inputData)
                        }.
                                map { it -> it.solve() }.subscribeOn(Schedulers.newThread()).
                                observeOn(AndroidSchedulers.mainThread()).subscribe({ it ->
                            resultView.text = getResources().getString(R.string.result_view_answer) + it
                            Log.e("Output", it)
                        }
                        )
                    }

                    addButton.setOnClickListener {
                        Observable.just(inputText.text.toString()).map { it ->
                            parseString(it)
                        }.doOnNext { it -> addToArray(it, inputData) }.subscribeOn(Schedulers.newThread()).
                                observeOn(AndroidSchedulers.mainThread()).subscribe({ it ->
                            resultView.text = if (it == null) getResources().getString(R.string.result_view_fail)
                            else getResources().getString(R.string.result_view_success)
                        })

                    }

                }
        )
    }

    fun parseString(input: String): List<String>? {
        val reg = Regex(" ")
        val result = input.split(reg)
        if (result.size != 3) {
            throw IllegalArgumentException("can't $input split by three components")
        }
        result.forEach {
            it.toInt()
        }
        return result
    }

    fun addToArray(str: List<String>?, list: ArrayList<Command>) {
        if (str == null) {
            return
        }
        list.add(Command(str[0].toInt(), Pair(
                str[1].toInt(),
                str[2].toInt()
        )))
    }
}

