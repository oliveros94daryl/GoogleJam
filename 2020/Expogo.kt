var results = HashMap<Pair<Int, Int>, String>()

fun main() {

    compute(0, 0, "", -1)

    val T = readLine().toString().toInt()
    for (t in 1..T) {
        solve(t)
    }


}

private fun solve(t: Int) {

    val inp = readLine().toString().split(" ").map { it.toInt() }
    val X = inp[0]
    val Y = inp[1]

    if (results.containsKey(Pair(X, Y)))
        println("Case #$t: ${results.get(Pair(X, Y))}")
    else println("Case #$t: IMPOSSIBLE")

}

private fun compute(x: Int, y: Int, path: String, i: Int) {

    val pair = Pair(x, y)
    if (!results.containsKey(pair) || results.get(pair)!!.length > path.length) {
        println("Adding $x,$y : $path")
        results.put(Pair(x, y), path)
    }

    val step = pow2(i + 1)

    if ((x.toLong() + step) <= 1000000000)
        compute(x + (step).toInt(), y, path + "E", i + 1)

    if ((x.toLong() - step) >= -1000000000)
        compute(x - (step).toInt(), y, path + "W", i + 1)

    if ((y.toLong() + step) <= 1000000000)
        compute(x, y + step.toInt(), path + "N", i + 1)

    if ((y.toLong() - step) >= -1000000000)
        compute(x, y - step.toInt(), path + "S", i + 1)
}

private fun pow2(n: Int): Long {
    return Math.pow(2.0, n.toDouble()).toLong()
}