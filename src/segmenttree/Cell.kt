package segmenttree

class Cell(v: Int = 0, left: Int = 0, right: Int = 0) {
    var value: Int = v
    val l = left
    val r = right
    var modified: Char = 'X'
    var modValue: Int = 0

    fun setModify(com: Char, v: Int) {
        modified = com
        modValue = v
    }

    fun resetModify() {
        setModify('X', 0)
    }

    fun isModify(): Boolean {
        return modified != 'X'
    }

    fun getRange(): Int {
        return r - l + 1
    }
}