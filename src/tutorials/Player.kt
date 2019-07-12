package tutorials

import java.io.File

/**
 * Created by tonykazanjian on 5/21/19.
 */
class Player (_name: String, override var healthPoints: Int = 100,
              var isBlessed: Boolean,
              private val isImmortal: Boolean) : Fightable {
    var name = _name
        get() = "${field.capitalize()} of $hometown"
        private set(value) {
            field = value.trim()
        }

    val hometown by lazy { selectHometown()}
    var currentPosition = Coordinate(0,0)

    init {
        require(healthPoints > 0, {"healthPoints must be greater than zero."})
        require(name.isNotBlank(), {"Player must have a name."})
    }

    constructor(name: String) : this(name,
            isBlessed = true,
            isImmortal = false) {
        if (name.toLowerCase() == "kar") healthPoints = 40
    }

    private fun selectHometown() = File("data/towns.txt")
            .readText()
            .split("\n")
            .shuffled()
            .first()


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

    override val diceCount = 3

    override val diceSides = 6

    override fun attack(opponent: Fightable): Int {
        val damageDealt = if (isBlessed) {
            damageRoll * 2
        } else {
            damageRoll
        }
        opponent.healthPoints -= damageDealt
        return damageDealt
    }
}