import kotlin.math.pow

fun main() {
    val T = readLine().toString().toInt()
    for (t in 1..T) {
        solve(t)
    }
}

private fun solve(t: Int) {
    val N = readLine().toString().toInt()

    println("Case #$t:")
    println("1 1")
    var steps = 1
    var sum = 1


    if(N < 32) {
        for(i in 2..N) {
            println("$i 1")
        }
        return
    }

    val Nminus30 = N - 30
    //Convert to binary
    var res = Nminus30
    var bin = ArrayList<Int>(0)
    while (res > 0) {
        bin.add(if (res % 2 == 0) 0 else 1)
        res /= 2
    }

    var walkingOnLeft = true
    var row = 0
    for (i in 1 until bin.size) {
        row = i + 1
        if (bin[i] == 0) {
            print("$row ")
            println(if (walkingOnLeft) "1" else row.toString())
            sum++
        } else {
            var j = if (walkingOnLeft) 1 else row
            var step = if (walkingOnLeft) 1 else -1
            while (walkingOnLeft && j <= row || !walkingOnLeft && j >= 1) {
                println("$row $j")
                j += step
                steps++
            }
            sum += 2.0.pow(i).toInt()
            walkingOnLeft = walkingOnLeft.not()
        }
    }

    //Sum missing ones
    var sumUntilNow = sum
    for(i in 1..(N-sumUntilNow)) {
        row++
        print("$row ")
        println(if (walkingOnLeft) 1 else row)
        steps++
        sum++
    }

    //println(steps)
    //println(sum)

}