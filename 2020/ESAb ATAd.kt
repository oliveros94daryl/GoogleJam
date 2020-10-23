import kotlin.system.exitProcess

var numQueries = 0
var B = 0
var different = -1
var equal = -1
var res = Array(B) { "?" }

fun main() {
    val firstInput = readLine().toString().split(" ").map { it.toInt() }
    val T = firstInput[0]
    B = firstInput[1]
    for (t in 1..T) {
        println(solve(t))
        if (readLine().toString() == "N") {
            exitProcess(0)
        }
    }
}

private fun solve(t: Int): String {
    numQueries = 0
    different = -1
    equal = -1
    res = Array(B) { "?" }

    for(i in 0 until B) {
        res[i] = getBit(i)
    }

    return res.joinToString("")
}

private fun getBit(b: Int): String {
    println((b + 1).toString())
    numQueries++

    if (numQueries.toString().last() == '1') {

        var equalsChanged = false
        var differentsChanged = false

        if (equal >= 0) {
            if (res[equal] != getBit(equal)) {
                equalsChanged = true
            }
        }

        if (different >= 0) {
            if (res[different] != getBit(different)) {
                differentsChanged = true
            }
        }

        if (equalsChanged && differentsChanged) {
            res = complement(res)
        } else if (differentsChanged) {
            res = reverse(res)
        } else if (equalsChanged) {
            res = both(res)
        }

    }

    var response = readLine().toString()

    if(different < 0 || equal < 0) {
        var b2 = getBit(B-b-1)
        if(b2 != response) different = b
        else equal = b
    }

    return response
}

private fun reverse(bits: Array<String>): Array<String> {
    return bits.reversedArray()
}

private fun complement(bits: Array<String>): Array<String> {
    for (i in 1 until bits.size) {
        if(bits[i] == "1") bits[i] = "0"
        else if (bits[i] == "0") bits[i] = "1"
    }
    return bits
}

private fun both(bits: Array<String>): Array<String> {
    var res = reverse(bits)
    return complement(res)
}
