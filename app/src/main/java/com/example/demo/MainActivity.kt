package com.example.demo


import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun сlickButton(view: View) {
        var editText1: EditText = findViewById(R.id.editText3)
        var textView: TextView = findViewById(R.id.textView3)
        var a = mutableListOf<Int>()
        val parts = (editText1.getText().toString()).split("\u0020")
        for (j in parts.indices) {
            if (parts[j].compareTo("") > 0) {
                a.add(Integer.parseInt(parts[j]))
            }
        }
        val arr = IntArray(a.size);
        for (j in a.indices) arr[j] = a[j];
        mergeSort(arr);
        var ans = "Sorted: ";
        for (j in arr.indices) {
            ans += arr[j].toString() + " ";
        }
        textView.text = ans;
    }

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

}

