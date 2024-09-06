package ex2

import kotlin.math.max
import kotlin.math.min

class Rectangle(p1: Pair<Int, Int>, p2: Pair<Int, Int>) {
    val left: Int = min(p1.first, p2.first)
    val right: Int = max(p1.first, p2.first)
    val bottom: Int = min(p1.second, p2.second)
    val top: Int = max(p1.second, p2.second)

    fun intersects(other: Rectangle): Boolean {
        return (this.left <= other.right) and (this.right >= other.left) and (this.top >= other.bottom) and (this.bottom <= other.top)
    }
}

fun main() {
    val rect1 = readCoordinates(" First rectangle coordinates (X1,Y1; X2,Y2): ")
    val rect2 = readCoordinates("Second rectangle coordinates (X1,Y1; X2,Y2): ")

    if (rect1.intersects(rect2))
        println("Rectangles intersects!")
    else
        println("Rectangles don't intersect")
}

fun readCoordinates(message: String): Rectangle {
    var validInput = false
    var input: String
    val regInput = Regex("""^\(?(-?\d+)[,; ]*(-?\d+)[,; ]*(-?\d+)[,; ]*(-?\d+)\)?$""")

    do {
        print(message)
        input = readln().trim()
        if (regInput.matches(input))
            validInput = true
        else
            println("[!] Please use the format (X1,Y1; X2,Y2)")
    } while (!validInput)

    val groups = regInput.matchEntire(input)?.groups
    val x1 = groups?.get(1)?.value?.toInt()
    val y1 = groups?.get(2)?.value?.toInt()
    val x2 = groups?.get(3)?.value?.toInt()
    val y2 = groups?.get(4)?.value?.toInt()

    if ((x1 == null) or (y1 == null) or (x2 == null) or (y2 == null))
        throw NullPointerException("Error parsing input")

    return Rectangle(Pair(x1!!, y1!!), Pair(x2!!, y2!!))
}