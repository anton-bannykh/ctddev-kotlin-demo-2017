package kt.sova.centroiddecomposition

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.method.ScrollingMovementMethod
import kotlinx.android.synthetic.main.activity_second.*

class SecondActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_second)
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
