package tutorials

/**
 * Created by tonykazanjian on 2/2/19.
 */

fun main(args: Array<String>) {
    val name = "Madrigal"
    var hp = 89
    val isBlessed = true
    val isImmortal = false
    val auraColor = auraColor(isBlessed, hp, isImmortal)
    val healthStatus = formatHealthStatus(hp, isBlessed)

    printPlayerStatus(auraColor, isBlessed, name, healthStatus)
    castFireball(2)

}

private fun castFireball(numFireballs: Int = 0)  {
    val inebriationStatus = when(numFireballs) {
        in 1..10 -> "tipsy."
        in 11..20 -> "sloshed."
        in 21..30 -> "soused."
        in 31..40 -> "stewed."
        in 41..50 -> "..t0aSt3d."
        else -> "OK."
    }
    println("$numFireballs glasses of Fireball spring into existence. You are $inebriationStatus")

}

private fun printPlayerStatus(auraColor: String, isBlessed: Boolean, name: String, healthStatus: String) {
    println("(Aura: $auraColor) " +
            "(Blessed: ${if (isBlessed) "YES" else "NO"})")
    println("$name $healthStatus")
}

private fun auraColor(isBlessed: Boolean, hp: Int, isImmortal: Boolean) =
        if (isBlessed && hp > 50 || isImmortal) "GREEN" else "NONE"


private fun formatHealthStatus(hp: Int, isBlessed: Boolean) =
        when (hp) {

            100 -> "is in excellent condition"
            in 90..99 -> "has a few scratches."
            in 75..89 -> if (isBlessed) {
                "has some minor wounds but is healing quite nicely"
            } else {
                "has some minor wounds"
            }
            in 15..74 -> "looks pretty hurt"
            else -> "is in awful condition"
        }
