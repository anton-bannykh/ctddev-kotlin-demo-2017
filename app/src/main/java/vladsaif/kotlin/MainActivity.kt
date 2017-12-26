package vladsaif.kotlin

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.support.v4.content.ContextCompat
import android.support.v7.widget.LinearLayoutCompat
import android.util.Log
import android.view.ViewGroup.LayoutParams.*
import android.widget.LinearLayout
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Integer.parseInt
import java.util.Random
import kotlin.collections.HashMap
import kotlin.math.max
import kotlin.math.min

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                linearLayout {
                    linearLayout {
                        height = WRAP_CONTENT
                        editText {
                            id = R.id.n // convenience
                            hint = "Введите N"
                            width = WRAP_CONTENT
                            height = WRAP_CONTENT
                        }
                        button {
                            id = R.id.startButton
                            onClick = { startClick(it) }
                            text = "OK"
                            width = WRAP_CONTENT
                            height = WRAP_CONTENT
                        }
                    }
                    text {
                        Log.d("HELP", "is here")
                        text = "Выберите элементы, обозначающие начало и конец перемещаемого отрезка массива, а так же элемент," +
                                "        перед которым будет произведена вставка"
                        id = R.id.hint
                        width = WRAP_CONTENT
                        height = WRAP_CONTENT
                    }
                    linearLayout {
                        height = WRAP_CONTENT
                        button {
                            id = R.id.start
                            onClick = { mode = 1 }
                            text = "От"
                            width = WRAP_CONTENT
                            height = WRAP_CONTENT
                        }
                        button {
                            id = R.id.end
                            text = "До"
                            onClick = { mode = 2 }
                            width = WRAP_CONTENT
                            height = WRAP_CONTENT
                        }
                        button {
                            id = R.id.to
                            text = "Перед"
                            onClick = { mode = 3 }
                            width = WRAP_CONTENT
                            height = WRAP_CONTENT
                        }
                        button {
                            id = R.id.slice
                            text = "Переместить"
                            onClick = { sliceClick(it) }
                            width = WRAP_CONTENT
                            height = WRAP_CONTENT
                        }
                    }
                    horizontalScroll {
                        width = WRAP_CONTENT
                        height = WRAP_CONTENT
                        linearLayout {
                            id = R.id.container
                            width = WRAP_CONTENT
                            height = WRAP_CONTENT
                        }
                    }
                }
        )
        hideShow(View.INVISIBLE)
    }

    private var listLetters = ArrayList<TextView>()
    private var nn = 0
    private val random = Random()
    private var array = ArrayList<Treap>()
    private val map = HashMap<TextView, Treap>()
    private var viewToPos = HashMap<TextView, Int>()
    private var mode = 0
    private var left = Integer.MAX_VALUE
    private var right = Integer.MAX_VALUE
    private var cut = Integer.MAX_VALUE
    private val container by lazy { findViewById(R.id.container) as LinearLayoutCompat }

    private val elementListener = View.OnClickListener {
        when (mode) {
            1 -> left = viewToPos[it]!!
            2 -> right = viewToPos[it]!!
            3 -> cut = viewToPos[it]!!
        }
        draw()
        mode = 0
    }

    private fun draw() {
        val min = min(left, right)
        val max = max(left, right)
        val elColor = ContextCompat.getColor(this, R.color.el)
        val interColor = ContextCompat.getColor(this, R.color.inter)
        val toColor = ContextCompat.getColor(this, R.color.to)
        for (i in 0 until container.childCount) {
            when {
                i == cut -> container.getChildAt(i).setBackgroundColor(toColor)
                i < min -> container.getChildAt(i).setBackgroundColor(0xFFFFFF)
                i == min -> container.getChildAt(i).setBackgroundColor(elColor)
                i < max -> container.getChildAt(i).setBackgroundColor(interColor)
                i == max -> container.getChildAt(i).setBackgroundColor(elColor)
                else -> container.getChildAt(i).setBackgroundColor(0xFFFFFF)
            }
        }
    }

    private fun hideShow(visibility: Int) {
        findViewById(R.id.start).visibility = visibility
        findViewById(R.id.end).visibility = visibility
        findViewById(R.id.to).visibility = visibility
        findViewById(R.id.slice).visibility = visibility
        findViewById(R.id.hint).visibility = visibility
    }

    fun startClick(view: View) {
        nn = parseInt(n.text.toString())
        Log.d("HELP", nn.toString())
        generateArray()
        Log.d("HELP", array.size.toString())
        refreshContainer()
        hideShow(View.VISIBLE)
    }

    fun sliceClick(view: View) {
        if (left == Int.MAX_VALUE || right == Int.MAX_VALUE || cut == Int.MAX_VALUE) return
        slice(array[left], array[right], array[cut])
        refreshContainer()
    }

    private fun generateArray() {
        array = ArrayList(nn)
        array.clear()
        for (i in 0 until nn) {
            array.add(i, Treap(random.nextInt(100)))
            if (i > 0) {
                merge(getRoot(array[i - 1]), getRoot(array[i]))
            }
        }
    }

    private fun refreshContainer() {
        map.clear()
        viewToPos.clear()
        container.removeAllViews()
        left = Integer.MAX_VALUE
        right = Integer.MAX_VALUE
        cut = Integer.MAX_VALUE
        array = getArray(array)
        for (i in 0 until nn) {
            layoutInflater.inflate(R.layout.letter, container, true)
            val v = container.getChildAt(i) as TextView
            listLetters.add(v)
            viewToPos.put(v, i)
            v.setOnClickListener(elementListener)
            v.text = array[i].value.toString()
            map.put(v, array[i])
        }
    }
}
