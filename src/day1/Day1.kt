package day1

import java.io.File

fun main() {
    fun part1(input: List<Int>): Int = input.windowed(2).count { (a, b) -> a < b }

    fun part2(input: List<Int>): Int = input.windowed(4).count { it[0] < it[3] }

    File("src/day1/input.txt").readLines().map { it.toInt() }.also {
        println("Part 1: ${part1(it)}")
        println("Part 2: ${part2(it)}")
    }
}