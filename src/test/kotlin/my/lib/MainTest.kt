package my.lib

import org.junit.Assert
import org.junit.Test

class MainTest {
    @Test
    fun testbfs() {
        gcreat()
        bfs(1)
        Assert.assertEquals(1, d[2])
        Assert.assertEquals(2, d[6])
    }
    @Test
    fun testbfsmany() {
        bfs(2)
        Assert.assertEquals(0, d[1])
        Assert.assertEquals(1, d[4])
        bfs(6)
        Assert.assertEquals(0, d[5])
        Assert.assertEquals(3, d[1])
    }
    @Test
    fun testbfsfinal(){
        for(i in 1..7){
            bfs(i)
            Assert.assertEquals(0,d[i-1])
        }
    }
}