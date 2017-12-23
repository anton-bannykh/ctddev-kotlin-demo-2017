package com.example.demo

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.LinearLayout
import my.lib.components

class MainActivity : AppCompatActivity() {
    var graph = ArrayList<ArrayList<Int>>()
    var edges: ArrayAdapter<String>? = null
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        val view = linearLayout {
            orientation = LinearLayout.VERTICAL
            setWidth(MATCH_PARENT)
            setHeight(MATCH_PARENT)
            var spinner1: MySpinner? = null
            var spinner2: MySpinner? = null
            var listEdges: MyList? = null
            var listComps: MyList? = null

            text {
                text = "Number"
            }
            linearLayout {

                setWidth(MATCH_PARENT)
                //setWeight(3f)
                orientation = LinearLayout.HORIZONTAL
                val edit = numberEdit {
                    setWeight(3f)
                }
                button {
                    setWeight(1f)
                    text = "START"

                    onClickEvent = View.OnClickListener {
                        val n = edit.text.toString().toInt()
                        graph = ArrayList()
                        //https://stackoverflow.com/questions/4533440/android-listview-text-color
                        edges = object : ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, ArrayList()) {
                            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                                val view = super.getView(position, convertView, parent)
                                val textView = view.findViewById<View>(android.R.id.text1) as TextView
                                textView.setTextColor(Color.BLACK)
                                return view
                            }
                        }
                        listEdges!!.adapter = edges
                        for (i in 1..n) {
                            graph.add(ArrayList())
                        }

                        spinner1!!.adapter = object : ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, Array(n, { "${it + 1}" })) {

                            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                                val view = super.getView(position, convertView, parent)
                                val textView = view.findViewById<View>(android.R.id.text1) as TextView
                                textView.setTextColor(Color.BLACK)
                                return view
                            }
                        }
                        spinner2!!.adapter = object : ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, Array(n, { "${it + 1}" })) {

                            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                                val view = super.getView(position, convertView, parent)
                                val textView = view.findViewById<View>(android.R.id.text1) as TextView
                                textView.setTextColor(Color.BLACK)
                                return view
                            }
                        }

                    }

                }
            }
            linearLayout {
                setWidth(MATCH_PARENT)
                orientation = LinearLayout.HORIZONTAL

                linearLayout {
                    setWeight(3f)
                    setHeight(MATCH_PARENT)
                    this.orientation = LinearLayout.VERTICAL
                    text {
                        text = "from"
                        setWeight(1f)
                    }
                    spinner1 = spinner {
                        setWeight(1f)
                    }
                }
                linearLayout {
                    setWeight(3f)
                    setHeight(MATCH_PARENT)
                    orientation = LinearLayout.VERTICAL
                    text {
                        text = "to"
                        setWeight(1f)
                    }
                    spinner2 = spinner {
                        setWeight(1f)
                    }
                }
                button {
                    setWeight(2f)
                    setHeight(MATCH_PARENT)
                    text = "ADD and\n COUNT"
                    onClickEvent = View.OnClickListener {
                        val from = spinner1!!.selectedItem().toString().toInt() - 1
                        val to = spinner2!!.selectedItem().toString().toInt() - 1
                        graph[from].add(to)
                        edges?.add("${from + 1} -> ${to + 1}")
                        val comps = components(graph)
                        listComps!!.adapter = object : ArrayAdapter<String>(applicationContext, android.R.layout.simple_spinner_item, Array(comps.size, { "${it + 1} <-> ${comps[it]}" })) {
                            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                                val view = super.getView(position, convertView, parent)
                                val textView = view.findViewById<View>(android.R.id.text1) as TextView
                                textView.setTextColor(Color.BLACK)
                                return view
                            }
                        }
                    }
                }
            }
            linearLayout {
                orientation = LinearLayout.HORIZONTAL

                setWidth(MATCH_PARENT)
                listEdges = list {
                    setWeight(1f)
                    setHeight(MATCH_PARENT)
                }
                listComps = list {
                    setWeight(1f)
                    setHeight(MATCH_PARENT)
                }
            }
        }
        setContentView(view.build(this))
    }
}
