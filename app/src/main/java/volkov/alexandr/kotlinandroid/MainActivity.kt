package volkov.alexandr.kotlinandroid

import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.AppCompatActivity
import android.view.View
import android.widget.ArrayAdapter
import kotlinx.android.synthetic.main.activity_main.*


class MainActivity : AppCompatActivity() {
    companion object {
        const val PATTERNS = "PATTERNS"
    }

    private var patterns = ArrayList<String>()
    private var adapter: ArrayAdapter<String>? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, patterns)
        lvPatterns.adapter = adapter
    }

    fun addPattern(v: View) {
        if (!etPattern.text.isEmpty()) {
            adapter?.add(etPattern.text.toString())
        }
    }

    fun clear(v: View) {
        adapter?.clear()
    }

    fun openEditor(v: View) {
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
            adapter?.addAll(savedInstanceState.getStringArrayList(PATTERNS))
        }
    }
}
