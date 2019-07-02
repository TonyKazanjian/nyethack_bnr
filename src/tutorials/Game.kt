package tutorials

/**
 * Created by tonykazanjian on 2/2/19.
 */

fun main(args: Array<String>) {

    Game.play();

}


object Game {

    var inSession = true
    val player = Player("Tony")
    var currentRoom: Room =  TownSquare()
    val topRoomList = listOf<Room>(currentRoom, Room("Tavern"), Room("Back Room"))
    val bottomRoomList = listOf<Room>(Room("Long Corridor"), Room ("Generic Room"))

    private var worldMap = listOf(
            topRoomList,
            bottomRoomList)

    private fun quitGame() : String {
        inSession = false;
        return "You've quit the game"

    }

    private fun move(directionInput: String) =
            try {
                val direction = Direction.valueOf(directionInput.toUpperCase())
                val newPosition = direction.updateCoordinate(player.currentPosition)
                if (!newPosition.isInBounds) {
                    throw IllegalStateException("$direction is out of bounds.")
                }

                val newRoom = worldMap[newPosition.y][newPosition.x]
                player.currentPosition = newPosition
                currentRoom = newRoom
                "Ok, you move $direction to the ${newRoom.name}.\n${newRoom.load()}"
            } catch (e: Exception){
                "Invalid direction: $directionInput"
            }


    init {
        println("Welcome, adventurer.")
        player.castFireball(2)

    }

    fun play(){
        while(inSession){
            // Play NyetHack
            println(currentRoom.description())
            println(currentRoom.load())

            printPlayerStatus(player)

            print("> Enter your command: ")
            println(GameInput(readLine()).processCommand())
        }
    }

    private fun printPlayerStatus(player: Player) {
        println("(Aura: ${player.auraColor()}) " +
                "(Blessed: ${if (player.isBlessed) "YES" else "NO"})")
        println("${player.name} ${player.formatHealthStatus()}")
    }

    private class GameInput(arg: String?) {
        private val input = arg ?: ""
        val command = input.split(" ")[0]
        val argument = input.split(" ").getOrElse(1, { "" })

        fun processCommand() = when (command.toLowerCase()) {
            "move" -> move(argument)
            "map" -> displayMap()
            "quit" -> quitGame()
            "ring" -> ringTownSquareBell(currentRoom)
            else -> commandNotFound()
        }


        private fun commandNotFound() = "I'm not quite sure what you're trying to do!"
    }

    private fun ringTownSquareBell(currentRoom: Room): String {

        if (currentRoom is TownSquare){
           return currentRoom.ringBell();
        } else {
            return "The bell's not here!"
        }
    }

    private fun displayMap() : String {
        val topRoomMap = buildRoomMap(topRoomList)
        val bottomRoomMap = buildRoomMap(bottomRoomList)
        val fullMap = "$topRoomMap\n$bottomRoomMap"
        return fullMap;

    }

    private fun buildRoomMap(roomList : List<Room>) : String{
        val roomStringBuilder = StringBuilder()
        roomList.forEach { room ->
            if (currentRoom.name.equals(room.name)){
                roomStringBuilder.append("X ")
            } else {
                roomStringBuilder.append("O ")
            }
        }
        return roomStringBuilder.toString()
    }
}