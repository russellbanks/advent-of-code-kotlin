package day2

import java.io.File

fun main() {
    fun part1(input: List<String>) = input
        .groupBy(
            { it.split(" ").first() },
            { it.split(" ").last().toInt() })
        .let { map ->
            (map["down"]!!.sum() - map["up"]!!.sum()) *
                    map["forward"]!!.sum() }

    fun part2(input: List<String>): Int {

        var aim = 0
        var horizontal = 0
        var depth = 0

        input
            .map { it.split(" ").let { splitted -> splitted.first() to splitted.last().toInt() } }
            .forEach {
                when (it.first) {
                    "up" -> aim -= it.second
                    "down" -> aim += it.second
                    "forward" -> {
                        horizontal += it.second
                        depth += it.second * aim
                    }
                }
            }

        return horizontal * depth
    }

    File("src/day2/input.txt").readLines().also {
        println("Part 1: ${part1(it)}")
        println("Part 2: ${part2(it)}")
    }
}