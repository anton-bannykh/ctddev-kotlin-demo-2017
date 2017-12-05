import org.junit.Assert.assertArrayEquals
import org.junit.Test


class MainTest{
    @Test
    fun Test0(){
        var e : MutableList<Pair<Int, Int>> = mutableListOf<Pair<Int, Int>>();
        e.add(Pair(1,2));
        e.add(Pair(1,3));
        var q : MutableList<Pair<Int, Int>> = mutableListOf<Pair<Int, Int>>();
        q.add(Pair(2,3));
        val ans : Array<Int> = arrayOf(1);
        assertArrayEquals(ans,LowestCommonAncestor(size = 3, edges = e, testx = q));
    }

    @Test
    fun test1(){
        var e : MutableList<Pair<Int, Int>> = mutableListOf<Pair<Int, Int>>();
        e.add(Pair(1,2));
        e.add(Pair(1,3));
        e.add(Pair(4,2));
        e.add(Pair(5,2));
        var q : MutableList<Pair<Int, Int>> = mutableListOf<Pair<Int, Int>>();
        q.add(Pair(4,5));
        q.add(Pair(4,3))
        q.add(Pair(1,1));
        val ans : Array<Int> = arrayOf(2,1,1);
        assertArrayEquals(ans,LowestCommonAncestor(size = 5, edges = e, testx = q));
    }

    @Test
    fun test2(){
        var e : MutableList<Pair<Int, Int>> = mutableListOf<Pair<Int, Int>>();
        e.add(Pair(1,2));
        e.add(Pair(1,3));
        e.add(Pair(4,2));
        e.add(Pair(5,2));
        e.add(Pair(6,5));
        e.add(Pair(7,5));
        e.add(Pair(7,8));
        var q : MutableList<Pair<Int, Int>> = mutableListOf<Pair<Int, Int>>();
        q.add(Pair(6,8));
        q.add(Pair(8,2))
        q.add(Pair(8,1));
        val ans : Array<Int> = arrayOf(5,2,1);
        assertArrayEquals(ans,LowestCommonAncestor(size = 8, edges = e, testx = q));
    }

}
