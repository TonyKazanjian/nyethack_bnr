package tutorials

/**
 * Created by tonykazanjian on 2/2/19.
 */

fun main(args: Array<String>) {
    val player = Player("Tony")
    player.castFireball(2)

    val auraColor = player.auraColor()

    var currentRoom = TownSquare()
    println(currentRoom.description())
    println(currentRoom.load())

    printPlayerStatus(player)

}

private fun printPlayerStatus(player: Player) {
    println("(Aura: ${player.auraColor()}) " +
            "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
    println("${player.name} ${player.formatHealthStatus()}")
}