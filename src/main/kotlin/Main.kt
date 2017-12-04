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



fun main(args: Array<String>) {
    val numbersArray = mutableListOf<Int>(1, 2, 3, 4, 5)
    val segmentTree = SegmentTree(numbersArray)
    println(segmentTree.query(0, 2))
    segmentTree.modify(2, 1)
    println(segmentTree.query(0, 2))
}