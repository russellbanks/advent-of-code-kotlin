package day1

import java.io.File

fun main() {
    fun part1(input: List<Int>): Int {
        return input.windowed(2).count { (a, b) -> a < b }
    }

    // A + B + C <=> B + C + D
    fun part2(input: List<Int>): Int {
        return input
            .windowed(4)
            .count {
                it[0] < it[3]
            }
    }

    val input = File("src/day1/input.txt")
        .readLines()
        .map { it.toInt() }
    println("Part 1: ${part1(input)}")
    println("Part 2: ${part2(input)}")
}