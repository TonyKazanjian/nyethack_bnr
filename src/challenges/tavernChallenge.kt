package challenges
import java.io.File
import kotlin.coroutines.experimental.buildIterator

/**
 * Created by tonykazanjian on 2/2/19.
 */

const val tavernName = "Taevyrn's Folly"


val menuFile = File("data/tavern-menu-data.txt").readText().split("\n")
var MENU_CHAR_LENGTH = 30
val itemTypeSet = mutableSetOf<String>()
fun main(args: Array<String>) {
    formatMenu(menuFile)
}

fun formatMenu(menuList: List<String>){
    println("*** Welome to Taevyrn's Folly! ***")
    println()
    val fullMenu = mutableListOf<String>()
    val categorySet = getCategorySet(menuList)
    for (item in menuFile){
        fullMenu.add(item)
    }
    println(sortDrinksToCategory(categorySet, fullMenu))
}

fun removeTypeAndComma(menuItem : String) : List<String> {
    val itemSet = getFullMenuItem(menuItem).toMutableList()
    itemSet.removeAt(0)
    return itemSet
}

/**
 * Returns a list of strings that contains the full menu item
 */
fun getFullMenuItem(menuItem: String) : List<String> {
    val (type, name, price) = menuItem.split(',')
    val capName = name.capitalize()
    val itemList = mutableListOf<String>(type, capName, price)
    return itemList
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



fun getCategoryFromMenuItem(menuItem : String) : String{
    val (type, name, price) = menuItem.split(',')
    return type
}

fun getCategorySet(menuItems : List<String>) : Set<String> {
    val categories = mutableSetOf<String>()
    for (item in menuFile){
        categories.add(getCategoryFromMenuItem(item))
    }
    return categories
}

/**
 * Checks if full menu items belong in a drink category
 */
fun sortDrinksToCategory(categorySet : Set<String>, menuItemList : MutableList<String>) : String {
    val stringBuider = StringBuilder()
    (0..categorySet.size - 1).forEach {
        for (item in menuItemList){
            val type = categorySet.elementAt(it)
            if (getFullMenuItem(item).contains(type)){
                val typeString = "~$type~\n"
                stringBuider.append(typeString)
                stringBuider.append(formattedMenuItem(removeTypeAndComma(menuItemList[it]))+"\n")
            }

        }
    }
    return stringBuider.toString()
}



