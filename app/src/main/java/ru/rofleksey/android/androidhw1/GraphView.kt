package ru.rofleksey.android.androidhw1

import android.app.Dialog
import android.content.Context
import android.graphics.Canvas
import android.os.AsyncTask
import android.widget.NumberPicker
import kotlinx.android.synthetic.main.edge_weight_dialog.*
import java.lang.ref.WeakReference


class GraphView(context: Context) : TouchView(context) {
    private var vertexes = mutableListOf<Vertex>()
    private var edges = mutableListOf<Edge>()
    private var vFrom: Vertex? = null
    private var vTo: Vertex? = null
    private var tmpEdge: Edge? = null
    var infoString = "Tap anywhere to create vertex"

    init {
        setOnTouchListener(this)
        println("GraphView created!")
    }

    private class MinSpanningTreeTask(val vertices: MutableList<Vertex>, val edges: MutableList<Edge>, val viewReference: WeakReference<GraphView>) : AsyncTask<Unit, Unit, List<Edge>?>() {
        override fun doInBackground(vararg p0: Unit?): List<Edge>? {
            val g = Graph()
            g.setSize(vertices.size)
            val map = HashMap<Pair<Int, Int>, Edge>()
            for ((i, v) in vertices.withIndex()) {
                v.num = i + 1
            }
            for (e in edges) {
                map.put(Pair(Math.min(e.v1.num, e.v2.num), Math.max(e.v1.num, e.v2.num)), e)
                //println(Math.min(e.v1.num, e.v2.num).toString() + " " + Math.max(e.v1.num, e.v2.num))
                g.addEdge(e.v1.num, e.v2.num, e.dist)
                e.isUsed.set(false)
            }
            return try {
                val answer = g.getMST()
                //println("ANSWER SIZE = " + answer.size)
                for (p in answer) {
                    println("p: " + p)
                }
                answer.mapNotNull { map[Pair(Math.min(it.first, it.second) + 1, Math.max(it.first, it.second) + 1)] }
            } catch (e: RuntimeException) {
                println("ERROR MST")
                null
            }
        }

        override fun onPostExecute(result: List<Edge>?) {
            super.onPostExecute(result)
            val v = viewReference.get()
            if (v != null) {
                if (result != null) {
                    var sum = 0
                    for (e in result) {
                        sum += e.dist
                        e.isUsed.set(true)
                    }
                    v.infoString = "Tree weight: " + sum.toString()
                } else {
                    v.infoString = "No MST!"
                }
                v.invalidate()
            }
        }
    }


    private fun nearestVertex(x: Float, y: Float): Vertex {
        var minDist = 100000.toDouble()
        var minV = vertexes[0]
        for (v in vertexes) {
            val d = v.dist(x, y)
            if (d < minDist) {
                minDist = d
                minV = v
            }
        }
        return minV
    }

    override fun onMoveTouch(st: PointerState) {
        if (vFrom != null) {
            val v = nearestVertex(st.x.toFloat(), st.y.toFloat())
            if (v.dist(st.x.toFloat(), st.y.toFloat()) <= Vertex.TOUCH_RAD) {
                vTo = null
                tmpEdge = Edge(vFrom!!, v)
            } else {
                if (vTo == null) {
                    vTo = Vertex(0f, 0f)
                    tmpEdge = Edge(vFrom!!, vTo!!)
                }
                vTo!!.x = st.x.toFloat()
                vTo!!.y = st.y.toFloat()
            }
        } else {
            vTo!!.x = st.x.toFloat()
            vTo!!.y = st.y.toFloat()
        }
        invalidate()
    }

    override fun onEndTouch(st: PointerState) {
        val tmpBool = tmpEdge == null || edges.contains(tmpEdge!!)
        if (vTo != null) {
            if (vertexes.isEmpty() || nearestVertex(st.x.toFloat(), st.y.toFloat()).dist(st.x.toFloat(), st.y.toFloat()) > Vertex.TOUCH_RAD) {
                vertexes.add(vTo!!)
                if (vertexes.size == 1) {
                    infoString = "Drag vertex to create new edge"
                } else if (tmpBool) {
                    for (e in edges) {
                        e.isUsed.set(false)
                    }
                    infoString = "No MST!"
                }
            }
        }
        if (!tmpBool) {
            edges.add(tmpEdge!!)
            val dialogEdge = tmpEdge!!
            dialogEdge.dist = 0
            val d = Dialog(context, R.style.EdgeWeightDialog)
            d.setTitle("Edge weight")
            d.setOnDismissListener {
                d.numberPicker.clearFocus()
                val task = MinSpanningTreeTask(vertexes, edges, WeakReference(this))
                task.execute()
            }
            d.setContentView(R.layout.edge_weight_dialog)
            d.buttonAddEdge.setOnClickListener{
                d.dismiss()
            }
            val np = d.numberPicker
            np.maxValue = 200
            np.minValue = 0
            np.setOnValueChangedListener { _: NumberPicker, _: Int, _: Int ->
                dialogEdge.dist = np.value
                invalidate()
            }
            d.show()
        }
        //
        vFrom = null
        vTo = null
        tmpEdge = null
        invalidate()
    }

    override fun onStartTouch(st: PointerState) {
        if (vertexes.isEmpty()) {
            vFrom = null
            vTo = Vertex(st.x.toFloat(), st.y.toFloat())
        } else {
            val v = nearestVertex(st.x.toFloat(), st.y.toFloat())
            if (v.dist(st.x.toFloat(), st.y.toFloat()) <= Vertex.TOUCH_RAD) {
                vFrom = v
                vTo = null
            } else {
                vFrom = null
                vTo = Vertex(st.x.toFloat(), st.y.toFloat())
            }
        }
        invalidate()
    }

    override fun onDraw(canvas: Canvas?) {
        super.onDraw(canvas)
        for (e in edges) {
            e.draw(canvas!!)
        }
        if (tmpEdge != null) {
            tmpEdge!!.draw(canvas!!)
        }
        for (v in vertexes) {
            v.draw(canvas!!)
        }
        if (vTo != null) {
            vTo!!.draw(canvas!!)
        }
        canvas!!.drawText(infoString, 50f, 50f, Edge.textPnt)
    }
}