/**
 * Created by Marina K. on 04.12.2017.
 */

var n=2017
var prime = IntArray(n+1, { true })

fun isPrime(x: Int): Boolean {
    return prime[x]
}

fun main(args: Array<String>) {
    //var n=readLine()//.parseInt()
    prime[0]=false
    prime[1]=false
    for(i in 2..n){
        if(prime[i])
            for(j in i*i..n step i)
                prime[j] = false;
    }


}
