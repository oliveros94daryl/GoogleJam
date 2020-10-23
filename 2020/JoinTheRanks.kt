fun main() {

    val T = readLine().toString().toInt()
    for (t in 1..T) {
        solve(t)
    }


}

private fun solve(t: Int) {
    val inp = readLine().toString().split(" ").map { it.toInt() }
    val R = inp[0]
    val S = inp[1]

    var pile = ArrayList<Array<Int>>(0)
    for(s in 1..S)
        for(r in 1..R){
            pile.add(arrayOf(r, s))
        }

    //1
    printPile(pile)

    var steps = ArrayList<String>(0)
    for(r in 1..R) {
        var modify = false
        for(i in 0 until pile.size) {
            if(pile[i][0] != r) modify = true
            if(modify && pile[i][0] == r) {
                pile = modifyPile(pile, i, 1)
                steps.add("$i 1")
            }

        }
        //printPile(pile)
    }
    println("Case #$t: ${steps.size}")
    for (s in steps) {
        println(s)
    }
}
private fun printPile(pile: ArrayList<Array<Int>>) {
    for(i in 0 until pile.size) {
        println("${pile[i][0]} ${pile[i][1]}")
    }
    println()
}

private fun modifyPile(pile: ArrayList<Array<Int>>, a:Int, b:Int): ArrayList<Array<Int>> {
    var pilaA = ArrayList<Array<Int>>(0)
    var pilaB = ArrayList<Array<Int>>(0)

    var i = 0
    while(i < a) {
        pilaA.add(pile[i])
        i++
    }
    var j = 0
    while(j < b) {
        pilaB.add(pile[i + j])
        j++
    }

    j = 0
    while(j < b) {
        pile[j] = pilaB[j]
        j++
    }

    i = 0
    while(i < a) {
        pile[j+i] = pilaA[i]
        i++
    }

    return pile
}