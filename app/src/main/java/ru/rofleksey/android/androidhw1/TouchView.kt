package ru.rofleksey.android.androidhw1

import android.content.Context
import android.util.SparseArray
import android.view.MotionEvent
import android.view.View

abstract class TouchView(context: Context) : View(context), View.OnTouchListener {
    private var pidMap = SparseArray<PointerState>()
    private var pidLast: Int = 0
    var isMultitouch = false

    val touchCount: Int
        get() = pidMap.size()

    class PointerState(x: Float, y: Float) {
        var x: Int = 0
        var y: Int = 0
        var startX: Int = 0
        var startY: Int = 0
        var lastX: Int = 0
        var lastY: Int = 0
        var touchStartTime: Long = 0

        val isTap: Boolean
            get() = System.currentTimeMillis() - touchStartTime < TAP_TIME_THRESHOLD
        val isLongTap: Boolean
            get() = System.currentTimeMillis() - touchStartTime >= LONG_TIME_THRESHOLD

        init {
            lastX = x.toInt()
            startX = lastX
            this.x = startX
            lastY = y.toInt()
            startY = lastY
            this.y = startY
            touchStartTime = System.currentTimeMillis()
        }

        companion object {
            val TAP_TIME_THRESHOLD = 100
            val LONG_TIME_THRESHOLD = 750
        }
    }

    private fun updatePointerState(pid: Int, x: Float, y: Float) {
        val pst = pidMap.get(pid)
        pst.lastX = pst.x
        pst.lastY = pst.y
        pst.x = x.toInt()
        pst.y = y.toInt()
    }

    private fun onTouch(e: MotionEvent) {
        val action = e.actionMasked
        val index = e.actionIndex
        pidLast = e.getPointerId(index)
        if (action == MotionEvent.ACTION_DOWN || action == MotionEvent.ACTION_POINTER_DOWN) {
            if (isMultitouch || pidMap.size() == 0) {
                pidMap.put(pidLast, PointerState(e.x, e.y))
                onStartTouch(pidMap.get(pidLast))
            }
        } else if (action == MotionEvent.ACTION_UP || action == MotionEvent.ACTION_POINTER_UP || action == MotionEvent.ACTION_OUTSIDE || action == MotionEvent.ACTION_CANCEL) {
            if (pidMap.get(pidLast) != null) {
                onEndTouch(pidMap.get(pidLast))
                pidMap.remove(pidLast)
            }
        } else if (action == MotionEvent.ACTION_MOVE) {
            if (pidMap.get(pidLast) != null) {
                updatePointerState(pidLast, e.x, e.y)
                onMoveTouch(pidMap.get(pidLast))
            }
        }
    }

    override fun onTouch(p0: View?, p1: MotionEvent?): Boolean {
        onTouch(p1!!)
        return true
    }

    fun cancelTouchEvents() {
        pidMap.clear()
    }

    abstract fun onStartTouch(st: PointerState)

    abstract fun onEndTouch(st: PointerState)

    abstract fun onMoveTouch(st: PointerState)
}
