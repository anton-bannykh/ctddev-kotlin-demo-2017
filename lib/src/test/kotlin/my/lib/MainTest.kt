package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun test1() {
        var d = Array(10, { Array(10, { 1 }) })
        assertEquals(d, floyd(d))
    }

    @Test
    fun test2() {
        var d = Array(3, { Array(3, { 0 }) })
        var ans = d
        d[0][1] = 3
        d[0][2] = 1
        d[1][0] = 1
        d[1][2] = 1
        d[2][0] = 1
        d[2][1] = 1
        ans[0][1] = 2
        assertEquals(ans, floyd(d))
    }

    @Test
    fun test3() {
        var d = Array(2, { Array(2, { 0 }) })
        d[0][1] = 2
        d[1][0] = 1
        assertEquals(d, floyd(d))
    }

    @Test
    fun test4() {
        var d = Array(5, { Array(5, { 0 }) })
        var ans = d
        d[0][0] = 0
        d[0][1] = 8
        d[0][2] = 9
        d[0][3] = 7
        d[0][4] = 5
        d[1][0] = 7
        d[1][1] = 0
        d[1][2] = 4
        d[1][3] = 11
        d[1][4] = 3
        d[2][0] = 4
        d[2][1] = 9
        d[2][2] = 0
        d[2][3] = 11
        d[2][4] = 5
        d[3][0] = 8
        d[3][1] = 2
        d[3][2] = 8
        d[3][3] = 0
        d[3][4] = 8
        d[4][0] = 3
        d[4][1] = 10
        d[4][2] = 9
        d[4][3] = 11
        d[4][4] = 0

        ans[0][0] = 0
        ans[0][1] = 8
        ans[0][2] = 9
        ans[0][3] = 7
        ans[0][4] = 5
        ans[1][0] = 6
        ans[1][1] = 0
        ans[1][2] = 4
        ans[1][3] = 11
        ans[1][4] = 3
        ans[2][0] = 4
        ans[2][1] = 9
        ans[2][2] = 0
        ans[2][3] = 11
        ans[2][4] = 5
        ans[3][0] = 8
        ans[3][1] = 2
        ans[3][2] = 6
        ans[3][3] = 0
        ans[3][4] = 5
        ans[4][0] = 3
        ans[4][1] = 10
        ans[4][2] = 9
        ans[4][3] = 10
        ans[4][4] = 0
        assertEquals(ans, floyd(d))
    }

    @Test
    fun test5() {
        var d = Array(5, { Array(5, { 0 }) })
        var ans = d
        d[0][0] = 0
        d[0][1] = 6
        d[0][2] = 5
        d[0][3] = 7
        d[0][4] = 3
        d[1][0] = 10
        d[1][1] = 0
        d[1][2] = 7
        d[1][3] = 7
        d[1][4] = 6
        d[2][0] = 3
        d[2][1] = 11
        d[2][2] = 0
        d[2][3] = 11
        d[2][4] = 4
        d[3][0] = 3
        d[3][1] = 11
        d[3][2] = 11
        d[3][3] = 0
        d[3][4] = 8
        d[4][0] = 2
        d[4][1] = 2
        d[4][2] = 10
        d[4][3] = 10
        d[4][4] = 0

        ans[0][0] = 0
        ans[0][1] = 5
        ans[0][2] = 5
        ans[0][3] = 7
        ans[0][4] = 3
        ans[1][0] = 8
        ans[1][1] = 0
        ans[1][2] = 7
        ans[1][3] = 7
        ans[1][4] = 6
        ans[2][0] = 3
        ans[2][1] = 6
        ans[2][2] = 0
        ans[2][3] = 10
        ans[2][4] = 4
        ans[3][0] = 3
        ans[3][1] = 8
        ans[3][2] = 8
        ans[3][3] = 0
        ans[3][4] = 6
        ans[4][0] = 2
        ans[4][1] = 2
        ans[4][2] = 7
        ans[4][3] = 9
        ans[4][4] = 0
        assertEquals(ans, floyd(d))
    }

    @Test
    fun test6() {
        var d = Array(3, { Array(3, { 0 }) })
        var ans = d
        d[0][0] = 0
        d[0][1] = 7
        d[0][2] = 9
        d[1][0] = 5
        d[1][1] = 0
        d[1][2] = 4
        d[2][0] = 10
        d[2][1] = 9
        d[2][2] = 0

        ans[0][0] = 0
        ans[0][1] = 7
        ans[0][2] = 9
        ans[1][0] = 5
        ans[1][1] = 0
        ans[1][2] = 4
        ans[2][0] = 10
        ans[2][1] = 9
        ans[2][2] = 0

        assertEquals(ans, floyd(d))
    }

    @Test
    fun test7() {
        var d = Array(3, { Array(3, { 0 }) })
        var ans = d
        d[0][0] = 0
        d[0][1] = 11
        d[0][2] = 2
        d[1][0] = 5
        d[1][1] = 0
        d[1][2] = 6
        d[2][0] = 9
        d[2][1] = 6
        d[2][2] = 0

        ans[0][0] = 0
        ans[0][1] = 8
        ans[0][2] = 2
        ans[1][0] = 5
        ans[1][1] = 0
        ans[1][2] = 6
        ans[2][0] = 9
        ans[2][1] = 6
        ans[2][2] = 0
        assertEquals(ans, floyd(d))
    }

    @Test
    fun test8() {
        var d = Array(4, { Array(4, { 0 }) })
        var ans = d
        d[0][0] = 0
        d[0][1] = 3
        d[0][2] = 4
        d[0][3] = 2
        d[1][0] = 11
        d[1][1] = 0
        d[1][2] = 8
        d[1][3] = 5
        d[2][0] = 7
        d[2][1] = 10
        d[2][2] = 0
        d[2][3] = 2
        d[3][0] = 11
        d[3][1] = 9
        d[3][2] = 3
        d[3][3] = 0

        ans[0][0] = 0
        ans[0][1] = 3
        ans[0][2] = 4
        ans[0][3] = 2
        ans[1][0] = 11
        ans[1][1] = 0
        ans[1][2] = 8
        ans[1][3] = 5
        ans[2][0] = 7
        ans[2][1] = 10
        ans[2][2] = 0
        ans[2][3] = 2
        ans[3][0] = 10
        ans[3][1] = 9
        ans[3][2] = 3
        ans[3][3] = 0
        assertEquals(ans, floyd(d))
    }
}