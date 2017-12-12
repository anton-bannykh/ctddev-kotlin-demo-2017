package my.lib.huffman

import org.junit.Assert.assertEquals
import org.junit.Test

/**
 * Created by Telnov Sergey on 01.12.2017.
 */

class HuffmanEncodeTest {

    @Test
    fun testHuffmanEncodeSimple() {
        assertEquals(
                "0",
                huffmanEncode("a")
        )
    }

    @Test
    fun testHuffmanEncode() {
        assertEquals(
                "01001100100111",
                huffmanEncode("abacabad")
        )
    }

    @Test
    fun testHuffmanDecodeSimple() {
        assertEquals(
                "a",
                decodeHuffmanEncode("0", hashMapOf(
                        Pair("0", 'a')
                ))
        )
    }

    @Test
    fun testHuffmanDecode() {
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

    @Test
    fun testHuffmanEncodeClass() {
        val encoder = HuffmanEncode()
        assertEquals(
                "01001100100111",
                encoder.huffmanEncode("abacabad")
        )

        assertEquals(
                "a: 0\n" +
                        "b: 10\n" +
                        "c: 110\n" +
                        "d: 111\n",
                encoder.digitsInfo
        )
    }
}