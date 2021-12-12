package day5

import java.io.File
import kotlin.math.absoluteValue
import kotlin.math.max
import kotlin.math.sign

fun main() {
    fun List<String>.day5(diagonals: Boolean = false): Int {
        val grid = mutableMapOf<Pair<Int, Int>, Int>()

        forEach { line ->
            val coordinates = line.split(" ")
            val firstCoordinate = coordinates[0].split(",").map { it.toInt() }
            val secondCoordinate = coordinates[2].split(",").map { it.toInt() }

            val xIncline = (firstCoordinate[0] - secondCoordinate[0]).sign
            val yIncline = (firstCoordinate[1] - secondCoordinate[1]).sign

            val h = max(
                (firstCoordinate[0] - secondCoordinate[0]).absoluteValue,
                (firstCoordinate[1] - secondCoordinate[1]).absoluteValue
            )

            for (i in 0..h) {
                if (!diagonals && xIncline != 0 && yIncline != 0) break
                val currentCoordinate = Pair(firstCoordinate[0] + i * -xIncline, firstCoordinate[1] + i * -yIncline)
                grid[currentCoordinate] = (grid[currentCoordinate] ?: 0).plus(1)
            }
        }

        return grid.count { it.value > 1 }
    }

    File("src/day5/input.txt").readLines().also {
        println("Part 1: ${it.day5(false)}")
        println("Part 2: ${it.day5(true)}")
    }
}