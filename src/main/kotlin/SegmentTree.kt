import kotlin.math.max

class SegmentTree {
    /*public fun outt() {
        for(i in 0..7) {
            print("${array[i]}  ")
        }
    }*/
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
        ST[v] = max(ST[2 * v + 1], ST[2 * v + 2])
    }



    private fun query_base(v:Int, vl:Int, vr:Int, l:Int, r:Int):Int {
        if((r < vl) || (vr < l)) {
            return -1
        }
        if((l <= vl) && (vr <= r)) {
            return ST[v]
        }
        val vm: Int = vl + (vr - vl)/2

        return max(query_base(2 * v + 1, vl, vm, l, r),
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
            ST[v] = max(ST[2 * v + 1], ST[2 * v + 2]);
        }
    }
}