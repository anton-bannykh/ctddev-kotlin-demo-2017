package com.example.python.ctdevhw2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import kotlinx.android.synthetic.main.activity_main.*
import rx.Observable
import java.util.*

import DynamicConnectivity;
import Command;
import android.util.Log
import rx.Subscriber
import rx.android.schedulers.AndroidSchedulers
import rx.schedulers.Schedulers


class MainActivity : AppCompatActivity() {

    var inputData = ArrayList<Command>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun addToArray(view: View) {
        val currentText = inputText.text.toString()
        Observable.just(currentText).map { it ->
            parseString(it)
        }.doOnNext { it -> addToArray(it, inputData) }.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).subscribe({ it ->
            resultView.text = if (it == null) getResources().getString(R.string.result_view_fail)
            else getResources().getString(R.string.result_view_success)
        })

    }


    fun calculateData(view: View) {
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

    fun clearData(view: View) {
        Observable.just(inputData).map { it -> it.clear() }.subscribeOn(Schedulers.newThread()).
                observeOn(AndroidSchedulers.mainThread()).subscribe({ it ->
            resultView.text = getResources().getString(R.string.result_view_clear)
        })
    }

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