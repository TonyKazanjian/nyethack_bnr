package tutorials

import java.io.File
import kotlin.math.roundToInt

/**
 * Created by tonykazanjian on 2/2/19.
 */

const val tavernName = "Taevyrn's Folly"

var playerGold = 10
var playerSilver = 10

val patronList: List<String> = mutableListOf("Eli", "Mordoc", "Sophie")
val lastName = listOf("Ironfoot", "Fernsworth", "Baggins")
val uniquePatrons = mutableSetOf<String>()
val menuList = File("data/tavern-menu-data.txt").readText().split("\n")

val patronGold = mapOf("Eli" to 10.5, "Mordoc" to 8.0, "Sophie" to 5.5)
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
    println(uniquePatrons)
    var orderCount = 0
    while (orderCount <=9){
        placeOrder(uniquePatrons.shuffled().first(),
                menuList.shuffled().first())
        orderCount++
    }

    println(patronGold)


}

fun performPurchase(price : Double){
    displayBalance()
    val totalPurse = playerGold + (playerSilver /100.0)
    println("Total purse : $totalPurse")
    println("Purchasing it for $price")

    val remainingBalance = totalPurse - price
    println("Remaining balance: ${"%.2f".format(remainingBalance)}")

    val remainingGold = remainingBalance.toInt()
    val remainingSilver = (remainingBalance % 1 * 100).roundToInt()
    playerGold = remainingGold
    playerSilver = remainingSilver
    displayBalance()
}

fun displayBalance(){
    println("Player's purse balance: Gold: $playerGold , Silver: $playerSilver")
}

private fun placeOrder(patron: String, menuData: String) {
    val indexOfApostrophe = tavernName.indexOf('\'')
    val tavernMaster = tavernName.substring(0 until indexOfApostrophe)
    println("$patron speaks with $tavernMaster about their order.")

    val (type, name, price) = menuData.split(',')
    val message = "$patron buys a $name ($type) for $price."
    println(message)

    val phrase = if (name == "Dragon's Breath") {
        "$patron exclaims ${toDragonSpeak("Ah, delicious $name!")}"
    } else {
        "$patron says: Thanks for the $name."
    }

    println(phrase)
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
