package DSL

import android.support.constraint.ConstraintLayout
import android.support.v7.app.AppCompatActivity

/**
 * Created by Илья Кокорин on 19.12.2017.
 */

fun AppCompatActivity.constraintLayout(_id: Int, init: ConstraintLayoutConstructor.() -> Unit): ConstraintLayout {
    val layout = ConstraintLayoutConstructor(this)
    layout.constraintLayout.id = _id
    layout.init()
    return layout.constraintLayout
}