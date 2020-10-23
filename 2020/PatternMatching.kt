fun main() {

    val T = readLine().toString().toInt()
    for (t in 1..T) {
        solve(t)
    }
}

private fun solve(t: Int) {
    val N = readLine().toString().toInt()
    var patterns = Array(N) { readLine().toString().split("*") }

    // check all prefixes are substrings
    var longestPrefix = ""
    var longestSuffix = ""
    var middleStuff = StringBuilder()
    for (pattern in patterns) {
        var pref = pattern.first()
        if (pref != "" && longestPrefix.length < pref.length) {
            longestPrefix = pref
        }

        var suff = pattern.last()
        if (suff != "" && longestSuffix.length < suff.length) {
            longestSuffix = suff
        }

        if (pattern.size > 2) {
            for (i in 1 until pattern.size - 1) {
                middleStuff.append(pattern[i])
            }
        }

    }

    var res = longestPrefix + middleStuff.toString() + longestSuffix

    for (pattern in patterns) {
        if (!res.startsWith(pattern.first()) || !res.endsWith(pattern.last())) {
            println("Case #$t: *")
            return
        }
    }

    println("Case #$t: $res")

}