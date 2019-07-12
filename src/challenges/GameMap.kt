package challenges

import tutorials.Game
import tutorials.Room
import tutorials.TownSquare

/**
 * Created by tonykazanjian on 7/11/19.
 */
class GameMap {

    var currentRoom: Room =  TownSquare()
    val topRoomList = listOf<Room>(currentRoom, Room("Tavern"), Room("Back Room"))
    val bottomRoomList = listOf<Room>(Room("Long Corridor"), Room ("Generic Room"))
    var worldMap : List<List<Room>>

    init {
        worldMap = initGameMap()
    }

    private fun initGameMap() = listOf(
            topRoomList,
            bottomRoomList)



}