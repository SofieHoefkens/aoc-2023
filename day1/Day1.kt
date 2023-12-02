package day1

import java.io.File

enum class Numbers(val number: Int) {
    ONE(1),
    TWO(2),
    THREE(3),
    FOUR( 4),
    FIVE(5),
    SIX(6),
    SEVEN(7),
    EIGHT(8),
    NINE(9)
}

fun readFileLines(fileName:String): List<String>
    = File(fileName).useLines { it.toList() }

fun part1(): Int {
    return readFileLines("day1/input_day1.txt")
            .map { line -> line.filter { it.isDigit() } }
            .sumOf { line -> (line.first().toString() + line.last()).toInt() }
}

fun part2(): Int {
    val numberSet = setOf("one", "two", "three", "four", "five", "six", "seven", "eight", "nine")
    val lines = readFileLines("day1/input_day1.txt")
    val newlines: MutableList<String> = lines.toMutableList()
    for (line in newlines) {
        var numberMap: MutableMap<Int, String> = mutableMapOf()
        for (number in numberSet) {
            if (line.contains(number)) {
                val place = line.indexOf(number)
                numberMap[place] = number
            }
        }
        var firstPart : String = line
        var lastPart: String = line
        if (numberMap.isNotEmpty()) {
            numberMap = numberMap.toSortedMap()
            val firstNumber = numberMap.values.first()
            firstPart = line.replaceFirst(firstNumber, Numbers.valueOf(firstNumber.uppercase()).number.toString())
        }
        numberMap = mutableMapOf()
        for (number in numberSet) {
            if (line.contains(number)) {
                val place = line.lastIndexOf(number)
                numberMap[place] = number
            }
        }
        if (numberMap.isNotEmpty()) {
            numberMap = numberMap.toSortedMap()
            val lastNumber = numberMap.values.last()
            lastPart = line.replace(lastNumber, Numbers.valueOf(lastNumber.uppercase()).number.toString())
        }
        val index = lines.indexOf(line)
        newlines[index] = firstPart + lastPart
    }
    return newlines.map { line -> line.filter { it.isDigit() } }
            .sumOf { line -> (line.first().toString() + line.last()).toInt() }
}

fun main() {
    println(part1())
    println(part2())
}