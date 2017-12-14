package good_morning.longestincreasingsubsequence

import android.os.Bundle
import android.support.design.widget.Snackbar
import android.support.v7.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.LinearLayout
import android.widget.SeekBar
import android.widget.TabHost
import good_morning.libLIS.LIS

class MainActivity : AppCompatActivity() {
    private var itemCounter: Int = 0
    private val data = ArrayList<Int>(0)
    private var task: Thread? = null

    private fun setupTab(tabHost: TabHost, string: String, obj: Int) =
        tabHost.addTab(tabHost.newTabSpec("tab"+string).setContent(obj).setIndicator(string))

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        tabHost.setup()

        setupTab(tabHost,"Input", R.id.input)
        setupTab(tabHost,"Result", R.id.result)

        tabHost.currentTab = 0

        fab0.setOnClickListener {
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200)
            val seek = SeekBar(this)
            seek.progress = 0
            data.add(seek.progress)

            seek.setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
                override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) = Unit
                override fun onStartTrackingTouch(seekBar: SeekBar) = Unit
                override fun onStopTrackingTouch(seekBar: SeekBar) {
                    data[seekBar.id] = seekBar.progress
                }
            })

            seek.id = itemCounter++
            seek.layoutParams = params
            input.addView(seek)
        }

        fab1.setOnClickListener {
            if (task?.isAlive != true) {
                task = LISWorker(this)
                task?.run()
            }
        }
    }

    class LISWorker(private val mainActivity: MainActivity) : Thread() {

        private fun checkArray(array: Array<Int>): Boolean {
            val used = HashSet<Int>()
            for (i in array) {
                if (used.contains(i)){
                    Snackbar
                        .make(mainActivity.input, "Bad input sequence", Snackbar.LENGTH_SHORT)
                        .setAction("What?!", {
                            Snackbar
                                .make(mainActivity.input, "At least 2 of elements are equal", Snackbar.LENGTH_SHORT)
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
            mainActivity.input.removeAllViews()
            mainActivity.result.removeAllViews()
            mainActivity.itemCounter = 0
            val params = LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, 200)
            for (i in lis) {
                val seekRes = SeekBar(mainActivity)
                seekRes.progress = i
                seekRes.layoutParams = params
                mainActivity.result.addView(seekRes)
            }
        }
    }
}
