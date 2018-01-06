package good_morning.longestincreasingsubsequence

import android.content.Context
import android.content.res.ColorStateList
import android.support.design.widget.FloatingActionButton
import android.view.View
import android.view.ViewGroup
import android.widget.*
import android.widget.LinearLayout.LayoutParams
import android.widget.LinearLayout.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout.LayoutParams.WRAP_CONTENT

fun autoSeekBar(context: Context, init: SeekBar.() -> Unit): SeekBar {
    val seekBar = SeekBar(context)
    seekBar.layoutParams = LayoutParams(MATCH_PARENT, 200)
    seekBar.init()
    return seekBar
}

fun autoFAB(context: Context, image: Int, init: FloatingActionButton.() -> Unit): FloatingActionButton {
    val fab = FloatingActionButton(context)
    fab.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    fab.setImageDrawable(context.getDrawable(image))
    fab.init()
    return fab
}

fun autoLinearLayout(context: Context, init: LinearLayout.() -> Unit): LinearLayout {
    val linearLayout = LinearLayout(context)
    linearLayout.layoutParams = LayoutParams(MATCH_PARENT, WRAP_CONTENT)
    linearLayout.orientation = LinearLayout.VERTICAL
    linearLayout.init()
    return linearLayout
}

fun autoScrollView(context: Context, init: LinearLayout.() -> Unit): ScrollView {
    val scrollView = ScrollView(context)
    scrollView.autoLinearLayout{}
    scrollView.layoutParams = LayoutParams(0, WRAP_CONTENT, 1.0f)
    (scrollView.getChildAt(0) as LinearLayout).init()
    return scrollView
}

fun <T : ViewGroup, V : View> T.addRetView(v : V): V = v.also{ addView(v) }

fun <T : ViewGroup> T.autoScrollView(init: LinearLayout.() -> Unit): LinearLayout {
    val scrollView = autoScrollView(this.context, init)
    addView(scrollView)
    return scrollView.getChildAt(0) as LinearLayout
}
fun <T : ViewGroup> T.autoLinearLayout(init: LinearLayout.() -> Unit) = addRetView(autoLinearLayout(this.context, init))
fun <T : ViewGroup> T.autoFAB(image: Int, init: FloatingActionButton.() -> Unit) = addRetView(autoFAB(this.context, image, init))
fun <T : ViewGroup> T.autoSeekBar(init: SeekBar.() -> Unit) = addRetView(autoSeekBar(this.context, init))

fun SeekBar.onChange(action: SeekBar.(SeekBar) -> Unit) {
    setOnSeekBarChangeListener(object : SeekBar.OnSeekBarChangeListener {
        override fun onProgressChanged(seekBar: SeekBar, progress: Int, fromUser: Boolean) = Unit
        override fun onStartTrackingTouch(seekBar: SeekBar) = Unit
        override fun onStopTrackingTouch(seekBar: SeekBar) { seekBar.action(seekBar) }
    })
}

fun FloatingActionButton.backgroundTint(color: Int) {
    backgroundTintList = ColorStateList.valueOf(context.getColor(color))
}

fun FloatingActionButton.action(action: () -> Unit) = setOnClickListener{ action() }

fun <T : View> T.LinearH(height: Int) {
    layoutParams = LinearLayout.LayoutParams(layoutParams.width, height)
}

fun <T : View> T.LinearW(width: Int) {
    layoutParams = LinearLayout.LayoutParams(layoutParams.height, width)
}

fun <T : View> T.LinearM(weight: Float) {
    layoutParams = LinearLayout.LayoutParams(layoutParams.width, layoutParams.height, weight)
}