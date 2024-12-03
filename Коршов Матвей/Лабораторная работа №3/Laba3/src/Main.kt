import java.io.File

fun main() {
    val inputFileReader = File("file.txt").bufferedReader()

    var lineCount = 0
    var totalSum = 0

    val transformSpacesToOnes = fun(line: String): String {
        return line.replace(" ", "1")
    }

    inputFileReader.lineSequence().forEach { line ->
        if (line.isNotBlank()) {
            val newLine = transformSpacesToOnes(line)
            var currentSum = 0
            newLine.filter { char -> char.isDigit() }.forEach { digitChar -> currentSum += digitChar.digitToInt() }
            totalSum += currentSum
            lineCount++

            println("Processed line: $newLine, Current sum: $currentSum, Line count: $lineCount")
        }
    }

    println("Final sum of values: $totalSum")
    println("Total lines processed: $lineCount")
}