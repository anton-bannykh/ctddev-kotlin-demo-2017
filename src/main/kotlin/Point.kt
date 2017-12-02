class Point(x: Double, y: Double, id: Int) {
    var x : Double
    var y : Double
    var id : Int

    init {
        this.x = x
        this.y = y
        this.id = id
    }

    override fun toString() = "point #$id: ($x $y)"
}