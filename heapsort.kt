var index = 1;
fun siftDown (i : Int, args : Array<Int>) {
    var k = i;
    while(2 * k + 1 < index) {
        var left = 2 * k + 1;
        var right = 2 * k + 2;
        var j = left;
        if(right < index && args[right] < args[left]) {
            j = right;
        }
        if(args[k] <= args[j]) {
            break;
        }
        var r = args[k];
        args[k] = args[j];
        args[j] = r;
        k = j;
    }
}

fun siftUp(i : Int, args : Array<Int>) {
    var k = i;
    while(args[k] < args[(k - 1) / 2]) {
        var r = args[k];
        args[k] = args[(k - 1) / 2];
        args[(k - 1) / 2] = r;
        k = (k - 1) / 2;
    }
}

fun extractMin(args : Array<Int>) : Int {
    var min = args[0];
    if(index > 0) {
        args[0] = args[index - 1];
        index--;
        siftDown(0, args);
    }
    return min;
}

fun insertKey(key : Int, args : Array<Int>) {
    index++;
    args[index - 1] = key;
    siftUp(index - 1, args);
}

fun heapsort(args: Array<Int>) {
    var ans : Array<Int> = Array(args.size, {i -> 0});
    ans[0] = args[0];
    var i = 1;
    while(i < args.size) {
        insertKey(args[i], ans);
        i++;
    }
    i = 0;
    while(i < args.size) {
        var res = extractMin(ans);
        args[i] = res;
        i++;
    }
}