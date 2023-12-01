package day1

import java.io.File
fun readFileLines(fileName:String): List<String>
    = File(fileName).useLines { it.toList() }

fun main() {
    val numbers = readFileLines("day1/input_day1.txt")
            .map{line -> line.filter {it.isDigit()}}
            .map{line -> (line.first().toString() + line.last()).toInt()}
    println(numbers.sum())
}
