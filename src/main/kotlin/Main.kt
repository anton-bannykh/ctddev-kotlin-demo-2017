package Main.Kotlin

fun merge(a: IntArray, l: Int, L: Int, r: Int, R: Int) {
    var i: Int = l;
    val i0: Int = L;
    var j: Int = r;
    var j0: Int = R;
    val b = IntArray(a.size);
    var k: Int = 0;
    while ((i < i0) && (j < j0)) {
        if (a[i] <= a[j]) {
            b[k] = a[i];
            i++;
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


