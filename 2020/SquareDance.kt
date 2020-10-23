fun main() {
    val T = readLine().toString().toInt()
    for (t in 1..T) {
        solve(t)
    }
}

private fun solve(t: Int) {

    var inp = readLine().toString().split(" ").map { it.toInt() }
    val R = inp[0]
    val C = inp[1]

    var danceFloor = Array(R) { readLine().toString().split(" ").map { Competitor(it.toInt()) } }

    // Update neighbors
    var roundInterest = 0L
    var competitors: ArrayList<Competitor> = ArrayList(R * C)
    for (r in 0 until R) {
        for (c in 0 until C) {
            competitors.add(danceFloor[r][c])
            roundInterest += danceFloor[r][c].skill
            if (r > 0) danceFloor[r][c].upNeighbor = danceFloor[r - 1][c]
            if (r < R - 1) danceFloor[r][c].downNeighbor = danceFloor[r + 1][c]
            if (c > 0) danceFloor[r][c].leftNeighbor = danceFloor[r][c - 1]
            if (c < C - 1) danceFloor[r][c].rightNeighbor = danceFloor[r][c + 1]
        }
    }

    //Rounds
    var competitionInterest = 0L

    do {
        competitionInterest += roundInterest
        //see who is eliminated
        var eliminated: ArrayList<Competitor> = ArrayList(0)
        for (competitor in competitors) {
            if (!competitor.eliminated && competitor.getNeighborsAverageSkill() > competitor.skill) {
                eliminated.add(competitor)
                competitor.eliminated = true
                roundInterest -= competitor.skill
            }
        }
        competitors = ArrayList(0)

        //remove them
        for (competitor in eliminated) {
            if (competitor.upNeighbor != null) competitors.add(competitor.upNeighbor!!)
            if (competitor.downNeighbor != null) competitors.add(competitor.downNeighbor!!)
            if (competitor.leftNeighbor != null) competitors.add(competitor.leftNeighbor!!)
            if (competitor.rightNeighbor != null) competitors.add(competitor.rightNeighbor!!)
            competitor.remove()
        }

    } while (eliminated.size > 0)

    println("Case #$t: $competitionInterest")

}

private fun printDanceFloor(dancefloor: Array<List<Competitor>>) {
    for (row in dancefloor) {
        println(row.joinToString(" "))
    }
    println()
}

private class Competitor(val skill: Int) {
    var eliminated = false
    var upNeighbor: Competitor? = null
    var downNeighbor: Competitor? = null
    var leftNeighbor: Competitor? = null
    var rightNeighbor: Competitor? = null

    fun getNeighborsAverageSkill(): Double {
        var numOfNeighbors = 0.0
        var totalSkill = 0.0
        if (upNeighbor != null) {
            numOfNeighbors++
            totalSkill += this.upNeighbor!!.skill
        }
        if (downNeighbor != null) {
            numOfNeighbors++
            totalSkill += this.downNeighbor!!.skill
        }
        if (leftNeighbor != null) {
            numOfNeighbors++
            totalSkill += this.leftNeighbor!!.skill
        }
        if (rightNeighbor != null) {
            numOfNeighbors++
            totalSkill += this.rightNeighbor!!.skill
        }
        return if (numOfNeighbors > 0) totalSkill / numOfNeighbors else 0.0
    }

    fun remove() {
        if (this.leftNeighbor != null) {
            this.leftNeighbor!!.rightNeighbor = this.rightNeighbor
        }
        if (this.rightNeighbor != null) {
            this.rightNeighbor!!.leftNeighbor = this.leftNeighbor
        }
        if (this.upNeighbor != null) {
            this.upNeighbor!!.downNeighbor = this.downNeighbor
        }
        if (this.downNeighbor != null) {
            this.downNeighbor!!.upNeighbor = this.upNeighbor
        }
    }

    override fun toString(): String {
        return if (eliminated) "." else skill.toString()
    }

}