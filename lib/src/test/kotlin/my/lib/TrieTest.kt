import my.lib.Trie
import org.junit.Assert.assertEquals
import org.junit.Test

class TrieTest {
    @Test
    fun simpleTest() {
        val trie = Trie()
        trie.add("abc")
        trie.add("bcdc")
        trie.add("cccb")
        trie.add("bcdd")
        trie.add("bbbc")
        val entries = trie.findAll("abcdcbcddbbbcccbbbcccbb")
        val ans = listOf(Pair(0, "abc"), Pair(1, "bcdc"), Pair(5, "bcdd"),
                Pair(9, "bbbc"), Pair(12, "cccb"), Pair(15, "bbbc"), Pair(18, "cccb"))
        assertEquals(entries, ans)
    }

    @Test
    fun englishTest() {
        val trie = Trie()
        trie.add("buy")
        trie.add("ticket")
        val entries = trie.findAll("buy ticket for Harry Potter")
        val ans = listOf(Pair(0, "buy"), Pair(4, "ticket"))
        assertEquals(entries, ans)
    }

    @Test
    fun containsTest() {
        val trie = Trie()
        trie.add("buy")
        trie.add("ticket")
        assert(trie.contains("buy"))
        assert(!trie.contains("cats"))
    }

    @Test
    fun russianTest() {
        val trie = Trie()
        trie.add("купить")
        trie.add("билеты")
        val entries = trie.findAll("купить билеты на Гарри Поттера")
        val ans = listOf(Pair(0, "купить"), Pair(7, "билеты"))
        assertEquals(entries, ans)
    }

    @Test
    fun emptyTrieTest() {
        val trie = Trie()
        val entries = trie.findAll("aaababab")
        assert(entries.isEmpty())
    }

    @Test
    fun emptyStringTest() {
        val trie = Trie()
        trie.add(" ")
        val entries = trie.findAll("a b")
        assertEquals(entries, listOf(Pair(1, " ")))
    }

    @Test
    fun hardTest() {
        val trie = Trie()
        trie.add("aa")
        val entries = trie.findAll("aaaaa")
        val ans = listOf(Pair(0, "aa"), Pair(1, "aa"), Pair(2, "aa"), Pair(3, "aa"))
        assertEquals(entries, ans)
    }
}