package day2

import java.io.File

fun readFileLines(fileName:String): List<String>
        = File(fileName).useLines { it.toList() }

fun part1 () : Int {
    val games = readFileLines("day2/input_day2.txt")
    var sum = 0
    for (game in games) {
        var valid = true
        val (gameNumber, colorGame) = game.split(":")
        val pulls = colorGame.split(";")
        for (pull in pulls) {
            if (valid) {
                val colors = pull.split(",")
                for (color in colors) {
                    if (valid) {
                        valid = when {
                            color.contains("red") && (color.filter { it.isDigit() }.toInt() <= 12) -> true
                            color.contains("blue") && (color.filter { it.isDigit() }.toInt() <= 14) -> true
                            color.contains("green") && (color.filter { it.isDigit() }.toInt() <= 13) -> true
                            else -> false
                        }
                    }
                }
            }
        }
        if (valid) {
            sum += gameNumber.filter { it.isDigit() }.toInt()
        }
    }
    return sum
}

fun part2 () : Int {
    val games = readFileLines("day2/input_day2.txt")
    var sum = 0
    for (game in games) {
        var blue = 0
        var red = 0
        var green = 0
        val (_, colorGame) = game.split(":")
        val pulls = colorGame.split(";")
        for (pull in pulls) {
            val colors = pull.split(",")
            for (color in colors) {
                when {
                    color.contains("red") && color.filter { it.isDigit() }.toInt() >= red -> red = color.filter { it.isDigit() }.toInt()
                    color.contains("blue") && color.filter { it.isDigit() }.toInt() >= blue -> blue = color.filter { it.isDigit() }.toInt()
                    color.contains("green") && color.filter { it.isDigit() }.toInt() >= green -> green = color.filter { it.isDigit() }.toInt()
                    else -> red += 0
                }
            }
        }
        sum += red * green * blue
    }
    return sum
}
fun main() {
    println(part1())
    println(part2())
}