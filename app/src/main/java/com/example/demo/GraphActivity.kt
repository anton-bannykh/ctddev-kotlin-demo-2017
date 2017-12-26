package com.example.demo
import android.annotation.SuppressLint
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_graph.*

class GraphActivity : AppCompatActivity() {

    companion object {
        const val TOTAL_COUNT = "total_count"
    }

    @SuppressLint("MissingSuperCall", "SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_graph)
        val size = intent.getIntExtra(TOTAL_COUNT, 0)
        val worker = Worker(size)
        addEdge.setOnClickListener() {
            val fV = firstVertex.intText
            val sV = secondVertex.intText
            if(fV == null && sV == null) {
                textWelcome.text = "Write first and second vertex of graph"
                return@setOnClickListener
            }
            if(fV == null) {
                textWelcome.text = "Write first vertex of graph"
                return@setOnClickListener
            }
            if(sV == null) {
                textWelcome.text = "Write second vertex of graph"
                return@setOnClickListener
            }
            if(fV <= 0 || fV > size || sV <= 0 || sV > size) {
                textWelcome.text = "Wrtie real numbers of vertex"
            }
            worker.addEdge(fV,sV)
            textBridges.text = "You have ${worker.bridges} bridges"
        }
    }

}