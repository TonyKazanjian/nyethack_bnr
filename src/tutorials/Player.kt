package tutorials

/**
 * Created by tonykazanjian on 5/21/19.
 */
class Player {
    var name = "madrigal"
    get() = field.capitalize()
    private set(value) {
        field = value.trim()
    }

    var healthPoints = 89
    val isBlessed = true
    private val isImmortal = false

    fun castFireball(numFireballs: Int){

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

    fun auraColor() =
            if (isBlessed && healthPoints > 50 || isImmortal) "GREEN" else "NONE"


    fun formatHealthStatus() =
            when (healthPoints) {

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

}