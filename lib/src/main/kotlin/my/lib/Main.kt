package my.lib

fun main(args: Array<String>) {
    println("Hello world!")
}



fun dv(n: Int) : Int { //ближайшая степень двойки
    var n2: Int = n
    n2--
    var t : Int = 1
    while(t < 64) {
        n2 = n2 or (n2 shr t)
        t = t shl 1 //сдвиг влево
    }
    return ++n2
}



class SegmentTree {
    public fun query(l:Int, r:Int):Int {
        if(size == 1) {
            return ST[0]
        }
        return query_base(0, 0, size - 1, l, r)
    }



    public fun modify(pos:Int, value:Int) {
        modify_base(0, 0, size - 1, pos, value)
    }



    private var array = mutableListOf<Int>()
    private var ST = mutableListOf<Int>()
    private var size: Int = 0

    constructor(){

    }
    constructor(array: MutableList<Int>) {
        this.array = array
        size = dv(array.size) //ближайшая степень двойки
        buildST()
    }



    private fun buildST(){ //build SegmentTree
        array.addAll((1..(this.size - array.size)).map { -1 })

        ST.clear()
        ST.addAll((1..size * 2).map{-1})

        build(0, 0, size - 1)
    }



    private fun build(v:Int, vl:Int, vr:Int) {
        if(vl == vr) {
            if(array[vl] == -1) {
                ST[v] = -1
            } else {
                ST[v] = array[vl]
            }
            return;
        }

        val vm: Int = vl + (vr - vl)/2
        build(2 * v + 1, vl, vm)
        build(2 * v + 2, vm + 1, vr)
        ST[v] = Math.max(ST[2 * v + 1], ST[2 * v + 2])
    }



    private fun query_base(v:Int, vl:Int, vr:Int, l:Int, r:Int):Int {
        if((r < vl) || (vr < l)) {
            return -1
        }
        if((l <= vl) && (vr <= r)) {
            return ST[v]
        }
        val vm: Int = vl + (vr - vl)/2

        return Math.max(query_base(2 * v + 1, vl, vm, l, r),
                query_base(2 * v + 2, vm + 1, vr, l, r))
    }



    private fun modify_base(v:Int, vl:Int, vr:Int, pos:Int, value:Int) {
        if(vl == vr){
            ST[v] = value;
            return;
        } else {
            val vm: Int = vl + (vr - vl)/2
            if(pos <= vm) {
                modify_base(2 * v + 1, vl, vm, pos, value);
            } else {
                modify_base(2 * v + 2, vm + 1, vr, pos, value)
            }
            ST[v] = Math.max(ST[2 * v + 1], ST[2 * v + 2]);
        }
    }
}