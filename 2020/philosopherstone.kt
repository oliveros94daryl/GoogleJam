fun main() {
    val T = readLine().toString().toInt()
    for (t in 1..T) {
        solve(t)
    }
}

private fun solve(t: Int) {

    val N = readLine().toString().toInt()
    val C = readLine().toString().map { "$it" }
    var A = C.stream().filter { it == "A" }.count()
    var B = C.stream().filter { it == "B" }.count()

    val lesser = if (A < B) A else B
    val bigger = if (A < B) B else A

    if ((bigger - (lesser - 1)) == 2L) {
        println("Case #$t: Y")
    } else {
        println("Case #$t: N")
    }

}
