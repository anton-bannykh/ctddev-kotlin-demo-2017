package my.lib

import org.junit.Assert.assertEquals
import org.junit.Test

class MainTest {
    @Test
    fun simpleInput() {
        var tree = SplayTree<Int>()
        for (i in 1..20 step 2) {
            tree.put(i)
        }

        for (i in 1..20 step 1) {
            if (i % 2 != 0)
                assert(tree.contains(i) == true)
            else
                assert(tree.contains(i) == false)
            if (tree.contains(i)) {
                if (i == 1) {
                    assertEquals(tree.prev(i - 1), null)
                } else {
                    assertEquals(tree.prev(i - 1), i - 2)
                }
                if (i == 19) {
                    assertEquals(tree.next(i + 1), null)
                } else {
                    assertEquals(tree.next(i + 1), i + 2)
                }
            }
        }
    }

    @Test
    fun insertErase() {
        var tree = SplayTree<String>()
        val names : MutableList<String> = arrayListOf("Game of Thrones", "The Flash", "Grey's Anatomy", "Supernatural", "Star Trek", "Supergirl", "The Big Bang Theory")
        tree.put("Supernatural")
        tree.put("Star Trek")
        tree.put("Supergirl")
        tree.put("The Big Bang Theory")
        tree.put("Stranger Things")
        tree.put("The Flash")
        tree.put("Grey's Anatomy")
        tree.put("Game of Thrones")

        tree.del("asdfasdfsadf")
        for (x in names) {
            assert(tree.contains(x))
        }

        var delNames : MutableList<String> = arrayListOf()

        while (!names.isEmpty()) {
            tree.del(names[0])
            delNames.add(names[0])
            names.removeAt(0)
            for (x in delNames)
                assert(!tree.contains(x))
            for (x in names)
                assert(tree.contains(x))
        }
    }

}