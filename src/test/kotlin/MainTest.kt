import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun test1() {
        var arrOfPoints = ArrayList<Point> ()

        var ans = 1.0
        arrOfPoints.add(Point(doubleArrayOf(29.1)))
        arrOfPoints.add(Point(doubleArrayOf(26.5)))
        arrOfPoints.add(Point(doubleArrayOf(0.0)))
        var controlPoint: Point = Point(doubleArrayOf(1.0))

        assertEquals(ans, pointCalculator(arrOfPoints, controlPoint), 0.0001)
    }

    @Test
    fun test2() {
        var arrOfPoints = ArrayList<Point> ()

        var ans = 25.73907
        arrOfPoints.add(Point(doubleArrayOf(29.1, 3.5)))
        arrOfPoints.add(Point(doubleArrayOf(26.5, -3.5)))
        var controlPoint: Point = Point(doubleArrayOf(1.0, 0.0))

        assertEquals(ans, pointCalculator(arrOfPoints, controlPoint), 0.0001)
    }

    @Test
    fun test3() {
        var arrOfPoints = ArrayList<Point> ()

        var ans = 739.95094
        arrOfPoints.add(Point(doubleArrayOf(29.1, 3.5, 3455.3453, 4767364.44444)))
        arrOfPoints.add(Point(doubleArrayOf(26.5, -3.5, 932345.88, -36674.3545)))
        arrOfPoints.add(Point(doubleArrayOf(0.0, 0.0, 0.0, 0.0)))
        var controlPoint: Point = Point(doubleArrayOf(1.0, 0.0, 734.432, -90.2))

        assertEquals(ans, pointCalculator(arrOfPoints, controlPoint), 0.0001)
    }

    @Test
    fun test4() {
        var arrOfPoints = ArrayList<Point> ()

        var ans = 1000000.0
        arrOfPoints.add(Point(doubleArrayOf(345346.5, 87346.7635762534875, 3847658234.7, 658.37465, 78346587.4635873465, -93847.5, -23.4, -2432.3)))
        arrOfPoints.add(Point(doubleArrayOf(3487560.2834, -365.0, -437485676.24, -35.634, 5768.3, 4765345.34, 50923097.76, 34987.34)))
        arrOfPoints.add(Point(doubleArrayOf(44.9, 58.67, 3453.453, 458.4, -5634.85, -34563847563.4958, -346587.3, 43.45)))
        arrOfPoints.add(Point(doubleArrayOf(34.4, 35.876, 34.2875, 64.5, -3465354.645, -354.609, 246.0982, 4857603.28475687)))
        var controlPoint: Point = Point(doubleArrayOf(3.0, 485.70, 93.4857, -093485.098, 23.4, -59.8, -03495809348.5, -934576.87436586512434))

        assertEquals(ans, pointCalculator(arrOfPoints, controlPoint), 0.0001)
    }

    @Test
    fun test5() {
        var arrOfPoints = ArrayList<Point> ()

        var ans = 0.0
        arrOfPoints.add(Point(doubleArrayOf(29.1, 3.5)))
        arrOfPoints.add(Point(doubleArrayOf(26.5, -3.5)))
        arrOfPoints.add(Point(doubleArrayOf(0.0, 0.0)))
        var controlPoint: Point = Point(doubleArrayOf(0.0, 0.0))

        assertEquals(ans, pointCalculator(arrOfPoints, controlPoint), 0.0001)
    }

}