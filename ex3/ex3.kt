package ex3

import ex2.Rectangle
import ex2.readCoordinates
import kotlin.math.max
import kotlin.math.min

fun Rectangle.intersectionArea(other: Rectangle): Int {
    // Add 1 to include boundary point
    val overlappingWidth = (min(this.right, other.right) - max(this.left, other.left)) + 1
    val overlappingHeight = (min(this.top, other.top) - max(this.bottom, other.bottom)) + 1
    return overlappingWidth * overlappingHeight
}

fun main() {
    val rect1 = readCoordinates(" First rectangle coordinates (X1,Y1; X2,Y2): ")
    val rect2 = readCoordinates("Second rectangle coordinates (X1,Y1; X2,Y2): ")
    println("Overlapping area: ${rect1.intersectionArea(rect2)}")
}