package challenges
import java.io.File
import kotlin.math.roundToInt

/**
 * Created by tonykazanjian on 2/2/19.
 */

const val tavernName = "Taevyrn's Folly"


val menuList = File("data/tavern-menu-data.txt").readText().split("\n")
var MENU_CHAR_LENGTH = 30
fun main(args: Array<String>) {
    formatMenu(menuList)
}

fun formatMenu(menuList: List<String>){
    println("*** Welome to Taevyrn's Folly! ***")
    println()
    for (item in menuList){
        val menuItem = removeTypeAndComma(item)
        println(formattedMenuItem(menuItem))
    }
}

fun removeTypeAndComma(menuItem : String) : List<String> {
    val itemSet = getFullMenuItem(menuItem).toMutableList()
    itemSet.removeAt(0)
    return itemSet
}

fun getFullMenuItem(menuItem: String) : Set<String> {
    val (type, name, price) = menuItem.split(',')
    val capName = name.capitalize()
    val itemSet = mutableSetOf<String>(type, capName, price)
//    createDrinkCategory(type, itemSet)
    return itemSet
}

fun sortDrinksIntoCategories(drinkType : String, menuItem : Set<String>, menuList : List<String>) : Set<String> {

    val itemTypeSet = mutableSetOf<String>()
    if (menuItem.contains(drinkType)){
        itemTypeSet.addAll(menuItem)
    }
    return itemTypeSet;
}

fun formattedMenuItem(menuItem : List<String>) : String{
    val item = menuItem[0].replace(",", "")
    val price = menuItem[1]
    val stringBuider = StringBuilder()
    stringBuider.append(item)
    while (stringBuider.length <= MENU_CHAR_LENGTH){
        stringBuider.append(".")
    }
    stringBuider.append(price)
    return  stringBuider.toString()
}


