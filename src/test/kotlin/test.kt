
import org.junit.jupiter.api.Test
import java.util.*
import kotlin.test.assertEquals

class Tester {
    var gen: Random = Random(123);
    @Test
    fun TestRand() {
        for (iteration in 0..100) {
            val CAP = 1000;
            val CAP2 = 100;
            print("Starting iteration ")
            println(iteration);
            var n = gen.nextInt(CAP) + 2
            var arr = IntArray(n)
            for (i in arr.indices) {
                arr[i] = gen.nextInt(CAP2)
            }
            var q = gen.nextInt(CAP) + 10
            var rawq = Array<Query>(q, { Query(0,0,0)})
            var slowans = IntArray(q);
            for (i in rawq.indices) {
                var cnt: TreeMap<Int, Int> = TreeMap();
                val l = gen.nextInt(n)
                val r = gen.nextInt( n - l) + l + 1;
                val id = i;
                rawq[i] = Query(l, r, id)
                for (j in l..r - 1) {
                    if (cnt.containsKey(arr[j]))
                        cnt[arr[j]] = cnt[arr[j]]!! + 1;
                    else
                        cnt[arr[j]] = 1;
                }
                var best = 0;
                var ans = -1;
                for ((Key, Val) in cnt) {
                    if (Val > best) {
                        ans = Key;
                        best = Val;
                    }
                }
                slowans[i] = ans;
            }
            var fastans = Mo(arr, rawq);
            assert(fastans.size == q);
            for (i in 0..q-1) {
                assertEquals(slowans[i], fastans[i], i.toString());
            }
        }
    }

}