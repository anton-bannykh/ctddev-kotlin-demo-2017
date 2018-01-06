package good_morning.longestincreasingsubsequence

import android.os.Bundle
import android.support.design.widget.FloatingActionButton
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TabHost
import good_morning.libLIS.LIS
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT

class MainActivity : AppCompatActivity() {
    private var itemCounter: Int = 0
    private val data = ArrayList<Int>(0)
    private var task: Thread? = null
    private var input: LinearLayout? = null
    private var result: LinearLayout? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
            autoLinearLayout(this@MainActivity) {
                orientation = LinearLayout.VERTICAL
                autoLinearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    LinearW(WRAP_CONTENT)
                    LinearH(MATCH_PARENT)
                    autoFAB(android.R.drawable.ic_input_add) {
                        action {
                            input!!.autoSeekBar {
                                progress = 0
                                data.add(progress)
                                onChange { seekBar: SeekBar -> data[seekBar.id] = seekBar.progress }
                                id = itemCounter++
                            }
                        }
                        LinearM(1.0f)
                        backgroundTint(R.color.colorPrimary)
                    }
                    autoFAB(android.R.drawable.ic_menu_upload) {
                        action {
                            if (task?.isAlive != true) {
                                task = LISWorker(this@MainActivity)
                                task?.run()
                            }
                        }
                        LinearM(1.0f)
                        backgroundTint(R.color.colorPrimary)
                    }
                }
                autoLinearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    input = autoScrollView {}
                    result = autoScrollView {}
                }
            }
        )
    }

    class LISWorker(private val mainActivity: MainActivity) : Thread() {

        private fun checkArray(array: Array<Int>): Boolean {
            val used = HashSet<Int>()
            for (i in array) {
                if (used.contains(i)){
                    Snackbar
                        .make(mainActivity.input!!, "Bad input sequence", Snackbar.LENGTH_SHORT)
                        .setAction("What?!", {
                            Snackbar
                                .make(mainActivity.input!!, "At least 2 of elements are equal", Snackbar.LENGTH_SHORT)
                                .show()
                        })
                        .show()
                    return false
                }
                used.add(i)
            }
            return true
        }

        override fun run() {
            super.run()
            val input = mainActivity.data.toTypedArray()
            if (!checkArray(input)) return
            val lis = LIS(input)
            mainActivity.data.clear()
            mainActivity.input!!.removeAllViews()
            mainActivity.result!!.removeAllViews()
            mainActivity.itemCounter = 0
            /*val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200)*/
            for (i in lis) {
                /*val seekRes = SeekBar(mainActivity)
                seekRes.progress = i
                seekRes.layoutParams = params
                mainActivity.result.addView(seekRes)*/
                mainActivity.result!!.autoSeekBar{ progress = i }
            }
        }
    }
}
