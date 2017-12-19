import java.util.*
import kotlin.math.roundToInt
import kotlin.math.sqrt

data class Query (
    val l: Int,
    val r: Int,
    val id: Int
)
//Do I need to change something to force travis?

class MyPair(val first: Int, val second: Int) : Comparable<MyPair>
{
    override fun compareTo(other: MyPair): Int {
        if (first.compareTo(other.first) != 0)
            return first.compareTo(other.first);
        return second.compareTo(other.second);
    }
}

fun add(value: Int, cnt: TreeMap<Int, Int>, best: TreeSet<MyPair> )
{
    if (cnt.containsKey(value)) {
        best.remove(MyPair(-cnt[value]!!, value));
        cnt[value] = cnt[value]!!.plus(1);
    } else {
        cnt[value] = 1;
    }
    cnt.get(value)?.let {
        best.add(MyPair(-it, value));
    }
}

fun del(value: Int, cnt: TreeMap<Int, Int>, best: TreeSet<MyPair> )
{
    best.remove(MyPair(-cnt[value]!!, value));
    cnt[value] = cnt[value]!!.minus(1);
    cnt.get(value)?.let {
        if (it != 0) {
            best.add(MyPair(-it, value));
        }
    }
}

fun Mo(arr:IntArray, rawq: Array<Query>) : IntArray {
    var n = arr.size;
    var q = rawq.size;
    var answers = IntArray(q);
    val BLOCK = sqrt(n.toDouble()).roundToInt();

    val queries = rawq.sortedWith(
            compareBy({it!!.l / BLOCK}, {it!!.r}, {it!!.id})
    );
    var cnt: TreeMap<Int, Int> = TreeMap();
    var best: TreeSet<MyPair> = TreeSet();
    var a = 0;
    var b = 0; //[a;b)
    for (q in queries) {
        val l = q!!.l;
        val r = q!!.r; //[l;r)
        while (b < r) {
            add(arr[b++]!!, cnt, best);
        }
        while (a > l) {
            add(arr[--a]!!, cnt, best);
        }
        while (a < l) {
            del(arr[a++]!!, cnt, best);
        }
        while (b > r) {
            del(arr[--b]!!, cnt, best);
        }
        answers[q!!.id] = best.first().second;
    }
    return answers;
}

fun main(args: Array<String>) {
    var n: Int = readLine()!!.toInt();
    var arr = IntArray(n);
    for (i in arr.indices) {
        arr[i] = readLine()!!.toInt();
    }
    var q: Int = readLine()!!.toInt();
    var rawq = Array(q, { Query(0,0,0) } );
    for (i in rawq.indices) {
        val (s1, s2) = readLine()!!.split(' ')
        rawq[i] = Query(s1.toInt() - 1, s2.toInt(), i);
    }
    var answers = Mo(arr, rawq);
    answers.forEach( { print(it); print(' '); } );
}