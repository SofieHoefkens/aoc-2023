package day1

import java.io.File
fun readFileLines(fileName:String): List<String>
    = File(fileName).useLines { it.toList() }

fun part1(): Int {
    return readFileLines("day1/input_day1.txt")
            .map { line -> line.filter { it.isDigit() } }
            .sumOf { line -> (line.first().toString() + line.last()).toInt() }
}

fun main() {
    println(part1())
}