package kt.sova.centroiddecomposition

import android.content.Intent
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import dsl.Layout
import dsl.onCLick
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(Layout(this, R.id.mainLayout) {

            textView(R.id.textView) {
                margin(TOP, 24)
                textSize = 30f
                text = "Enter your tree by edges"
                constraint(TOP, R.id.mainLayout, TOP)
                constraint(START, R.id.mainLayout, START)
                constraint(END, R.id.mainLayout, END)
            }

            button(R.id.add_edge_button) {
                onCLick { addEdge(this) }
                text = "Add edge"
                constraint(TOP, R.id.textView, BOTTOM)
                constraint(BOTTOM, R.id.mainLayout, BOTTOM)
                constraint(START, R.id.mainLayout, START)
                constraint(END, R.id.mainLayout, END)
            }

            button(R.id.build_button) {
                onCLick { buildCentroidDec(this) }
                text = "Build"
                constraint(TOP, R.id.add_edge_button, BOTTOM)
                constraint(BOTTOM, R.id.clear_button, TOP)
                constraint(START, R.id.mainLayout, START)
                constraint(END, R.id.mainLayout, END)
            }

            button(R.id.clear_button) {
                onCLick { clear(this) }
                text = "Clear"
                constraint(BOTTOM, R.id.mainLayout, BOTTOM)
                constraint(START, R.id.mainLayout, START)
                constraint(END, R.id.mainLayout, END)
            }

            editText(R.id.first_vertex) {
                constraint(TOP, R.id.textView, BOTTOM)
                constraint(BOTTOM, R.id.add_edge_button, TOP)
                constraint(START, R.id.mainLayout, START)
                constraint(END, R.id.mainLayout, END)
                horizontalBias(0.1f)
            }

            editText(R.id.second_vertex) {
                constraint(TOP, R.id.textView, BOTTOM)
                constraint(BOTTOM, R.id.add_edge_button, TOP)
                constraint(START, R.id.mainLayout, START)
                constraint(END, R.id.mainLayout, END)
                horizontalBias(0.9f)
            }

        }.getLayout())
    }

    fun addEdge(view: View) {
        val first = Integer.parseInt(first_vertex.text.toString())
        val second = Integer.parseInt(second_vertex.text.toString())
        if (first < 0 || second < 0 || first > 199999 || second > 199999) {
            val myToast = Toast.makeText(this, "What the edge?!", Toast.LENGTH_SHORT)
            myToast.show()
            return
        }
        if (size <= first) {
            size = first + 1
        }
        if (size <= second) {
            size = second + 1
        }
        edges[first].add(second)
        edges[second].add(first)

        val myToast = Toast.makeText(this, "Edge added.", Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun clear(view: View) {
        edges = Array(200000, { mutableSetOf<Int>() })
        prevCentroid = emptyArray()
        sizeV = emptyArray()
        size = 0;

        val myToast = Toast.makeText(this, "Edges removed.", Toast.LENGTH_SHORT)
        myToast.show()
    }

    fun buildCentroidDec(view: View) {
        visited = Array(size, { false })
        if (size > 0 && !check(-1, 0)) {
            val myToast = Toast.makeText(this, "Sorry, but it's not a tree. Clear and try again.", Toast.LENGTH_SHORT)
            myToast.show()
            return
        }

        if (size > 0) {
            sizeV = Array(size, { 0 })
            prevCentroid = Array(size, { -1 })
            build(0, -1)
        }

        var root = false
        for (i in prevCentroid) {
            if (i == -1) {
                if (!root) {
                    root = true
                } else {
                    val myToast = Toast.makeText(this, "Sorry, but there are more than 1 connectivity component. Clear and try again.", Toast.LENGTH_SHORT)
                    myToast.show()
                    return
                }
            }
        }

        val centroidDecIntent = Intent(this, SecondActivity::class.java)
        centroidDecIntent.putExtra(SecondActivity.CENTROID_DEC, prevCentroid.toIntArray())
        startActivity(centroidDecIntent)

        clear(view)
    }

    private var edges: Array<MutableSet<Int>> = Array(200000, { mutableSetOf<Int>() })
    private var sizeV: Array<Int> = emptyArray()
    private var prevCentroid: Array<Int> = emptyArray()
    private var size: Int = 0

    private fun dfs(p: Int, v: Int): Int {
        var res: Int = 1
        for (i in edges[v]) {
            if (i != p) {
                res += dfs(v, i)
            }
        }
        sizeV[v] = res
        return res
    }

    private fun findCentroid(v: Int): Int {
        dfs(v, v)
        val size: Int = sizeV[v]
        var end: Boolean = false
        var prev: Int = v
        var centroid: Int = v
        while (!end)
        {
            end = true
            for (i in edges[v]) {
                if (i != prev && sizeV[i] > size / 2) {
                    end = false
                    prev = centroid
                    centroid = i
                    break
                }
            }
        }
        return centroid
    }

    private fun build(v: Int, prev: Int) {
        val cur = findCentroid(v)
        prevCentroid[cur] = prev
        val nextStep: Set<Int> = edges[cur]
        edges[cur] = mutableSetOf()
        for (i in nextStep) {
            edges[i].remove(cur)
            build(i, cur)
        }
    }

    private var visited: Array<Boolean> = emptyArray()

    private fun check(p: Int, v: Int): Boolean {
        visited[v] = true
        var res: Boolean = true
        for (i in edges[v]) {
            if (i != p && visited[i]) {
                res = false
            }
            if (!visited[i]) {
                res = check(v, i)
            }
            if (!res) {
                return res
            }
        }
        return res
    }
}
