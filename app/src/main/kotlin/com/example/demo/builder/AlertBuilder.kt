package com.example.demo.builder

import android.app.AlertDialog
import android.content.Context
import android.content.DialogInterface
import android.view.View
import com.example.demo.R
import kotlin.DeprecationLevel.ERROR

/**
* Created by greg on 19/12/2017.
*/

fun Context.alert(init: AlertDialog.Builder.() -> Unit): AlertDialog {
    val alertDialogBuilder = AlertDialog.Builder(this)
    alertDialogBuilder.init()
    return alertDialogBuilder.create()
}

var AlertDialog.Builder.customView: View
    @Deprecated(NO_GETTER, level = ERROR) get() =
        throw IllegalStateException("Don't even try through reflection!")
    set(value) { setView(value) }

var AlertDialog.Builder.title: CharSequence
    @Deprecated(NO_GETTER, level = ERROR) get() =
        throw IllegalStateException("Don't even try through reflection!")
    set(value) { setTitle(value) }

fun AlertDialog.Builder.applyButton(onClick: () -> Unit) {
    setPositiveButton(R.string.apply, {_: DialogInterface, _: Int -> onClick()})
}

fun AlertDialog.Builder.cancelButton(onClick: () -> Unit) {
    setNegativeButton(android.R.string.cancel,{_: DialogInterface, _: Int -> onClick()})
}
