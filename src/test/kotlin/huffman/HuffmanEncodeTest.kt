package huffman

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Telnov Sergey on 01.12.2017.
 */

class HuffmanEncodeTest {

    @Test
    fun huffmanEncodeTest() {
        assertEquals(
                "01001100100111",
                huffmanEncode("abacabad")
        )
        assertEquals(
                "0",
                huffmanEncode("a")
        )
        assertEquals(
                "a",
                decodeHuffmanEncode("0", hashMapOf(
                        Pair("0", 'a')
                ))
        )
        assertEquals(
                "abacabad",
                decodeHuffmanEncode("01001100100111", hashMapOf(
                        Pair("0", 'a'),
                        Pair("10", 'b'),
                        Pair("110", 'c'),
                        Pair("111", 'd')
                ))
        )

    }
//    @Test
//    fun huffmanRandomTest() {
//        val random = Random()
//        (1..10).forEach {
//            val s = buildString {
//                val characters = Array<Int>(random.nextInt(1000)) { random.nextInt(26)}
//                characters.forEach {
//                    append((it + 141).toChar())
//                }
//            }
//
//        }
//    }
}