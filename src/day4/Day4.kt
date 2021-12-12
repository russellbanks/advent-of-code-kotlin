package day4

import java.io.File

fun main() {
    fun part1(input: List<String>): Int {
        val winningNumbers = input.first().split(",").map(String::toInt)
        val boards = mutableListOf<Board>()
        val boardsNumbers = input.subList(2, input.size).flatMap {
            it.split(" ").filterNot(String::isBlank).map(String::toInt)
        }.toList()

        val numberOfBoards = boardsNumbers.size / 25
        repeat(numberOfBoards) { boardIndex ->
            val numbers = mutableListOf<Number>()
            repeat(5) { rowIndex ->
                repeat(5) { columnIndex ->
                    val numberIndex = 25 * boardIndex + 5 * rowIndex + columnIndex
                    numbers.add(Number(boardsNumbers[numberIndex], rowIndex, columnIndex))
                }
            }
            boards.add(Board(numbers))
        }

        winningNumbers.forEach { drawnNumber ->
            boards.forEach { board ->
                board.numbers.forEach { number ->
                    if (number.value == drawnNumber) {
                        number.drawn = true
                        if (board.hasWon()) {
                            return board.numbers.filter { it.drawn.not() }.sumOf(Number::value) * drawnNumber
                        }
                    }
                }
            }
        }

        error("No board has won.")
    }

    fun part2(input: List<String>): Int {
        val winningNumbers = input.first().split(",").map(String::toInt)
        val boards = mutableListOf<Board>()
        val boardsNumbers = input.subList(2, input.size).flatMap {
            it.split(" ").filterNot(String::isBlank).map(String::toInt)
        }.toList()

        val numberOfBoards = boardsNumbers.size / 25
        repeat(numberOfBoards) { boardIndex ->
            val numbers = mutableListOf<Number>()
            repeat(5) { rowIndex ->
                repeat(5) { columnIndex ->
                    val numberIndex = 25 * boardIndex + 5 * rowIndex + columnIndex
                    numbers.add(Number(boardsNumbers[numberIndex], rowIndex, columnIndex))
                }
            }
            boards.add(Board(numbers))
        }

        winningNumbers.forEach { drawnNumber ->
            boards.forEach { board ->
                board.numbers.forEach { number ->
                    if (number.value == drawnNumber) {
                        number.drawn = true
                        if (boards.all(Board::hasWon)) {
                            return board.numbers.filter { it.drawn.not() }.sumOf(Number::value) * drawnNumber
                        }
                    }
                }
            }
        }

        error("No board has won.")
    }

    File("src/day4/input.txt").readLines().also {
        println("Part 1: ${part1(it)}")
        println("Part 2: ${part2(it)}")
    }
}

data class Board(
    val numbers: MutableList<Number>
) {
    fun hasWon() = hasCompleteRow() || hasCompleteColumn()
    private fun hasCompleteRow() = numbers.groupBy { it.x }.any { (_, value) -> value.all(Number::drawn) }
    private fun hasCompleteColumn() = numbers.groupBy { it.y }.any { (_, value) -> value.all(Number::drawn) }
}

data class Number(val value: Int, val x: Int, val y: Int, var drawn: Boolean = false)