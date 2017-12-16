package ru.rofleksey.android.androidhw1

import android.graphics.Canvas
import android.graphics.Color
import android.graphics.Paint
import java.util.concurrent.atomic.AtomicBoolean


class Edge(var v1: Vertex, var v2: Vertex) {
    var distStr = "TBD"
    var dist: Int = -1
        set(value) {
            field = value
            distStr = if (value == -1) {
                "TBD"
            } else {
                value.toString()
            }
        }
    var isUsed = AtomicBoolean(false)

    companion object {
        var pnt = Paint()
        var treePnt = Paint()
        var textPnt = Paint()

        init {
            pnt.color = Color.GRAY
            pnt.strokeWidth = 3f
            pnt.isAntiAlias = true
            treePnt.color = Color.GREEN
            treePnt.strokeWidth = 6f
            treePnt.isAntiAlias = true
            textPnt.color = Color.BLACK
            textPnt.isAntiAlias = true
            textPnt.textSize = 30f
        }

    }

    fun getAngle(): Float {
        val angle = Math.toDegrees(Math.acos((v2.x - v1.x) / Math.sqrt(Math.pow((v2.x - v1.x).toDouble(), 2.0) + Math.pow((v2.y - v1.y).toDouble(), 2.0)))).toFloat()
        return if (v2.y > v1.y) {
            angle
        } else {
            -angle
        }
    }

    fun draw(c: Canvas) {
        c.drawLine(v1.x, v1.y, v2.x, v2.y, if (isUsed.get()) {
            treePnt
        } else {
            pnt
        })
        c.save()
        c.translate((v1.x + v2.x) / 2, (v1.y + v2.y) / 2)
        c.rotate(getAngle())
        if (v2.x < v1.x) {
            c.scale(-1f, -1f)
        }
        c.drawText(distStr, -textPnt.measureText(distStr) / 2, -20f, textPnt)
        c.restore()
    }

    override fun equals(other: Any?): Boolean {
        if (other is Edge) {
            return (v1 === other.v1 && v2 === other.v2) || (v1 === other.v2 && v2 === other.v1)
        }
        return false
    }
}
