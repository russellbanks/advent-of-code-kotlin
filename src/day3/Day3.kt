package day3

import java.io.File
import kotlin.math.max
import kotlin.math.min

private fun main() {
    fun part1(input: List<String>): Int {
        val numLength = input.first().length
        val counts = IntArray(numLength) { 0 }
        input.forEach { number ->
            for (i in 0 until numLength) {
                if (number[i] == '1') counts[i]++ else counts[i]--
            }
        }
        val gammaStr = counts.map { max(min(it, 1), 0) }.joinToString("") { it.toString() }
        val gamma = gammaStr.toInt(2)
        val epsilon = gammaStr.toCharArray().map { if (it == '0') '1' else '0' }.joinToString("").toInt(2)
        return gamma * epsilon
    }

    fun part2(input: List<String>): Int {
        tailrec fun findNumber(input: List<String>, filter: (Int, Int) -> Boolean, position: Int = 0): List<String> {
            val ones = input.count { it[position].digitToInt() == 1 }
            val zeroes = input.size - ones
            val mostCommonOrOne = if (ones >= zeroes) 1 else 0
            val filtered = input.filter { filter(it[position].digitToInt(), mostCommonOrOne) }
            return if (filtered.size == 1) filtered else findNumber(filtered, filter, position + 1)
        }

        val oxygen = findNumber(input, { digit, mostCommonOrOne -> digit == mostCommonOrOne }).single().toInt(2)
        val co2 = findNumber(input, { digit, mostCommonOrOne -> digit != mostCommonOrOne }).single().toInt(2)
        return oxygen * co2
    }

    File("src/day3/input.txt").readLines().also {
        println("Part 1: ${part1(it)}")
        println("Part 2: ${part2(it)}")
    }
}