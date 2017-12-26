package volkov.alexandr.kotlinandroid

import android.content.Intent
import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.view.Gravity.BOTTOM
import android.view.View
import android.view.ViewGroup
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.ArrayAdapter
import android.widget.LinearLayout
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    companion object {
        const val PATTERNS = "PATTERNS"
    }

    private var patterns = ArrayList<String>()
    private var listAdapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = object : ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, patterns) {
            override fun getView(position: Int, convertView: View?, parent: ViewGroup): View {
                val view = super.getView(position, convertView, parent)
                val textView = view.findViewById<View>(android.R.id.text1) as TextView
                textView.setTextColor(Color.BLACK)
                return view
            }
        }

        val view = linearLayout {
            linearLayout {
                setHeight(MATCH_PARENT)
                setWeight(1f)

                linearLayout {
                    orientation = LinearLayout.HORIZONTAL
                    val word = edit {
                        setWeight(4f)
                        textSize = 20f
                        hint = "Enter text"
                    }
                    button {
                        text = "add"
                        setWeight(1f)
                        onClick = View.OnClickListener {
                            val text = word.getText().toString()
                            listAdapter!!.add(text)
                        }
                    }
                }

            }

            list {
                setWeight(7f)
                setHeight(MATCH_PARENT)
                setWidth(MATCH_PARENT)
                adapter = listAdapter
            }

            linearLayout {
                setWidth(MATCH_PARENT)
                setWeight(1f)

                button {
                    setGravity(BOTTOM)
                    setWidth(MATCH_PARENT)
                    text = "next"
                    onClick = View.OnClickListener {
                        openEditor()
                    }
                }
            }
        }.build(this)
        setContentView(view)
    }

    private fun openEditor() {
        val intent = Intent(this, TextActivity::class.java)
        intent.putStringArrayListExtra(PATTERNS, patterns)
        startActivity(intent)
    }

    override fun onSaveInstanceState(outState: Bundle?) {
        super.onSaveInstanceState(outState)
        outState?.putStringArrayList(PATTERNS, patterns)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle?) {
        super.onRestoreInstanceState(savedInstanceState)
        if (savedInstanceState?.containsKey(PATTERNS)!!) {
            listAdapter?.addAll(savedInstanceState.getStringArrayList(PATTERNS))
        }
    }
}
