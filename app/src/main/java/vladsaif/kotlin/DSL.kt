package vladsaif.kotlin

import android.content.Context
import android.graphics.Color
import android.support.v7.widget.AppCompatEditText
import android.support.v7.widget.LinearLayoutCompat
import android.support.v7.widget.LinearLayoutCompat.LayoutParams.*
import android.view.Gravity
import android.view.View
import android.view.ViewGroup
import android.widget.*

abstract class DSLView<out T : View>(val context: Context) {
    var onClick: (View) -> Unit = {}
    var width: Int = MATCH_PARENT
    var height: Int = MATCH_PARENT
    var id: Int = -1
    protected fun setParams(view: View) {
        view.layoutParams = ViewGroup.LayoutParams(width, height);
        view.setOnClickListener(onClick)
        view.id = id
    }
    abstract fun init() : T
}

abstract class DSLGroupView<out T : ViewGroup>(context: Context) : DSLView<T>(context) {
    abstract val view: T
    fun linearLayout(ext: DSLLinearLayout.() -> Unit) = baseOp(DSLLinearLayout(context), ext)
    fun button(ext: DSLButton.() -> Unit) = baseOp(DSLButton(context), ext)
    fun text(ext: DSLText.() -> Unit) = baseOp(DSLText(context), ext)
    fun editText(ext: DSLEditText.() -> Unit) = baseOp(DSLEditText(context), ext)
    fun horizontalScroll(ext: DSLHorizontalScroll.() -> Unit) = baseOp(DSLHorizontalScroll(context), ext)
    override fun init() : T {
        super.setParams(view)
        return view
    }
    private fun <X : DSLView<View>> baseOp(x: X, y: X.() -> Unit) {
        x.y()
        view.addView(x.init())
    }
}

open class DSLText(context: Context) : DSLView<TextView>(context) {
    var textColor: Int = Color.BLACK
    var text = ""
    protected fun setParams(x : TextView) {
        x.setTextColor(textColor)
        x.text = text
        super.setParams(x)
    }
    override fun init(): TextView {
        val x = TextView(context)
        setParams(x)
        return x
    }
}

class DSLEditText(context: Context) : DSLText(context) {
    var hint: String = ""
    protected fun setParams(x: AppCompatEditText) {
        x.hint = hint
        super.setParams(x)
    }
    override fun init(): AppCompatEditText {
        val x = AppCompatEditText(context)
        setParams(x)
        return x
    }
}

class DSLButton(context: Context) : DSLText(context) {
    override fun init(): Button {
        val x = Button(context)
        setParams(x)
        return x
    }
}

class DSLLinearLayout(context: Context,
                      override val view: LinearLayoutCompat = LinearLayoutCompat(context)) : DSLGroupView<LinearLayoutCompat>(context) {
    protected fun setParams(x: LinearLayoutCompat) {
        x.gravity = Gravity.CENTER
        super.setParams(x)
    }
    override fun init(): LinearLayoutCompat {
        setParams(view)
        return super.init()
    }
}

class DSLHorizontalScroll(context: Context,
                          override val view: HorizontalScrollView = HorizontalScrollView(context)) : DSLGroupView<HorizontalScrollView>(context)

fun Context.linearLayout(ext: DSLLinearLayout.() -> Unit): LinearLayoutCompat {
    val x = DSLLinearLayout(this)
    x.ext()
    return x.init()
}

// Other Context extension functions
