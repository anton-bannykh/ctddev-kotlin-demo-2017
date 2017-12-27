package kt.sova.centroiddecomposition

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import android.view.View
import android.view.View.*
import dsl.Layout
import dsl.onCLick
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(Layout(this, R.id.secondLayout) {

            textView(R.id.textView_label) {
                margin(TOP, 24)
                margin(START, 24)
                margin(END, 24)
                textSize = 24f
                text = "Here is a centroid decomposition for your tree."
                constraint(TOP, R.id.secondLayout, TOP)
                constraint(START, R.id.secondLayout, START)
                constraint(END, R.id.secondLayout, END)
            }

            textView(R.id.textView_centoid_dec) {
                maxLines = 5
                text = ""
                textAlignment = View.TEXT_ALIGNMENT_CENTER
                textSize = 72f
                verticalScrollbarPosition = SCROLLBAR_POSITION_RIGHT
                constraint(TOP, R.id.textView_label, BOTTOM)
                constraint(BOTTOM, R.id.secondLayout, BOTTOM)
                constraint(START, R.id.secondLayout, START)
                constraint(END, R.id.secondLayout, END)
            }

        }.getLayout())
        showCentroidDec()
    }

    companion object {
        const val CENTROID_DEC = "centroid_dec"
    }

    fun showCentroidDec() {
        val centroidDec = intent.getIntArrayExtra(CENTROID_DEC)
        val text = StringBuilder();
        for (i in centroidDec.indices) {
            text.append(i)
            text.append(": ")
            text.append(centroidDec[i])
            text.append(";\n")
        }
        textView_centoid_dec.text = text;
        textView_centoid_dec.setMovementMethod(ScrollingMovementMethod())
    }
}
