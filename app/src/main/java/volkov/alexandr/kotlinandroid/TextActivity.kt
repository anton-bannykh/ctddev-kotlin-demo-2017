package volkov.alexandr.kotlinandroid

import android.graphics.Color
import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.text.Editable
import android.text.Spannable
import android.text.SpannableString
import android.text.TextWatcher
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.method.ScrollingMovementMethod
import android.text.style.BackgroundColorSpan
import kotlinx.android.synthetic.main.activity_text.*
import my.lib.Trie

class TextActivity : AppCompatActivity() {
    private var trie: Trie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_text)

        trie = Trie(intent.getStringArrayListExtra(MainActivity.PATTERNS))
        tvFind.movementMethod = ScrollingMovementMethod()
        etText.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(p0: Editable?) {
                val text = etText.text.toString()
                val founded = trie?.findAll(text)
                tvFind.text = decorate(text, founded!!)
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }
        })
    }

    fun decorate(text: String, founded: List<Pair<Int, String>>): Spannable {
        if (founded.isEmpty()) {
            return SpannableString(text)
        }
        var currWord = 0
        var lastIdx = 0
        val res = SpannableStringBuilder()
        for (i in text.indices) {
            if (i == founded[currWord].first) {
                res.append(text.substring(lastIdx, i))
                val secondIdx = i + founded[currWord].second.length
                res.append(text.substring(i, secondIdx))
                res.setSpan(BackgroundColorSpan(Color.YELLOW), i, secondIdx, Spanned.SPAN_EXCLUSIVE_EXCLUSIVE)
                lastIdx = secondIdx
                currWord++
                if (currWord == founded.size) {
                    break
                }
            }
        }
        res.append(text.substring(lastIdx))
        return res
    }
}
