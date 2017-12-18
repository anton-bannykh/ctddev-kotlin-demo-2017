package com.example.demo

import android.content.Context
import android.graphics.Color
import android.graphics.Canvas
import android.graphics.Point
import android.graphics.Path
import android.graphics.Paint
import android.util.AttributeSet
import android.view.View
import java.lang.Math.max
import java.lang.Math.min

class GraphView(context: Context, attrs: AttributeSet) : View(context, attrs) {
    private val p: Paint = Paint(Paint.ANTI_ALIAS_FLAG)
    private val path: Path = Path()
    private val minRadius = 5
    private val vertexRadiusK = 2.5f
    private var vertexRadius: Float = 25f
        set(value) {
            field = min(value, 35f)
            vertexHoleRadius = field * 3f / 5f
            p.strokeWidth = field * 2f / 5f
        }
    private var vertexHoleRadius: Float = 15f

    var leftSideNum = 0
    var rightSideNum = 0
    var edges = mutableListOf<Pair<Int, Int>>()
    var matching = listOf<Pair<Int, Int>>()

    var recommendedNum: Int = 100
        set(value) = Unit

    private fun updateDimen() {
        vertexRadius = height / (max(leftSideNum, rightSideNum) + 1) / vertexRadiusK
        recommendedNum = (height / (vertexRadiusK * minRadius)).toInt()
    }

    private fun getPointByNum(num: Int, side: Int) = Point(width / 4 * (2 * side - 1), num * height / if (side == 1) (leftSideNum + 1) else (rightSideNum + 1))

    override fun onDraw(canvas: Canvas) {
        updateDimen()
        edges.forEach { pair ->
            drawEdge(getPointByNum(pair.first, 1), getPointByNum(pair.second, 2), canvas, matching.contains(pair))
        }
        for (v in 1..leftSideNum)
            drawVertex(getPointByNum(v, 1), canvas, true)
        for (v in 1..rightSideNum)
            drawVertex(getPointByNum(v, 2), canvas, true)
        matching.forEach { pair ->
            drawVertex(getPointByNum(pair.first, 1), canvas)
            drawVertex(getPointByNum(pair.second, 2), canvas)
        }
    }

    private fun drawEdge(position1: Point, position2: Point, canvas: Canvas, inMatching: Boolean = false) {
        path.reset()
        path.moveTo(position1.x.toFloat(), position1.y.toFloat())
        path.lineTo(position2.x.toFloat(), position2.y.toFloat())
        p.style = Paint.Style.STROKE
        p.color = if (inMatching) Color.BLUE else Color.GRAY
        canvas.drawPath(path, p)
    }

    private fun drawVertex(position: Point, canvas: Canvas, isHollow: Boolean = false) {
        if (isHollow) drawVertex(position, canvas)
        p.style = Paint.Style.FILL
        p.color = if (isHollow) Color.WHITE else Color.BLACK
        canvas.drawCircle(position.x.toFloat(), position.y.toFloat(), if (isHollow) vertexHoleRadius else vertexRadius, p)
    }
}
