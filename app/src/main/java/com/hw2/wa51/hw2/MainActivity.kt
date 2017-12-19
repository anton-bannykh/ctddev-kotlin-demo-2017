package com.hw2.wa51.hw2

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import java.util.Random
import kotlin.collections.ArrayList
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    var TextView.value: Int
        get() = text.toString().toInt()
        set(x) {
            text = x.toString()
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        val display = getWindowManager().getDefaultDisplay()
        val w = display.getWidth();
        val h = display.getHeight();
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(R.layout.activity_main) {
                    textView(R.id.textView) {
                        leftMargin(R.layout.activity_main, LEFT, (w - dp(200)) / 2)
                        text = "find points"
                        width = dp(200)
                    }

                    button(R.id.gen) {
                        bottomMargin(R.layout.activity_main, BOTTOM, dp(10))
                        leftMargin(R.layout.activity_main, LEFT, (w - dp(200)) / 2)
                        text = "generate"
                        width = dp(200)
                        height = dp(50)

                        onCLick {
                            gen()
                        }
                    }

                    button(R.id.run) {
                        bottomMargin(R.layout.activity_main, BOTTOM, dp(70))
                        leftMargin(R.layout.activity_main, LEFT, (w - dp(200)) / 2)
                        text = "run"
                        width = dp(200)
                        height = dp(50)

                        onCLick {
                            run()
                        }
                    }
                }.layout
        )
    }

    fun min(a : Int, b : Int) : Int = if (a > b) b else a

    class Vertex
    {
        var to : ArrayList<Int> = ArrayList()
        var id : ArrayList<Int> = ArrayList()
        fun insert (a : Int, id_e : Int) {
            to.add(a)
            id.add(id_e)
        }
        fun size () = to.size
    }

    class Graph (max_size : Int)
    {
        var listInc : ArrayList<Vertex> = ArrayList()
        var tmp_id = 0

        init {
            for (i in 0..(max_size - 1)) {
                var tmp : Vertex = Vertex()
                listInc.add(tmp)
            }
        }

        fun insert (a : Int, b : Int) {
            listInc[a].insert(b, tmp_id)
            listInc[b].insert(a, tmp_id)
            tmp_id++
        }

        fun to(i : Int, j : Int) = listInc[i].to[j]

        fun size() = listInc.size

        fun id(i : Int, j : Int) = listInc[i].id[j]

        fun size_id(i : Int) = (listInc[i].id).size
    }

    fun points (g : Graph) : ArrayList<Boolean> {
        var vis : ArrayList<Boolean> = ArrayList()
        var d : ArrayList<Int> = ArrayList()
        var up : ArrayList<Int> = ArrayList()
        var is_point : ArrayList<Boolean> = ArrayList()
        for (i in 0..(g.size() - 1)) {
            vis.add(false)
            d.add(0)
            up.add(0)
            is_point.add(false)
        }
        var cnt = 0
        fun dfs(v : Int, d_v : Int, e_id : Int, root : Int) {
            vis[v] = true
            d[v] = d_v
            up[v] = d_v
            for (i in 0..(g.size_id(v) - 1)) {
                var u = g.to(v, i)
                if (vis[u] == false) {
                    if (v == root) {
                        cnt++
                    }
                    dfs(u, d_v + 1, g.id(v, i), root)
                    up[v] = min(up[v], up[u])
                    if ((up[u] >= d[v]) && (v != root)) {
                        is_point[v] = true
                    }
                } else {
                    if (g.id(v, i) != e_id) {
                        up[v] = min(up[v], d[u])
                    }
                }
            }
            if (cnt > 1) {
                is_point[root] = true
            }
        }
        for (i in 0..(g.size() - 1)) {
            if (vis[i] == false) {
                cnt = 0
                dfs(i, 0, -1, i)
            }
        }
        return is_point
    }

    var g : Graph = Graph(0)

    public fun gen () {
        var rand = Random()
        var n = rand.nextInt(4) + 2
        var m = rand.nextInt(n * (n - 1) / 2) + 1
        var str = ""
        g = Graph(n + 1)
        for (i in 0..m) {
            var a = rand.nextInt(n)
            var b = rand.nextInt(n)
            g.insert(a, b)
            str += a.toString() + " " + b.toString() + "\n"
        }
        textView.text = str
    }

    public fun run () {
        var ans = points(g)
        var str = ""
        for (i in ans) {
            str += i.toString() + "\n"
        }
        textView.text = str
    }
}
