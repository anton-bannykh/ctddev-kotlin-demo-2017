class Point (val coordinates: DoubleArray) {
    operator fun get(i: Int): Double { return coordinates[i] }
}