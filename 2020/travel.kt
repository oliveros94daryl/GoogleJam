import java.util.*

fun main() {
    val T = readLine().toString().toInt()
    for (t in 1..T) {
        solve(t)
    }
}

private fun solve(t: Int) {
    println("Case #$t:")
    val N = readLine().toString().toInt()
    val I = readLine().toString().map { "Y".equals("$it") }
    val O = readLine().toString().map { "Y".equals("$it") }

    for(n in 0 until N) {
        val P = Array(N) {"N"}
        P[n] = "Y"
        if(O[n]) {
            // -->
            for(i in n+1..N-1) {
                if(I[i] && O[i]) P[i] = "Y"
                else if (I[i]) {
                    P[i] = "Y"
                    break
                } else {
                    break
                }
            }
            // <--
            for(i in n-1 downTo 0) {
                if(I[i] && O[i]) P[i] = "Y"
                else if (I[i]) {
                    P[i] = "Y"
                    break
                } else {
                    break
                }
            }

        }

        println(P.joinToString(""))
    }


}
