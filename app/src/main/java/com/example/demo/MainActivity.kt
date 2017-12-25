package com.example.demo

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.widget.TextView


class MainActivity : AppCompatActivity() {
    fun merge(a: IntArray, l: Int, L: Int, r: Int, R: Int) {
        var i = l;
        val i0 = L;
        var j = r;
        var j0 = R;
        val b = IntArray(a.size);
        var k = 0;
        while ((i < i0) && (j < j0)) {
            if (a[i] <= a[j]) {
                b[k] = a[i];
                i++
            } else {
                b[k] = a[j];
                j++;
            }
            k++;
        }
        while (i < i0) {
            b[k] = a[i];
            i++;
            k++;
        }
        while (j < j0) {
            b[k] = a[j];
            j++;
            k++;
        }
        j0--;
        k--;
        while (k >= 0) {
            a[j0] = b[k];
            k--;
            j0--;
        }
    }

    fun mergeSort(a: IntArray, L: Int = 0, R: Int = a.size) {
        if (L < R - 1) {
            mergeSort(a, L, (L + R) / 2);
            mergeSort(a, (L + R) / 2, R);
            merge(a, L, (L + R) / 2, (L + R) / 2, R);
        }
    }

    var TextView.value: IntArray
        get() {
            var a = mutableListOf<Int>()
            val parts = (text.toString()).split("\u0020")
            for (j in parts.indices) {
                if (parts[j].compareTo("") > 0) {
                    a.add(Integer.parseInt(parts[j]))
                }
            }
            val arr = IntArray(a.size);
            for (j in a.indices) arr[j] = a[j];
            return arr;
        }
        set(x) {
            var res = "Sorted: ";
            for (j in x.indices) {
                res += x[j].toString() + " ";
            }
            text = res
        }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(
                constraintLayout(R.id.layoutId) {
                    textView(R.id.hello) {
                        text = "I can sort your arrays!"
                        topMargin(R.id.layoutId, TOP, dp(24))
                    }
                    textView(R.id.inArrayText) {
                        text = "Enter values separated by a space:"
                        topMargin(R.id.hello, TOP, dp(24))
                    }

                    val initArray = number(R.id.inputArray) {
                        topMargin(R.id.inArrayText, TOP, dp(50))
                    }

                    val ans = number(R.id.outputArray) {
                        topMargin(R.id.sortButton, BOTTOM, dp(40))
                    }

                    button(R.id.sortButton) {
                        topMargin(R.id.inputArray, BOTTOM, dp(32))
                        center(R.id.layoutId)
                        text = "Sort!"
                        onCLick {
                            val a = initArray.value;
                            mergeSort(a);
                            ans.value = a;
                        }
                    }
                }
        )
    }
}
