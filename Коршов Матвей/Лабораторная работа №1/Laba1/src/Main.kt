import java.io.File
import java.io.FileNotFoundException
import java.io.IOException
import java.util.*

fun main() {
    val fileText: String
    try {
        val file = File("file.txt")
        fileText = file.readText()
    } catch (e: FileNotFoundException) {
        println("File not found")
        return;
    }
    println("File was successfully read!")
    if (fileText.isBlank()) {
        println("File is blank")
        return
    }
    val fileSentences = fileText.trim().split(".").toMutableList()
    fileSentences.forEach { sentence ->
        val sentenceWords = sentence.split(" ").toMutableList()
        sentenceWords.forEach { word ->
            sentenceWords[sentenceWords.indexOf(word)] =
                word.replaceFirstChar { if (it.isLowerCase()) it.titlecase(Locale.getDefault()) else it.toString() }
        }
        fileSentences[fileSentences.indexOf(sentence)] = sentenceWords.joinToString(" ").trim()
    }
    val resultString = fileSentences.joinToString(".\n")
    try {
        File("new-file.txt").writeText(resultString)
        println("New refactored file was successfully created!")
    } catch (e: IOException) {
        println(e.message)
    }
}