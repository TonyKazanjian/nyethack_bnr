package challenges

import java.io.File
import kotlin.math.roundToInt

/**
 * Created by tonykazanjian on 5/19/19.
 */
const val tavernTitle = "Taevyrn's Folly"

val patronList: List<String> = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-data.txt").readText().split("\n")

val patronGold = mutableMapOf<String, Double>()
fun main(args: Array<String>) {

    (0..9).forEach {
        val first = patronList.shuffled().first()
        val last = lastName.shuffled().first()
        val name = "$first $last"
        uniquePatrons += name
    }
    uniquePatrons.forEach {
        patronGold[it] = 6.0
    }

    var orderCount = 0
    while (orderCount <=3){
        placeOrder(uniquePatrons.shuffled().first(),
                menuList.shuffled().first())
        orderCount++
    }

    displayPatronBalances()
    displayRemainingPatrons()


}

fun displayPatronBalances(){
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}

fun displayRemainingPatrons(){
    println("The patrons who are left are $uniquePatrons")
}

fun getDrinkPrice(menuData: String) : Double {
    val (type, name, price) = menuData.split(',')
    return price.toDouble()
}

private fun placeOrder(patron: String, menuData: String) {
    val indexOfApostrophe = tavernTitle.indexOf('\'')
    val tavernMaster = tavernTitle.substring(0 until indexOfApostrophe)
    println("$patron speaks with $tavernMaster about their order.")

    performPurchase(menuData, patron)
}

fun performPurchase(menuData: String, patron: String) {
    val (type, name, price) = menuData.split(',')

    val totalPurse = patronGold.getValue(patron)

    patronGold[patron] = totalPurse - price.toDouble()

    if (totalPurse < price.toDouble()){
        uniquePatrons.remove(patron)
        println("The bouncer throws $patron out")

    } else {
        val phrase = if (name == "Dragon's Breath") {
            "$patron exclaims ${toDragonSpeak("Ah, delicious $name!")}"
        } else {
            "$patron says: Thanks for the $name."
        }
        println(phrase)
    }

}

private fun toDragonSpeak(phrase: String) =
        phrase.replace(Regex("[aeiou]")) {
            when (it.value) {
                "a" -> "4"
                "e" -> "3"
                "i" -> "1"
                "o" -> "0"
                "u" -> "|_|"
                else -> it.value
            }
        }
