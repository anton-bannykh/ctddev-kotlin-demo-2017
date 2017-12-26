package volkov.alexandr.kotlinandroid

import android.graphics.Color
import android.os.Bundle
import android.support.v7.app.AppCompatActivity
import android.text.Spannable
import android.text.Spanned
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.InputType
import android.text.method.ScrollingMovementMethod
import android.text.style.BackgroundColorSpan
import android.view.Gravity
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import my.lib.Trie

class TextActivity : AppCompatActivity() {
    private var trie: Trie? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        trie = Trie(intent.getStringArrayListExtra(MainActivity.PATTERNS))

        val view = linearLayout {
            var find: Text? = null
            edit {
                setHeight(MATCH_PARENT)
                setWidth(MATCH_PARENT)
                setWeight(1f)
                setGravity(Gravity.TOP or Gravity.START)
                textSize = 18f
                hint = "Enter text"
                inputType = InputType.TYPE_TEXT_FLAG_IME_MULTI_LINE
                textWatcher = simpleTextWatcher {
                    val text = getText().toString()
                    val founded = trie?.findAll(text)
                    find?.text = decorate(text, founded!!)
                }
            }
            find = text {
                setHeight(MATCH_PARENT)
                setWidth(MATCH_PARENT)
                setWeight(1f)
                setGravity(Gravity.TOP)
                textSize = 18f
                movementMethod = ScrollingMovementMethod()
                maxLines = 10
                lines = 8
            }
        }.build(this)
        setContentView(view)
    }

    private fun decorate(text: String, founded: kotlin.collections.List<Pair<Int, String>>): Spannable {
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
