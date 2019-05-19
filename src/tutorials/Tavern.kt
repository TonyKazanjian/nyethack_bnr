package tutorials

import java.io.File
import kotlin.math.roundToInt

/**
 * Created by tonykazanjian on 2/2/19.
 */

const val tavernName = "Taevyrn's Folly"

val patronList: List<String> = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-data.txt").readText().split("\n")

val patronGold = mutableMapOf<String, Double>()
fun main(args: Array<String>) {

    if (patronList.contains("Eli")) {
        println("The tavern master says: Eli's in the back playing cards.")
    } else {
        println("The tavern master says: Eli isn't here.")
    }

    if (patronList.containsAll(listOf("Sophie", "Mordoc"))) {
        println("The tavern master says: Yea, they're seated by the stew kettle.")
    } else {
        println("The tavern master says: Nay, they departed hours ago.")
    }
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
    while (orderCount <=9){
        placeOrder(uniquePatrons.shuffled().first(),
                menuList.shuffled().first())
        orderCount++
    }

    displayPatronBalances()


}

fun displayPatronBalances(){
    patronGold.forEach { patron, balance ->
        println("$patron, balance: ${"%.2f".format(balance)}")
    }
}

private fun placeOrder(patron: String, menuData: String) {
    val indexOfApostrophe = tavernName.indexOf('\'')
    val tavernMaster = tavernName.substring(0 until indexOfApostrophe)
    println("$patron speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    performPurchase(price.toDouble(), patron)

    val phrase = if (name == "Dragon's Breath") {
        "$patron exclaims ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "$patron says: Thanks for the $name."
    }

    println(phrase)
}

fun performPurchase(price: Double, patronName: String) {
    val totalPurse = patronGold.getValue(patronName)
    patronGold[patronName] = totalPurse - price;
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
