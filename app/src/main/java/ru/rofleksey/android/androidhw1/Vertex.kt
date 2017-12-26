package ru.rofleksey.android.androidhw1

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import android.graphics.RectF


class Vertex() {
    constructor(x: Float, y: Float) : this() {
        this.x = x
        this.y = y
    }

    var num = 0
    var r = RectF()
    var x = 0f
        set(value) {
            field = value
            recalcRect()
        }
    var y = 0f
        set(value) {
            field = value
            recalcRect()
        }

    companion object {
        var pnt: Paint = Paint()
        val RAD = 15
        val TOUCH_RAD = 40

        init {
            pnt.color = Color.RED
            pnt.isAntiAlias = true
        }
    }

    fun dist(x: Float, y: Float): Double {
        return Math.sqrt(Math.pow((this.x - x).toDouble(), 2.toDouble()) + Math.pow((this.y - y).toDouble(), 2.toDouble()))
    }

    private fun recalcRect() {
        r.set(x - RAD, y - RAD, x + RAD, y + RAD)
    }

    fun draw(c: Canvas) {
        c.drawArc(r, 0f, 360f, true, pnt)
    }
}