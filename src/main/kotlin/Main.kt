fun main(args: Array<String>){
    val automat = AhoCorasick("aaa")
    val res = automat.findPos("aaaaa")
    for (i in res)
        println(i)
}