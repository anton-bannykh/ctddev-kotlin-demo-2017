package my.lib.segmenttree

class Cell(v: Int = 0, left: Int = 0, right: Int = 0) {
    var value: Int = v
    val l = left
    val r = right
    var modified: Int = 0

    fun isModify(): Boolean {
        return modified > 0
    }

    fun getRange(): Int {
        return r - l + 1
    }
}
